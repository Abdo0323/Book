package com.example.bookstore219J05.mapper;


import com.example.bookstore219J05.model.Book;
import org.springframework.jdbc.core.RowMapper;


import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setId(rs.getInt("id"));
        book.setBookName(rs.getString("bookName"));
        book.setAuthorName(rs.getString("authorName"));
        book.setBookPrice(rs.getDouble("bookPrice"));
        book.setStockQuantity(rs.getInt("stockQuantity"));

        // New property for picturePath
        book.setPicturePath(rs.getString("picturePath"));

        return book;
    }
}
