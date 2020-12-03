package controller;

import model.Book;
import model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.BookService;
import service.CategoryService;

import java.util.Optional;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

    @ModelAttribute("category")
    public Iterable<Category> categories(){
        return categoryService.findAll();
    }
@GetMapping("/create-book")
public ModelAndView showCreateForm(){
    ModelAndView modelAndView= new ModelAndView("create");
    modelAndView.addObject("book", new Book());
//        Iterable<Province> provinces= provinceService.findAll();
//        modelAndView.addObject("provinces", provinces);
    return modelAndView;
}
    @PostMapping("/create-book")
    public ModelAndView saveCustomer(@Validated @ModelAttribute("book")Book book, BindingResult bindingResult){
        if (bindingResult.hasFieldErrors()){
            ModelAndView modelAndView= new ModelAndView("create");
            return modelAndView;
        }

        bookService.save(book);
        ModelAndView modelAndView= new ModelAndView("create");
        modelAndView.addObject("book", new Book());
        modelAndView.addObject("message","New Book created successfully");
        return modelAndView;
    }
    @GetMapping("/books")
    public ModelAndView listCustomer(@RequestParam("search") Optional<String> search, Pageable pageable){
        Page<Book> list;
        if (search.isPresent()){
            list= bookService.findByName(search.get(),pageable);
        }else {
            list= bookService.findAll(pageable);
        }
        ModelAndView modelAndView= new ModelAndView("index");
        modelAndView.addObject("books", list);
        return modelAndView;
    }
    @GetMapping("/edit-book/{id}")
    public ModelAndView showformEdit(@PathVariable Long id){
        ModelAndView modelAndView= new ModelAndView("edit");
        Book book= bookService.findById(id);
        modelAndView.addObject("book", book);
        return modelAndView;
    }

    @PostMapping("/edit-book")
    public ModelAndView updateBook(@ModelAttribute("book") Book book){
        bookService.save(book);
        ModelAndView modelAndView= new ModelAndView("edit");
        modelAndView.addObject("message","Update Book Succcessfully!");
        return modelAndView;
    }
    @GetMapping("/delete-book/{id}")
    public ModelAndView deleteBook(@PathVariable Long id, Pageable pageable){
        bookService.remove(id);
        Page<Book> list= bookService.findAll(pageable);
        ModelAndView modelAndView= new ModelAndView("index");
        modelAndView.addObject("books", list);
        return modelAndView;
    }



}
