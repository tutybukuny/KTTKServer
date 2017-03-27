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
@WebServlet(name = "AuthorManagement", urlPatterns = {"/AuthorManagement"})
public class AuthorManagement extends HttpServlet {

    private BookControl authorControl = new BookControl();

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
        if (action.equals("toAllAuthor")) {
            allAuthors(request, response);
        } else if (action.equals("toAddAuthor")) {
            addAuthor(request, response);
        } else if (action.equals("update")) {
            updateAuthor(request, response);
        } else if (action.equals("delete")) {
            deleteAuthor(request, response);
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
        } else if (action.equals("addAuthor")) {
            confirmAddAuthor(request, response);
        } else if (action.equals("confirmDelete")) {
            confirmDeleteAuthor(request, response);
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

    private void allAuthors(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ArrayList<Author> authors = authorControl.getAuthors();
        request.getSession().setAttribute("authors", authors);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/allAuthors.jsp");
        dispatcher.forward(request, response);
    }

    private void updateAuthor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Author> authors = (ArrayList<Author>) request.getSession().getAttribute("authors");
        request.setAttribute("author", authors.get(Integer.parseInt(request.getParameter("index"))));
        RequestDispatcher dis = getServletContext().getRequestDispatcher("/updateAuthor.jsp");
        dis.forward(request, response);
    }

    private void confirmUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Author author = authorControl.getAuthorById(Integer.parseInt((String) request.getParameter("authorID")));
        author.setName((String) request.getParameter("name"));
        author.setDescription((String) request.getParameter("description"));
        authorControl.updateAuthor(author);
        allAuthors(request, response);
    }

    private void addAuthor(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher dis = getServletContext().getRequestDispatcher("/addAuthor.jsp");
        dis.forward(request, response);
    }

    private void confirmAddAuthor(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Author author = new Author();
        author.setName((String) request.getParameter("name"));
        author.setDescription((String) request.getParameter("description"));
        authorControl.insertAuthor(author);
        allAuthors(request, response);
    }

    private void deleteAuthor(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        ArrayList<Author> authors = (ArrayList<Author>) request.getSession().getAttribute("authors");
        request.setAttribute("author", authors.get(Integer.parseInt(request.getParameter("index"))));
        RequestDispatcher dis = getServletContext().getRequestDispatcher("/deleteAuthor.jsp");
        try {
            dis.forward(request, response);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void confirmDeleteAuthor(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int authorID = Integer.parseInt((String) request.getParameter("authorID"));
        Author author = new Author();
        author.setID(authorID);
        authorControl.deleteAuthor(author);
        allAuthors(request, response);
    }

}
