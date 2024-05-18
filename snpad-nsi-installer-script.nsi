Name "SNotepad"

OutFile "fgroupindonesia\released\Setup_SNotepad_v1.0.0.1.exe"

InstallDir "$PROGRAMFILES\fgroupindonesia\SNotepad"

DirText "Please choose a directory to which you'd like to install this application."

; ----------------------------------------------------------------------------------
; *************************** SECTION FOR INSTALLING *******************************
; ----------------------------------------------------------------------------------

Section "" ; A "useful" name is not needed as we are not installing separate components

; Set output path to the installation directory. Also sets the working
; directory for shortcuts
SetOutPath $INSTDIR\

File fgroupindonesia\snotepad.exe
File fgroupindonesia\snotepad.ico
File fgroupindonesia\snpad-engine.jar

WriteUninstaller $INSTDIR\Uninstall.exe

MessageBox MB_YESNO "Add Supplemental JRE Engine for SNotepad?" IDYES true IDNO false
true:
SetOutPath $APPDATA\fgroupindonesia\

File fgroupindonesia\jdk-11-mini-jre.exe
ExecWait "$APPDATA\fgroupindonesia\jdk-11-mini-jre.exe"

File fgroupindonesia\engine-to-path-win.bat
ExecWait "$APPDATA\fgroupindonesia\engine-to-path-win.bat"
DetailPrint "installing supplementall success!"
Goto next
false:
DetailPrint "skipping supplementall installation!"
next:
DetailPrint "Now creating shortcuts..."
; ///////////////// CREATE SHORT CUTS //////////////////////////////////////

CreateDirectory "$SMPROGRAMS\fgroupindonesia\SNotepad"


CreateShortCut "$SMPROGRAMS\fgroupindonesia\SNotepad\snotepad.lnk" "$INSTDIR\snotepad.exe"
CreateShortCut "$SMPROGRAMS\fgroupindonesia\SNotepad\Uninstall.lnk" "$INSTDIR\Uninstall.exe"

; ///////////////// END CREATING SHORTCUTS //////////////////////////////////

; //////// CREATE REGISTRY KEYS FOR ADD/REMOVE PROGRAMS IN CONTROL PANEL /////////

WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\SNotepad" "DisplayName" "SNotepad (remove only)"
WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\SNotepad" "DisplayIcon" "$INSTDIR\snotepad.exe"
WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\SNotepad" "UninstallString" "$INSTDIR\Uninstall.exe"
WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\SNotepad" "QuietUninstallString" '"$INSTDIR\Uninstall.exe" /S'

; //////////////////////// END CREATING REGISTRY KEYS ////////////////////////////

MessageBox MB_OK "Installation was successful."

SectionEnd

; ----------------------------------------------------------------------------------
; ************************** SECTION FOR UNINSTALLING ******************************
; ----------------------------------------------------------------------------------

Section "Uninstall"
; remove all the files and folders
Delete $INSTDIR\Uninstall.exe ; delete self
Delete $INSTDIR\snotepad.exe
Delete $INSTDIR\snotepad.ico
Delete $INSTDIR\snpad-engine.jar

RMDir $INSTDIR

; now remove all the startmenu links
Delete "$SMPROGRAMS\fgroupindonesia\SNotepad\snotepad.lnk"
Delete "$SMPROGRAMS\fgroupindonesia\SNotepad\Uninstall.lnk"
RMDIR "$SMPROGRAMS\fgroupindonesia\SNotepad"
RMDIR "$SMPROGRAMS\fgroupindonesia"

; now remove all the supplemental files
Delete "$APPDATA\fgroupindonesia\jdk-11-mini-jre.exe"
Delete "$APPDATA\fgroupindonesia\engine-to-path-win.bat"
RMDIR /r "$APPDATA\fgroupindonesia\jdk-11-mini-jre"
RMDIR /r "$APPDATA\fgroupindonesia\snpad"

; Now delete registry keys
DeleteRegKey HKLM "SOFTWARE\SNotepad"
DeleteRegKey HKLM "SOFTWARE\Microsoft\Windows\CurrentVersion\Uninstall\SNotepad"

SectionEnd