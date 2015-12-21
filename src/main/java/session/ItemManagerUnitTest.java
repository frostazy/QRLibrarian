package session;

import net.sf.json.JSONObject;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by ZY on 2015/12/21.
 */
public class ItemManagerUnitTest {
    @PersistenceContext(unitName="mysql")
    protected EntityManager em;

    public void getItemInfoTest() {
        try {
            InitialContext ic = new InitialContext();
            ItemManager im = (ItemManager) ic.lookup("java:global/QRLibrarian_Web_exploded/ItemManagerEJB!session.ItemManager");
            JSONObject json = im.getItemInfo(1);
            System.out.println("get item info test: " + json.toString());
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public void getAllItemTest() {
        try {
            InitialContext ic = new InitialContext();
            ItemManager im = (ItemManager) ic.lookup("java:global/QRLibrarian_Web_exploded/ItemManagerEJB!session.ItemManager");
            JSONObject json = im.getAllItem();
            System.out.println("get all item info test: " + json.toString());
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public void main() {
        getItemInfoTest();
        getAllItemTest();
    }
}
