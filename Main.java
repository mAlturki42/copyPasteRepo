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
