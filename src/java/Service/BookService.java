/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DAO.BookDAO;
import DAO.BookDAOFactory;
import Model.Book;
import Model.Const;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author tutyb
 */
@WebService(serviceName = "BookService")
public class BookService {

    /**
     * This is a sample web service operation
     *
     * @param book
     */
    @WebMethod(operationName = "addBook")
    public void addBook(@WebParam(name = "book") Book book) {
        BookDAO dao = new BookDAOFactory().getBookDAO(Const.USED_DATABASE);
        dao.insert(book);
        dao.closeConnection();
    }

    @WebMethod(operationName = "updateBook")
    public void updateBook(@WebParam(name = "book") Book book) {
        BookDAO dao = new BookDAOFactory().getBookDAO(Const.USED_DATABASE);
        dao.update(book);
        dao.closeConnection();
    }

    @WebMethod(operationName = "deleteBook")
    public void deleteBook(@WebParam(name = "book") Book book) {
        BookDAO dao = new BookDAOFactory().getBookDAO(Const.USED_DATABASE);
        dao.delete(book);
        dao.closeConnection();
    }
}
