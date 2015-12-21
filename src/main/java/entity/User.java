package entity;
import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ZY on 2015/12/17.
 */

@NamedQueries({
        @NamedQuery(name = "findUserByName", query = "from User where name = ?1")
})
@Entity
@Table(name = "user")
public class User implements Serializable {
    private Integer uid;      //
    private String name;
    private String password;

    @Id
    @GeneratedValue
    public Integer getId() {
        return uid;
    }
    public void setId(Integer uid) {
        this.uid = uid;
    }

    @Column(name = "NAME", length = 45)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column(name="PASSWORD",length = 45)
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}
