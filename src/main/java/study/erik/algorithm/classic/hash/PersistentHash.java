package study.erik.algorithm.classic.hash;

import org.apache.commons.codec.digest.MurmurHash3;
import org.junit.Test;
import study.erik.algorithm.util.Generator;

import java.util.*;

/**
 * @author erik.wang
 * @date 2020-03-03 18:07
 * @description
 */
public class PersistentHash {

    private TreeMap<Integer, Node> treeMap = new TreeMap<>();

    public static class Node {
        private String name;
        private Set<String> keys = new HashSet<>();

        public Node(String name) {
            this.name = name;
        }

        public void addKey(String key) {
            keys.add(key);
        }

        public Set<String> getKeys() {
            return keys;
        }
    }

    public Set<Node> nodes = new HashSet<>();

    public void addNode(Node node) {
        nodes.add(node);
        adjustHashTable();
    }


    public void removeNode(Node node) {
        nodes.remove(node);
        adjustHashTable();
    }


    public PersistentHash() {
    }

    public void initHashTable() {
        for (Node node : nodes) {
            for (int i = 0; i < 160; i++) {
                String virtualNodeName = node.name + i;
                treeMap.put(MurmurHash3.hash32(virtualNodeName.getBytes()), node);
            }
        }
    }

    public void adjustHashTable() {
        initHashTable();
    }

    public Node getNode(String key) {
        SortedMap<Integer, Node> tailMap = treeMap.tailMap(key.hashCode());
        if (tailMap.isEmpty()) {
            return treeMap.get(treeMap.firstKey());
        }
        return tailMap.get(tailMap.firstKey());
    }


    @Test
    public void test_persistent_hash() {
        PersistentHash hash = new PersistentHash();

        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            nodes.add(new Node("node" + i));
        }

        for (Node node : nodes) {
            hash.addNode(node);
        }

        List<String> keys = Generator.getKeys();
        for (String key : keys) {
            hash.getNode(key).addKey(key);
        }

        for (Node node : nodes) {
            Set<String> mappedKeys = node.getKeys();
            System.out.println(mappedKeys.size());
        }


    }

    @Test
    public void test_hash_code() {
        for (int i = 0; i < 5; i++) {
            String key = "node" + i;
            System.out.println("hashCode=" + key.hashCode());
        }
    }


}
