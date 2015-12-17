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
public class LoginBean implements Login{
    @PersistenceContext(unitName="mysql")
    protected EntityManager em;

    public LoginBean() {
    }

    public JSONObject execute(String name, String password) {
        Query query = em.createNamedQuery("getIdByName");
        query.setParameter(1, name);
        List<User> result = (List<User>)query.getResultList();
        JSONObject json = new JSONObject();
        if(!result.isEmpty()) {
            System.out.println(result.get(0).getId());
            json.put("status", 1);
        } else {
            json.put("status", 0);
        }
        return json;
    }
}
