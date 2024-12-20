package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Book;
import model.Borrowing;
import model.BorrowingDetails;
import model.Category;

public class BorrowingDAO {
	private Connection conn;

	public BorrowingDAO() {
		conn = ConnectDatabase.getConnection();
	}

	public List<Borrowing> find() {
		List<Borrowing> borrowings = new ArrayList<Borrowing>();
		String sql = "SELECT * FROM Borrowings ORDER BY createdAt DESC";
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Borrowing borrowing = new Borrowing();
				borrowing.setId(rs.getInt("id"));
				borrowing.setBorrowDate(rs.getDate("borrowDate"));

				ReaderDAO readerDAO = new ReaderDAO();
				borrowing.setReader(readerDAO.getReaderById(rs.getInt("readerId")));

				UserDAO userDAO = new UserDAO();
				borrowing.setUser(userDAO.getUserById(rs.getInt("userId")));

				borrowing.setBorrowingDetails(getDetailsByBorrowingId(rs.getInt("id")));
				System.out.println(borrowing);

				borrowings.add(borrowing);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return borrowings;
	}

	private List<BorrowingDetails> getDetailsByBorrowingId(int borrowingId) {
		List<BorrowingDetails> detailsList = new ArrayList<>();
		String query = "SELECT bd.id, b.id AS bookId, b.name AS bookName, b.author, b.categoryId, b.image, b.description, b.amount, bd.returnDate FROM BorrowingDetails bd " + "JOIN Books b ON bd.bookId = b.id WHERE bd.borrowingId = ?";

		try {
			PreparedStatement preparedStatement = conn.prepareStatement(query);

			preparedStatement.setInt(1, borrowingId);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Book book = new Book();
				CategoryDAO categoryDAO = new CategoryDAO();
				book.setId(resultSet.getInt("bookId"));
				book.setName(resultSet.getString("bookName"));
				Category c = categoryDAO.findById(resultSet.getInt("categoryId"));
                book.setCategory(c);
				book.setImage(resultSet.getString("image"));
				book.setAuthor(resultSet.getString("author"));
				book.setDescription(resultSet.getString("description"));
				book.setAmount(resultSet.getInt("amount"));

				BorrowingDetails detail = new BorrowingDetails(resultSet.getInt("id"), book,
						resultSet.getDate("returnDate"));

				detailsList.add(detail);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return detailsList;
	}
	
	public boolean returnBook(int borrowingDetailsId) {
		String sql = "UPDATE BorrowingDetails SET returnDate = NOW() WHERE id = ?";

		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);

			preparedStatement.setInt(1, borrowingDetailsId);
			boolean resultSet = preparedStatement.execute();

			return resultSet;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
	
	public int createBorrowing(Borrowing borrowing) {
	    PreparedStatement borrowStmt = null;
	    PreparedStatement detailStmt = null;
	    ResultSet generatedKeys = null;

	    try {
	        // Insert the borrowing record
	        String insertBorrowingSQL = "INSERT INTO Borrowings (readerId, userId, borrowDate) VALUES (?, ?, ?)";
	        borrowStmt = conn.prepareStatement(insertBorrowingSQL, PreparedStatement.RETURN_GENERATED_KEYS);
	        borrowStmt.setInt(1, borrowing.getReader().getId());
	        borrowStmt.setInt(2, 1); // You can replace this with actual user ID
	        LocalDate currentDate = LocalDate.now();
	        Date sqlDate = Date.valueOf(currentDate);
	        borrowStmt.setDate(3, sqlDate);

	        int rowsAffected = borrowStmt.executeUpdate();

	        // Check if the insertion was successful
	        if (rowsAffected == 0) {
	            throw new SQLException("Creating borrowing failed, no rows affected.");
	        }

	        // Retrieve the generated borrowingId
	        generatedKeys = borrowStmt.getGeneratedKeys();
	        int borrowingId = 0;
	        if (generatedKeys.next()) {
	            borrowingId = generatedKeys.getInt(1);
	        } else {
	            throw new SQLException("Creating borrowing failed, no ID obtained.");
	        }

	        // Insert BorrowingDetails records
	        for (BorrowingDetails borrowingDetail : borrowing.getBorrowingDetails()) {
	            String insertDetailSQL = "INSERT INTO BorrowingDetails (borrowingId, bookId, returnDate) VALUES (?, ?, ?)";
	            detailStmt = conn.prepareStatement(insertDetailSQL);
	            detailStmt.setInt(1, borrowingId);
	            detailStmt.setInt(2, borrowingDetail.getBook().getId());
	            detailStmt.setDate(3, null);
	            detailStmt.executeUpdate();
	        }

	        return borrowingId;

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Ensure resources are closed to avoid memory leaks
	        try {
	            if (borrowStmt != null) borrowStmt.close();
	            if (detailStmt != null) detailStmt.close();
	            if (generatedKeys != null) generatedKeys.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return 0;
	}


}
