import model.Item;
import store.HbrStore;
import store.Store;

import java.sql.Timestamp;

public class Runner {
    public static void main(String[] args) {
        Store store = HbrStore.instOf();
        add(store);
        done(store);
        findAll(store);
        findAll(store);
        findById(store);
        delete(store);
    }

    public static void add(Store store) {
        store.add(new Item(0, "desc1"));
    }

    public static void done(Store store) {
        store.done("1");
    }

    public static void findAll(Store store) {
        System.out.println("find All " + store.findAll());
    }

    public static void findById(Store store) {
        System.out.println("find by id " + store.findById("1"));
    }

    public static void delete(Store store) {
        store.delete("1");
    }
}
