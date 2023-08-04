# copyPasteRepo

g command : Error executing DDL "create table user (id integer not null, email varchar(255) not null, name varchar(255) not null, role varchar(255) not null, primary key (id))" via JDBC Statement


data.SQL

-- Insert data into the book table
INSERT INTO book (name) VALUES ('trading_book_1'), ('trading_book_2'), ('Trading_book_3'), ('Trading_book_4'), ('Trading_book_6');

-- Insert data into the userr table
INSERT INTO userr (name, email, role) VALUES ('AZ Holdings Inc', 'azholdings@example.com', 'bond_holder'), 
('Acme co', 'acmeco@example.com', 'bond_holder'),
('Sovereign Investments', 'sovereigninvestments@example.com', 'bond_holder'),
('Astra Trading Ltd', 'astratradingltd@example.com', 'bond_holder'),
('Muncipal Gov Of Orange County', 'muncipalgovoforangecounty@example.com', 'bond_holder'),
('Goldman Sachs', 'goldmansachs@example.com', 'bond_holder'),
('UBS', 'ubs@example.com', 'bond_holder'),
('Barclays', 'barclays@example.com', 'bond_holder'),
('British Telecom', 'britishtelecom@example.com', 'bond_holder'), 
('Pension Holdings', 'pensionholdings@example.com', 'bond_holder'),
('Zurich Pension fund 4', 'zurichpensionfund4@example.com', 'bond_holder');

-- Insert data into the counterparty table
INSERT INTO counterparty (name) VALUES ('BNPParibasIssu 4,37% Microsoft Corp (USD)'), ('Airbus 3.15%  USD'),
('UBS Facebook (USD)'),
('Amazon'),
('HM Treasury United Kingdon'), 
('TEMASEK FINL I LTD GLOBAL MEDIUM TERM NTS BOOK ENTRY REG S'),
('First Norway Alpha Kl.IV');

-- Insert data into the book_user table
INSERT INTO book_user (book_id, user_id) VALUES (1, 1), (2, 2), (3, 3), (2, 4) (2, 3), (4, 5), (6, 5), (6, 6), (6, 7), (6, 8), (6, 9), (6, 10), (4, 11);

-- Insert data into the security table
INSERT INTO security (isin, cusip, issuer_name, maturity_date, coupon, type, face_value, currency, status) VALUES ('XS1988387210', NULL, 'BNPParibasIssu 4,37% Microsoft Corp (USD)', '2021-08-05', 4.37, 'CORP', 1000, 'USD', 'active'), 
('USN0280EAR64','123456780','Airbus 3.15%  USD','2021-07-30',3.15,'CORP',900,'USD','active'),
('A12356111','123456bh0','UBS Facebook (USD)','2021-09-30',2,'CORP',900,'USD','active'),
('USU02320AG12','AMZN 3.15 08/22/27 REGS','Amazon','2021-08-03',3.15,'CORP',900,'USD','active'),
(NULL,'BDCHBW8','HM Treasury United Kingdon','2021-08-09',0.75,'GOVN',900,'GBP','active'), 
('US87973RAA86','87973RAA8','TEMASEK FINL I LTD GLOBAL MEDIUM TERM NTS BOOK ENTRY REG S','2021-08-06',2.02,'SOVN',690,'USD','active'),
('IE00B29LNP31','87973RAA8','First Norway Alpha Kl.IV','2030-12-22',1.123,'SOVN',340,'USD','active');

-- Insert data into the trades table
INSERT INTO trades (book_id, security_id, counterparty_id, currency, status, quantity, unit_price, buy_sell, trade_date, settlement_date) VALUES (1, 1, 1, 'USD', 'open', 50, 90, 'buy', '2021-05-13', '2021-08-04'), (1, 1, 1, 'GBP', 'open', 40, 89.56, 'sell', '2021-02-04', '2021-08-04'), 
(2, 2, 2, 'USD', 'open', 1000, 105.775, 'buy', '2021-05-13', '2021-08-23'), (2, 2, 2, 'GBP', 'open', 900, 105.775, 'sell', '2021-02-04', '2021-09-10'), 
(3, 3, 3, 'USD', 'open', 50, 90, 'buy', '2021-05-13', '2021-08-23'), (2, 2, 2, 'USD', 'open', 1000, 105.775, 'buy', '2021-05-13', '2021-08-23'), 
(2, 3, 3, 'USD', 'open', 50, 90, 'sell', '2021-05-13', '2021-08-23');










