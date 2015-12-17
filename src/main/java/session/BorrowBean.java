package session;

import entity.User;
import net.sf.json.JSONObject;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


/**
 * Created by ZY on 2015/12/17.
 */
@Stateless(name = "LoginEJB")
public class BorrowBean implements Login{
    @PersistenceContext(unitName="mysql")
    protected EntityManager em;

    public BorrowBean() {
    }

    public JSONObject execute(String name, String password) {
        Query query = em.createNamedQuery("getIdByName");
        query.setParameter(1, name);
        List<User> result = (List<User>)query.getResultList();
        JSONObject json = new JSONObject();
        if(!result.isEmpty()) {
            User user = result.get(0);
            System.out.println(user.getId());
            if(password.equals(user.getPassword())) {
                json.put("status", "1");  //login successful
                json.put("name", user.getName());
            } else {
                json.put("status", -1);  //wrong password
            }
        } else {
            json.put("status", 0);  //user not exist
        }
        return json;
    }
}
