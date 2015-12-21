package session;

import entity.Item;
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
@Stateless(name = "ItemManagerEJB")
public class ItemManagerBean implements ItemManager {
    @PersistenceContext(unitName="mysql")
    protected EntityManager em;

    public ItemManagerBean() {
    }

    public JSONObject getItemInfo(Integer iid) {
        Item item = em.find(Item.class, iid);
        JSONObject json = new JSONObject();
        if(item != null) {
            json.put("iid", iid);
            json.put("name", item.getItemName());
            json.put("owner", item.getOwner());
            json.put("description", item.getDescription());
            json.put("availability", item.getAvailability());
            json.put("url", item.getUrl());
            if(item.getAvailability() == 0) {
                json.put("borrowUserId", item.getBorrowUserId());
                json.put("borrowUserName", item.getBorrowUserName());
                json.put("borrowTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(item.getBorrowTime()));
            } else {
                json.put("borrowUserId", 0);
            }
        }
        return json;
    }

    public JSONObject getAllItem() {
        Query query = em.createNamedQuery("findAvailableItem");
        List result = query.getResultList();
        JSONArray ja = new JSONArray();
        for(Object item: result) {
            JSONObject jo = new JSONObject();
            jo.put("iid", ((Object[])item)[0]);
            jo.put("name", ((Object[])item)[1]);
            ja.add(jo);
        }
        JSONObject json = new JSONObject();
        json.put("available", ja);

        query = em.createNamedQuery("findNotAvailableItem");
        result = query.getResultList();
        ja = new JSONArray();
        for(Object item: result) {
            JSONObject jo = new JSONObject();
            jo.put("iid", ((Object[])item)[0]);
            jo.put("name", ((Object[])item)[1]);
            ja.add(jo);
        }
        json.put("notAvailable", ja);

        return json;
    }

    public boolean checkAvailability(Integer iid) {
        return em.find(Item.class, iid).getAvailability()==1;
    }
}
