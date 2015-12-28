package session;
import net.sf.json.JSONObject;

import javax.ejb.Local;

/**
 * Created by ZY on 2015/12/9.
 */
@Local
public interface ActiveUser {
    Integer getUid();
    JSONObject login(String name, String password);
    JSONObject borrowItem(Integer iid);
    JSONObject returnItem(Integer iid);
    JSONObject createItem(Integer fid, String itemName, String description);
    JSONObject changeItem(Integer iid, String itemName, String description);
    JSONObject removeItem(Integer iid);
    JSONObject createPrivilege(String userName, Integer fid, Integer role);
    JSONObject removePrivilege(String userName, Integer fid);
}
