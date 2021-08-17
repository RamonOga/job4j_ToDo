package store;

import model.Item;

import java.util.List;

public interface ItemStore {
    boolean add(Item item);

    boolean delete(String id);

    void done(String id);

    Item findById(String id);

    List<Item> findAll();

}
