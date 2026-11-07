import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Dictionar<K, V> {
    private Map<K, V> dictionar;

    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock writeLock = readWriteLock.writeLock();
    private final Lock readLock = readWriteLock.readLock();

    public Dictionar() {
        this.dictionar = new HashMap<>();
    }

    public boolean verifyKey(K k) {
        readLock.lock();
        for (K key : dictionar.keySet()) {
            if (key == k) {
                readLock.unlock();
                return true;
            }

        }
        readLock.unlock();
        return false;
    }

    public void insertKey(K k) {
        writeLock.lock();
        if (!verifyKey(k))
            dictionar.put(k, null);
        writeLock.unlock();
    }

    public void pushValue(K k, V v) {
        writeLock.lock();
        if (verifyKey(k)){
            dictionar.put(k, v);
        }
        writeLock.unlock();
    }

    public void deleteValue(K k, V v){
        writeLock.lock();
        if (verifyKey(k)){
            dictionar.remove(k, v);
        }
        writeLock.unlock();
    }

    public Map<K,V> iterator(){
        return new HashMap<>(dictionar);
    }

}
