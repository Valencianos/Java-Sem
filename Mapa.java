package sem4;

public class Mapa<K, V> {
    int capacity = 16;
    public int size;
    Node<K, V>[] nodes = new Node[capacity];

    public V put(K key, V value) {
        Node<K, V> newNode = new Node<>(key, value);
        int index = getIndex(key);
        Node<K, V> currentNode = nodes[index];
        while (currentNode != null) {
            if (currentNode.key == key) {
                V tmp = currentNode.value;
                currentNode.value = value;
                return tmp;
            }
            currentNode = currentNode.nextNode;
        }
        newNode.nextNode = nodes[index];
        nodes[index] = newNode;
        return null;
    }

    private int getIndex(K key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    public V get(K key) {
        int index = getIndex(key);
        Node<K, V> node = nodes[index];
        while (node != null) {
            if (node.key.equals(key)) {
                return node.value;
            }
            node = node.nextNode;
        }
        return null;
    }

    public V remove(K key) {
        int index = getIndex(key);
        Node<K, V> currentNode = nodes[index];
        Node<K, V> previousNode = null;
        while (currentNode != null) {
            if (currentNode.key.equals(key)) {
                V tmp = currentNode.value;
                if (previousNode == null)
                    nodes[index] = currentNode.nextNode;
                else if (currentNode.nextNode == null)
                    previousNode.nextNode = null;
                else previousNode.nextNode = currentNode.nextNode;
                return tmp;
            }
            previousNode = currentNode;
            currentNode = currentNode.nextNode;
        }
        return null;
    }

    public boolean containsKey(K key) {
        int index = getIndex(key);
        Node<K, V> currentNode = nodes[index];
        while (currentNode != null) {
            if (currentNode.key.equals(key)) {
                return true;
            }
            currentNode = currentNode.nextNode;
        }
        return false;
    }

    public boolean containsValue(V value) {
        if (isEmpty()){
            return false;
        }
        for (Node<K, V> currentNode : nodes){
            while (currentNode != null) {
                if (currentNode.value == value) {
                    return true;
                }
                currentNode = currentNode.nextNode;
            }
        }
        return false;
    }


    public V replace(K key, V value) {
        int index = getIndex(key);
        Node<K, V> currentNode = nodes[index];
        Node<K, V> previousNode = null;
        while (currentNode != null) {
            if (currentNode.key.equals(key)) {
                V tmp = currentNode.value;
                if (previousNode == null) {
                    nodes[index] = currentNode.nextNode;
                }else if (currentNode.nextNode == null) {
                    previousNode.nextNode = null;
                }else{ previousNode.nextNode = currentNode.nextNode;
                }
                return tmp;
            }
            previousNode = currentNode;
            currentNode = currentNode.nextNode;
        }
        return null;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}