package org.example.repositories;

import java.util.List;

public interface Repository<TModel> {
    TModel getById(int id);
    List<TModel> getAll();
    Integer save(TModel t);
    void update(TModel t);
    void delete(int id);
}
