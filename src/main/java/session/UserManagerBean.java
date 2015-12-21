package session;

import entity.Item;
import entity.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.text.SimpleDateFormat;
import java.util.List;


/**
 * Created by ZY on 2015/12/17.
 */
@Stateless(name = "UserManagerEJB")
public class UserManagerBean implements UserManager {
    @PersistenceContext(unitName="mysql")
    protected EntityManager em;

    public UserManagerBean() {
    }

    public JSONObject login(String name, String password) {
        Query query = em.createNamedQuery("findUserByName");
        query.setParameter(1, name);
        List<User> result = (List<User>)query.getResultList();
        JSONObject json = new JSONObject();
        if(!result.isEmpty()) {
            User user = result.get(0);
            if(password.equals(user.getPassword())) {
                json.put("status", "1");  //login successful
                json.put("uid", user.getId());
                json.put("name", user.getName());
            } else {
                json.put("status", -1);  //wrong password
            }
        } else {
            json.put("status", 0);  //user not exist
        }
        return json;
    }

    public JSONArray getBorrowInfo(Integer uid) {
        Query query = em.createNamedQuery("findItemByBorrowUser");
        query.setParameter(1, uid);
        List<Item> result = (List<Item>)query.getResultList();
        JSONArray json = new JSONArray();
        for(Item item:result) {
            JSONObject itemInfo = new JSONObject();
            itemInfo.put("iid", item.getId());
            itemInfo.put("name", item.getItemName());
            itemInfo.put("borrowTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(item.getBorrowTime()));
            json.add(itemInfo);
        }
        return json;
    }
}
