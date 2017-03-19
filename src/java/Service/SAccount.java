/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DAO.HumanDAO;
import DAO.HumanDAOFactory;
import Model.Const;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author tutyb
 */
@WebService(serviceName = "SAccount")
public class SAccount {

    /**
     * This is a sample web service operation
     * @param username
     * @param password
     * @return 
     */
    @WebMethod(operationName = "checkLogin")
    public boolean checkLogin(@WebParam(name = "username") String username,
            @WebParam(name = "password") String password) {
        HumanDAO dao = new HumanDAOFactory().getHumanDAO(Const.USED_DATABASE);
        boolean res = dao.login(username, password);
        dao.closeConnection();
        return res;
    }
}
