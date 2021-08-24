package trash.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "humans")
public class Human {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "info")
    private String info;

    @Column(name = "created")
    private Calendar created;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, )
    private List<City> cities = new ArrayList<>();

    public void addCity(City city) {
        this.cities.add(city);
    }

    public static Human of(String info) {
        Human human = new Human();
        human.info = info;
        human.created = Calendar.getInstance();
        return human;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Calendar getCreated() {
        return created;
    }

    public void setCreated(Calendar created) {
        this.created = created;
    }

    public List<City> getCities() {
        return cities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Human human = (Human) o;
        return id == human.id &&
                Objects.equals(info, human.info) &&
                Objects.equals(created, human.created) &&
                Objects.equals(cities, human.cities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, info, created, cities);
    }

    @Override
    public String toString() {
        return "Human{" +
                "id=" + id +
                ", info='" + info + '\'' +
                ", created=" + created +
                ", cities=" + cities +
                '}';
    }
}