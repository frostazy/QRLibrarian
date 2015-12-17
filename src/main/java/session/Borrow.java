package session;
import net.sf.json.JSONObject;

import javax.ejb.Local;

/**
 * Created by ZY on 2015/12/9.
 */
@Local
public interface Borrow {
    public JSONObject execute(Integer userId, Integer bookId);
}
