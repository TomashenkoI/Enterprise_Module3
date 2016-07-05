package Main;

/**
 * Created by 7 on 04.07.2016.
 */
public class Runner {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new Person(i)).start();
        }
    }
}
