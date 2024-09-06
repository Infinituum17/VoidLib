package infinituum.void_lib.fabric.utils;

import java.io.File;

public class Misc {
    public static String classPathStringToJavaPath(String path) {
        return path.substring(0, path.length() - ".class".length()).replace(File.separatorChar, '.');
    }
}
