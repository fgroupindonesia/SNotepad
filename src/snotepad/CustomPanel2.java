package snotepad;

import java.awt.Font;
import java.awt.Robot;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

/**
 *
 * @author FGroupIndonesia
 */
public class CustomPanel2 extends javax.swing.JPanel {

    private int nomer;
    private String originalText;
    private String codeText;
    private boolean locked;
    private boolean saved;
    private File fileObject;
    private String fileName;
    private String completeLocation;
    private StringBuffer stb = new StringBuffer();
    private JTabbedPane tabbedPanel;
    private JMenuItem menuLock;
    private JMenuItem menuSave;
    private JButton btn_save;

    public void setSavedStatus(boolean b) {
        saved = b;

        addMark();
    }

    public void changeTitleOfTab(String newName) {
        tabbedPanel.setTitleAt(nomer, newName);
    }

    public void appendText(String n) {

        stb.append(n);
        this.setOriginalText(stb.toString());

        setSavedStatus(false);

        addMark();
    }

    int fontSize;
    Font font = null;

    public void makeBiggerFont() {
        int newSize = jTextArea.getFont().getSize();
        newSize += 2;
        jTextArea.setFont(new Font(jTextArea.getFont().getName(), jTextArea.getFont().getStyle(), newSize));
    }

    public void makeSmallerFont() {
        int newSize = jTextArea.getFont().getSize();
        newSize -= 2;
        jTextArea.setFont(new Font(jTextArea.getFont().getName(), jTextArea.getFont().getStyle(), newSize));

    }

    public boolean isSaved() {
        return saved;
    }

    private void addMark() {
        String title = tabbedPanel.getTitleAt(nomer);

        if (!title.contains("*")) {
            if (!isSaved()) {
                changeTitleOfTab(title + "*");
            }
        } else {
            if (isSaved()) {
                changeTitleOfTab(title.replace("*", ""));
            }
        }

    }

    public  void clear() {
        stb = new StringBuffer();
        this.setOriginalText(stb.toString());

    }

    public void lock() {
        // this will automatically toggle
        locked = !locked;
        refreshRender();

        // disable the editor
        jTextArea.setEnabled(!locked);
    }

    public void setNewOriginalText(String n){
        this.clear();
        this.appendText(n);
        this.refreshRender();
    }
    
    public void refreshRender() {
        if (isLockOrNot()) {
            jTextArea.setText(this.getCodeText());
        } else {
            jTextArea.setText(this.getOriginalText());
        }
    }

    /**
     * @return the originalText
     */
    public String getOriginalText() {
        return originalText;
    }

    /**
     * @param originalText the originalText to set
     */
    public void setOriginalText(String originalText) {
        this.originalText = originalText;
    }

    /**
     * @return the codeText
     */
    public String getCodeText() {
        return codeText;
    }

    /**
     * @param codeText the codeText to set
     */
    public void setCodeText(String codeText) {
        this.codeText = codeText;
    }

    /**
     * @return the lockOrNot
     */
    public boolean isLockOrNot() {
        return locked;
    }

    /**
     * @param lockOrNot the lockOrNot to set
     */
    public void setLockOrNot(boolean lockOrNot) {
        this.locked = lockOrNot;
    }

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return the completeLocation
     */
    public String getCompleteLocation() {
        return completeLocation;
    }

    /**
     * @param completeLocation the completeLocation to set
     */
    public void setCompleteLocation(String completeLocation) {
        this.completeLocation = completeLocation;
    }

    /**
     * Creates new form CustomPanel2
     */
    public CustomPanel2() {
        initComponents();
        applyDropable();
    }

    public CustomPanel2(JTabbedPane jtb, int nomerX,
            JMenuItem menuLockNa, JMenuItem menuSaveNa,
            JButton btnSaveNa) {
        initComponents();
        tabbedPanel = jtb;
        nomer = nomerX;
        menuLock = menuLockNa;
        menuSave = menuSaveNa;
        btn_save = btnSaveNa;
        
        applyDropable();
    }

