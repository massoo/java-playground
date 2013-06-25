package be.demo.examples;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * User: massoo
 */
public class FilePathSanitization {

    static final List<String> allowedExtensions;

    static {
        allowedExtensions = new ArrayList<String>();
        allowedExtensions.add("xls");
        allowedExtensions.add("doc");
        allowedExtensions.add("ppt");
    }

    public static void main(String[] args) {

        String filename = null;

        if (args.length > 0) {
            filename = args[0];
        }

        // clean the filename for unwanted characters
        filename = FilenameUtils.normalize(filename);

        // make sure we work with valid extensions
        if (!FilenameUtils.isExtension(filename, allowedExtensions)) {
            // handle error invalid extension
        } else {

            // using getCanonicalPath starts the resolve the path to and absolute path
            // it could be that it interacts with the filesystem
            FileInputStream fis = null;
            try {
                String filePath = new File(filename).getCanonicalPath();
                fis = new FileInputStream(filePath);

                // process FileInputStream

                fis.close();
            } catch (FileNotFoundException e) {
                // handle errors
            } catch (IOException e) {
                // handle errors
            } finally {
                IOUtils.closeQuietly(fis);
            }
        }

    }


    /**
     * EXAMPLES NOT TO BE USED !!!!!!
     */

    /**
     * NOT TO BE USED: Using this message will cause interaction with the file system
     *
     * @param filePath
     * @return
     * @throws Exception
     */
    public static String javaAPINormalizeFilePath(String filePath) throws IOException {
        return new File(filePath).getCanonicalPath();
    }

    /**
     * NOT TO BE USED: No interaction with the file system, this will have a weird effect on symbolic links on linux
     *
     * @param filePath
     * @return
     * @throws Exception
     */
    public static String javaURINormalizeFilePath(String filePath) throws URISyntaxException {
        return new URI(filePath).normalize().toString();
    }

}
