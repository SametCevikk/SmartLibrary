package dev.patika;

import dev.patika.entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class App
{
    public static void main( String[] args )
    {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("SmartLibrary");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        //author add
        Author author = new Author();
        author.setName("Mustafa");
        author.setBirthDate(Date.valueOf("1985-05-05"));
        author.setCountry("turkey");
        entityManager.persist(author);

        //BookBorrowing
        BookBorrowing bookBorrowing = new BookBorrowing();
        bookBorrowing.setName("samet");
        bookBorrowing.setBorrowingDate(Date.valueOf("2025-06-11"));
        bookBorrowing.setReturnDate(Date.valueOf("2025-07-03"));
        entityManager.persist(bookBorrowing);

        //publisher
        Publisher publisher = new Publisher();
        publisher.setName("samet kitap");
        publisher.setEstablishmentYear("2023");
        publisher.setAddress("istanbul");

        entityManager.persist(publisher);

        //category
        Category action = new Category("action");
        Category dram = new Category("dram");
        Category comedy = new Category("comedy");
        entityManager.persist(action);
        entityManager.persist(dram);
        entityManager.persist(comedy);




        //Book
        Book book = new Book();
        book.setName("beyaz diş");
        book.setPublicationYear("2000");
        book.setStock(20);
        book.setBookBorrowing(bookBorrowing);
        book.setAuthor(author);
        book.setPublisher(publisher);
        List<Category> categoryList = new ArrayList<>();

        categoryList.add(action);
        categoryList.add(dram);
        book.setCategoryList(categoryList);

        entityManager.persist(book);

        Book book2 = new Book();
        book2.setName("martı");
        book2.setPublicationYear("2010");
        book2.setStock(50);
        book2.setBookBorrowing(bookBorrowing);
        book2.setAuthor(author);
        book2.setPublisher(publisher);

        List<Category> categoryList2 = new ArrayList<>();

        categoryList2.add(comedy);
        categoryList2.add(dram);

        book2.setCategoryList(categoryList2);

        entityManager.persist(book2);




        transaction.commit();

        entityManager.close();
        entityManagerFactory.close();

    }
}
