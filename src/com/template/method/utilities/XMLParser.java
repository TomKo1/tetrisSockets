package com.template.method.utilities;

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

/**
 *  Class which encapsulates XML parsing of configuration which is stored in XML file
 */
public class XMLParser {

    /**
     * Utility method which parses xml file with configuration under given path
     *
    */
    public static Configuration parseConfiguration(String fileName) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        String ip = "";
        Integer port = 8181;
        Integer playerCount = 2;
        Integer playTime = 600000;

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

                switch(node.getNodeName()) {
                    case "ip":
                        ip = node.getTextContent();
                        break;
                    case "port":
                        port = Integer.parseInt(node.getTextContent());
                        break;
                    case "playerCount":
                        playerCount = Integer.parseInt(node.getTextContent());
                        break;
                    case "playTime":
                        playTime = Integer.parseInt(node.getTextContent());
                        break;
                }
            }


        } catch(ParserConfigurationException | SAXException | IOException  e) {
            e.printStackTrace();
            System.exit(1);
        }

        return new Configuration(ip, port, playerCount, playTime);
    }


    /**
     * Class encapsulating configuration which was read from xml file
     * and is returned after parsing the doc
    */
    public static class Configuration {
        String ipNumber;
        Integer port;
        Integer playerCount;
        Integer playTime;

        public Configuration(String ipNumber, Integer port, Integer playerCount, Integer playTime) {
            this.ipNumber = ipNumber;
            this.port = port;
            this.playerCount = playerCount;
            this.playTime = playTime;
        }

        @Override
        public String toString() {
            return ipNumber + "  " + port;
        }

        public String getIpNumber() {
            return ipNumber;
        }

        public Integer getPlayerCount() { return playerCount; }

        public Integer getPort() {
            return port;
        }

        public Integer getPlayTime() { return playTime; }
    }


}
