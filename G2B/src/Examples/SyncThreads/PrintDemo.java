package Examples.SyncThreads;

/**
 * Created by Christoph on 12.05.2017.
 */
public class PrintDemo {

    public void printCount() {
        try {
            for(int i = 5; i > 0; i--) {
                System.out.println("Counter   ---   "  + i );
            }
        }catch (Exception e) {
            System.out.println("Thread  interrupted.");
        }
    }
}

