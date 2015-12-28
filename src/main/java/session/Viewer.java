package session;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.ejb.Local;

/**
 * Created by ZY on 2015/12/9.
 */
@Local
public interface Viewer {
    void createUser(String name, String password);
    void createItem(String name, String description, Integer field);
    void createPrivilege(Integer uid, String name, Integer field, Integer role);
    JSONObject getItemInfo(Integer iid);
    JSONArray getUserBorrowInfo(Integer uid);
    JSONArray getUserPrivilegeInfo(Integer uid);
    JSONObject getAllItem();
    JSONObject getItemByField(Integer field);
}
