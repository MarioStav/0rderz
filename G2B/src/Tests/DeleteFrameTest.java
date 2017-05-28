import GUI.Anmeldung.DeleteFrame;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Christoph on 28.05.2017.
 */
public class DeleteFrameTest {

    private DeleteFrame delete = new DeleteFrame();

    @Test
    public void inDB() throws Exception {

        assertFalse(delete.inDB("0123456789"));
        assertFalse(delete.inDB("abcdefghij"));
        assertFalse(delete.inDB("123701O180"));
        assertTrue(delete.inDB("1237010180"));

    }

}