/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Subject;

public class SubjectDAO {
    private Connection conn;

    public SubjectDAO(Connection conn) {
        this.conn = conn;
    }

    public List<Subject> getSubjectsBySemesterId(int semesterId) throws SQLException {
        List<Subject> subjects = new ArrayList<>();
        String query = "SELECT * FROM subjects WHERE semester_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, semesterId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Subject s = new Subject();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setSemesterId(rs.getInt("semester_id"));
                subjects.add(s);
            }
        }
        return subjects;
    }
}
