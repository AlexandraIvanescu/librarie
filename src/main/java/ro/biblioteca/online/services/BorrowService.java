package ro.biblioteca.online.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.biblioteca.online.models.Borrow;
import ro.biblioteca.online.repositories.BorrowRepository;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Alexandra Ale on 12/06/2017.
 */

@Service
public class BorrowService {

    private BorrowRepository borrowRepository;

    @Autowired
    public BorrowService(BorrowRepository borrowRepository) {
        this.borrowRepository = borrowRepository;
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

}
