package dao;

import model.Book;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    private Connection conn;

    public BookDAO(Connection conn) {
        this.conn = conn;
    }

    // Fetch books by subject name
    public List<Book> getBooksBySubject(String subjectName) {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT name, file_path FROM books WHERE subject_name = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, subjectName);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Book book = new Book();
                book.setName(rs.getString("name"));
                book.setFilePath(rs.getString("file_path"));
                books.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }

    // Add a book
    public boolean addBook(Book book) {
        String sql = "INSERT INTO books (name, subject_name, file_path) VALUES (?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, book.getName());
            ps.setString(2, book.getSubjectName());
            ps.setString(3, book.getFilePath());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete a book by ID
    public boolean deleteBook(int bookId) {
        String sql = "DELETE FROM books WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, bookId);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
