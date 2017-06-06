package utils;

import java.util.*;

public class MyCache<K,V> {
    private static final int MAXIMUM_CAPACITY = 100;
    private LinkedList<K> keyList = new LinkedList<>();

    private HashMap<K,V> hashMap = new HashMap<>(MAXIMUM_CAPACITY);

    public int size() {
        return hashMap.size();
    }

    public boolean isEmpty() {
        return hashMap.isEmpty();
    }

    public boolean containsKey(K key) {
        return hashMap.containsKey(key);
    }

    public boolean containsValue(V value) {
        return hashMap.containsValue(value);
    }

    public V get(K key) {
        V value = hashMap.get(key);
        if (value != null) {
            keyList.remove(key);
            keyList.addFirst(key);
        }
        return value;
    }

    public void put(K key, V value) {
        if (size() >= MAXIMUM_CAPACITY) {
            K keyToRemove = keyList.removeLast();
            hashMap.remove(keyToRemove);
        }
        keyList.addFirst(key);
        hashMap.put(key, value);
    }

    public V remove(K key) {
        keyList.remove(key);
        return hashMap.remove(key);
    }

    public void clear() {
        keyList.clear();
        hashMap.clear();
    }
}
