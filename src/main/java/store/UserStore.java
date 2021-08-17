package store;

import model.User;

public interface UserStore {

    void add(User user);

    User findByName(String name);

}
