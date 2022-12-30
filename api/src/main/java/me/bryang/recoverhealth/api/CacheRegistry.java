package me.bryang.recoverhealth.api;

import java.util.List;

public interface CacheRegistry<V> {

    void add(V value);

    void remove(int id);

    List<V> get();
}
