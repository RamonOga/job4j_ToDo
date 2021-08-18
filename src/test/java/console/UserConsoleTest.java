package console;

import model.Item;
import model.User;
import store.*;

public class UserConsoleTest {

    public static void main(String[] args) {
        UserStore store = HbrUserStore.instOf();
        /*add(store, "login1", "password1");
        add(store, "login2", "password2");
        findAll(store);*/
        System.out.println(findByName(store, "login33"));


    }

    public static void add(UserStore store, String login, String password) {
        store.add(new User( login, password));
    }


    public static void findAll(UserStore store) {
        System.out.println(store.findAll());
    }

    public static User findByName(UserStore store, String login) {
        return store.findByName(login);
    }


}
