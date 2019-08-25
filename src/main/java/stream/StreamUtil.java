package stream;

import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Stream Utilities
 *
 * @author mazhanbin
 * @since 2019-08-25
 */
@UtilityClass
public final class StreamUtil {
    private static final int BUFFER_SIZE = 1024;

    public static void input2Output(InputStream inputStream, OutputStream outputStream) throws IOException {
        int len;
        byte[] buffer = new byte[BUFFER_SIZE];
        while ((len = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, len);
        }
        outputStream.flush();
    }
}