package com.example.bookstore219J05.dao;

import com.example.bookstore219J05.mapper.BookMapper;
import com.example.bookstore219J05.model.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class BookDao {
    private JdbcTemplate jdbcTemplate;

    public BookDao() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/bookstore?serverTimezone=GMT");
        dataSource.setUsername("root");
        dataSource.setPassword("123456789");
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int addNewBook(Book book) {
        String sql = "insert into books (bookName, authorName, bookPrice, stockQuantity, picturePath) values (?, ?, ?, ?, ?)";
        Object[] params = {book.getBookName(), book.getAuthorName(), book.getBookPrice(), book.getStockQuantity(), book.getPicturePath()};
        return jdbcTemplate.update(sql, params);
    }

    public int updateBookPrice() {
        String sql = "update books set bookPrice = 20.0";
        return jdbcTemplate.update(sql);
    }

    public int deleteById(int id) {
        String sql = "delete from books where id = ?";
        Object[] params = {id};
        return jdbcTemplate.update(sql, params);
    }

    public List<Book> getAllBooks() {
        String sql = "select * from books";
        List<Book> books = jdbcTemplate.query(sql, new BookMapper());
        return books;
    }

    public Book getBookById(int id) {
        String sql = "select * from books where id = " + id;
        List<Book> books = jdbcTemplate.query(sql, new BookMapper());
        return books.isEmpty() ? null : books.get(0);
    }

    public int updateBook(Book book) {
        String sql = "update books set bookName = ?, authorName = ?, bookPrice = ?, stockQuantity = ?, picturePath = ? where id = ?";
        Object[] params = {book.getBookName(), book.getAuthorName(), book.getBookPrice(), book.getStockQuantity(), book.getPicturePath(), book.getId()};
        return jdbcTemplate.update(sql, params);
    }
}
