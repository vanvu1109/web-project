package model;

import java.util.Date;

public class BorrowingDetails {
    private int id;
    private Book book;
    private Date returnDate;

    public BorrowingDetails() {
    }

    public BorrowingDetails(int id, Book book, Date returnDate) {
        this.id = id;
        this.book = book;
        this.returnDate = returnDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "BorrowingDetail{" +
                "id=" + id +
                ", book=" + book +
                ", returnDate=" + returnDate +
                '}';
    }
}
