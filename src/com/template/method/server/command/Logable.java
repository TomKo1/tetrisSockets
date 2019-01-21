package com.template.method.server.command;

/**
 * interface implemented by objects which are
 * able to print themselves
 */
public interface Logable {
    void info(String message);
    void error(String message);
}
