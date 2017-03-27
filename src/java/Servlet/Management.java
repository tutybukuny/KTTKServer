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
import javax.servlet.http.HttpSession;

/**
 *
 * @author tutyb
 */
@WebServlet(name = "Management", urlPatterns = {"/Management"})
public class Management extends HttpServlet {

    private HumanControl humanControl = new HumanControl();
    private BookControl bookControl = new BookControl();

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

        if (request.getSession().getAttribute("human") == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        String action = request.getParameter("action");
        if (action.equals("toAllBook")) {
            allBooks(request, response);
        } else if (action.equals("toAddBook")) {
            addBook(request, response);
        } else if (action.equals("update")) {
            updateBook(request, response);
        } else if (action.equals("delete")) {
            deleteBook(request, response);
        } else if (action.equals("logout")) {
            logout(request, response);
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

        if (action.equals("login")) {
            checkLogin(request, response);
        } else if (action.equals("signup")) {
            signup(request, response);
        }

        if (request.getSession().getAttribute("human") == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        if (action.equals("confirmUpdate")) {
            confirmUpdate(request, response);
        } else if (action.equals("addBook")) {
            confirmAddBook(request, response);
        } else if (action.equals("confirmDelete")) {
            confirmDeleteBook(request, response);
        } else if(action.equals("addAuthor")){
            addAuthor(request, response);
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

        if (humanControl.checkLogin(acc)) {
            HttpSession session = request.getSession();
            Human human = humanControl.getHumanByAccount(acc);
            session.setAttribute("human", human);
            try {
                allBooks(request, response);
            } catch (IOException | ServletException ex) {
                ex.printStackTrace();
            }

        } else {
            dis = getServletContext().getRequestDispatcher("/index.jsp");
            try {
                dis.forward(request, response);
            } catch (ServletException | IOException ex) {
                ex.printStackTrace();
            }
        }

    }

    private void allBooks(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ArrayList<Book> books = bookControl.getBooks();
        request.getSession().setAttribute("books", books);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/allBooks.jsp");
        dispatcher.forward(request, response);
    }

    private void updateBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Book> books = (ArrayList<Book>) request.getSession().getAttribute("books");
        ArrayList<Author> authors = bookControl.getAuthors();
        ArrayList<Publisher> publishers = bookControl.getPublishers();
        ArrayList<BookType> bookTypes = bookControl.getBookTypes();
        request.setAttribute("book", books.get(Integer.parseInt(request.getParameter("index"))));
        request.setAttribute("publishers", publishers);
        request.setAttribute("authors", authors);
        request.setAttribute("bookTypes", bookTypes);
        RequestDispatcher dis = getServletContext().getRequestDispatcher("/updateBook.jsp");
        dis.forward(request, response);
    }

    private void confirmUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Book book = bookControl.getBookById(Integer.parseInt((String) request.getParameter("bookID")));
        Author author = new Author();
        author.setID(Integer.parseInt((String) request.getParameter("author")));
        BookType type = new BookType();
        type.setID(Integer.parseInt((String) request.getParameter("bookType")));
        Publisher pub = new Publisher();
        pub.setID(Integer.parseInt((String) request.getParameter("publisher")));
        book.setAuthor(author);
        book.setBookType(type);
        book.setPublisher(pub);
        book.setName((String) request.getParameter("name"));
        book.setDescription((String) request.getParameter("description"));
        book.setCost(Float.parseFloat((String) request.getParameter("cost")));
        bookControl.updateBook(book);
        allBooks(request, response);
    }

    private void addBook(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ArrayList<Author> authors = bookControl.getAuthors();
        ArrayList<Publisher> publishers = bookControl.getPublishers();
        ArrayList<BookType> bookTypes = bookControl.getBookTypes();
        request.setAttribute("publishers", publishers);
        request.setAttribute("authors", authors);
        request.setAttribute("bookTypes", bookTypes);
        RequestDispatcher dis = getServletContext().getRequestDispatcher("/addBook.jsp");
        dis.forward(request, response);
    }

    private void confirmAddBook(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Book book = new Book();
        Author author = new Author();
        author.setID(Integer.parseInt((String) request.getParameter("author")));
        BookType type = new BookType();
        type.setID(Integer.parseInt((String) request.getParameter("bookType")));
        Publisher pub = new Publisher();
        pub.setID(Integer.parseInt((String) request.getParameter("publisher")));
        book.setAuthor(author);
        book.setBookType(type);
        book.setPublisher(pub);
        book.setName((String) request.getParameter("name"));
        book.setDescription((String) request.getParameter("description"));
        book.setCost(Float.parseFloat((String) request.getParameter("cost")));
        bookControl.addBook(book);
        allBooks(request, response);
    }

    private void deleteBook(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        ArrayList<Book> books = (ArrayList<Book>) request.getSession().getAttribute("books");
        request.setAttribute("book", books.get(Integer.parseInt(request.getParameter("index"))));
        RequestDispatcher dis = getServletContext().getRequestDispatcher("/deleteBook.jsp");
        try {
            dis.forward(request, response);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void signup(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        boolean check = humanControl.checkUsername(username);

        Human human = new Human();
        String password = request.getParameter("password");
        String number = request.getParameter("number");
        String street = request.getParameter("street");
        String city = request.getParameter("city");
        String county = "Vietnam";
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String middleName = request.getParameter("middlename");
        String mounth = request.getParameter("mounth");
        int day = Integer.parseInt(request.getParameter("day"));
        int year = Integer.parseInt(request.getParameter("year"));
        String dis = request.getParameter("dis");
        Address add = new Address();
        add.setNumber(number);
        add.setCity(city);
        add.setCountry(county);
        add.setStreet(street);
        Name name = new Name();
        name.setFirstName(firstName);
        name.setLastName(lastName);
        name.setMiddleName(middleName);
        Birthday birthday = new Birthday();
        birthday.setDay(day);
        birthday.setMonth(mounth);
        birthday.setYear(year);
        Account acc = new Account();
        acc.setUsername(username);
        acc.setPassword(password);
        human.setAcc(acc);
        human.setAddress(add);
        human.setBirthday(birthday);
        human.setName(name);
        human.setDiscriminator(dis);
        if (check) {
            humanControl.insertHuman(human);
            human = humanControl.getHumanByAccount(acc);
            request.getSession().setAttribute("human", human);
            allBooks(request, response);
        } else {
            request.setAttribute("human", human);
            request.setAttribute("errorusername", "Tên đăng nhập đã  được sử dụng");
            RequestDispatcher disp = getServletContext().getRequestDispatcher("/signup.jsp");
            disp.forward(request, response);
        }
    }

    private void confirmDeleteBook(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int bookID = Integer.parseInt((String) request.getParameter("bookID"));
        Book book = new Book();
        book.setID(bookID);
        bookControl.deleteBook(book);
        allBooks(request, response);
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute("human");
        RequestDispatcher dis = getServletContext().getRequestDispatcher("/index.jsp");
        try {
            dis.forward(request, response);
        } catch (IOException | ServletException ex) {
            ex.printStackTrace();
        }
    }

    private void addAuthor(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        Author author = new Author();
        author.setDescription(description);
        author.setName(name);
        
        bookControl.insertAuthor(author);
    }
    
    private void allAuthors(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ArrayList<Author> authors = bookControl.getAuthors();
        request.getSession().setAttribute("authors", authors);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/allBooks.jsp");
        dispatcher.forward(request, response);
    }

}
