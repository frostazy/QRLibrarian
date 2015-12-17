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
@Stateless(name = "BorrowEJB")
public class BorrowBean implements Borrow{
    @PersistenceContext(unitName="mysql")
    protected EntityManager em;

    public BorrowBean() {
    }

    public JSONObject execute(Integer userId, Integer bookId) {
        JSONObject json = new JSONObject();
        return json;
    }
}
