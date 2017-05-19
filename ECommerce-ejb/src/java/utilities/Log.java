/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Amaury
 */
public class Log {
   
    private static final Logger logger = Logger.getLogger(Log.class.getName());
    
    // Par defaut, on log en warning
    public static void log(String log)
    {	
        logger.info("___________________________________________________");
        logger.info("Message logger : ");
        logger.warning(log);
        logger.info("___________________________________________________");
    }
    
    public static void log(String[] log)
    {	
        logger.info("___________________________________________________");
        logger.info("Message logger : ");
        for(String s: log){
            logger.warning(s);
        }
        logger.info("___________________________________________________");
    }
}
