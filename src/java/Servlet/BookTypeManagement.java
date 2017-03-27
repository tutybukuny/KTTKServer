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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author tutyb
 */
@WebServlet(name = "BookTypeManagement", urlPatterns = {"/BookTypeManagement"})
public class BookTypeManagement extends HttpServlet {

    private BookControl bookTypeControl = new BookControl();

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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action.equals("toAllType")) {
            allBookTypes(request, response);
        } else if (action.equals("toAddType")) {
            addBookType(request, response);
        } else if (action.equals("update")) {
            updateBookType(request, response);
        } else if (action.equals("delete")) {
            deleteBookType(request, response);
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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action.equals("confirmUpdate")) {
            confirmUpdate(request, response);
        } else if (action.equals("addType")) {
            confirmAddBookType(request, response);
        } else if (action.equals("confirmDelete")) {
            confirmDeleteBookType(request, response);
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

    private void allBookTypes(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ArrayList<BookType> bookTypes = bookTypeControl.getBookTypes();
        request.getSession().setAttribute("bookTypes", bookTypes);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/allTypes.jsp");
        dispatcher.forward(request, response);
    }

    private void updateBookType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<BookType> bookTypes = (ArrayList<BookType>) request.getSession().getAttribute("bookTypes");
        request.setAttribute("bookType", bookTypes.get(Integer.parseInt(request.getParameter("index"))));
        RequestDispatcher dis = getServletContext().getRequestDispatcher("/updateType.jsp");
        dis.forward(request, response);
    }

    private void confirmUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookType bookType = bookTypeControl.getBookTypeById(Integer.parseInt((String) request.getParameter("bookTypeID")));
        bookType.setName((String) request.getParameter("name"));
        bookType.setDescription((String) request.getParameter("description"));
        bookTypeControl.updateBookType(bookType);
        allBookTypes(request, response);
    }

    private void addBookType(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher dis = getServletContext().getRequestDispatcher("/addType.jsp");
        dis.forward(request, response);
    }

    private void confirmAddBookType(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        BookType bookType = new BookType();
        bookType.setName((String) request.getParameter("name"));
        bookType.setDescription((String) request.getParameter("description"));
        bookTypeControl.insertBookType(bookType);
        allBookTypes(request, response);
    }

    private void deleteBookType(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        ArrayList<BookType> bookTypes = (ArrayList<BookType>) request.getSession().getAttribute("bookTypes");
        request.setAttribute("bookType", bookTypes.get(Integer.parseInt(request.getParameter("index"))));
        RequestDispatcher dis = getServletContext().getRequestDispatcher("/deleteType.jsp");
        try {
            dis.forward(request, response);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void confirmDeleteBookType(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int bookTypeID = Integer.parseInt((String) request.getParameter("bookTypeID"));
        BookType bookType = new BookType();
        bookType.setID(bookTypeID);
        bookTypeControl.deleteBookType(bookType);
        allBookTypes(request, response);
    }

}
