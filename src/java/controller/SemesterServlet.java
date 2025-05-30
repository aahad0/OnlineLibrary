/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SemesterServlet extends HttpServlet {

    private Connection conn;

    public void init() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lm_system?useSSL=false", "root", "Ahad1@local");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
   // @Override
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

    List<String> semesters = new ArrayList<>();

    try {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT name FROM semesters");
        while (rs.next()) {
            semesters.add(rs.getString("name"));
            
        }
        System.out.println(semesters);
    } catch (SQLException e) {
        e.printStackTrace();
    }

    req.setAttribute("semesters", semesters);
    req.getRequestDispatcher("sem").forward(req, resp); // ✅ Make sure your JSP is named correctly
}
   // @Override
//protected void procesRequest(HttpServletRequest req, HttpServletResponse resp)
//        throws ServletException, IOException {
//
//    String semesterName = req.getParameter("semester");
//    if (semesterName != null) {
//        resp.sendRedirect("subjectList.jsp?semester=" + semesterName); // or call SubjectServlet
//    } else {
//        resp.sendRedirect("SemesterServlet"); // fallback in case nothing selected
//    }
//}


//    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        // Fetch all semesters and forward to selectSemester.jsp
//        List<String> semesters = Arrays.asList("Semester 1", "Semester 2", "Semester 3");
//
//        req.setAttribute("semesters", semesters);
//        req.getRequestDispatcher("sem").forward(req, resp);
//        try {
//            Statement stmt = conn.createStatement();
//            ResultSet rs = stmt.executeQuery("SELECT * FROM semesters");
//            while (rs.next()) {
//                semesters.add(rs.getString("name"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        req.setAttribute("semesters", semesters);
//        req.getRequestDispatcher("selectSemester.jsp").forward(req, resp);
//    //}
//
//   // protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        // User selected semester, redirect to subjectList.jsp with semester param
//        String semesterName = req.getParameter("semester");
//        resp.sendRedirect("subjectList.jsp?semester=" + semesterName);
//    }


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
