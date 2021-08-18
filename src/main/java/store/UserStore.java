package store;

import model.User;

import java.util.List;

public interface UserStore {

    void add(User user);

    List<User> findAll();

    User findByName(String name);

    User findById(String id);

}
