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
    private Integer availability; //1��ʾ�ɳ��裬0��ʾ���ɳ���
    private String url;

    @Id
    @TableGenerator(
            name = "ITEM_ID", //��ID��������
            table = "hibernate_sequences", //���ݿ������
            pkColumnName = "sequence_name",//�ֶ���1 pk=primary key
            valueColumnName = "next_val", //�ֶ���2
            pkColumnValue = "item",//�ֶ�1ֵ
            allocationSize = 1//ÿ�����ӵ�ֵ...���鿴��Ƶhibernate 23
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
