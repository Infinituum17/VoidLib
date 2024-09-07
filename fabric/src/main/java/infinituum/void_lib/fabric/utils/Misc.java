package infinituum.void_lib.fabric.utils;

import java.io.File;

/**
 * Utility class
 */
public final class Misc {
    /**
     * Converts a class file path to a Java path
     *
     * @param path The class file path to convert
     * @return The resulting Java path
     */
    public static String classPathStringToJavaPath(String path) {
        return path.substring(0, path.length() - ".class".length()).replace(File.separatorChar, '.');
    }
}
