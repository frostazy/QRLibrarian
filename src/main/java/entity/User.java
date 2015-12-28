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
            name = "USER_ID", //��ID��������
            table = "hibernate_sequences", //���ݿ������
            pkColumnName = "sequence_name",//�ֶ���1 pk=primary key
            valueColumnName = "next_val", //�ֶ���2
            pkColumnValue = "user",//�ֶ�1ֵ
            allocationSize = 1//ÿ�����ӵ�ֵ...���鿴��Ƶhibernate 23
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