Java:


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public List<Bond> getMatureBondsForRegisteredUsers(Connection conn) throws SQLException {
    List<Bond> matureBonds = new ArrayList<>();

    String sql = "SELECT b.* FROM Bonds b " +
                 "JOIN book_user bu ON b.book_name = bu.book_id " +
                 "JOIN userr u ON bu.user_id = u.id " +
                 "WHERE b.maturity_date <= CURRENT_DATE()";

    try (Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {
        while (rs.next()) {
            Bond bond = new Bond();
            bond.setTradeType(rs.getString("trade_type"));
            bond.setTradeCurrency(rs.getString("trade_currency"));
            bond.setQuantity(rs.getInt("quantity"));
            bond.setSettlementDate(rs.getDate("settlement_date"));
            bond.setTradeStatus(rs.getString("trade_status"));
            bond.setTradeDate(rs.getDate("trade_date"));
            bond.setUnitPrice(rs.getFloat("unit_price"));
            bond.setCouponPercent(rs.getFloat("coupon_percent"));
            bond.setBondCurrency(rs.getString("bond_currency"));
            bond.setCusip(rs.getString("cusip"));
            bond.setFaceValue(rs.getFloat("face_value"));
            bond.setIsin(rs.getString("isin"));
            bond.setIssuerName(rs.getString("issuer_name"));
            bond.setMaturityDate(rs.getDate("maturity_date"));
            bond.setStatus(rs.getString("status"));
            bond.setType(rs.getString("type"));
            bond.setBookName(rs.getString("book_name"));
            bond.setBondHolder(rs.getString("bond_holder"));

            matureBonds.add(bond);
        }
    }

    return matureBonds;
}






Caused by: org.h2.jdbc.JdbcSQLSyntaxErrorException: Syntax error in SQL statement "CREATE TABLE Bonds ( trade_type VARCHAR(255) NOT NULL, trade_currency varchar(10) NOT NULL, quantity int NOT NULL,[*], settlement_date datetime NOT NULL, trade_status varchar(32) NOT NULL, trade_date datetime NOT NULL, unit_price float NOT NULL, coupon_percent float NOT NULL, bond_currency VARCHAR(10) NOT NULL, cusip varchar(50) DEFAULT NULL, face_value float NOT NULL, isin varchar(50) DEFAULT NULL, issuer_name varchar(255) NOT NULL, maturity_date datetime NOT NULL, status varchar(32) NOT NULL, type VARCHAR(255) NOT NULL, book_name VARCHAR(255) NOT NULL, bond_holder VARCHAR(255) NOT NULL )"; expected "identifier"; SQL statement:
CREATE TABLE Bonds ( trade_type VARCHAR(255) NOT NULL, trade_currency varchar(10) NOT NULL, quantity int NOT NULL,, settlement_date datetime NOT NULL, trade_status varchar(32) NOT NULL, trade_date datetime NOT NULL, unit_price float NOT NULL, coupon_percent float NOT NULL, bond_currency VARCHAR(10) NOT NULL, cusip varchar(50) DEFAULT NULL, face_value float NOT NULL, isin varchar(50) DEFAULT NULL, issuer_name varchar(255) NOT NULL, maturity_date datetime NOT NULL, status varchar(32) NOT NULL, type VARCHAR(255) NOT NULL, book_name VARCHAR(255) NOT NULL, bond_holder VARCHAR(255) NOT NULL ) [42001-212]



DROP ALL OBJECTS;

CREATE TABLE Bonds (
  trade_type VARCHAR(255) NOT NULL,
  trade_currency varchar(10) NOT NULL,
  quantity int NOT NULL,,
  settlement_date datetime NOT NULL,
  trade_status varchar(32) NOT NULL,
  trade_date datetime NOT NULL,
  unit_price float NOT NULL,
  coupon_percent float NOT NULL,
  bond_currency VARCHAR(10) NOT NULL,
  cusip varchar(50) DEFAULT NULL,
  face_value float NOT NULL,
  isin varchar(50) DEFAULT NULL,
  issuer_name varchar(255) NOT NULL,
  maturity_date datetime NOT NULL,
  status varchar(32) NOT NULL,
  type VARCHAR(255) NOT NULL,
  book_name VARCHAR(255) NOT NULL,
  bond_holder VARCHAR(255) NOT NULL
);

CREATE TABLE book (
  id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name varchar(255) NOT NULL
);

CREATE TABLE userr (
  id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name varchar(255) NOT NULL,
  email varchar(255) NOT NULL,
  role varchar(255) NOT NULL
);

CREATE TABLE counterparty (
  id int NOT NULL AUTO_INCREMENT,
  name varchar(255) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE book_user (
  `book_id` int NOT NULL,
  `user_id` int NOT NULL,
  FOREIGN KEY (`book_id`) REFERENCES book(id),
  FOREIGN KEY (`user_id`) REFERENCES userr(id)
);


CREATE TABLE security (
  `id` int NOT NULL AUTO_INCREMENT,
  `isin` varchar(50) DEFAULT NULL,
  `cusip` varchar(50) DEFAULT NULL,
  `issuer_name` varchar(255) NOT NULL,
  `maturity_date` datetime NOT NULL,
  `coupon` float NOT NULL,
  `type` varchar(255) NOT NULL,
  `face_value` float NOT NULL,
  `currency` varchar(10) NOT NULL,
  `status` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE trades (
  `id` int NOT NULL AUTO_INCREMENT,
  `book_id` int NOT NULL,
  `security_id` int NOT NULL,
  `counterparty_id` int NOT NULL,
  `currency` varchar(10) NOT NULL,
  `status` varchar(32) NOT NULL,
  `quantity` int NOT NULL,
  `unit_price` float NOT NULL,
  `buy_sell` varchar(32) NOT NULL,
  `trade_date` datetime NOT NULL,
  `settlement_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`security_id`) REFERENCES security(id),
  FOREIGN KEY (`counterparty_id`) REFERENCES counterparty(id),
  FOREIGN KEY (`book_id`) REFERENCES book(id)
);


ALTER TABLE book_user ADD PRIMARY KEY(book_id, user_id);



Caused by: org.h2.jdbc.JdbcSQLSyntaxErrorException: Syntax error in SQL statement "RUNSCRIPT FROM[*]"; expected "INTERSECTS, NOT, EXISTS, UNIQUE, INTERSECTS"; SQL statement:
RUNSCRIPT FROM [42001-212]


Caused by: org.h2.jdbc.JdbcSQLSyntaxErrorException: Syntax error in SQL statement "[*]BULK INSERT bonds FROM 'C:/Users/Admin/Downloads/db-bonds-data.csv' WITH ( FIELDTERMINATOR = ',', ROWTERMINATOR = '\\n', FIRSTROW = 2 )"; expected "BACKUP, BEGIN"; SQL statement:
BULK INSERT bonds FROM 'C:/Users/Admin/Downloads/db-bonds-data.csv' WITH ( FIELDTERMINATOR = ',', ROWTERMINATOR = '\n', FIRSTROW = 2 ) [42001-212]


Caused by: org.h2.jdbc.JdbcSQLSyntaxErrorException: Syntax error in SQL statement "CREATE TABLE [*]'user' ( `id` int NOT NULL AUTO_INCREMENT, `name` varchar(255) NOT NULL, `email` varchar(255) NOT NULL, `role` varchar(255) NOT NULL, PRIMARY KEY (`id`) )"; expected "identifier"; SQL statement:
CREATE TABLE 'user' ( `id` int NOT NULL AUTO_INCREMENT, `name` varchar(255) NOT NULL, `email` varchar(255) NOT NULL, `role` varchar(255) NOT NULL, PRIMARY KEY (`id`) ) [42001-212]


Caused by: org.h2.jdbc.JdbcSQLSyntaxErrorException: Syntax error in SQL statement "CREATE TABLE book ( `id` int NOT NULL AUTO_INCREMENT, `name` varchar(255) NOT NULL, PRIMARY KEY (`id`) ) ENGINE[*]=InnoDB DEFAULT CHARSET=latin1"; expected "identifier"; SQL statement:
CREATE TABLE book ( `id` int NOT NULL AUTO_INCREMENT, `name` varchar(255) NOT NULL, PRIMARY KEY (`id`) ) ENGINE=InnoDB DEFAULT CHARSET=latin1 [42001-212]


Caused by: org.h2.jdbc.JdbcSQLSyntaxErrorException: Syntax error in SQL statement "DROP [*]DATABASE IF EXISTS bonds"; expected "TABLE, INDEX, USER, SEQUENCE, CONSTANT, TRIGGER, VIEW, ROLE, ALIAS, SCHEMA, ALL, DOMAIN, TYPE, DATATYPE, AGGREGATE, SYNONYM"; SQL statement:
DROP DATABASE IF EXISTS bonds [42001-212]




https://deliveringtechnology2018.atlassian.net/wiki/spaces/CC21S/pages/2490728473/QL-3+Inheritance+and+Polymorphism

mvn archetype:generate -DarchetypeArtifactId=maven-archetype-quickstart



mvn package
java -cp target/hello-maven-1.0-SNAPSHOT.jar com.qa.App

Book

package com.qa;
import java.util.Arrays;

public class Book {
    private String name;
    private String[] authors;
    private double price;

    public Book(String name, String[] authors, double price) {
        this.name = name;
        this.authors = authors;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getAuthors() {
        return authors;
    }
    public void setAuthors(String[] authors) {
        this.authors = authors;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book [name=" + name + ", authors=" +
                Arrays.toString(authors) + ", price=" + price + "]";
    }
}

BookTest.java

package com.qa;

public class TestBooks {
    public static void main(String[] args) {
        String[] arr = {"Author1", "Author2", "Author3"};
        Book b1 = new Book("title", arr, 10.99);
        Book b2 = new Book("title2", arr, 5.99);
        Book b3 = new Book("title3", arr, 1.00);
        System.out.println(b1);
        System.out.println(b2);
        System.out.println(b3);

        Book[] bookArray = new Book[3];
        bookArray[0] = b1;
        bookArray[1] = b2;
        bookArray[2] = b3;

        //this could have been done with
        //          "Book[] bookArray = {b, b2, b3};"

        for (int x = 0; x < bookArray.length; x++){
            System.out.println(bookArray[x]);
        }
    }
}
