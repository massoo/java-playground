package be.demo.examples;

import be.demo.exception.TechnicalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Pattern;

/**
 * User: massoo
 */
public class OSCommandSanitization {

    private static final Logger LOG = LoggerFactory.getLogger(OSCommandSanitization.class);

    public static void main(String[] args) {

        if (args.length != 1) {
            throw new TechnicalException("1 argument is expected !");
        }

        badOSCommandSanitization(args[0]);
        //goodStrictOSCommandSanitization(args[0]);
        //goodTrustedOSCommandSanitization(args[0]);
    }

    private static void badOSCommandSanitization(String argument) {
        executeCommand(argument);
    }

    private static void goodStrictOSCommandSanitization(String argument) {
        if (!Pattern.matches("[0-9A-Za-z@.]+", argument)) {
            LOG.error("You are not allowed to use any other characters than [0-9A-Za-z@.]");
        } else {
            executeCommand(argument);
        }
    }

    private static void goodTrustedOSCommandSanitization(String argument) {

        // only allow integer arguments to be passed which represent trusted Strings
        String directory = null;
        int number = Integer.parseInt(argument);
        switch (number) {
            case 1:
                directory = "directory_1";
                break;
            case 2:
                directory = "directory_2";
                break;
            default:
                break;
        }

        if (directory == null) {
            LOG.error("The number you passed can't get resolved into a directory");
        } else {
            executeCommand(directory);
        }

    }

    private static void executeCommand(String argument) {
        try {

            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec(new String[]{"sh", "-c", "ls " + argument});
            int result = proc.waitFor();

            if (result != 0) {
                LOG.error("process error: " + result);
            }

            InputStream in = (result == 0) ? proc.getInputStream() : proc.getErrorStream();

            int c;
            while ((c = in.read()) != -1) {
                LOG.info(String.valueOf((char) c));
            }

        } catch (InterruptedException e) {
            LOG.error(e.getMessage());
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
    }
}