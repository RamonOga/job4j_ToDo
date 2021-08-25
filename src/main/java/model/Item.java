package model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="discription")
    private String description;

    @Column(name="created")
    private Timestamp created;

    @Column(name="done")
    private boolean done;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Category> categories = new ArrayList<>();;

    public Item() {
    }

    public Item(int id, String description, User user) {
        this.id = id;
        this.description = description;
        this.user = user;
        created = new Timestamp(System.currentTimeMillis());
    }

    public void addCategory(Category category) {
        categories.add(category);
    }

    public void addCategories(List<Category> categories) {
        this.categories.addAll(categories);
    }

    public List<Category> getCategories() {
        return categories;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public boolean isDone() {
        return done;
    }

    public User getUser() {
        return user;
    }

    public void setDoneTrue() {
        this.done = true;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", created=" + created +
                ", done=" + done +
                '}';
    }
}
