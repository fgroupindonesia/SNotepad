package snotepad.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.util.Properties;

/**
 * Project : SNotepad
 * File : SettingProperties.java
 * @author FGroupIndonesia
 */
public class SettingProperties {

    public static String KEY_AUTO_PUBLIC_LINK = "auto_public_link";
    public static String KEY_AUTORUNS = "autoruns";
    public static String KEY_AUTO_COPY = "auto_copy";

    static String filePath = System.getenv("APPDATA") + "\\fgroupindonesia\\snpad";
    static String fileName = "settings.dat";
    static String fileIcon = "snotepad.ico";
    static String fileExe = "snotepad.exe";

    static Properties properties = new Properties();

    private static String getAppPath() {
        Class<?> clazz = SettingProperties.class;
        ProtectionDomain protectionDomain = clazz.getProtectionDomain();
        CodeSource codeSource = protectionDomain.getCodeSource();
        URL location = codeSource.getLocation();
        String fileProgramPath = new File(location.getFile()).getParent().replaceAll("%20", " ");

        return fileProgramPath;
    }

    public static String getAppFileName() {
        return fileExe;
    }
    
    public static String getAppCompletePath() {

        return new File(getAppPath(), fileExe).getAbsolutePath();

    }

    public static String getIconCompletePath() {
        return new File(getAppPath(), fileIcon).getAbsolutePath();
    }

    public static boolean isExist() {
        return new File(filePath, fileName).exists();
    }

    public static String loadData(String k) {

        String res = null;

        try {

            File file = new File(filePath, fileName);

            FileInputStream fileIn = new FileInputStream(file);
            properties.load(fileIn);
            fileIn.close();

            // System.out.println("Read properties:");
            for (String key : properties.stringPropertyNames()) {
                if (key.equalsIgnoreCase(k)) {
                    res = properties.getProperty(key);
                }
            }

        } catch (Exception e) {
            System.err.println("File Settings not found.");
        }

        return res;
    }

    public static void writeData(String k, String v) {

        properties.setProperty(k, v);

        try {

            File file = new File(filePath, fileName);
            file.getParentFile().mkdirs(); // Create parent directories if they don't exist

            FileOutputStream fileOut = new FileOutputStream(file);
            properties.store(fileOut, "snpad");
            fileOut.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error at writing Setting Properties #29!");
        }

    }

}
