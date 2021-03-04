package com.Lesson8;

public class HashTableImpl <K, V> implements HashTable<K, V>{
    private final BucketImpl<K, V>[] data;
    private int size;
    private final static int DEFAULT_CAPACITY=10;

    public HashTableImpl(int capacity) {
        this.data = new BucketImpl[capacity*2];
    }
    public HashTableImpl() {
        this(DEFAULT_CAPACITY);
    }

    private int hashFunc(K key){
        return key.hashCode()<0?(key.hashCode()*(-1))%data.length:key.hashCode()%data.length;
    }
    @Override
    public boolean put(K key, V value) {
        int index = hashFunc(key);
        BucketImpl currentBucket=data[index];

        if (data[index]==null){
            data[index]=new BucketImpl<>(key,value);
        } else {

            while (currentBucket.getNextBucket()!=null){
                if (data[index].getKey().equals(key)) {
                    data[index].setValue(value);
                    return true;
                }
                currentBucket=currentBucket.getNextBucket();
            }

            currentBucket.setNextBucket(new BucketImpl(key,value));
        }

    size++;
    return true;

    }

    @Override
    public V get(K key) {
        BucketImpl<K,V> currentBucket;
            currentBucket=data[hashFunc(key)];
            while (currentBucket!=null){
                if (currentBucket.getKey().equals(key)){
                    return currentBucket.getValue();
                }
                currentBucket=currentBucket.getNextBucket();
            }
        return null;
    }

    @Override
    public V remove(K key) {
        int index= hashFunc(key);
        BucketImpl<K,V> currentBucket=data[index];
        BucketImpl<K,V> previousBucket = null;

            while (currentBucket != null) {
                if (currentBucket.getKey().equals(key)) {
                    if (previousBucket == null && currentBucket.getNextBucket() == null) {
                        data[index] = null;
                    } else if (previousBucket == null) {
                        data[index] = currentBucket.getNextBucket();
                    } else if (currentBucket.getNextBucket() == null) {
                        previousBucket.setNextBucket(null);
                    } else {
                        previousBucket.setNextBucket(currentBucket.getNextBucket());
                    }
                    size--;
                    return currentBucket.getValue();
                }
                previousBucket = currentBucket;
                currentBucket = currentBucket.getNextBucket();
            }

            return null;

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public void display() {
    BucketImpl currentBucket= null;
        for (int i = 0; i < data.length; i++) {
            if (data[i] != null){
                System.out.print("Index " + i + ": ");
                currentBucket=data[i];
                while (currentBucket!=null){
                    System.out.print(currentBucket + " ");
                    currentBucket=currentBucket.getNextBucket();
                }
                System.out.println();
            }
        }
    }

    static class BucketImpl<K, V> implements Bucket<K,V>{
        private K key;
        private V value;
        private BucketImpl<K,V> nextBucket=null;

        public BucketImpl<K, V> getNextBucket() {
            return nextBucket;
        }

        public void setNextBucket(BucketImpl<K,V > bucket) {
            nextBucket= bucket;
        }

        public BucketImpl(K key, V value){
            this.key=key;
            this.value=value;
        }

        @Override
        public String toString() {
            return '{'+
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }

        @Override
        public K getKey() {
            return key;
        }


        @Override
        public V getValue() {
            return value;
        }

        @Override
        public void setValue(V value) {
            this.value=value;
        }

    }
}
