import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BondViewer {
    private Connection connection;

    public BondViewer(Connection connection) {
        this.connection = connection;
    }

    public List<Security> getBondsForUser(int userId) {
        List<Security> securities = new ArrayList<>();
        String query = "SELECT DISTINCT s.* FROM security s " +
                "INNER JOIN trades t ON s.id = t.security_id " +
                "INNER JOIN book_user bu ON t.book_id = bu.book_id " +
                "INNER JOIN userr u ON bu.user_id = u.id " +
                "WHERE u.id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Security security = new Security();
                security.setId(resultSet.getInt("id"));
                security.setIsin(resultSet.getString("isin"));
                security.setCusip(resultSet.getString("cusip"));
                security.setIssuerName(resultSet.getString("issuer_name"));
                security.setMaturityDate(resultSet.getDate("maturity_date"));
                security.setCoupon(resultSet.getFloat("coupon"));
                security.setType(resultSet.getString("type"));
                security.setFaceValue(resultSet.getFloat("face_value"));
                security.setCurrency(resultSet.getString("currency"));
                security.setStatus(resultSet.getString("status"));
                securities.add(security);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return securities;
    }
}









{"timestamp":"2023-08-04T18:32:10.427+00:00","message":"could not execute query; SQL [select * from trades]; nested exception is org.hibernate.exception.SQLGrammarException: could not execute query","details":"uri=/api/v1/trades"}

TradesRepository:

package com.db.grad.javaapi.repository;

import com.db.grad.javaapi.model.Trades;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TradesRepository extends JpaRepository<Trades, Long>
{
    @Query(nativeQuery = true, value = "select * from trades")
    List<Trades> findAll();
}

TradesController:

package com.db.grad.javaapi.controller;

import com.db.grad.javaapi.model.Trades;
import com.db.grad.javaapi.service.TradesHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:3000")
public class TradesController {
    private TradesHandler tradesService;

    @Autowired
    public TradesController(TradesHandler ds)
    {
        tradesService = ds;
    }

    @GetMapping("/trades")
    public List <Trades> getAllTrades() {
        return tradesService.getAllTrades();
    }
}


TradesHandler:

package com.db.grad.javaapi.service;

import com.db.grad.javaapi.model.Trades;
import com.db.grad.javaapi.repository.TradesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradesHandler implements ITradesService
{
    private TradesRepository itsTradesRepo;

    @Autowired
    public TradesHandler( TradesRepository tradeRepo )
    {
        itsTradesRepo = tradeRepo;
    }

    @Override
    public List<Trades> getAllTrades()
    {
        return itsTradesRepo.findAll();
    }
}

ITradesService:

package com.db.grad.javaapi.service;

import com.db.grad.javaapi.model.Trades;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ITradesService
{
    public List<Trades> getAllTrades();
    //public List<Trades> getTradeByName(String TradeName );

    /*
    public Dog addDog(Dog theDog);

    public long getNoOfDogs();

    public boolean removeDog(long uniqueId);

    public Dog getDogById(long uniqueId);

    public Dog getDogByName(String dogsName );

    public Dog updateDogDetails(Dog dogToUpdate);
    */
}

