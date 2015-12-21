package session;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.ejb.Local;

/**
 * Created by ZY on 2015/12/9.
 */
@Local
public interface UserManager {
    public JSONObject login(String name, String password);
    public JSONArray getBorrowInfo(Integer uid);
}