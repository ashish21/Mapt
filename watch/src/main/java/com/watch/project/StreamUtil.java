package com.watch.project;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class StreamUtil {

    public static final String PREFIX = "stream2file";
    public static final String SUFFIX = ".tmp";

    public static File stream2file (InputStream in) throws IOException {
        final File tempFile = File.createTempFile(PREFIX, SUFFIX);
        FileOutputStream out = new FileOutputStream(tempFile);
        int read = 0;
        byte[] bytes = new byte[10240];
        while ((read = in.read(bytes)) != -1) {
            out.write(bytes, 0, read);
        }
        return tempFile;
    }

}