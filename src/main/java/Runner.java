import model.Item;
import store.HbrItemStore;
import store.ItemStore;

public class Runner {
    public static void main(String[] args) {
        ItemStore store = HbrItemStore.instOf();
        /*add(store);*/
        done(store);
       /* findAll(store);
        findAll(store);
        findById(store);
        delete(store);*/
    }

    public static void add(ItemStore store) {
        store.add(new Item(0, "desc33"));
    }

    public static void done(ItemStore store) {
        store.done("18");
    }

    public static void findAll(ItemStore store) {
        System.out.println("find All " + store.findAll());
    }

    public static void findById(ItemStore store) {
        System.out.println("find by id " + store.findById("1"));
    }

    public static void delete(ItemStore store) {
        store.delete("1");
    }
}
