
Caused by: org.hibernate.AnnotationException: No identifier specified for entity: com.db.grad.javaapi.model.Bookuser


package com.db.grad.javaapi.model;
import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "book_user")
public class Bookuser {

    @ManyToMany
    @JoinTable(name= "book", joinColumns = @JoinColumn(name="book_id", referencedColumnName = "id"))
    private int book_id;
    @ManyToMany
    @JoinTable(name= "user", joinColumns = @JoinColumn(name="user_id", referencedColumnName = "id"))
    private int user_id;

    @Column(name= "book_id", nullable = false)
    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    @Column(name= "user_id", nullable = false)
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}



***************************
APPLICATION FAILED TO START
***************************

Description:

Parameter 0 of constructor in com.db.grad.javaapi.controller.BookuserController required a bean of type 'com.db.grad.javaapi.service.BookuserHandler' that could not be found.


Action:

Consider defining a bean of type 'com.db.grad.javaapi.service.BookuserHandler' in your configuration.




BookuserController:
package com.db.grad.javaapi.controller;


import com.db.grad.javaapi.model.Book;
import com.db.grad.javaapi.model.Bookuser;
import com.db.grad.javaapi.model.Trades;
import com.db.grad.javaapi.service.BookHandler;
import com.db.grad.javaapi.service.BookuserHandler;
import com.db.grad.javaapi.service.TradesHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:3000")
public class BookuserController {

    private BookuserHandler bookuserService;

    @Autowired
    public BookuserController(BookuserHandler ds)
    {
        bookuserService = ds;
    }

    @GetMapping("/book_user")
    public List <Bookuser> getAllBooksuser() {
        return bookuserService.getAllBooksuser();
    }

}


BookuserHandler:

package com.db.grad.javaapi.service;

import com.db.grad.javaapi.model.Bookuser;
import com.db.grad.javaapi.repository.BookRepository;
import com.db.grad.javaapi.repository.BookuserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BookuserHandler implements IBookuserService {

    private BookuserRepository itsBookuserRepo;

    @Autowired
    public BookuserHandler( BookuserRepository bookuserRepo )
    {
        itsBookuserRepo = bookuserRepo;
    }

    @Override
    public List<Bookuser> getAllBooksuser()
    {
        return itsBookuserRepo.findAll();
    }
}


IBookuserService:

package com.db.grad.javaapi.service;

import com.db.grad.javaapi.model.Book;
import com.db.grad.javaapi.model.Bookuser;

import java.util.List;

public interface IBookuserService {

    public List<Bookuser> getAllBooksuser();

}









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

        for (int x = 0; x < bookArray.length; x++) {
            System.out.println(bookArray[x]);
        }
    }
}
