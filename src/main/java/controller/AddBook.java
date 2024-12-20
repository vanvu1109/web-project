package controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Book;
import model.Category;

import java.io.IOException;
import java.util.List;

import dao.BookDAO;
import dao.CategoryDAO;

@WebServlet("/add-book")
public class AddBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddBook() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		request.setAttribute("activePage", "books");
		request.setAttribute("titlePage", "Thêm sách");
		
		CategoryDAO categoryDAO = new CategoryDAO();
		List<Category> listCategories = categoryDAO.find();
		request.setAttribute("listCategories", listCategories);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("add_book.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		System.out.println(request.getParameter("category"));
		int categoryId = 1; // Integer.parseInt(request.getParameter("category"));
		String author = request.getParameter("author");
		int amount = Integer.parseInt(request.getParameter("amount"));
		String description = request.getParameter("description");
		
		Book book = new Book();
		book.setName(name);
		book.setAuthor(author);
		book.setAmount(amount);
		book.setDescription(description);
		Category c = new Category();
		c.setId(categoryId);
		book.setCategory(c);
		
		BookDAO bookDAO = new BookDAO();
		bookDAO.create(book);
		
		doGet(request, response);
	}

}
