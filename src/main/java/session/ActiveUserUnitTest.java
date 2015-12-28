package session;

import entity.Privilege;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by ZY on 2015/12/21.
 */
public class ActiveUserUnitTest {
    @PersistenceContext(unitName="mysql")
    protected EntityManager em;

    public void loginTest() {
        try {
            InitialContext ic = new InitialContext();
            ActiveUser activeUser = (ActiveUser) ic.lookup("java:module/ActiveUserEJB");
            System.out.println("login test: uid=" + activeUser.login("testuser1", "password"));
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public void borrowItemTest() {
        try {
            InitialContext ic = new InitialContext();
            ActiveUser activeUser = (ActiveUser) ic.lookup("java:module/ActiveUserEJB");
            activeUser.login("testuser1", "password");
            System.out.println("borrow item test:" + activeUser.borrowItem(1));
            System.out.println("borrow item already borrowed test:" + activeUser.borrowItem(2));
            System.out.println("borrow item without privilege test:" + activeUser.borrowItem(4));
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public void returnItemTest() {
        try {
            InitialContext ic = new InitialContext();
            ActiveUser activeUser = (ActiveUser) ic.lookup("java:module/ActiveUserEJB");
            activeUser.login("testuser1", "password");
            System.out.println("return item test: " + activeUser.returnItem(1));
            System.out.println("return item not borrowed test: " + activeUser.returnItem(3));
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public void ItemTest() {
        try {
            InitialContext ic = new InitialContext();
            ActiveUser activeUser = (ActiveUser) ic.lookup("java:module/ActiveUserEJB");
            activeUser.login("testuser1", "password");
            System.out.println("create item test: " + activeUser.createItem(1, "a", ""));
            //System.out.println("change item test: " + activeUser.createItem(1, "a", ""));
            //System.out.println("remove item test: " + activeUser.createItem(1, "a", ""));
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public void PrivilegeTest() {
        try {
            InitialContext ic = new InitialContext();
            ActiveUser activeUser = (ActiveUser) ic.lookup("java:module/ActiveUserEJB");
            activeUser.login("testuser1", "password");
            System.out.println("create privilege test: " + activeUser.createPrivilege("testuser2", 1, Privilege.LIBRARIAN));
            System.out.println("remove privilege test: " );
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public void main() {
        loginTest();
        borrowItemTest();
        returnItemTest();
        ItemTest();
        PrivilegeTest();
    }
}
