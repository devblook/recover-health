package me.bryang.recoverhealth.api;

import java.util.ArrayList;
import java.util.List;

public class CacheRegistryImpl<V> implements CacheRegistry<V> {

    private final List<V> set = new ArrayList<>();


    @Override
    public void add(V value) {
        set.add(value);
    }

    @Override
    public void remove(int id) {
        set.remove(id);

    }

    @Override
    public List<V> get() {
        return set;
    }
}
