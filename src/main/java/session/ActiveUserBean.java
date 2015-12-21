package session;

import entity.Item;
import net.sf.json.JSONObject;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 * Created by ZY on 2015/12/17.
 */
@Stateful(name = "ActiveUserEJB")
public class ActiveUserBean implements ActiveUser {
    @PersistenceContext(unitName="mysql")
    protected EntityManager em;

    private Integer uid;

    public Integer getUid() {return uid;}

    public ActiveUserBean() {
    }

    public void init(Integer uid) {
        this.uid = uid;
    }

    public JSONObject borrowItem(Integer iid) {
        Item item = em.find(Item.class, iid);
        item.setBorrowUser(uid);
        em.persist(item);
        return new JSONObject();
    }
}
