package com.template.method.server;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;

public class XMLParser {

    public static Configuration parseConfiguration(String fileName) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        String ip = "";
        String port = "";

        try {
            File file = new File(fileName);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);
            doc.getDocumentElement().normalize();
            // root
            Element root = doc.getDocumentElement();
            NodeList nodes = root.getChildNodes();


            for(int i  = 0 ; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);
                if(node.getNodeName().equals("ip")) {
                    ip = node.getTextContent();
                } else if(node.getNodeName().equals("port")) {
                    port = node.getTextContent();
                }
            }

            return new Configuration(ip, port);

        } catch(ParserConfigurationException | SAXException | IOException  e) {
            e.printStackTrace();
            System.exit(1);
        }

        return new Configuration(ip, port);
    }


    public static class Configuration {
        String ipNumber;
        String port;

        public Configuration(String ipNumber, String port) {
            this.ipNumber = ipNumber;
            this.port = port;
        }

        @Override
        public String toString() {
            return ipNumber + "  " + port;
        }
    }


}
