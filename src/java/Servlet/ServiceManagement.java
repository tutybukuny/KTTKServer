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
import Model.Human;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
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
        Human human = control.getHumanByAccount(acc);
        String jsonResult = "error";
        
        if (human != null) {
            jsonResult = gson.toJson(human);
        }
        
        control.closeDAO();
        try {
            response.getWriter().write(jsonResult);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    protected void checkUsername(HttpServletRequest request, HttpServletResponse response) {
        HumanControl humanControl = new HumanControl();
        String username = request.getParameter("username");
        boolean check = humanControl.checkUsername(username);
        if (check) {
            try {
                response.getWriter().write("true");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            try {
                response.getWriter().write("false");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    protected void getBookByName(HttpServletRequest request, HttpServletResponse response) {
        BookControl control = new BookControl();
        Gson gson = new Gson();
        String name = request.getParameter("json");
        ArrayList<Book> books = control.getBookByName(name);
        String jsonResult = gson.toJson(books);
        control.closeDAO();
        try {
            response.getWriter().write(jsonResult);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    protected void getBookById(HttpServletRequest request, HttpServletResponse response) {
        BookControl control = new BookControl();
        Gson gson = new Gson();
        String idofBook = request.getParameter("json");
        int id = Integer.parseInt(idofBook);
        Book book = control.getBookById(id);
        String jsonResult = gson.toJson(book);
        control.closeDAO();
        try {
            response.getWriter().write(jsonResult);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    protected void getBooks(HttpServletRequest request, HttpServletResponse response) {
        BookControl control = new BookControl();
        Gson gson = new Gson();
        String jsonResult = gson.toJson(control.getBooks());
        control.closeDAO();
        try {
            response.getWriter().write(jsonResult);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    protected void getAuthors(HttpServletRequest request, HttpServletResponse response) {
        BookControl control = new BookControl();
        Gson gson = new Gson();
        String jsonResult = gson.toJson(control.getAuthors());
        control.closeDAO();
        try {
            response.getWriter().write(jsonResult);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    protected void getPublishers(HttpServletRequest request, HttpServletResponse response) {
        BookControl control = new BookControl();
        Gson gson = new Gson();
        String jsonResult = gson.toJson(control.getPublishers());
        control.closeDAO();
        try {
            response.getWriter().write(jsonResult);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    protected void signup(HttpServletRequest request, HttpServletResponse response) {
        String json = request.getParameter("json");
        Human human = new Gson().fromJson(json, Human.class);
        HumanControl humanControl = new HumanControl();
        String username = human.getAcc().getUsername();
        boolean check = humanControl.checkUsername(username);
        String result;
        if (check) {
            humanControl.insertHuman(human);
            Account acc = human.getAcc();
            human = humanControl.getHumanByAccount(acc);
            
            result = new Gson().toJson(human);
        } else {
            result = "{error: false}";
        }
        
        try {
            response.getWriter().write(result);
        } catch (IOException ex) {
            ex.printStackTrace();
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
        if (action.equals("login")) {
            checkUsername(request, response);
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
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        //viết chỗ xử lý json, trả về json vào đây
        String action = request.getParameter("action");
        if (action.equals("login")) {
            login(request, response);
        }
        if (action.equals("getBookById")) {
            getBookById(request, response);
        }
        if (action.equals("getBookByName")) {
            getBookByName(request, response);
        }
        if (action.equals("getBooks")) {
            getBooks(request, response);
        }
        if (action.equals("getAuthors")) {
            getAuthors(request, response);
        }
        if (action.equals("getPublishers")) {
            getPublishers(request, response);
        }
        if(action.equals("sigup")){
            signup(request, response);
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
