# copyPasteRepo

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
