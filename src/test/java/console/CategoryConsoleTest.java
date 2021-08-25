package console;

import model.Category;
import model.Item;
import model.User;
import store.CategoryStore;
import store.HbrCategoryStore;
import store.HbrItemStore;
import store.ItemStore;

public class CategoryConsoleTest {
    public static void main(String[] args) {
        CategoryStore store = HbrCategoryStore.instOf();
        /*add(store);*/
        //findById(store);
        findAll(store);




    }



    public static void add(CategoryStore store) {
        store.add(new Category(0,"Java"));
        store.add(new Category(0,"Java script"));
    }


    public static void findAll(CategoryStore store) {
        System.out.println("find All " + store.findAll());
    }

    public static void findById(CategoryStore store) {
        System.out.println("find by id " + store.findById("1"));
    }

}
