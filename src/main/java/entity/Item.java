package entity;
import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ZY on 2015/12/17.
 */

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NamedQueries({
        @NamedQuery(name = "findItemByField", query = "from Item where field = ?1"),
        @NamedQuery(name = "findAllItem", query = "from Item"),
})
@Entity
@Table(name = "item")
public class Item implements Serializable {

    public final static Integer AVAILABLE = 1;
    public final static Integer NOT_AVAILABLE = 0;
    private Integer id;      //
    private String itemName;
    private Integer field;
    private String description;
    private Integer availability; //1表示可出借，0表示不可出借
    private String url;

    @Id
    @TableGenerator(
            name = "ITEM_ID", //表ID策略名字
            table = "hibernate_sequences", //数据库表名字
            pkColumnName = "sequence_name",//字段名1 pk=primary key
            valueColumnName = "next_val", //字段名2
            pkColumnValue = "item",//字段1值
            allocationSize = 1//每次增加的值...详情看视频hibernate 23
    )
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ITEM_ID")
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "ITEMNAME", length = 45)
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Column(name = "OWNER")
    public Integer getField() {
        return field;
    }

    public void setField(Integer field) {
        this.field = field;
    }

    @Column(name = "DESCRIPTION", length = 450)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "AVAILABILITY")
    public Integer getAvailability() {
        return availability;
    }

    public void setAvailability(Integer availability) {
        this.availability = availability;
    }

    @Column(name = "URL", length = 45)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
