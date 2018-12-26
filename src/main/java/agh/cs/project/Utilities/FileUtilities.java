package agh.cs.project.Utilities;

import java.io.File;

public  class FileUtilities {
    public static boolean isStandardPath(String path) {
        return (path.matches("^(\\./)?(\\w+/{1})*\\w+\\.txt$")) || (path.matches("\\w*"));
    }

    public static boolean isNotEmpty(String path) {
        File directory = new File(path);

        return (checkIfFileExists(directory) && checkIfFileIsDirectory(directory) && !checkIfDirectoryIsEmpty(directory));
    }

    public static boolean checkIfFileExists(File file) {
        return file.exists();
    }

    public static boolean checkIfFileIsDirectory(File file) {
        return file.isDirectory();
    }

    private static boolean checkIfDirectoryIsEmpty(File dir) {
        if (checkIfFileExists(dir)) {
            return dir.listFiles().length == 0;
        }

        return false;
    }
}
