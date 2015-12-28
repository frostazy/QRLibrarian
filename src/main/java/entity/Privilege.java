package entity;
import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ZY on 2015/12/17.
 */

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NamedQueries({
        @NamedQuery(name = "findPrivilegeByFieldId", query = "from Privilege where fieldId = ?1"),
        @NamedQuery(name = "findPrivilegeByUserId", query = "from Privilege where userId = ?1"),
        @NamedQuery(name = "findPrivilegeByAll", query = "from Privilege where userId = :uid and fieldId = :fid"),
        @NamedQuery(name = "findUserByPrivilege", query = "select userId from Privilege where fieldId = ?1 and role >= ?2"),
        @NamedQuery(name = "findUserByPrivilegeStrict", query = "select userId from Privilege where fieldId = ?1 and role = ?2"),
})
@Entity
@Table(name = "privilege")
public class Privilege implements Serializable {

    public final static Integer USER = 1;
    public final static Integer LIBRARIAN = 2;
    public final static Integer OWNER = 3;
    private Integer id;      //
    private Integer fieldId;
    private Integer userId;
    private String userName;
    private Integer role;

    @Id
    @TableGenerator(
            name = "PRIVILEGE_ID", //表ID策略名字
            table = "hibernate_sequences", //数据库表名字
            pkColumnName = "sequence_name",//字段名1 pk=primary key
            valueColumnName = "next_val", //字段名2
            pkColumnValue = "privilege",//字段1值
            allocationSize = 1//每次增加的值...详情看视频hibernate 23
    )
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "PRIVILEGE_ID")
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "USER_ID")
    public Integer getUserId() { return userId; }

    public void setUserId(Integer userId) { this.userId = userId; }

    @Column(name = "USER_NAME", length = 45)
    public String getUserName() { return userName; }

    public void setUserName(String userName) { this.userName = userName; }

    @Column(name = "FIELD_ID")
    public Integer getFieldId() {
        return fieldId;
    }

    public void setFieldId(Integer fieldId) {
        this.fieldId = fieldId;
    }

    @Column(name = "ROLE")
    public Integer getRole() { return role; }

    public void setRole(Integer role) { this.role = role; }

}
