package com.example.bookstore219J05.controller;

import com.example.bookstore219J05.dao.BookDao;
import com.example.bookstore219J05.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookController {

    @Autowired
    private BookDao bookDao;

    // List all books
    @RequestMapping("/viewallbooks")
    public String getAllBooks(Model model) {
        model.addAttribute("allbooks", bookDao.getAllBooks());
        return "/admin/bookslist";
    }

    // Delete a book by id
    @RequestMapping("/deletebook")
    public String deleteBookById(int id) {
        bookDao.deleteById(id);
        return "redirect:/viewallbooks";
    }



    @RequestMapping("/showaddbookpage")
    public String showAddPage() {
        return "/admin/addnewbook";
    }

    @RequestMapping("/addbook")
    public String add(Book book) {
        bookDao.addNewBook(book);
        String path = "redirect:/viewallbooks";
        return path;
    }

    // Edit a book by id
    @RequestMapping("/editbook")
    public String editBookById(int id, Model model) {
        model.addAttribute("book", bookDao.getBookById(id));
        return "/admin/editbook";
    }

    // Update a book using a BookEntity object
    @RequestMapping("/updatebook")
    public String updateBookById(Book book) {
        bookDao.updateBook(book);
        String path = "redirect:/viewallbooks";
        return path; // Corrected the redirect path
    }
}
