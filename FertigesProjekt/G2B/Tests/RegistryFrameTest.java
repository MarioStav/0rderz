import SignIn.RegistryFrame;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Christoph on 26.05.2017.
 */
public class RegistryFrameTest {

    private RegistryFrame reg = new RegistryFrame();
    String test_str1 = "0123456789";    //nur Zahlen, falsche SVNr
    String test_str2 = "abcdefghij";    //nur Buchstaben
    String test_str3 = "123701O180";    //großes O inhalten
    String test_str4 = "1237010180";    //nur Zahlen, richtige SVNr
    String test_str5 = "abcde fghij";   //nur Buchstaben, mit Leerzeichen
    String test_str6 = "123";           //nur Buchstaben, mit Leerzeichen

    @Test
    public void isSVNR() throws Exception {

        assertFalse(reg.isSVNR(test_str1));
        assertFalse(reg.isSVNR(test_str2));
        assertFalse(reg.isSVNR(test_str3));
        assertTrue(reg.isSVNR(test_str4));

    }

    @Test
    public void isWord() throws Exception {

        assertFalse(reg.isWord(test_str1));
        assertTrue(reg.isWord(test_str2));
        assertFalse(reg.isWord(test_str3));
        assertFalse(reg.isWord(test_str4));
        assertFalse(reg.isWord(test_str5));

    }

    @Test
    public void isString() throws Exception {

        assertTrue(reg.isString(test_str2));
        assertTrue(reg.isString(test_str5));

    }

    @Test
    public void isNumber() throws Exception {

        assertTrue(reg.isNumber(test_str1));
        assertFalse(reg.isNumber(test_str2));
        assertFalse(reg.isNumber(test_str5));

    }

    @Test
    public void toInt() throws Exception {

        assertEquals(123, reg.toInt(test_str6));
        assertNotEquals(1234, reg.toInt(test_str2));

    }

    @Test
    public void isAdress() throws Exception {

        assertFalse(reg.isAdress(test_str1, test_str2));
        assertFalse(reg.isAdress(test_str2, test_str1));
        assertFalse(reg.isAdress(test_str2, test_str2));

    }

    @Test
    public void appendXML() throws Exception {

        //assertTrue(reg.appendXML("Addresses.xml", "addresses", 2700, "Neustadt"));
        //assertTrue(reg.appendXML("Addresses.xml","addresses", 2753, "Neustadt"));
        //assertFalse(reg.appendXML("Addresses","addresses", 2753, "Neustadt"));

    }

    @Test
    public void readXML() throws Exception {

        assertFalse(reg.readXML("Addresses.xml","addresses", "2753", "Neustadt"));
        //assertTrue(reg.readXML("Addresses.xml", "addresses", "2753", "Piesting"));

    }

}