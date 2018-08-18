package site.niufan.common.utils.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Fan Niu
 * @since 2018/08/18ss
 */
public class LogUtils {

    public static Logger getLogger(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }

    public static Logger getLogger(String name) {
        return LoggerFactory.getLogger(name);
    }

    public static void trace(Logger logger, String format, Object argument){
        logger.trace(format, argument);
    }

    public static void trace(Logger logger, String format, Object... arguments){
        logger.trace(format, arguments);
    }

    public static void trace(Logger logger, String message){
        logger.trace(message);
    }

    public static void trace(Logger logger, String message, Throwable throwable){
        logger.trace(message, throwable);
    }

    public static void debug(Logger logger, String format, Object argument){
        logger.debug(format, argument);
    }

    public static void debug(Logger logger, String format, Object... arguments){
        logger.debug(format, arguments);
    }

    public static void debug(Logger logger, String message){
        logger.debug(message);
    }

    public static void debug(Logger logger, String message, Throwable throwable){
        logger.debug(message, throwable);
    }

    public static void info(Logger logger, String format, Object argument){
        logger.info(format, argument);
    }

    public static void info(Logger logger, String format, Object... arguments){
        logger.info(format, arguments);
    }

    public static void info(Logger logger, String message){
        logger.info(message);
    }

    public static void info(Logger logger, String message, Throwable throwable){
        logger.info(message, throwable);
    }

    public static void warn(Logger logger, String format, Object argument){
        logger.warn(format, argument);
    }

    public static void warn(Logger logger, String format, Object... arguments){
        logger.warn(format, arguments);
    }

    public static void warn(Logger logger, String message){
        logger.warn(message);
    }

    public static void warn(Logger logger, String message, Throwable throwable){
        logger.warn(message, throwable);
    }

    public static void error(Logger logger, String format, Object argument){
        logger.error(format, argument);
    }

    public static void error(Logger logger, String format, Object... arguments){
        logger.error(format, arguments);
    }

    public static void error(Logger logger, String message){
        logger.error(message);
    }

    public static void error(Logger logger, String message, Throwable throwable){
        logger.error(message, throwable);
    }
}
