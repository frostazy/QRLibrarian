package session;
import net.sf.json.JSONObject;

import javax.ejb.Local;

/**
 * Created by ZY on 2015/12/9.
 */
@Local
public interface ActiveUser {
    public void init(Integer uid);
    public JSONObject borrowItem(Integer iid);
    public Integer getUid();
}
