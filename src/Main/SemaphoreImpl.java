package Main;

/**
 * Created by 7 on 04.07.2016.
 */
public class SemaphoreImpl implements Semaphore {
    private final Object lock = new Object();

    public static volatile int freePlaces = PublicToilet.getNumberOfSeats();

    public static int getFreePlaces() {
        return freePlaces;
    }

    public static void setFreePlaces(int freePlaces) {
        SemaphoreImpl.freePlaces = freePlaces;
    }

    public void acquire() throws InterruptedException {
        synchronized (lock) {
            if (getFreePlaces() > 0) {
                setFreePlaces(freePlaces -= 1);
            } else {
                while (getFreePlaces() == 0) {
                    lock.wait();
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

    public void release() throws InterruptedException {
        synchronized (lock) {
            freePlaces++;
            lock.notifyAll();
        }
    }

    public void release(int permits) {
        synchronized (lock) {
            freePlaces += permits;
            lock.notify();
        }
    }

    public int getAvailablePermits() {
        return freePlaces;
    }

}


