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
            au.init(100);
            System.out.println("get uid test: uid=" + au.getUid().toString());
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public void borrowItemTest() {
        try {
            InitialContext ic = new InitialContext();
            ActiveUser au = (ActiveUser) ic.lookup("java:global/QRLibrarian_Web_exploded/ActiveUserEJB!session.ActiveUser");
            au.init(1);
            au.borrowItem(1);
            ItemManager im = (ItemManager) ic.lookup("java:global/QRLibrarian_Web_exploded/ItemManagerEJB!session.ItemManager");
            JSONObject jsonInfo = im.getBorrowInfo(1);
            System.out.println("borrow item test: iid=" + jsonInfo.getString("itemName") + ",uid=" + jsonInfo.getString("userName"));
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public void main() {
        getUidTest();
        borrowItemTest();
    }
}
