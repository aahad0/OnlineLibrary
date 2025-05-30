/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.*;

/**
 *
 * @author abdul
 */
public class SubjectServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private Connection conn;

    public void init() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lm_system?useSSL=false", "root", "Ahad1@local");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String selectedSemesterName = request.getParameter("semester");
        List<String> subjects = new ArrayList<>();

        try {
            // Step 1: Get the semester_id from the name
            int semesterId = -1;
            PreparedStatement ps1 = conn.prepareStatement("SELECT id FROM semesters WHERE name = ?");
            ps1.setString(1, selectedSemesterName);
            ResultSet rs1 = ps1.executeQuery();
            if (rs1.next()) {
                semesterId = rs1.getInt("id");
            }

            // Step 2: If ID is found, get the subjects
            if (semesterId != -1) {
                PreparedStatement ps2 = conn.prepareStatement("SELECT name FROM subjects WHERE semester_id = ?");
                ps2.setInt(1, semesterId);
                ResultSet rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    subjects.add(rs2.getString("name"));
                }
            } else {
                System.out.println("Semester not found: " + selectedSemesterName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("selectedSemester", selectedSemesterName);
        request.setAttribute("subjects", subjects);
        request.getRequestDispatcher("sublist").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
