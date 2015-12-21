package entity;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by ZY on 2015/12/17.
 */

@NamedQueries({
})
@Entity
@Table(name = "book")
public class Item implements Serializable {

    private Integer id;      //
    private String itemName;
    private String author;
    private String description;
    private Integer availability;
    private String borrowUser;
    private Timestamp borrowTime;
    private String url;

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "BOOKNAME", length = 45)
    public String getItemname() {
        return itemName;
    }

    public void setItemname(String itemName) {
        this.itemName = itemName;
    }

    @Column(name = "AUTHOR", length = 45)
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    @Column(name = "BORROW_USER", length = 45)
    public String getBorrowUser() {
        return borrowUser;
    }

    public void setBorrowUser(String borrowUser) {
        this.borrowUser = borrowUser;
    }

    @Column(name = "BORROW_TIME")
    public Timestamp getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(Timestamp borrowTime) {
        this.borrowTime = borrowTime;
    }

    @Column(name = "URL", length = 45)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
