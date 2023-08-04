{"timestamp":"2023-08-04T18:32:10.427+00:00","message":"could not execute query; SQL [select * from trades]; nested exception is org.hibernate.exception.SQLGrammarException: could not execute query","details":"uri=/api/v1/trades"}

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




SELECT s.isin, s.cusip, s.issuer_name, s.maturity_date, s.coupon, s.type, s.face_value, s.currency  
FROM security s  
JOIN trades t ON t.security_id = s.id  
JOIN book_user bu ON bu.book_id = t.book_id  
WHERE bu.user_id = 2
AND DATEDIFF(s.maturity_date, CURRENT_DATE()) BETWEEN -5 AND 5;
Syntax error in SQL statement "SELECT s.isin, s.cusip, s.issuer_name, s.maturity_date, s.coupon, s.type, s.face_value, s.currency  \000d\000aFROM security s  \000d\000aJOIN trades t ON t.security_id = s.id  \000d\000aJOIN book_user bu ON bu.book_id = t.book_id  \000d\000aWHERE bu.user_id = 2 \000d\000aAND DATEDIFF(s[*].maturity_date, CURRENT_DATE()) BETWEEN -5 AND 5"; expected ","; SQL statement:
SELECT s.isin, s.cusip, s.issuer_name, s.maturity_date, s.coupon, s.type, s.face_value, s.currency  
FROM security s  
JOIN trades t ON t.security_id = s.id  
JOIN book_user bu ON bu.book_id = t.book_id  
WHERE bu.user_id = 2
AND DATEDIFF(s.maturity_date, CURRENT_DATE()) BETWEEN -5 AND 5 [42001-212] 42001/42001 (Help)


import java.sql.*;
import java.util.*;

public class BondViewer {
    private Connection conn;

    public BondViewer(String dbUrl, String user, String password) throws SQLException {
        conn = DriverManager.getConnection(dbUrl, user, password);
    }

    public List<Map<String, Object>> getMaturedBondsForUser(int userId) throws SQLException {
        List<Map<String, Object>> result = new ArrayList<>();
        String query = "SELECT s.isin, s.cusip, s.issuer_name, s.maturity_date, s.coupon, s.type, s.face_value, s.currency " +
                "FROM security s " +
                "JOIN trades t ON t.security_id = s.id " +
                "JOIN book_user bu ON bu.book_id = t.book_id " +
                "WHERE bu.user_id = ? AND s.maturity_date <= CURRENT_DATE()";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, userId);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Map<String, Object> row = new HashMap<>();
            row.put("isin", rs.getString("isin"));
            row.put("cusip", rs.getString("cusip"));
            row.put("issuer_name", rs.getString("issuer_name"));
            row.put("maturity_date", rs.getDate("maturity_date"));
            row.put("coupon", rs.getFloat("coupon"));
            row.put("type", rs.getString("type"));
            row.put("face_value", rs.getFloat("face_value"));
            row.put("currency", rs.getString("currency"));
            result.add(row);
        }
        return result;
    }
}
