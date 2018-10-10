package com.java.log;

import java.util.logging.LogRecord;
import java.util.logging.StreamHandler;
/**
 * 
 * @author sabaja
 *Java Logging Handlers
 *
 *We can add multiple handlers to a java logger and whenever we log any message, 
 *every handler will process it accordingly. There are two default handlers provided by Java Logging API.
 *
 *ConsoleHandler: 
 *	This handler writes all the logging messages to console 
 *
 *FileHandler: 
 *	This handler writes all the logging messages to file in the XML format.
 *
 *We can create our own custom handlers also to perform specific tasks. 
 *To create our own Handler class, we need to extend java.util.logging.Handler class 
 *or any of it’s subclasses like StreamHandler, SocketHandler etc.
 *
 *
 *Here is an example of a custom java logging handler:
 * */
public class MyHandler extends StreamHandler {

    @Override
    public void publish(LogRecord record) {
        //add own logic to publish
        super.publish(record);
    }


    @Override
    public void flush() {
        super.flush();
    }


    @Override
    public void close() throws SecurityException {
        super.close();
    }

}