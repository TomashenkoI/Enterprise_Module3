package Main;

/**
 * Created by 7 on 04.07.2016.
 */
public class SemaphoreImpl implements Semaphore {
    private final Object lock = new Object();

    public int freePlaces;

    public SemaphoreImpl(int freePlaces) {
        this.freePlaces = freePlaces;
    }

    public void acquire() {
        synchronized (lock) {
            if (freePlaces > 0) {
                freePlaces--;
            } else {
                while (freePlaces == 0) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void acquire(int permits) {
        synchronized (lock) {
            if (freePlaces > permits) {
                freePlaces -= permits;
            } else {
                while (freePlaces < permits) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                freePlaces -= permits;
                lock.notifyAll();
            }
        }
    }

    public void release() {
        synchronized (lock) {
            freePlaces++;
            lock.notifyAll();
        }
    }

    public void release(int permits) {
        synchronized (lock) {
            freePlaces += permits;
            lock.notifyAll();
        }
    }

    public int getAvailablePermits() {
        return freePlaces;
    }

}


