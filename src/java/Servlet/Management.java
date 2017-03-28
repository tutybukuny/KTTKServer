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
 * @author NgocThien
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
        } else if (action.equals("updateBook")) {
            updateBook(request, response);
        } else if (action.equals("deleteBook")) {
            deleteBook(request, response);
        } else if (action.equals("logout")) {
            logout(request, response);
        } else if (action.equals("toAllAuthor")) {
            allAuthors(request, response);
        } else if (action.equals("toAddAuthor")) {
            addAuthor(request, response);
        } else if (action.equals("updateAuthor")) {
            updateAuthor(request, response);
        } else if (action.equals("deleteAuthor")) {
            deleteAuthor(request, response);
        } else if (action.equals("toAllPub")) {
            allPublishers(request, response);
        } else if (action.equals("toAddPub")) {
            addPublisher(request, response);
        } else if (action.equals("updatePub")) {
            updatePublisher(request, response);
        } else if (action.equals("deletePub")) {
            deletePublisher(request, response);
        } else if (action.equals("toAllType")) {
            allBookTypes(request, response);
        } else if (action.equals("toAddType")) {
            addBookType(request, response);
        } else if (action.equals("updateType")) {
            updateBookType(request, response);
        } else if (action.equals("deleteType")) {
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

        if (action.equals("login")) {
            checkLogin(request, response);
        } else if (action.equals("signup")) {
            signup(request, response);
        }

        if (request.getSession().getAttribute("human") == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        if (action.equals("confirmUpdateBook")) {
            confirmUpdateBook(request, response);
        } else if (action.equals("addBook")) {
            confirmAddBook(request, response);
        } else if (action.equals("confirmDeleteBook")) {
            confirmDeleteBook(request, response);
        } else if (action.equals("confirmUpdateAuthor")) {
            confirmUpdateAuthor(request, response);
        } else if (action.equals("addAuthor")) {
            confirmAddAuthor(request, response);
        } else if (action.equals("confirmDeleteAuthor")) {
            confirmDeleteAuthor(request, response);
        } else  if (action.equals("confirmUpdatePub")) {
            confirmUpdatePublisher(request, response);
        } else if (action.equals("addPub")) {
            confirmAddPublisher(request, response);
        } else if (action.equals("confirmDeletePub")) {
            confirmDeletePublisher(request, response);
        } else if (action.equals("confirmUpdateType")) {
            confirmUpdateBookType(request, response);
        } else if (action.equals("addType")) {
            confirmAddBookType(request, response);
        } else if (action.equals("confirmDeleteType")) {
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

    private void confirmUpdateBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

    
    // AUTHOR
    
    private void allAuthors(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ArrayList<Author> authors = bookControl.getAuthors();
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

    private void confirmUpdateAuthor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Author author = bookControl.getAuthorById(Integer.parseInt((String) request.getParameter("authorID")));
        author.setName((String) request.getParameter("name"));
        author.setDescription((String) request.getParameter("description"));
        bookControl.updateAuthor(author);
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
        bookControl.insertAuthor(author);
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
        bookControl.deleteAuthor(author);
        allAuthors(request, response);
    }

    // PUBLISHER
    
        private void allPublishers(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ArrayList<Publisher> publishers = bookControl.getPublishers();
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

    private void confirmUpdatePublisher(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Publisher publisher = bookControl.getPublisherById(Integer.parseInt((String) request.getParameter("publisherID")));
        publisher.setName((String) request.getParameter("name"));
        publisher.setDescription((String) request.getParameter("description"));
        bookControl.updatePublisher(publisher);
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
        bookControl.insertPublisher(publisher);
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
        bookControl.deletePublisher(publisher);
        allPublishers(request, response);
    }

    // BOOK TYPE   
    
        private void allBookTypes(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ArrayList<BookType> bookTypes = bookControl.getBookTypes();
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

    private void confirmUpdateBookType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookType bookType = bookControl.getBookTypeById(Integer.parseInt((String) request.getParameter("bookTypeID")));
        bookType.setName((String) request.getParameter("name"));
        bookType.setDescription((String) request.getParameter("description"));
        bookControl.updateBookType(bookType);
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
        bookControl.insertBookType(bookType);
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
        bookControl.deleteBookType(bookType);
        allBookTypes(request, response);
    }

}
