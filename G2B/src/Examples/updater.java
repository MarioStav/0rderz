package Examples;
public class updater extends Thread {

    public void run() {
        System.out.println("thred");
        frame f = new frame();

        int p = f.progress;
        while (p != 100) {

            f.bar.setValue(p);
        }
    }
}
