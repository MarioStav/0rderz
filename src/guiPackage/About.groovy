
/**@author Christoph \n @since (datum) */
package guiPackage;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;

public class About {

    public static void main(String[] args) {

        createFrameAbout();

    }

    public static void createFrameAbout() {
        //default creating method in groovy; scripting
        JFrame about = new JFrame("About");
        about.setSize(500, 220);
        about.setLocation(700, 300);
        about.setResizable(false);
        about.setLayout(new BorderLayout());
        about.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

        JTextPane textPane = new JTextPane();
        textPane.setEditable(false);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        String output = "";

        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse("ProjektPeople.xml");
            NodeList personlist = doc.getElementsByTagName("person");
            output += "Developer: \n\n";
            for (int i = 0; i < personlist.getLength(); i++) {

                Node p = personlist.item(i);
                if (p.getNodeType() == Node.ELEMENT_NODE) {

                    Element person = (Element) p;
                    //String id = person.getAttribute("id");
                    NodeList namelist = person.getChildNodes();
                    for (int j = 0; j < namelist.getLength(); j++) {

                        Node n = namelist.item(j);
                        if (n.getNodeType() == Node.ELEMENT_NODE) {

                            Element name = (Element) n;
                            output += name.getTextContent() + " ";

                        }

                    }
                    output += "\n";

                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        textPane.setText(output + "\n\n\n\n Die Software '0rderz' ist ein Bestellungsverwaltungs-Tool für Kellner " +
                "um ihnen das Aufnehmen von Bestellungen zu erleichtern. Außerdem können " +
                "neu eingestellte Kellner registriert werden und im Falle einer Kündigung " +
                "wieder gelöscht werden.");
        about.add(textPane);
        about.setVisible(true);

    }
}