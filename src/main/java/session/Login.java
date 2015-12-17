package session;
import javax.ejb.Local;

/**
 * Created by ZY on 2015/12/9.
 */
@Local
public interface Login {
    public int add(int x,int y);
    public int substact(int x,int y);
}
