package site.niufan.common.utils.file;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Fan Niu
 * @since 2018/7/18
 */
public class FileSearchUtils {

    private FileSearchUtils() {}

    public static List<File> listFiles(File file, FileFilter filter) {
        List<File> files = new ArrayList<>();
        for (File temp: listFiles(file, false)) {
            if ((filter == null) || filter.accept(temp))
                files.add(temp);
        }
        return files;
    }

    public static List<File> listFilesByRecursion(File file, FileFilter filter) {
        List<File> files = new ArrayList<>();
        for (File temp: listFiles(file, true)) {
            if ((filter == null) || filter.accept(temp))
                files.add(temp);
        }
        return files;
    }

    private static List<File> listFiles(File file, boolean recursion) {
        List<File> files = new ArrayList<>();
        File[] children = file.listFiles();
        if (children != null) {
            for (File child: children) {
                files.add(child);
                if (child.isDirectory() && recursion) {
                    files.addAll(listFiles(child, true));
                }
            }
        }
        return files;
    }
}
