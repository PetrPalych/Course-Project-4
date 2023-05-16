package kg.ash.hospital.services.interfaces;

import java.util.List;

public interface BaseService <T> {

    T save(T t);

    T find(int id);

    List<T> findAll();

    void delete(int id);

}
