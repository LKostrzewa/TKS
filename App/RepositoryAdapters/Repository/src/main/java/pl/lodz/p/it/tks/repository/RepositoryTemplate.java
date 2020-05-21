package pl.lodz.p.it.tks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class RepositoryTemplate<T> {

    private Map<String, T> data = new HashMap<>();

    public T add (String key, T value){
        return data.putIfAbsent(key, value);
    }

    public T get(String key){
        return data.get(key);
    }

    public void update(String key, T value){
        data.replace(key, value);
    }

    public void delete(String key){
        data.remove(key);
    }

    public List<T> getAll(){
        return new ArrayList<>(data.values());
    }
}
