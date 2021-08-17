package tp.persistence;

import tp.model.utils.WithId;

import java.util.Collection;
import java.util.Optional;

public interface BasicDAO<T extends WithId> {

    public Collection<T> findAll();
    public Optional<T> findById(String id);
    public Optional<T> update(T object);
    public Optional<T> insert(T object);
    public Optional<T> deleteById(String id);

}
