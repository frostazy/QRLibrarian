package session;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by ZY on 2015/12/21.
 */
public class ViewerUnitTest {
    @PersistenceContext(unitName="mysql")
    protected EntityManager em;

    public void getItemInfoTest() {
        try {
            InitialContext ic = new InitialContext();
            Viewer viewer = (Viewer) ic.lookup("java:module/ViewerEJB");
            System.out.println("get item info test: " + viewer.getItemInfo(1));
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public void getAllItemTest() {
        try {
            InitialContext ic = new InitialContext();;
            Viewer viewer = (Viewer) ic.lookup("java:module/ViewerEJB");
            System.out.println("get all item info test: " + viewer.getAllItem());
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public void getItemByFieldTest() {
        try {
            InitialContext ic = new InitialContext();;
            Viewer viewer = (Viewer) ic.lookup("java:module/ViewerEJB");
            System.out.println("get item by field test: " + viewer.getItemByField(1));
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public void getUserBorrowInfoTest() {
        try {
            InitialContext ic = new InitialContext();;
            Viewer viewer = (Viewer) ic.lookup("java:module/ViewerEJB");
            System.out.println("get borrow info test: " + viewer.getUserBorrowInfo(1));
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public void getUserPrivilegeInfoTest() {
        try {
            InitialContext ic = new InitialContext();;
            Viewer viewer = (Viewer) ic.lookup("java:module/ViewerEJB");
            System.out.println("get privilege info test: " + viewer.getUserPrivilegeInfo(1));
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public void main() {
        try {
            InitialContext ic = new InitialContext();
            Viewer viewer = (Viewer) ic.lookup("java:module/ViewerEJB");
            viewer.createUser("testuser1", "password");
            viewer.createUser("testuser2", "password");
            viewer.createUser("testuser3", "password");
            viewer.createUser("testuser4", "password");
            viewer.createItem("testitem1", "test", 1);
            viewer.createItem("testitem2", "test", 1);
            viewer.createItem("testitem3", "test", 1);
            viewer.createItem("testitem4", "test", 2);
            viewer.createPrivilege(1, "testuser1", 1, 3);
        } catch (NamingException e) {
            e.printStackTrace();
        }
        getItemInfoTest();
        getAllItemTest();
        getItemByFieldTest();
        getUserBorrowInfoTest();
        getUserPrivilegeInfoTest();
    }
}
