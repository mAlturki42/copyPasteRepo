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

