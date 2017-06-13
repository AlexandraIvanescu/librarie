package ro.biblioteca.online.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.biblioteca.online.models.Book;
import ro.biblioteca.online.models.BookBorrow;
import ro.biblioteca.online.models.Borrow;
import ro.biblioteca.online.repositories.BookRepository;
import ro.biblioteca.online.repositories.BorrowRepository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Alexandra Ale on 12/06/2017.
 */

@Service
public class BorrowService {

    private BorrowRepository borrowRepository;
    private BookRepository bookRepository;

    @Autowired
    public BorrowService(BorrowRepository borrowRepository, BookRepository bookRepository) {
        this.borrowRepository = borrowRepository;
        this.bookRepository = bookRepository;
    }

    public boolean addBorrow(Borrow borrow) {
        Date startDate = new Date();

        Calendar now = Calendar.getInstance();

        now.setTime(startDate);
        now.add(Calendar.MONTH, 1);

        Date endDate = now.getTime();

        borrow.setStartDate(startDate);
        borrow.setEndDate(endDate);
        borrow.setBorrowed(true);

        borrowRepository.saveAndFlush(borrow);

        return true;
    }

    public List<BookBorrow> getAllSubscriberBorrow(int subscriberId) {
        List<Borrow> borrows = borrowRepository.getAllSubscriberBorrow(subscriberId);
        List<BookBorrow> bookBorrows = new ArrayList<>();

        borrows.forEach(borrow -> {
            Book book = bookRepository.findBookById(borrow.getBookId());
            BookBorrow bookBorrow = new BookBorrow();

            borrow.setSubscriber(null);
            book.setLibrary(null);
            book.setCategory(null);

            bookBorrow.setBook(book);
            bookBorrow.setBorrow(borrow);

            bookBorrows.add(bookBorrow);
        });

        return bookBorrows;
    }

    public boolean extendedBorrow(Borrow borrow) {
        Calendar now = Calendar.getInstance();

        now.setTime(borrow.getEndDate());
        now.add(Calendar.MONTH, 1);

        Date endDate = now.getTime();

        borrowRepository.updateEndDate(endDate, borrow.getId());

        return true;
    }

}
