package Utility;

import Data.Worker;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
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
        String[] workerInStringType = new String[9];
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
                workerInStringType[0] = element.getElementsByTagName("name").item(0).getTextContent();
                if (node == element.getElementsByTagName("coordinates")){
                    NodeList tempNodeList = node.getChildNodes();
                    element = (Element) tempNodeList.item(0);
                    workerInStringType[1] = element.getElementsByTagName("coordinateX").item(0).getTextContent();
                    workerInStringType[2] = element.getElementsByTagName("coordinateY").item(0).getTextContent();
                }
                workerInStringType[3] = element.getElementsByTagName("salary").item(0).getTextContent();
                workerInStringType[4] = element.getElementsByTagName("startDate").item(0).getTextContent();
                workerInStringType[5] = element.getElementsByTagName("endDate").item(0).getTextContent();
                workerInStringType[6] = element.getElementsByTagName("position").item(0).getTextContent();
                if (node == element.getElementsByTagName("person")){
                    NodeList tempNodeList = node.getChildNodes();
                    element = (Element) tempNodeList.item(0);
                    workerInStringType[7] = element.getElementsByTagName("height").item(0).getTextContent();
                    workerInStringType[8] = element.getElementsByTagName("weight").item(0).getTextContent();
                }
            }
        }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Worker worker = new Worker();
        return collectionFromFile;
    }
}




