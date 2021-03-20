//package Utility;
//
//import Data.Worker;
//import org.w3c.dom.*;
//import org.xml.sax.SAXException;
//
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.ParserConfigurationException;
//import java.io.File;
//import java.io.IOException;
//import java.util.LinkedList;
//import java.util.Scanner;
//
//public class FileScanner {
//    private Scanner scanner;
//    public FileScanner (Scanner scanner){
//        this.scanner = scanner;
//    }
//}
//
//public LinkedList<Worker> parser(String filename) throws IOException, SAXException, ParserConfigurationException {
//    filename = scanner.next();
//    LinkedList<Worker> ll = new LinkedList<>();
//    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//    DocumentBuilder builder = factory.newDocumentBuilder();
//    Document document = builder.parse(new File(filename)); //Заменить на считывание из консоли
//    NodeList worker = document.getElementsByTagName("collection");
//    for (int i = 0; i < worker.getLength(); i++) {
//        String[] args = new String[10];
//        Node workerElements = worker.item(i);
//        Element element;
//        element = (Element) workerElements;
//        Long id = Long.valueOf(element.getElementsByTagName("id").item(0).getTextContent());
//        args[0] = id.toString();
//        args[1] = element.getElementsByTagName("name").item(0).getTextContent();
//        args[2] = ((Element) element.getElementsByTagName("coordinates").item(0)).getElementsByTagName("coordinatesX").item(0).getTextContent();
//        args[3] = ((Element) element.getElementsByTagName("coordinates").item(0)).getElementsByTagName("coordinatesY").item(0).getTextContent();
//        args[4] = element.getElementsByTagName("salary").item(0).getTextContent();
//        args[5] = element.getElementsByTagName("startDate").item(0).getTextContent();
//        args[6] = element.getElementsByTagName("endDate").item(0).getTextContent();
//        args[7] = element.getElementsByTagName("position").item(0).getTextContent();
//        args[8] = ((Element) element.getElementsByTagName("person").item(0)).getElementsByTagName("pHeight").item(0).getTextContent();
//        args[9] = ((Element) element.getElementsByTagName("person").item(0)).getElementsByTagName("pWeight").item(0).getTextContent();
//    }
//    return ll;
//}
//
//
//
//
//
