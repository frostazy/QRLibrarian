package entity;
import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ZY on 2015/12/17.
 */

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
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
    @TableGenerator(
            name = "USER_ID", //表ID策略名字
            table = "hibernate_sequences", //数据库表名字
            pkColumnName = "sequence_name",//字段名1 pk=primary key
            valueColumnName = "next_val", //字段名2
            pkColumnValue = "user",//字段1值
            allocationSize = 1//每次增加的值...详情看视频hibernate 23
    )
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "USER_ID")
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
