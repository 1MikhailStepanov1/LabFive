package utility;

import data.Coordinates;
import data.Person;
import data.Position;
import data.Worker;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.LinkedList;


/**
 * This class is used to operate with files
 */
public class FileWorker {
    private final CollectionManager collectionManager;

    public FileWorker(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }


    /**
     * Read collection from indicated file
     * @return collection from indicated file
     * @throws IllegalArgumentException if some methods have incorrect argument
     * @throws NullPointerException if some of the fields is null
     * @throws IOException if can't read collection from file
     * @throws SAXException if can't match XML format in file
     * @throws ParserConfigurationException if document builder can't be created
     */
    public LinkedList<Worker> parse() throws IllegalArgumentException, NullPointerException, IOException, SAXException, ParserConfigurationException {
        LinkedList<Worker> collectionFromFile = new LinkedList<>();
        Long tempId = null;
        String tempName = null;
        Long tempX = null;
        Integer tempY = null;
        ZonedDateTime tempCreationDate = null;
        Double tempSalary = null;
        ZonedDateTime tempStartDate = null;
        ZonedDateTime tempEndDate = null;
        Position tempPosition = null;
        Long tempHeight = null;
        Integer tempWeight = null;
        try {
            File input = new File(collectionManager.getFilePath());
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document doc = documentBuilder.parse(input);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("worker");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node node = nList.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    tempId = Long.parseLong(element.getElementsByTagName("id").item(0).getTextContent());
                    tempName = element.getElementsByTagName("name").item(0).getTextContent();

                    NodeList tempNodeListCoord = element.getElementsByTagName("coordinates");
                    Element elementCoord = (Element) tempNodeListCoord.item(0);
                    tempX = Long.parseLong(elementCoord.getElementsByTagName("coordinateX").item(0).getTextContent());
                    tempY = Integer.parseInt(elementCoord.getElementsByTagName("coordinateY").item(0).getTextContent());

                    tempCreationDate = ZonedDateTime.parse(element.getElementsByTagName("creationDate").item(0).getTextContent());
                    tempSalary = Double.parseDouble(element.getElementsByTagName("salary").item(0).getTextContent());
                    tempStartDate = ZonedDateTime.parse(element.getElementsByTagName("startDate").item(0).getTextContent());
                    tempEndDate = ZonedDateTime.parse(element.getElementsByTagName("endDate").item(0).getTextContent());
                    tempPosition = Position.valueOf(element.getElementsByTagName("position").item(0).getTextContent());

                    NodeList tempNodeListPerson = element.getElementsByTagName("person");
                    Element elementPerson = (Element) tempNodeListPerson.item(0);
                    tempHeight = Long.parseLong(elementPerson.getElementsByTagName("height").item(0).getTextContent());
                    tempWeight = Integer.parseInt(elementPerson.getElementsByTagName("weight").item(0).getTextContent());
                }

                Worker worker = new Worker(tempId, tempName, new Coordinates(tempX, tempY), tempCreationDate, tempSalary, tempStartDate, tempEndDate, tempPosition, new Person(tempHeight, tempWeight));
                collectionFromFile.add(worker);
            }
        } catch (IllegalArgumentException | NullPointerException exception) {
            System.out.println("Some fields contain incorrect data. Please correct file and try again." + exception.getMessage());
        }
        return collectionFromFile;
    }

    /**
     * @param filePath - file in which collection wil be wrote
     */
    public void getToXmlFormat(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
            writer.write("<collection>\n");
            for (Worker w : collectionManager.getCollection()) {
                writer.write("<worker>\n");
                writer.write("<id>" + w.getId() + "</id>\n");
                writer.write("<name>" + w.getName() + "</name>\n");
                writer.write("<coordinates>\n");
                writer.write("<coordinateX>" + w.getCoordinates().getX() + "</coordinateX>\n");
                writer.write("<coordinateY>" + w.getCoordinates().getY() + "</coordinateY>\n");
                writer.write("</coordinates>\n");
                writer.write("<creationDate>" + w.getCreationDate() + "</creationDate>\n");
                writer.write("<salary>" + w.getSalary() + "</salary>\n");
                writer.write("<startDate>" + w.getStartDate() + "</startDate>\n");
                writer.write("<endDate>" + w.getEndDate() + "</endDate>\n");
                writer.write("<position>" + w.getPosition() + "</position>\n");
                writer.write("<person>\n");
                writer.write("<height>" + w.getPerson().getHeight() + "</height>\n");
                writer.write("<weight>" + w.getPerson().getWeight() + "</weight>\n");
                writer.write("</person>\n");
                writer.write("</worker>\n");
            }
            writer.write("</collection>");
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}




