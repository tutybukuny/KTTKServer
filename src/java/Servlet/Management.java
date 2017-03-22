/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Control.*;
import Model.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author tutyb
 */
@WebServlet(name = "Management", urlPatterns = {"/Management"})
public class Management extends HttpServlet {

    private HumanControl humanControl = new HumanControl();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Management</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Management at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        String action = request.getParameter("action");
        if (action.equals("toAllBook")) {
            allBooks(request, response);
        }
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
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");

        if (action.equals("login")) {
            checkLogin(request, response);
        }
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

    private void checkLogin(HttpServletRequest request, HttpServletResponse response) {
        Account acc = new Account();
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        acc.setUsername(username);
        acc.setPassword(password);

        RequestDispatcher dis;

        if (humanControl.login(acc)) {
            HttpSession session = request.getSession();
            session.setAttribute("account", acc);
            try {
                allBooks(request, response);
            } catch (IOException ex) {
                Logger.getLogger(Management.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ServletException ex) {
                Logger.getLogger(Management.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            dis = getServletContext().getRequestDispatcher("/index.jsp");
            try {
                dis.forward(request, response);
            } catch (ServletException | IOException ex) {
                Logger.getLogger(Management.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private void allBooks(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("ddmm");
        BookControl bookControl = new BookControl();
        ArrayList<Book> books = bookControl.getBooks();
        System.out.println("booksize: " + books.size());
        request.setAttribute("books", books);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/allBooks.jsp");
        dispatcher.forward(request, response);
    }

}
