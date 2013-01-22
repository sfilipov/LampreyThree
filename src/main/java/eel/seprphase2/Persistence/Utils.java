package eel.seprphase2.Persistence;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * Utils contains a set of functions that are not part of any model
 *
 * @author James
 */
public class Utils {

    /**
     * readFile reads a file on disk and returns its contents as a string. The function uses a FileInputStream and
     * copies the contents into a bytebuffer which is then casted to a string
     *
     * @url http://stackoverflow.com/questions/326390/how-to-create-a-java-string-from-the-contents-of-a-file
     * @license CC-BY-SA 3.0
     * @author erickson
     * @param path The path to the file to be read
     *
     * @return The contents of the file
     *
     * @throws IOException An error occurred in reading the file
     */
    public static String readFile(String path) throws IOException {
        FileInputStream stream = new FileInputStream(new File(path));
        try {
            FileChannel fc = stream.getChannel();
            MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
            /* Instead of using default, pass in a decoder. */
            return Charset.defaultCharset().decode(bb).toString();
        } finally {
            stream.close();
        }
    }
}
