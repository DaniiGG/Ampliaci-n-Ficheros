package ejercicio2;

import com.opencsv.CSVReader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileReader;
import java.io.FileWriter;

public class ConversorCSVaXML {
    public void convertirCSVtoXML(String csvFile, String xmlFile) {
        try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            Element rootElement = doc.createElement("Estudiantes");
            doc.appendChild(rootElement);

            String[] headers = reader.readNext();
            String[] line;

            while ((line = reader.readNext()) != null) {
                Element estudiante = doc.createElement("Estudiante");
                for (int i = 0; i < headers.length; i++) {
                    Element nodo = doc.createElement(headers[i]);
                    nodo.appendChild(doc.createTextNode(line[i]));
                    estudiante.appendChild(nodo);
                }
                rootElement.appendChild(estudiante);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new FileWriter(xmlFile));
            transformer.transform(source, result);

            System.out.println("Archivo XML generado exitosamente.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
