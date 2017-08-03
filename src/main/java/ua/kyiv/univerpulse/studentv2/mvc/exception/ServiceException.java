package ua.kyiv.univerpulse.studentv2.mvc.exception;

import org.apache.log4j.Logger;

public class ServiceException extends RuntimeException {

    private static final Logger logger = Logger.getLogger(ServiceException.class);

    public ServiceException(String message) {
        super(message);
        logger.info("Constructor ServiceException!");
    }

}
