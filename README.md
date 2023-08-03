# copyPasteRepo

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
