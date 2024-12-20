package controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Book;
import model.Borrowing;
import model.BorrowingDetails;
import model.Reader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dao.BookDAO;
import dao.BorrowingDAO;
import dao.ReaderDAO;

@WebServlet("/borrow")
public class Borrow extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Borrow() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		request.setAttribute("activePage", "borrow");
		request.setAttribute("titlePage", "Cho mượn sách");
		
		BookDAO bookDAO = new BookDAO();
		List<Book> books = bookDAO.find();
		
		ReaderDAO readerDAO = new ReaderDAO();
		List<Reader> readers = readerDAO.find();
		
		request.setAttribute("books", books);
		request.setAttribute("readers", readers);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("borrow.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] bookIds = request.getParameterValues("bookId[]");
		System.out.println(bookIds);

		int id = Integer.parseInt(request.getParameter("id"));
		String phone = request.getParameter("phone");
		String name = request.getParameter("name");
		String dob = request.getParameter("dob");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		Reader reader = new Reader();
		reader.setId(id);
		reader.setPhone(phone);
		reader.setName(name);
		reader.setEmail(email);
		reader.setAddress(address);
		
		BookDAO bookDAO = new BookDAO();

		List<BorrowingDetails> listBorrowingDetails = new ArrayList<BorrowingDetails>();
		for (String bookId : bookIds) {
			BorrowingDetails b = new BorrowingDetails();
		    Book book = bookDAO.findById(Integer.parseInt(bookId));
		    b.setBook(book);
		    b.setReturnDate(null);
		    listBorrowingDetails.add(b);
		}

		Borrowing borrowing = new Borrowing();
		borrowing.setBorrowingDetails(listBorrowingDetails);
		borrowing.setReader(reader);
		
		System.out.println(borrowing);
		BorrowingDAO borrowingDAO = new BorrowingDAO();
		borrowingDAO.createBorrowing(borrowing);

		doGet(request, response);
	}

}
