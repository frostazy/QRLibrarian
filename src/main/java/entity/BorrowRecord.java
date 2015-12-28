package entity;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by ZY on 2015/12/17.
 */

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NamedQueries({
//        @NamedQuery(name = "findBorrowedItemByField", query = "from BorrowRecord where fieldId = ?1"),
        @NamedQuery(name = "findBorrowedItemByUserId", query = "from BorrowRecord where userId = ?1"),
        @NamedQuery(name = "findBorrowedItemByUserName", query = "from BorrowRecord where userName = ?1"),
        @NamedQuery(name = "findByBorrowedItemId", query = "from BorrowRecord where itemId = ?1"),
        @NamedQuery(name = "findByBorrowedItemName", query = "from BorrowRecord where itemName = ?1"),
})
@Entity
@Table(name = "borrowrecord")
public class BorrowRecord implements Serializable {

    private Integer id;      //
    private Integer fieldId;
    private Integer userId;
    private String userName;
    private Integer itemId;
    private String itemName;
    private Timestamp borrowTime;

    @Id
    @TableGenerator(
            name = "BORROW_ID", //表ID策略名字
            table = "hibernate_sequences", //数据库表名字
            pkColumnName = "sequence_name",//字段名1 pk=primary key
            valueColumnName = "next_val", //字段名2
            pkColumnValue = "borrow",//字段1值
            allocationSize = 1//每次增加的值...详情看视频hibernate 23
    )
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "BORROW_ID")
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "FIELD_ID")
    public Integer getFieldId() {
        return fieldId;
    }

    public void setFieldId(Integer fieldId) {
        this.fieldId = fieldId;
    }

    @Column(name = "USER_ID")
    public Integer getUserId() { return userId; }

    public void setUserId(Integer userId) { this.userId = userId; }

    @Column(name = "USER_NAME", length = 45)
    public String getUserName() { return userName; }

    public void setUserName(String userName) { this.userName = userName; }

    @Column(name = "ITEM_ID")
    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    @Column(name = "ITEM_NAME", length = 45)
    public String getItemName() { return itemName; }

    public void setItemName(String itemName) { this.itemName = itemName; }

    @Column(name = "BORROW_TIME")
    public Timestamp getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(Timestamp borrowTime) {
        this.borrowTime = borrowTime;
    }

}
