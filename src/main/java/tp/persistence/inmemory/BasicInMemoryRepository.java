package tp.persistence.inmemory;

import tp.model.utils.WithId;
import tp.persistence.BasicDAO;

import java.util.*;

public class BasicInMemoryRepository<T extends WithId> implements BasicDAO<T> {

    private HashMap<String, T> repo = new HashMap<>();

    public void init(HashMap<String, T> values) {
        this.repo.putAll(values);
    }

    @Override
    public Collection<T> findAll() {
        return repo.values();
    }

    @Override
    public Optional<T> findById(String id) {
        return Optional.ofNullable(repo.get(id));
    }

    public Optional<T> update(T object) {
        return insert(object);
    }

    @Override
    public Optional<T> insert(T object) {
        return Optional.ofNullable(repo.put(object.getId().toString(), object));
    }

    @Override
    public Optional<T> deleteById(String id) {
        return Optional.ofNullable(repo.remove(id));
    }

}
