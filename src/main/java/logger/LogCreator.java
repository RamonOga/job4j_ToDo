package logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogCreator {
    private final static Logger LOG = LogManager.getLogger();

    private LogCreator() {

    }

    public static Logger getLogger() {
        return LOG;
    }
}
