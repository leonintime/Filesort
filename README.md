# Filesort
A program that is supposed to help you to manage your desktop and download folder.

ENVIRONMENT VARIABLES YOU NEED TO SET in C:\Windows\System32\SystemPropertiesAdvanced

OR You can just copy and paste this command into your powershell to set them. (You need to insert the correct User)

[System.Environment]::SetEnvironmentVariable('MoveFolder1', 'C:\Users\YourUser\Downloads\',[System.EnvironmentVariableTarget]::Machine)
[System.Environment]::SetEnvironmentVariable('MoveFolder2', 'C:\Users\YourUser\Desktop\',[System.EnvironmentVariableTarget]::Machine)
[System.Environment]::SetEnvironmentVariable('Pdf_files', 'C:\Users\YourUser\Desktop\Pdfs\',[System.EnvironmentVariableTarget]::Machine)
[System.Environment]::SetEnvironmentVariable('Word_files', 'C:\Users\YourUser\Desktop\Word_Dokumente\',[System.EnvironmentVariableTarget]::Machine)
[System.Environment]::SetEnvironmentVariable('Excel_files', 'C:\Users\YourUser\Desktop\Excel_files\',[System.EnvironmentVariableTarget]::Machine)
[System.Environment]::SetEnvironmentVariable('Powerpoints', 'C:\Users\YourUser\Desktop\Powerpoints\',[System.EnvironmentVariableTarget]::Machine)
[System.Environment]::SetEnvironmentVariable('Pictures', 'C:\Users\YourUser\Pictures\',[System.EnvironmentVariableTarget]::Machine)

After you created the environment variables, restart your device. 


