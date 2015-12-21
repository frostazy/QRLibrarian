package session;

import net.sf.json.JSONObject;

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

    public void getUidTest() {
        try {
            InitialContext ic = new InitialContext();
            ActiveUser au = (ActiveUser) ic.lookup("java:global/QRLibrarian_Web_exploded/ActiveUserEJB!session.ActiveUser");
            au.init(1, "testuser");
            System.out.println("get uid test: uid=" + au.getUid().toString());
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public void borrowItemTest() {
        try {
            InitialContext ic = new InitialContext();
            ActiveUser au = (ActiveUser) ic.lookup("java:global/QRLibrarian_Web_exploded/ActiveUserEJB!session.ActiveUser");
            au.init(1, "testuser");
            au.borrowItem(1);
            ItemManager im = (ItemManager) ic.lookup("java:global/QRLibrarian_Web_exploded/ItemManagerEJB!session.ItemManager");
            JSONObject json = im.getItemInfo(1);
            System.out.println("borrow item test:" + json.toString());
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public void returnItemTest() {
        try {
            InitialContext ic = new InitialContext();
            ActiveUser au = (ActiveUser) ic.lookup("java:global/QRLibrarian_Web_exploded/ActiveUserEJB!session.ActiveUser");
            au.init(1, "testuser");
            au.returnItem(1);
            ItemManager im = (ItemManager) ic.lookup("java:global/QRLibrarian_Web_exploded/ItemManagerEJB!session.ItemManager");
            JSONObject json = im.getItemInfo(1);
            System.out.println("return item test: " + json.toString());
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public void main() {
        getUidTest();
        borrowItemTest();
        returnItemTest();
    }
}
