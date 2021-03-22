package Utility;

import Data.Coordinates;
import Data.Person;
import Data.Position;
import Data.Worker;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.util.Scanner;

public class FileScanner {
    private final Receiver receiver;
    private final Scanner scanner;

    public FileScanner(Receiver receiver, Scanner scanner) {
        this.receiver = receiver;
        this.scanner = scanner;
        }


    public LinkedList<Worker> parse() {
        LinkedList<Worker> collectionFromFile = new LinkedList<>();
        String tempName = null;
        Long tempX = null;
        Integer tempY = null;
        Double tempSalary = null;
        ZonedDateTime tempStartDate = null;
        ZonedDateTime tempEndDate = null;
        Position tempPosition = null;
        Long tempHeight = null;
        Integer tempWeight = null;
        long id = 0;
        ZonedDateTime creationDate = ZonedDateTime.now();
        try{
            File input = new File("D:\\Java projects\\LabFive\\src\\test\\java");
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document doc = documentBuilder.parse(input);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("worker");
            for (int temp=0; temp < nList.getLength(); temp++){
                Node node = nList.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) node;
                    tempName = element.getElementsByTagName("name").item(0).getTextContent();
                    if (node == element.getElementsByTagName("coordinates")){
                        NodeList tempNodeList = node.getChildNodes();
                        element = (Element) tempNodeList.item(0);
                        tempX = Long.parseLong(element.getElementsByTagName("coordinateX").item(0).getTextContent());
                        tempY = Integer.parseInt(element.getElementsByTagName("coordinateY").item(0).getTextContent());
                    }
                    tempSalary = Double.parseDouble(element.getElementsByTagName("salary").item(0).getTextContent());
                    tempStartDate = ZonedDateTime.parse(element.getElementsByTagName("startDate").item(0).getTextContent());
                    tempEndDate = ZonedDateTime.parse(element.getElementsByTagName("endDate").item(0).getTextContent());
                    tempPosition = Position.valueOf(element.getElementsByTagName("position").item(0).getTextContent());
                    if (node == element.getElementsByTagName("person")){
                        NodeList tempNodeList = node.getChildNodes();
                        element = (Element) tempNodeList.item(0);
                        tempHeight = Long.parseLong(element.getElementsByTagName("height").item(0).getTextContent());
                        tempWeight = Integer.parseInt(element.getElementsByTagName("weight").item(0).getTextContent());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        id = receiver.getMaxId();
        Worker worker = new Worker(id, tempName, new Coordinates(tempX, tempY), creationDate, tempSalary,tempStartDate, tempEndDate, tempPosition, new Person(tempHeight, tempWeight));
        collectionFromFile.add(worker);
        return collectionFromFile;
    }
}




