/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Control.BookControl;
import Control.HumanControl;
import Model.Account;
import Model.Book;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author huutien
 */
@WebServlet(name = "ServiceManagement", urlPatterns = {"/ServiceManagement"})
public class ServiceManagement extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     */
    protected void login(HttpServletRequest request, HttpServletResponse response) {
        HumanControl control = new HumanControl();
        String json = request.getParameter("json");
        Gson gson = new Gson();
        Account acc = gson.fromJson(json, Account.class);
        String jsonResult = "{result: \"" + control.login(acc) + "\"}";
        try {
            response.getWriter().write(jsonResult);
        } catch (IOException ex) {
            Logger.getLogger(ServiceManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void addBook(HttpServletRequest request, HttpServletResponse response) {
        BookControl control = new BookControl();
        String json = request.getParameter("json");
        Gson gson = new Gson();
        Book book = gson.fromJson(json, Book.class);
        control.deleteBook(book);
    }

    protected void deleteBook(HttpServletRequest request, HttpServletResponse response) {
        BookControl control = new BookControl();
        String json = request.getParameter("json");
        Gson gson = new Gson();
        Book book = gson.fromJson(json, Book.class);
        control.addBook(book);
    }

    protected void updateBook(HttpServletRequest request, HttpServletResponse response) {
        BookControl control = new BookControl();
        String json = request.getParameter("json");
        Gson gson = new Gson();
        Book book = gson.fromJson(json, Book.class);
        control.updateBook(book);
    }

    protected void getBookByName(HttpServletRequest request, HttpServletResponse response) {
        BookControl control = new BookControl();
        Gson gson = new Gson();
        String name = request.getParameter("json");
        ArrayList<Book> books = control.getBookByName(name);
        String jsonResult = gson.toJson(books);
        try {
            response.getWriter().write(jsonResult);
        } catch (IOException ex) {
            Logger.getLogger(ServiceManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void getBookById(HttpServletRequest request, HttpServletResponse response) {
        BookControl control = new BookControl();
        Gson gson = new Gson();
        String idofBook = request.getParameter("json");
        int id = Integer.parseInt(idofBook);
        Book book = control.getBookById(id);
        String jsonResult = gson.toJson(book);
        try {
            response.getWriter().write(jsonResult);
        } catch (IOException ex) {
            Logger.getLogger(ServiceManagement.class.getName()).log(Level.SEVERE, null, ex);
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
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        //viết chỗ xử lý json, trả về json vào đây
        String action = request.getParameter("action");
        if (action.equals("login")) {
            login(request, response);
        }
        if (action.equals("addBook")) {
            addBook(request, response);
        }
        if (action.equals("updateBook")) {
            updateBook(request, response);
        }
        if (action.equals("deleteBook")) {
            deleteBook(request, response);
        }
        if (action.equals("getBookById")) {
            getBookById(request, response);
        }
        if (action.equals("getBookByName")) {
            getBookByName(request, response);
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

}
