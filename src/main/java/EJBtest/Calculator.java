package EJBtest;
import javax.ejb.Remote;

/**
 * Created by ZY on 2015/12/9.
 */
@Remote
public interface Calculator {
    public int add(int x,int y);
    public int substact(int x,int y);
}
