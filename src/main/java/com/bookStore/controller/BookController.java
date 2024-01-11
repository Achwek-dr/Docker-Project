package com.bookStore.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.bookStore.entity.Book;
import com.bookStore.entity.MyBookList;
import com.bookStore.service.BookService;
import com.bookStore.service.MyBookListService;
import com.bookStore.entity.Author;
import com.bookStore.service.AuthorService;

@Controller
public class BookController {
	@Autowired
	private BookService service;
	@Autowired
	
	private MyBookListService myBookService;
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/book-register")
	public String bookRegister(Model model) {
	    List<Author> authors = authorService.getAllAuthors();
	    model.addAttribute("authors", authors);
	    model.addAttribute("book", new Book());  // Ajoutez cette ligne pour créer un objet Book vide dans le modèle
	    return "bookRegister";
	}

	@GetMapping("/available_books")
	public ModelAndView getAllBook() {
	List<Book>list=service.getAllBook();
	//ModelAndView m=new ModelAndView();
	//m.setViewName("bookList");
	//m.addObject("book",list);
	return new ModelAndView ("bookListe","book",list);
	}
	@PostMapping("/save")
	public String addBook(@ModelAttribute Book b) {
		service.save(b);
		return "redirect:/available_books"; 
	}
	@GetMapping("/my-books")
	public String getMyBooks(Model model)
	{
		List<MyBookList>list=myBookService.getAllMyBooks();
		model.addAttribute("book",list);
		return "myBooks";
	}
	@RequestMapping("/mylist/{id}")
	public String getMyList(@PathVariable("id")int id) {
		Book b=service.getBookById(id);
		MyBookList mb=new MyBookList(b.getId(),b.getName(),b.getAuthor(),b.getPrice());
		myBookService.saveMyBooks(mb);
		return"redirect:/my-books";
	}
	@RequestMapping("/editBook/{id}")
	public String editBook(@PathVariable("id")int id ,Model model) {
	
		Book b=service.getBookById(id);
		model.addAttribute("book",b);
		return "bookEdit";
	}
	@RequestMapping("/deleteBook/{id}")
	public String deleteBook(@PathVariable("id")int id ,Model model) {
	service.deleteById(id);
		return"redirect:/available_books";
}
	@GetMapping("/search")
	public ModelAndView searchBooks(@RequestParam(required = false) String name, @RequestParam(required = false) String author) {
	    List<Book> resultList = service.searchBooks(name, author);
	    return new ModelAndView("searchResult", "books", resultList);
	}
	 @Autowired
	    private AuthorService authorService;

	    // ...

	 @RequestMapping("/authors")
	    public String getAllAuthors(Model model) {
	        List<Author> authors = authorService.getAllAuthors();
	        model.addAttribute("authors", authors);
	        return "authors";
	    }

	    @GetMapping("/author-register")
	    public String authorRegister(Model model) {
	        model.addAttribute("author", new Author());
	        return "authorRegister";
	    }

	    @PostMapping("/saveAuthor")
	    public String addAuthor(@ModelAttribute Author author) {
	        authorService.saveAuthor(author);
	        return "redirect:/authors";
	    }

	    @GetMapping("/editAuthor/{id}")
	    public String editAuthor(@PathVariable("id") Long id, Model model) {
	        Author author = authorService.getAuthorById(id);
	        model.addAttribute("author", author);
	        return "authorEdit";
	    }

	    @RequestMapping("/deleteAuthor/{id}")
	    public String deleteAuthor(@PathVariable("id") Long id) {
	        authorService.deleteById(id);
	        return "redirect:/authors";
	    }

}