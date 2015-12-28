package session;

import entity.BorrowRecord;
import entity.Item;
import entity.Privilege;
import entity.User;
import net.sf.json.JSONObject;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


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

    public JSONObject login(String name, String password) {
        Query query = em.createNamedQuery("findUserByName");
        query.setParameter(1, name);
        List<User> result = (List<User>)query.getResultList();
        JSONObject json = new JSONObject();
        if(!result.isEmpty()) {
            User user = result.get(0);
            if(password.equals(user.getPassword())) {
                this.uid = user.getId();
                this.name = name;
                json.put("status", "1");  //login successful
                json.put("uid", this.uid);
                json.put("name", this.name);
            } else {
                json.put("status", -1);  //wrong password
            }
        } else {
            json.put("status", 0);  //user not exist
        }
        return json;
    }

    public JSONObject borrowItem(Integer iid) {
        Item item = em.find(Item.class, iid);
        JSONObject json = new JSONObject();
        if(item == null) {
            json.put("status", 0); //����Ʒ������
        } else if(item.getAvailability() == Item.AVAILABLE &&
                checkPrivilege(item.getField(), Privilege.USER)){
            BorrowRecord borrowRecord = new BorrowRecord();
            borrowRecord.setUserId(uid);
            borrowRecord.setUserName(name);
            borrowRecord.setItemId(iid);
            borrowRecord.setItemName(item.getItemName());
            borrowRecord.setFieldId(item.getField());
            borrowRecord.setBorrowTime(new Timestamp(new Date().getTime()));
            em.persist(borrowRecord);
            item.setAvailability(Item.NOT_AVAILABLE);
            em.persist(item);
            json.put("status", 1); //���ĳɹ�
        } else {
            json.put("status", 0); //����Ʒ�ѱ����Ļ�û��Ȩ��
        }
        json.put("uid", uid);
        json.put("name", name);
        return json;
    }

    public JSONObject returnItem(Integer iid) {
        Item item = em.find(Item.class, iid);
        JSONObject json = new JSONObject();
        if(item == null) {
            json.put("status", 0); //����Ʒ������
        } else if(item.getAvailability() == Item.NOT_AVAILABLE){
            Query query = em.createNamedQuery("findByBorrowedItemId");
            query.setParameter(1, iid);
            List<BorrowRecord> result = (List<BorrowRecord>)query.getResultList();
            if(result.isEmpty()) {
                json.put("status", -1); //���ݿ��¼����,û�и���Ʒ��Ӧ�Ľ��ļ�¼��
            } else {
                BorrowRecord borrowRecord = result.get(0);
                if(borrowRecord.getUserId() != uid) {
                    json.put("status", -1); //���ݿ��¼����,û����Ʒ�Ľ��ļ�¼���û�������
                } else {
                    em.remove(borrowRecord);
                    item.setAvailability(Item.AVAILABLE);
                    em.persist(item);
                    json.put("status", 1); //�黹�ɹ�
                }
            }
        } else {
            json.put("status", 0); //����Ʒδ������
        }
        json.put("uid", uid);
        json.put("name", name);
        return json;
    }

    public JSONObject createItem(Integer fid, String itemName, String description) {
        JSONObject json = new JSONObject();
        if(checkPrivilege(fid, Privilege.LIBRARIAN)) {
            Item item = new Item();
            item.setItemName(itemName);
            item.setDescription(description);
            item.setField(fid);
            item.setUrl("");
            item.setAvailability(Item.AVAILABLE);
            em.persist(item);
            json.put("status", 1);
        } else {
            json.put("status", -1); //������ƷȨ�޲���
        }
        return json;
    }

    public JSONObject changeItem(Integer iid, String itemName, String description) {
        JSONObject json = new JSONObject();
        Item item = em.find(Item.class, iid);
        if(item == null){
            json.put("status", 0);  //��Ʒ������
        } else if (checkPrivilege(item.getField(), Privilege.LIBRARIAN)) {
            item.setItemName(itemName);
            item.setDescription(description);
            item.setUrl("");
            em.persist(item);
            json.put("status", 1);
        } else {
            json.put("status", -1); //Ȩ�޲���
        }
        return json;
    }

    public JSONObject removeItem(Integer iid) {
        JSONObject json = new JSONObject();
        Item item = em.find(Item.class, iid);
        if(item == null) {
            json.put("status", 0); //����Ʒ������
        } else if(checkPrivilege(item.getField(), Privilege.LIBRARIAN)) {
            em.remove(item);
            json.put("status", 1);
        } else {
            json.put("status", -1); //Ȩ�޲���
        }
        return json;
    }

    public JSONObject createPrivilege(String userName, Integer fid, Integer role) {
        JSONObject json = new JSONObject();
        Query query = em.createNamedQuery("findUserByName");
        query.setParameter(1, userName);
        List<User> result = (List<User>)query.getResultList();
        if(!result.isEmpty()) {
            User user = result.get(0);
            if(checkPrivilege(fid, Privilege.OWNER) && (user.getId() != uid)) {
                query = em.createNamedQuery("findPrivilegeByAll");
                query.setParameter("uid", user.getId());
                query.setParameter("fid", fid);
                List<Privilege> result2 = (List<Privilege>)query.getResultList();
                if(!result2.isEmpty()) {
                    result2.get(0).setRole(role); //����Ȩ��
                } else {
                    Privilege privilege = new Privilege();
                    privilege.setUserId(user.getId());
                    privilege.setUserName(user.getName());
                    privilege.setFieldId(fid);
                    privilege.setRole(role);
                    em.persist(privilege); //������Ȩ��
                }
                json.put("status", 1);
            } else {
                json.put("status", -1);  //Ȩ�޲���
            }
        } else {
            json.put("status", 0);  //�û�������
        }
        return null;
    }

    public JSONObject removePrivilege(String userName, Integer fid) {
        JSONObject json = new JSONObject();
        Query query = em.createNamedQuery("findUserByName");
        query.setParameter(1, userName);
        List<User> result = (List<User>)query.getResultList();
        if(!result.isEmpty()) {
            User user = result.get(0);
            if(checkPrivilege(fid, Privilege.OWNER) && (user.getId() != uid)) {
                query = em.createNamedQuery("findPrivilegeByAll");
                query.setParameter("uid", user.getId());
                query.setParameter("fid", fid);
                List<Privilege> result2 = (List<Privilege>)query.getResultList();
                if(!result.isEmpty()) {
                    em.remove(result2.get(0)); //ɾ��Ȩ��
                    json.put("status", 1);
                } else {
                    json.put("status", 0); //Ȩ�޲�����
                }
            } else {
                json.put("status", -1);  //Ȩ�޲���
            }
        } else {
            json.put("status", 0);  //�û�������
        }
        return null;
    }

    private boolean checkPrivilege(Integer fid, Integer role) {
        Query query = em.createNamedQuery("findUserByPrivilege");
        query.setParameter(1, fid);
        query.setParameter(2, role);
        List<Integer> result = (List<Integer>)query.getResultList();
        return result.contains(uid);
    }
}
