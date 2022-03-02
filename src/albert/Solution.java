package albert;

/*
**Problem Setup**

Design a cache thats supports versioning and 3 operations:

- set value: returns the current version
- get value: gets the most recently set value for a key
- get value at version: gets the value for a key at that revision

The version should start at 0, and increment by 1 for each 'set' operation on any key. Think of this like a Git commit hash.

The cache reads and writes should be fast

**Example Operations**

cache = Cache()
cache.set(key='key_1', value=70) -> 1
cache.set(key='key_2', value=50) -> 2
cache.set(key='key_1', value=60) -> 3
cache.set(key='key_2', value=10) -> 4

cache.get(key='key_1') -> 60
cache.get(key='key_1', version=1) -> 70
cache.get(key='key_1', version=2) -> 70
cache.get(key='key_2', version=3) -> 50

max version <= current_version
no version ==> the largest one

1) version -- global varibale
2) HashMap  key : value  value ==> version + value there
3) value:  PQ (max heap)
key + version : value
key1 : PQ<version : value>
key1
*/
import java.util.*;

class Item {
    int version;
    int value;
    Item(int version, int value) {
        this.version = version;
        this.value = value;
    }
}

class Cache {

    int gVersion = 0;
    Map<Integer, PriorityQueue<Item>> map;

    Cache() {
        map = new HashMap<>();

    }

    public int set(int key, int value) {
        gVersion++;
        if (!map.containsKey(key)) {
            map.put(key, new PriorityQueue<Item>((a, b) -> (b.version - a.version)));
        }

        Item item = new Item(gVersion, value);
        map.get(key).offer(item);
        return gVersion;
    }

    public int get(int key, int version) {
        if (!map.containsKey(key)) {
            return -1;
        }

        PriorityQueue<Item> pq = map.get(key);

        if (version == -1 ) {
            Item item = pq.peek();
            return  item.value;
        }

        if (version > gVersion) {
            return -1;
        }

        while (!pq.isEmpty()) {

            int topVersion = pq.peek().version; //
            // key2 :  {4: 10};  {2: 50} ;
            // cache.get(key='key_2', version=3) -> 50   ==> version 3  --> 50
            while (version < topVersion) {
                pq.poll(); //pq: {2: 50}
                if (pq.isEmpty()) {
                    return -1;
                }
                topVersion = pq.peek().version;
            }


            return pq.peek().value;


        }

        return -1;


    }

}

class Solution {
//   cache = Cache()
// cache.set(key='key_1', value=70) -> 1
// cache.set(key='key_2', value=50) -> 2
// cache.set(key='key_1', value=60) -> 3
// cache.set(key='key_2', value=10) -> 4

    // cache.get(key='key_1') -> 60
// cache.get(key='key_1', version=1) -> 70
// cache.get(key='key_1', version=2) -> 70
// cache.get(key='key_2', version=3) -> 50
    public static void main(String[] args) {

        Cache cache = new Cache();
        System.out.println(cache.set(1, 70));
        System.out.println(cache.set(2, 50));
        System.out.println(cache.set(1, 60));
        System.out.println(cache.set(2, 10));

        System.out.println(cache.get(1, -1));
        System.out.println(cache.get(1,1));
        System.out.println(cache.get(1, 2));
        System.out.println(cache.get(1, 1));
        System.out.println(cache.get(2, 3));
        System.out.println(cache.get(2, 4));

    }
}



