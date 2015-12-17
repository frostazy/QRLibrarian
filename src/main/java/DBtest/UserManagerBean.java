package DBtest;

/**
 * Created by ZY on 2015/12/17.
 */
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless(name = "UserManagerEJB")
public class UserManagerBean implements UserManager {
    @PersistenceContext(unitName="mysql")
    protected EntityManager em;

    public void addUser(User user) {
        em.persist(user);   //±£´æ¼ÇÂ¼
    }

}
