package console;

import model.Category;
import model.Item;
import model.User;
import store.*;


public class ItemConsoleTest {
    public static void main(String[] args) {
        ItemStore itemStore = HbrItemStore.instOf();
      //  add(itemStore);
     //  done(itemStore);
      //  findAll(itemStore);
       // delete(itemStore);
      //  findById(itemStore);
        findItemByUserId(itemStore);


    }

    public static void findItemByUserId(ItemStore store) {
        System.out.println("find by id " + store.findByUserId("4"));
    }

    public static void add(ItemStore store) {
        User user = HbrUserStore.instOf().findById("4");
        Item item = new Item(0, "learn all Java!!111oneOneOne", user);
        Category category = HbrCategoryStore.instOf().findById("1");
        item.addCategory(category);
        store.add(item);
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
