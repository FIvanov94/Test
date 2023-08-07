package exceptions;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class AutomationImplementationException extends RuntimeException {

    private static final Logger logger = LogManager.getLogger(AutomationImplementationException.class);

    public AutomationImplementationException(String message) {
        super(message);
        logger.error(message);
    }

    public AutomationImplementationException(Throwable cause) {
        super(cause);
        logger.error(cause);
    }

    public AutomationImplementationException(String message, Throwable cause) {
        super(message, cause);
        logger.error(message, cause);
    }
}
