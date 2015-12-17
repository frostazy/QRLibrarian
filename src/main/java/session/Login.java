package session;
import javax.ejb.Local;

/**
 * Created by ZY on 2015/12/9.
 */
@Local
public interface Login {
    public boolean execute();
}