    private void applyDropable() {

        jTextArea.setDropTarget(new DropTarget() {
            public synchronized void drop(DropTargetDropEvent evt) {
                try {

                    evt.acceptDrop(DnDConstants.ACTION_COPY);
                    Transferable transferable = evt.getTransferable();

                    if (transferable.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                        java.util.List<File> files = (java.util.List<File>) transferable.getTransferData(DataFlavor.javaFileListFlavor);
                        if (!files.isEmpty()) {
                            File file = files.get(0);
                            // clear the notepad first
                            clear();
                            readFileContent(file);

                            // to make the cursor at the beginning of the content
                            jTextArea.setCaretPosition(0);
                        }
                    }

                } catch (Exception ex) {
                    System.err.println("");
                }
            }
        });

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea = new javax.swing.JTextArea();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        jTextArea.setColumns(20);
        jTextArea.setRows(5);
        jTextArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextAreaKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTextArea);

        add(jScrollPane1);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextAreaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextAreaKeyPressed

        if (evt.isControlDown() && evt.getKeyCode() == KeyEvent.VK_EQUALS) {

            makeBiggerFont();

        } else if (evt.isControlDown() && evt.getKeyCode() == KeyEvent.VK_MINUS) {

            makeSmallerFont();

        } else if (evt.isControlDown() && evt.getKeyCode() == KeyEvent.VK_E) {

            toggleWrapMode();
            System.out.println("Detek");

        } else if (evt.isControlDown() && evt.isAltDown() && (evt.getKeyCode() == KeyEvent.VK_U || evt.getKeyCode() == KeyEvent.VK_L)) {

            if (isLockOrNot()) {
                menuLock.setText("Unlock");
            } else {
                menuLock.setText("Lock");
            }

            this.lock();

        } else {

            // toggle the save button
            menuSave.setEnabled(true);
            btn_save.setEnabled(true);
            this.appendText(jTextArea.getText());

        }

    }//GEN-LAST:event_jTextAreaKeyPressed

    private boolean isBackSpace(KeyEvent evt) {
        boolean t = false;
        int number = evt.getKeyChar();

        if (number == 8) {
            t = true;
            System.out.println("Backspace");
        }

        return t;
    }

    boolean ctrlPressed;
    boolean shiftPressed;
    boolean selectAllPressed;

    boolean wrapMode = true;

    public void toggleWrapMode() {
        this.wrapMode = !wrapMode;

        this.jTextArea.setLineWrap(wrapMode);
        this.jTextArea.setWrapStyleWord(wrapMode);

    }

    private void readFileContent(File file) {
        try {

            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line;
            while ((line = reader.readLine()) != null) {
                appendText(line);
                appendText("\n");
            }

            this.jTextArea.setText(getOriginalText());

        } catch (Exception e) {
            System.err.println("Error when reading file content...");
        }
        
    }

    public boolean isEmptyEditor() {
        if (jTextArea.getText().length() == 0) {
            return true;
        }

        return false;
    }

    private void backspace() {
        // deleting one char from the original text
        stb = new StringBuffer();
        this.appendText(
                getOriginalText().substring(0, getOriginalText().length() - 1));

    }

    void highlightText() {

        this.jTextArea.requestFocus();

        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_A);
            robot.keyRelease(KeyEvent.VK_A);
            robot.keyRelease(KeyEvent.VK_CONTROL);
        } catch (Exception e) {

        }

    }

    void pasteText() {

         this.jTextArea.requestFocus();

        
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
        } catch (Exception e) {

        }

    }

    private class EditorPane extends JTextArea {

        public EditorPane() {
            super();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the fileObject
     */
    public File getFileObject() {
        return fileObject;
    }

    /**
     * @param fileObject the fileObject to set
     */
    public void setFileObject(File fileObject) {
        this.fileObject = fileObject;
    }
}
