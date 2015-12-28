package session;

import entity.BorrowRecord;
import entity.Item;
import entity.Privilege;
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
@Stateless(name = "ViewerEJB")
public class ViewerBean implements Viewer {

    @PersistenceContext(unitName="mysql")
    protected EntityManager em;

    public ViewerBean() {
    }

    public void createUser(String name, String password) {
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        em.persist(user);
    }

    public void createItem(String name, String description, Integer field) {
        Item item = new Item();
        item.setItemName(name);
        item.setField(field);
        item.setDescription(description);
        item.setAvailability(Item.AVAILABLE);
        em.persist(item);
    }

    public void createPrivilege(Integer uid, String name, Integer field, Integer role) {
        Privilege privilege = new Privilege();
        privilege.setUserId(uid);
        privilege.setUserName(name);
        privilege.setFieldId(field);
        privilege.setRole(role);
        em.persist(privilege);
    }

    public JSONObject getItemInfo(Integer iid) {
        Item item = em.find(Item.class, iid);
        JSONObject json = new JSONObject();
        if(item != null) {
            json.put("iid", iid);
            json.put("name", item.getItemName());
            json.put("field", item.getField());
            json.put("description", item.getDescription());
            json.put("availability", item.getAvailability());
        }
        return json;
    }

    public JSONObject getAllItem() {
        Query query = em.createNamedQuery("findAllItem");
        List<Item> result = (List<Item>)query.getResultList();
        JSONArray ja1 = new JSONArray();
        JSONArray ja2 = new JSONArray();
        for(Item item: result) {
            JSONObject jo = new JSONObject();
            jo.put("iid", item.getId());
            jo.put("name", item.getItemName());
            if(item.getAvailability() == Item.AVAILABLE) ja1.add(jo);
            else ja2.add(jo);
        }
        JSONObject json = new JSONObject();
        json.put("available", ja1);
        json.put("notAvailable", ja2);
        return json;
    }

    public JSONObject getItemByField(Integer field) {
        Query query = em.createNamedQuery("findItemByField");
        query.setParameter(1, field);
        List<Item> result = (List<Item>)query.getResultList();
        JSONArray ja1 = new JSONArray();
        JSONArray ja2 = new JSONArray();
        for(Item item: result) {
            JSONObject jo = new JSONObject();
            jo.put("iid", item.getId());
            jo.put("name", item.getItemName());
            jo.put("fid", item.getField());
            if(item.getAvailability() == Item.AVAILABLE) ja1.add(jo);
            else ja2.add(jo);
        }
        JSONObject json = new JSONObject();
        json.put("available", ja1);
        json.put("notAvailable", ja2);
        return json;
    }

    public JSONArray getUserBorrowInfo(Integer uid) {
        Query query = em.createNamedQuery("findBorrowedItemByUserId");
        query.setParameter(1, uid);
        List<BorrowRecord> result = (List<BorrowRecord>)query.getResultList();
        JSONArray json = new JSONArray();
        for(BorrowRecord borrowRecord:result) {
            JSONObject itemInfo = new JSONObject();
            itemInfo.put("iid", borrowRecord.getItemId());
            itemInfo.put("name", borrowRecord.getItemName());
            itemInfo.put("borrowTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(borrowRecord.getBorrowTime()));
            json.add(itemInfo);
        }
        return json;
    }

    public JSONArray getUserPrivilegeInfo(Integer uid) {
        Query query = em.createNamedQuery("findPrivilegeByUserId");
        query.setParameter(1, uid);
        List<Privilege> result = (List<Privilege>)query.getResultList();
        JSONArray json = new JSONArray();
        for(Privilege privilege:result) {
            JSONObject itemInfo = new JSONObject();
            itemInfo.put("fid", privilege.getFieldId());
            itemInfo.put("role", privilege.getRole());
            json.add(itemInfo);
        }
        return json;
    }

}
