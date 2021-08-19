package console;

import model.Item;
import model.User;
import store.HbrItemStore;
import store.ItemStore;


public class ItemConsoleTest {
    public static void main(String[] args) {
        ItemStore store = HbrItemStore.instOf();
      /*  add(store);
       done(store);
        findAll(store);
        delete(store);
        findById(store);*/
        findItemByUserId(store);


    }

    public static void findItemByUserId(ItemStore store) {
        System.out.println("find by id " + store.findByUserId("10"));
    }

    public static void add(ItemStore store) {
        store.add(new Item(0, "learn all Java", new User()));
    }

    public static void done(ItemStore store) {
        store.done("22");
    }

    public static void findAll(ItemStore store) {
        System.out.println("find All " + store.findAll());
    }

    public static void findById(ItemStore store) {
        System.out.println("find by id " + store.findById("555"));
    }

    public static void delete(ItemStore store) {
        store.delete("22");
    }
}
