package PP_1C_2014;

public class MyCustomMap<K,V> implements CustomMap<K,V> {
    Node<K,V> header = null;
    Node<K,V> mostAcc = null;
    Node<K,V> leastAcc = null;

    @Override
    public V get(K key) {
        if (key == null)
            throw new RuntimeException("ERROR");
        if (header == null)
            return null;
        else if (header.getKey().equals(key)) {
            header.setCount(header.count+1);
            if(mostAcc.count < header.count)
                mostAcc = header;
            return header.getValue();
        }
        return getR(header.getNext(), key);
    }

    private V getR(Node<K, V> node, K key) {
        if (node == null) {
            throw new RuntimeException("KEY NOT FOUND");
        }
        if (node.getKey().equals(key)) {
            node.setCount(node.count+1);
            if(mostAcc.count < node.count)
                mostAcc = node;
            return node.getValue();
        }
        return getR(node.getNext(), key);
    }

    @Override
    public void put(K key, V value) {
        if (key == null || value == null)
            throw new IllegalArgumentException("ILLEGAL");
        if (header == null){
            header = new Node<>(key, value);
            header.setCount(header.getCount()+1);
            mostAcc = header;
            leastAcc = header;
            return;
        }
        putR(header, key, value);
    }

    private void putR(Node<K, V> node, K key, V value) {
        if (node == null) {
            node = new Node<>(key, value);
            node.setCount(node.count+1);
            if(mostAcc.count < node.count)
                mostAcc = node;
        } else if (node.getKey().equals(key)) {
            node.setValue(value);
            node.setCount(node.count+1);
            if (mostAcc.count < node.count)
                mostAcc = node;
        } else if (node.getNext() == null){
            node.setNext(new Node<>(key, value));
            node.getNext().setCount(1);
            if (mostAcc.count < node.getNext().count)
                mostAcc = node.getNext();
        }
        else {
            putR(node.next, key, value);
        }
        return;
    }

    @Override
    public K getMostAccessed() {
        return mostAcc.getKey();
    }

    @Override
    public void removeLeastAccessed() {
        leastAcc = null;
        return;
    }

    private class Node<K, V> {
        private K key;
        private V value;
        private int count = 0;
        private Node<K,V> next = null;

        public Node<K, V> getNext() {
            return next;
        }

        public void setNext(Node<K, V> next) {
            this.next = next;
        }

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public K getKey() {
            return key;
        }
    }
}
