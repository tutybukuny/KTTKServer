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
@WebServlet(name = "PublisherManagement", urlPatterns = {"/PublisherManagement"})
public class PublisherManagement extends HttpServlet {

    private BookControl publisherControl = new BookControl();

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
        if (action.equals("toAllPub")) {
            allPublishers(request, response);
        } else if (action.equals("toAddPub")) {
            addPublisher(request, response);
        } else if (action.equals("update")) {
            updatePublisher(request, response);
        } else if (action.equals("delete")) {
            deletePublisher(request, response);
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
        } else if (action.equals("addPub")) {
            confirmAddPublisher(request, response);
        } else if (action.equals("confirmDelete")) {
            confirmDeletePublisher(request, response);
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

    private void allPublishers(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ArrayList<Publisher> publishers = publisherControl.getPublishers();
        request.getSession().setAttribute("publishers", publishers);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/allPubs.jsp");
        dispatcher.forward(request, response);
    }

    private void updatePublisher(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Publisher> publishers = (ArrayList<Publisher>) request.getSession().getAttribute("publishers");
        request.setAttribute("publisher", publishers.get(Integer.parseInt(request.getParameter("index"))));
        RequestDispatcher dis = getServletContext().getRequestDispatcher("/updatePub.jsp");
        dis.forward(request, response);
    }

    private void confirmUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Publisher publisher = publisherControl.getPublisherById(Integer.parseInt((String) request.getParameter("publisherID")));
        publisher.setName((String) request.getParameter("name"));
        publisher.setDescription((String) request.getParameter("description"));
        publisherControl.updatePublisher(publisher);
        allPublishers(request, response);
    }

    private void addPublisher(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher dis = getServletContext().getRequestDispatcher("/addPub.jsp");
        dis.forward(request, response);
    }

    private void confirmAddPublisher(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Publisher publisher = new Publisher();
        publisher.setName((String) request.getParameter("name"));
        publisher.setDescription((String) request.getParameter("description"));
        publisherControl.insertPublisher(publisher);
        allPublishers(request, response);
    }

    private void deletePublisher(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        ArrayList<Publisher> publishers = (ArrayList<Publisher>) request.getSession().getAttribute("publishers");
        request.setAttribute("publisher", publishers.get(Integer.parseInt(request.getParameter("index"))));
        RequestDispatcher dis = getServletContext().getRequestDispatcher("/deletePub.jsp");
        try {
            dis.forward(request, response);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void confirmDeletePublisher(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int publisherID = Integer.parseInt((String) request.getParameter("publisherID"));
        Publisher publisher = new Publisher();
        publisher.setID(publisherID);
        publisherControl.deletePublisher(publisher);
        allPublishers(request, response);
    }

}
