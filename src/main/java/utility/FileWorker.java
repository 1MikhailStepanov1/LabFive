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
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import java.io.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;


/**
 * This class is used to operate with files
 */
public class FileWorker {
    private final CollectionManager collectionManager;
    private String filePath;

    public FileWorker(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
        filePath = "";
    }


    /**
     * Read collection from indicated file
     *
     * @return collection from indicated file
     * @throws IllegalArgumentException     if some methods have incorrect argument
     * @throws NullPointerException         if some of the fields is null
     * @throws IOException                  if can't read collection from file
     * @throws SAXException                 if can't match XML format in file
     * @throws ParserConfigurationException if document builder can't be created
     */
    public LinkedList<Worker> parse(String path) throws IllegalArgumentException, NullPointerException, IOException, SAXException, ParserConfigurationException {
        filePath = path;
        LinkedList<Worker> collectionFromFile = new LinkedList<>();
        Long tempId = null;
        String tempName = null;
        long tempX = 0;
        Integer tempY = null;
        ZonedDateTime tempCreationDate = null;
        double tempSalary = 0;
        ZonedDateTime tempStartDate = null;
        ZonedDateTime tempEndDate = null;
        Position tempPosition = null;
        Long tempHeight = null;
        Integer tempWeight = null;
        try {
            File input = new File(path);
            Scanner scanner = new Scanner(input);
            String file = "";
            String backup = "";
            HashMap<Long, String> incorrectNames = new HashMap<>();
            long workerNumberForNames = 1;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                backup += line;
                if (line.contains("<worker>")) {
                    workerNumberForNames += 1;
                }
                if (line.contains("<name>")) {
                    String tempLine = line;
                    tempLine = tempLine.replace("<name>", "").replace("</name>", "");
                    incorrectNames.put(workerNumberForNames, tempLine);
                    tempLine = tempLine.replace("<", "");
                    file += "<name>" + tempLine + "</name>";
                } else {
                    file += line;
                }
            }
            scanner.close();
            incorrectNames.replaceAll((s, v) -> v.replace(" ", ""));
            FileWriter tempWriter = new FileWriter(input);
            tempWriter.write(file);
            tempWriter.close();
            long workerNumber = 1;
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document doc = documentBuilder.parse(input);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("worker");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node node = nList.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    try {
                        tempId = Long.parseLong(element.getElementsByTagName("id").item(0).getTextContent());
                    } catch (NumberFormatException exception) {
                        System.out.println("Wrong id in worker number " + workerNumber);
                    }
                    tempName = element.getElementsByTagName("name").item(0).getTextContent();

                    NodeList tempNodeListCoord = element.getElementsByTagName("coordinates");
                    Element elementCoord = (Element) tempNodeListCoord.item(0);
                    try {
                        tempX = Long.parseLong(elementCoord.getElementsByTagName("coordinateX").item(0).getTextContent());
                        if (tempX > 768) {
                            throw new NumberFormatException();
                        }
                    } catch (NumberFormatException exception) {
                        System.out.println("Wrong coordinate X in worker number " + workerNumber);
                    }
                    try {
                        tempY = Integer.parseInt(elementCoord.getElementsByTagName("coordinateY").item(0).getTextContent());
                    } catch (NumberFormatException exception) {
                        System.out.println("Wrong coordinate Y in worker number " + workerNumber);
                    }

                    try {
                        tempCreationDate = ZonedDateTime.parse(element.getElementsByTagName("creationDate").item(0).getTextContent());
                    } catch (DateTimeParseException exception) {
                        System.out.println("Wrong creation date in worker number " + workerNumber);
                    }
                    try {
                        tempSalary = Double.parseDouble(element.getElementsByTagName("salary").item(0).getTextContent());
                        if (tempSalary <= 0) {
                            throw new NumberFormatException();
                        }
                    } catch (NumberFormatException exception) {
                        System.out.println("Wrong salary in worker number " + workerNumber);
                        tempSalary = 0;
                    }
                    try {
                        tempStartDate = ZonedDateTime.parse(element.getElementsByTagName("startDate").item(0).getTextContent());
                    } catch (DateTimeParseException exception) {
                        System.out.println("Wrong start date in worker number " + workerNumber);
                    }
                    if (!element.getElementsByTagName("endDate").item(0).getTextContent().equals("")) {
                        try {
                            tempEndDate = ZonedDateTime.parse(element.getElementsByTagName("endDate").item(0).getTextContent());
                        } catch (DateTimeParseException exception) {
                            System.out.println("Wrong end date in worker number " + workerNumber);
                        }
                    }
                    tempPosition = Position.valueOf(element.getElementsByTagName("position").item(0).getTextContent());

                    NodeList tempNodeListPerson = element.getElementsByTagName("person");
                    Element elementPerson = (Element) tempNodeListPerson.item(0);
                    try {
                        tempHeight = Long.parseLong(elementPerson.getElementsByTagName("height").item(0).getTextContent());
                        if (tempHeight <= 0) {
                            throw new NumberFormatException();
                        }
                    } catch (NumberFormatException exception) {
                        System.out.println("Wrong height in worker number " + workerNumber);
                        tempHeight = 0L;
                    }
                    try {
                        tempWeight = Integer.parseInt(elementPerson.getElementsByTagName("weight").item(0).getTextContent());
                        if (tempWeight <= 0) {
                            throw new NumberFormatException();
                        }
                    } catch (NumberFormatException exception) {
                        System.out.println("Wrong weight in worker number " + workerNumber);
                        tempWeight = 0;
                    }
                }
                workerNumber += 1;
                if (tempId == null || tempName == null || tempY == null || tempX == 0 || tempCreationDate == null || tempSalary == 0 || tempStartDate == null) {
                    System.out.println("Worker can't be added. Some data is wrong.");
                } else if (incorrectNames.containsKey(workerNumber)) {
                    Worker worker = new Worker(tempId, incorrectNames.get(workerNumber), new Coordinates(tempX, tempY), tempCreationDate, tempSalary, tempStartDate, tempEndDate, tempPosition, new Person(tempHeight, tempWeight));
                    collectionFromFile.add(worker);
                } else {
                    Worker worker = new Worker(tempId, tempName, new Coordinates(tempX, tempY), tempCreationDate, tempSalary, tempStartDate, tempEndDate, tempPosition, new Person(tempHeight, tempWeight));
                    collectionFromFile.add(worker);
                }
            }
            FileWriter tempWriter2 = new FileWriter(input);
            for (String str : backup.split(">")) {
                str.replaceAll("(^ )+", "").replaceAll("( $)+", "");
                if (str.replaceAll(" ", "").equals("<id") || str.replaceAll(" ", "").equals("<name") || str.replaceAll(" ", "").equals("<coordinateX") || str.replaceAll(" ", "").equals("<coordinateY") || str.replaceAll(" ", "").equals("<creationDate") || str.replaceAll(" ", "").equals("<salary") || str.replaceAll(" ", "").equals("<startDate") || str.replaceAll(" ", "").equals("<endDate") || str.replaceAll(" ", "").equals("<position") || str.replaceAll(" ", "").equals("<height") || str.replaceAll(" ", "").equals("<weight")) {
                    tempWriter2.write(str + ">");
                } else {
                    tempWriter2.write(str + ">\n");
                }
            }
            tempWriter2.close();
            System.out.println("Collection was loaded successfully.");
        } catch (FactoryConfigurationError | ParserConfigurationException | IOException | SAXException exception) {
            System.out.println("Something goes wrong. Please correct file and try again. (" + exception.getMessage() + ")");
        }

        return collectionFromFile;
    }

    public String getFilePath() {
        return filePath;
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
                if (w.getEndDate() == null) {
                    writer.write("<endDate></endDate>\n");
                } else {
                    writer.write("<endDate>" + w.getEndDate() + "</endDate>\n");
                }
                if (w.getPosition() == null) {
                    writer.write("<position></position>\n");
                } else {
                    writer.write("<position>" + w.getPosition() + "</position>\n");
                }
                writer.write("<person>\n");
                if (w.getPerson().getHeight() == null) {
                    writer.write("<height>" + w.getPerson().getHeight() + "</height>\n");
                } else {
                    writer.write("<height>" + w.getPerson().getHeight() + "</height>\n");
                }
                if (w.getPerson().getWeight() == null) {
                    writer.write("<weight></weight>\n");
                } else {
                    writer.write("<weight>" + w.getPerson().getWeight() + "</weight>\n");
                }
                writer.write("</person>\n");
                writer.write("</worker>\n");
            }
            writer.write("</collection>");
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
