package Main;

/**
 * Created by 7 on 05.07.2016.
 */
public class Person implements Runnable {
    SemaphoreImpl semaphore = new SemaphoreImpl();
    private int personNumber;

    public Person(int personNumber) {
        this.personNumber = personNumber;
    }

    @Override
    public void run() {
        System.out.println("Person #" + personNumber + " is going to toilet");
        try {
            semaphore.acquire();
            System.out.println("Person #" + personNumber + " has entered to the toilet");
            semaphore.release();
            System.out.println("Person #" + personNumber + " has left the toilet");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
