package service;

import model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import repository.BookRepository;

public class BookServiceImpl  implements BookService{
    @Autowired
    private BookRepository bookRepository;
    @Override
    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public Book findById(long id) {
        return bookRepository.findOne(id);
    }

    @Override
    public void remove(Long id) {
        bookRepository.delete(id);
    }

    @Override
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Override
    public Page<Book> findByName(String name, Pageable pageable) {
        return bookRepository.findBooksByNameContaining(name,pageable);
    }


}
