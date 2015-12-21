package session;
import net.sf.json.JSONObject;

import javax.ejb.Local;

/**
 * Created by ZY on 2015/12/9.
 */
@Local
public interface ItemManager {
    public JSONObject getItemInfo(Integer iid);
    public JSONObject getAllItem();
    public boolean checkAvailability(Integer iid);
}
