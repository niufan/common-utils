package site.niufan.common.utils.exception;

import site.niufan.common.exception.error.Error;
import site.niufan.common.utils.exception.enums.example.DataErrorEnum;
import site.niufan.common.utils.exception.enums.example.BusinessErrorEnum;
import site.niufan.common.utils.file.FileSearchUtils;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.*;

/**
 * @author Fan Niu
 * @since 2018/7/18
 */
public class SimpleErrorEnumHelper implements ErrorEnumHelper<Serializable, Error> {

    private Collection<Error> errors;

    public SimpleErrorEnumHelper(String... packages) {
        errors = new ArrayList<>();
        for (String _package: packages) {
            String resourceName = _package.replace(".", "/");
            String basePackagePath = resourceName.replace("/", File.separator);
            ClassLoader classLoader = this.getClass().getClassLoader();
            Enumeration<URL> urls;
            try {
                urls = classLoader.getResources(resourceName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                if (url != null) {
                    List<File> classFiles = new ArrayList<>();
                    File file = new File(url.getPath());
                    if (file.isDirectory()) {
                        classFiles.addAll(FileSearchUtils.listFilesByRecursion(file, one -> one.isFile() && one.getName().endsWith(".class")));
                    } else {
                        if (file.getName().endsWith(".class")) {
                            classFiles.add(file);
                        }
                    }
                    for (File classFile: classFiles) {
                        String currentPath = classFile.getPath();
                        String currentClassName = classFile.getName();
                        currentClassName = currentClassName.substring(0, currentClassName.length() - ".class".length());
                        currentPath = currentPath.substring(0, currentPath.indexOf(currentClassName));
                        String currentPackageName = currentPath.substring(currentPath.indexOf(basePackagePath)).replace(File.separator, ".");
                        String className = currentPackageName + currentClassName;

                        try {
                            Class.forName(className);
                            Class<?> clazz = this.getClass().getClassLoader().loadClass(className);
                            if (Error.class.isAssignableFrom(clazz) && clazz.isEnum()) {
                                clazz.getEnumConstants();
                                for (Object obj: clazz.getEnumConstants()) {
                                    errors.add((Error) obj);
                                }
                            }
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        }
    }

    @Override
    public Collection<Error> errors() {
        return errors;
    }

    public static void main(String[] args) {
        String[] packages = {"site.niufan.common"};
        SimpleErrorEnumHelper simpleErrorEnumHelper = new SimpleErrorEnumHelper(packages);
        Collection<Error> errors = simpleErrorEnumHelper.errors();
        System.out.println(Arrays.toString(errors.toArray()));
        Error error;
        error = simpleErrorEnumHelper.codeOf(DataErrorEnum.DATA_ERROR.getCode());
        if (DataErrorEnum.DATA_ERROR.equals(error)) {
            System.out.println(error + "|" + error.getType().getName());
        }
        error = simpleErrorEnumHelper.codeOf(BusinessErrorEnum.BUSINESS_ERROR.getCode());
        if (BusinessErrorEnum.BUSINESS_ERROR.equals(error)) {
            System.out.println(error + "|" + error.getType().getName());
        }
    }
}
