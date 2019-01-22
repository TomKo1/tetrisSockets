package com.template.method.utilities;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class XMLParserTest {

    @Test
    void parseConfiguration() {
        String pathToSampleValidXML = "./tests/com/template/method/utilities/config.xml";
        String ipNumber = "192.45.32.1";
        Integer portNumb = 8001;
        Integer  playerCount = 1;
        Integer playTime = 632;

        XMLParser.Configuration configObj = XMLParser.parseConfiguration(pathToSampleValidXML);

        assertEquals(ipNumber, configObj.getIpNumber());
        assertEquals(playerCount, configObj.getPlayerCount());
        assertEquals(portNumb, configObj.getPort());
        assertEquals(playTime, configObj.getPlayTime());
    }
}