package snotepad.helper;

import com.sun.jna.platform.win32.Advapi32Util;
import com.sun.jna.platform.win32.WinReg;

/**
 *
 * @author pc-i3 gen4
 */

public class RegistryWorks {

    private static String keyName = "SNotepad";
    private static String keyIcon = "Icon";
    private static String keyMRU = "MRUList";
    private static String keyA = "a";
    private static String fileDesc = "SNotepad File";
    private static String appUserModelDesc = "FGroupIndonesia." + keyName;
    private static String fileExtension = ".snpad";
    private static String registryKeyRun = "Software\\Microsoft\\Windows\\CurrentVersion\\Run";
    private static String registryKeyOpenWithAlways = "Software\\Microsoft\\Windows\\CurrentVersion\\Explorer\\FileExts\\" + fileExtension;
    private static String registryKeyOpenWithAlwaysList = registryKeyOpenWithAlways + "\\OpenWithList";
    private static String registryKeyExt = "Software\\Classes\\" + fileExtension;
    private static String registryKeyExtInner = "Software\\Classes\\" + fileExtension + "\\OpenWithProgids";
    private static String registryKeyOpenWith = "Software\\Classes\\" + keyName + fileExtension;
    private static String registryKeyOpenWithDefaultIcon = "Software\\Classes\\" + keyName + fileExtension + "\\DefaultIcon";
    private static String registryKeyOpenWithShell = "Software\\Classes\\" + keyName + fileExtension + "\\shell";
    private static String registryKeyOpenWithShellOpen = "Software\\Classes\\" + keyName + fileExtension + "\\shell\\open";
    private static String registryKeyOpenWithShellOpenCmd = "Software\\Classes\\" + keyName + fileExtension + "\\shell\\open\\command";

    private static String keyDefault = "";
    private static String keyAppUserModelID = "AppUserModelID";

    /* for testing purposes
      public static void main(String[] args) {
        RegistryWorks t = new RegistryWorks();
        RegistryWorks.addOpenWithDefaultHandler();
    }*/
    private static String getIconLocation() {
        return SettingProperties.getIconCompletePath();
    }

    private static String getAppLocation() {
        return SettingProperties.getAppCompletePath();
    }

    private static String getAppFileNameOnly() {
        return SettingProperties.getAppFileName();
    }

    private static String withQuotes(String val) {
        return "\"" + val + "\"";
    }

    private static String getOpenCommandCompleteParameter() {
// making a complete parameter with spaces, and percentage of 1
// for example : 
// "C:\Users\pc-i3 gen4\AppData\Local\Programs\Microsoft VS Code\Code.exe" "%1"
        return withQuotes(getAppLocation()) + " " + withQuotes("%1");
    }

    public static void deleteAutoruns() {

        Advapi32Util.registryDeleteValue(WinReg.HKEY_LOCAL_MACHINE, registryKeyRun, keyName);

    }

    public static void addAutoruns() {

        Advapi32Util.registrySetStringValue(WinReg.HKEY_LOCAL_MACHINE, registryKeyRun, keyName, withQuotes(getAppLocation()));

    }

    public static void addOpenWithDefaultHandler() {

        Advapi32Util.registryCreateKey(WinReg.HKEY_CURRENT_USER, registryKeyExt);
        Advapi32Util.registryCreateKey(WinReg.HKEY_CURRENT_USER, registryKeyExtInner);
        Advapi32Util.registrySetStringValue(WinReg.HKEY_CURRENT_USER, registryKeyExtInner, keyName + fileExtension, "");

        Advapi32Util.registryCreateKey(WinReg.HKEY_CURRENT_USER, registryKeyOpenWith);
        Advapi32Util.registrySetStringValue(WinReg.HKEY_CURRENT_USER, registryKeyOpenWith, keyDefault, fileDesc);
        Advapi32Util.registrySetStringValue(WinReg.HKEY_CURRENT_USER, registryKeyOpenWith, keyAppUserModelID, appUserModelDesc);

        Advapi32Util.registryCreateKey(WinReg.HKEY_CURRENT_USER, registryKeyOpenWithDefaultIcon);
        Advapi32Util.registrySetStringValue(WinReg.HKEY_CURRENT_USER, registryKeyOpenWithDefaultIcon, keyDefault, getIconLocation());

        Advapi32Util.registryCreateKey(WinReg.HKEY_CURRENT_USER, registryKeyOpenWithShell);
        Advapi32Util.registryCreateKey(WinReg.HKEY_CURRENT_USER, registryKeyOpenWithShellOpen);
        Advapi32Util.registrySetStringValue(WinReg.HKEY_CURRENT_USER, registryKeyOpenWithShellOpen, keyIcon, withQuotes(getAppLocation()));

        Advapi32Util.registryCreateKey(WinReg.HKEY_CURRENT_USER, registryKeyOpenWithShellOpenCmd);
        Advapi32Util.registrySetStringValue(WinReg.HKEY_CURRENT_USER, registryKeyOpenWithShellOpenCmd, keyDefault, getOpenCommandCompleteParameter());

        Advapi32Util.registryCreateKey(WinReg.HKEY_CURRENT_USER, registryKeyOpenWithAlways);
        Advapi32Util.registryCreateKey(WinReg.HKEY_CURRENT_USER, registryKeyOpenWithAlwaysList);

        Advapi32Util.registrySetStringValue(WinReg.HKEY_CURRENT_USER, registryKeyOpenWithAlwaysList, keyDefault, getAppFileNameOnly());
        Advapi32Util.registrySetStringValue(WinReg.HKEY_CURRENT_USER, registryKeyOpenWithAlwaysList, keyA, getAppFileNameOnly());
        Advapi32Util.registrySetStringValue(WinReg.HKEY_CURRENT_USER, registryKeyOpenWithAlwaysList, keyMRU, keyA);

    }

    public static void deleteOpenWithDefaultHandler() {
        Advapi32Util.registryDeleteKey(WinReg.HKEY_LOCAL_MACHINE, registryKeyExt);
        Advapi32Util.registryDeleteKey(WinReg.HKEY_LOCAL_MACHINE, registryKeyOpenWith);
    }

}
