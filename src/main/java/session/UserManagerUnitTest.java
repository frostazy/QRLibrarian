package session;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by ZY on 2015/12/21.
 */
public class UserManagerUnitTest {
    @PersistenceContext(unitName="mysql")
    protected EntityManager em;

    public void loginTest() {
        try {
            InitialContext ic = new InitialContext();
            UserManager um = (UserManager) ic.lookup("java:global/QRLibrarian_Web_exploded/UserManagerEJB!session.UserManager");
            JSONObject json = um.login("testuser", "password");
            System.out.println("login test: " + json.toString());
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public void getBorrowInfoTest() {
        try {
            InitialContext ic = new InitialContext();
            UserManager um = (UserManager) ic.lookup("java:global/QRLibrarian_Web_exploded/UserManagerEJB!session.UserManager");
            JSONArray json = um.getBorrowInfo(1);
            System.out.println("get borrow info test: " + json.toString());
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public void main() {
        loginTest();
        getBorrowInfoTest();
    }
}
