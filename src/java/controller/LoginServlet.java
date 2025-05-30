package controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import dao.UserDAO;
import model.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

public class LoginServlet extends HttpServlet {

    private UserDAO userDAO;

    public void init() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lm_system?useSSL=false", "root", "Ahad1@local");
            userDAO = new UserDAO(conn);
            System.out.println("init method called");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println(username);
        try {
            System.out.println("Username: " + username);
            System.out.println("Password: " + password);
            System.out.println("userDAO is null? " + (userDAO == null));

            if (username == null || password == null || userDAO == null) {
                throw new NullPointerException("username, password, or userDAO is null");
            }
            User user = userDAO.getUserByUsernameAndPassword(username.trim(), password.trim());
            if (user != null) {
                HttpSession session = req.getSession();
                session.setAttribute("currentUser", user);
                resp.sendRedirect("SemesterServlet"); // ✅ Your target page
            } else {
                req.setAttribute("error", "Invalid username or password.");
                req.getRequestDispatcher("lgin").forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Error during login.");
            req.getRequestDispatcher("lgin").forward(req, resp);
        }
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
