package snotepad;

/**
 * Project : SNotepad
 * File : MainApp.java
 * @author FGroupIndonesia
 */
public class MainApp {

    public static void main(String[] args) {
        MainFrame mf = new MainFrame();

        if (args.length == 0) {
            
            mf.runMe();
            
        }else{
            
            mf.runMeWithFileOpened(args[0]);
        
        }

        // detection if any parameter received into it,
        // thus we open a new tab and along with its file content
    }

}
