package session;

import entity.Item;
import net.sf.json.JSONObject;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;


/**
 * Created by ZY on 2015/12/17.
 */
@Stateful(name = "ActiveUserEJB")
public class ActiveUserBean implements ActiveUser {
    @PersistenceContext(unitName="mysql")
    protected EntityManager em;

    private Integer uid;
    private String name;

    public Integer getUid() {return uid;}

    public ActiveUserBean() {
    }

    public void init(Integer uid, String name) {
        this.uid = uid;
        this.name = name;
    }

    public JSONObject borrowItem(Integer iid) {
        Item item = em.find(Item.class, iid);
        JSONObject json = new JSONObject();
        if(item.getAvailability() == 1){
            item.setBorrowUserId(uid);
            item.setBorrowUserName(name);
            item.setBorrowTime(new java.sql.Timestamp(new Date().getTime()));
            item.setAvailability(0);
            em.persist(item);
            json.put("status", 1);
        } else {
            json.put("status", 0);
        }
        json.put("uid", uid);
        json.put("name", name);
        return json;
    }

    public JSONObject returnItem(Integer iid) {
        Item item = em.find(Item.class, iid);
        JSONObject json = new JSONObject();
        if(item.getAvailability() == 0){
            item.setBorrowUserId(0);
            item.setBorrowUserName(null);
            item.setBorrowTime(null);
            item.setAvailability(1);
            em.persist(item);
            json.put("status", 1);
        } else {
            json.put("status", 0);
        }
        json.put("uid", uid);
        json.put("name", name);
        return json;
    }
}
