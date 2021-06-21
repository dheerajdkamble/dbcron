package co.dheeraj.dbcron.service;

import java.util.List;
import java.util.Optional;

public interface CrudService<T> {

    Optional<T> get(Long id);

    List<T> getAll();

    T save(T t);

    T update(T t);

    void delete(Long id);
}
