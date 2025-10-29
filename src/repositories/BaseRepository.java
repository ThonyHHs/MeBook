package repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import models.Interfaces.Identification;

public abstract class BaseRepository<T extends Identification<ID>, ID> {
    private Map<ID, T> objectMap = new HashMap<>();

    public Optional<T> findById(ID id) {
        return Optional.ofNullable(objectMap.get(id));
    }

    public List<T> getAll() {
        return new ArrayList<>(objectMap.values());
    }

    public T save(T newObject) {
        objectMap.put(newObject.getId(), newObject);
        return newObject;
    }
    
    public void deleteById(ID id) {
        objectMap.remove(id);
    }
}
