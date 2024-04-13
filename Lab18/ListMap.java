import java.util.ArrayList;
import java.util.List;

public class ListMap<K, V> implements Map<K, V> {
    private List<Map.Node<K, V>> list;

    public ListMap() {
        list = new ArrayList<>();
    }

    @Override
    public boolean containsKey(Object key) {
        for (Map.Node<K, V> node : list) {
            if (node.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        for (Map.Node<K, V> node : list) {
            if (node.getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public V get(Object key) {
        for (Map.Node<K, V> node : list) {
            if (node.getKey().equals(key)) {
                return node.getValue();
            }
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        for (Map.Node<K, V> node : list) {
            if (node.getKey().equals(key)) {
                V oldValue = node.getValue();
                node.setValue(value);
                return oldValue;
            }
        }
        list.add(new Map.Node<>(key, value));
        return null;
    }

    @Override
    public V remove(Object key) {
        for (Map.Node<K, V> node : list) {
            if (node.getKey().equals(key)) {
                V value = node.getValue();
                list.remove(node);
                return value;
            }
        }
        return null;
    }

    @Override
    public List<V> values() {
        List<V> values = new ArrayList<>();
        for (Map.Node<K, V> node : list) {
            values.add(node.getValue());
        }
        return values;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keys = new ListSet<>();
        for (Map.Node<K, V> node : list) {
            keys.add(node.getKey());
        }
        return keys;
    }
}
