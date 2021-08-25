package store;

import model.Category;

import java.util.List;

public interface CategoryStore {
    void add(Category category);

    List<Category> findAll();

    Category findById(String Id);


}
