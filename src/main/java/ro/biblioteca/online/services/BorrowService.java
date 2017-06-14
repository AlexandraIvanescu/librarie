package ro.biblioteca.online.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.biblioteca.online.config.SecurityUtils;
import ro.biblioteca.online.models.Book;
import ro.biblioteca.online.models.BookBorrow;
import ro.biblioteca.online.models.Borrow;
import ro.biblioteca.online.repositories.BookRepository;
import ro.biblioteca.online.repositories.BorrowRepository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    public boolean deleteBorrow(Borrow borrow) {

        borrowRepository.delete(borrow);

        return true;
    }

    public List<BookBorrow> searchBorrow(String title, String author, String sStartDate, String sEndDate) {

        try {

            DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            Date startDate;
            Date endDate;

            if (sStartDate.equals("")) {
                startDate = format.parse("10-10-1930");
            } else {
                startDate = format.parse(sStartDate);
            }

            if (sEndDate.equals("")) {
                endDate = format.parse("10-10-2030");
            } else {
                endDate = format.parse(sEndDate);
            }

            List<Borrow> borrows = borrowRepository.findBorrowsByLibraryEmailAndTitleAndAuthor(SecurityUtils.getCurrentLogin(), title, author, startDate, endDate);
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

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean hasBorrow(int subscriberId) {

        List<Borrow> borrows = borrowRepository.getAllBorrowBooksBySubscriber(subscriberId);

        return borrows.isEmpty();
    }


}
