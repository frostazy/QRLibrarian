package session;

import entity.Item;
import entity.User;
import net.sf.json.JSONObject;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 * Created by ZY on 2015/12/17.
 */
@Stateless(name = "ItemManagerEJB")
public class ItemManagerBean implements ItemManager {
    @PersistenceContext(unitName="mysql")
    protected EntityManager em;

    public ItemManagerBean() {
    }

    public JSONObject getBorrowInfo(Integer iid) {
        Item item = em.find(Item.class, iid);
        JSONObject json = new JSONObject();
        if(item != null) {
            json.put("itemId", iid);
            json.put("itemName", item.getItemName());
            if(item.getAvailability() == 0) {
                json.put("userId", item.getBorrowUser());
                json.put("userName", em.find(User.class, item.getBorrowUser()).getName());
            }else json.put("userId", 0);
        }
        return json;
    }
}
