package file;

import lombok.experimental.UtilityClass;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Stream Utilities
 *
 * @author mazhanbin
 * @since 2019-08-25
 */
@UtilityClass
public final class FileUtil {
    private static final int BUFFER_SIZE = 1024;

    /**
     * Put the file into the response
     *
     * @param httpServletResponse Response
     * @param file                File
     * @throws IOException I/O Exception
     */
    public static void file2Response(HttpServletResponse httpServletResponse, File file) throws IOException {
        file2Response(httpServletResponse, file, file.getName(), "application/x-msdownload");
    }

    /**
     * Put the file into the response
     *
     * @param httpServletResponse Response
     * @param file                File
     * @param fileName            File Name
     * @param contentType         HTTP Content-Type Header
     * @throws IOException I/O Exception
     */
    public static void file2Response(HttpServletResponse httpServletResponse, File file, String fileName,
        String contentType) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(file);
            ServletOutputStream outputStream = httpServletResponse.getOutputStream()) {
            httpServletResponse.setHeader("content-disposition", "attachment;fileName-" + fileName);
            httpServletResponse.setHeader("content-type", contentType);
            int len;
            byte[] buffer = new byte[BUFFER_SIZE];
            while ((len = fileInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
            outputStream.flush();
        }
    }
}
