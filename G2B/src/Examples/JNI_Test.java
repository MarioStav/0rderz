package Examples;

/**
 * Created by Christoph on 10.05.2017.
 */
public class JNI_Test {

    native static String  print();

    static {

        System.loadLibrary("c_print");

    }

    public static void main(String[] args) {

        System.out.println(print());

    }

}
