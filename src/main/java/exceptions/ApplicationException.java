package exceptions;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ApplicationException extends RuntimeException{

    private static final Logger logger = LogManager.getLogger(ApplicationException.class);

    public ApplicationException(String message) {
        super(message);
        logger.error(message);
    }

    public ApplicationException(Throwable cause) {
        super(cause);
        logger.error(cause);
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
        logger.error(message, cause);
    }
}
