package service;

import model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {
    Page<Book> findAll(Pageable pageable);
    Book findById(long id);
    void remove(Long id);
    void save(Book book);
    Page<Book> findByName(String name, Pageable pageable);
}
