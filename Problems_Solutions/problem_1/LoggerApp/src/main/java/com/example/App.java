package com.example;

import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

/**
 *  * Hello world!
 *   *
 *    */
public class App 
{
        private static Logger logger = LogManager.getLogger(App.class);
    public static void main( String[] args )
    {
        System.out.println( "Logging" );
        for (int i = 0; i < 10 ; i++) {
                logger.info("Log Message");
        }
    }
}
