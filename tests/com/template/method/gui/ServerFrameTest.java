package com.template.method.gui;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

//TODO: avoid code repetiton!
class ServerFrameTest {


    //TODO: refactor this code to use ergular expression!
    @Test
    void info() {
        ServerFrame sut = new ServerFrame(9090, 2, 6000, new TetrisFrame());
        String testMessage = "Info";
        String currentYear = String.valueOf(LocalDate.now().getYear());

        sut.info(testMessage);

        String setValue = sut.getTetrisFrame().getServerLogTextArea().getText();
////20.01.2019, 18:36:38: Info
//        System.out.println();
//
//        System.out.println(setValue);
//        String regexp = String.format("\\^d{2}\\.\\d{2}\\.\\d{4}\\, \\d{2}:\\d{2}:\\d{2}: %s", testMessage);
//
//        System.out.println("20.01.2019, 18:46:20: Info".matches(regexp));
//
//        String[] results = Pattern.compile()
//                .matcher("string to search from here")
//                .results()
//                .map(MatchResult::group)
//                .toArray(String[]::new);
//
//        System.out.println("Results: ");
//        for(String s : results) {
//            System.out.println(s);
//        }
//        System.out.println("Results:");


        assertTrue(setValue.contains(currentYear));
        assertTrue(setValue.contains(testMessage));
    }

    @Test
    void error() {
        ServerFrame sut = new ServerFrame(9090, 2, 6000, new TetrisFrame());
        String testMessage = "Error";
        String currentYear = String.valueOf(LocalDate.now().getYear());

        sut.info(testMessage);

        String setValue = sut.getTetrisFrame().getServerLogTextArea().getText();

        assertTrue(setValue.contains(currentYear));
        assertTrue(setValue.contains(testMessage));

    }

    @Test
    void logMessage() {
        ServerFrame sut = new ServerFrame(9090, 2, 6000, new TetrisFrame());
        String testMessage = "Log";
        String currentYear = String.valueOf(LocalDate.now().getYear());

        sut.info(testMessage);

        String setValue = sut.getTetrisFrame().getServerLogTextArea().getText();

        assertTrue(setValue.contains(currentYear));
        assertTrue(setValue.contains(testMessage));

    }
}