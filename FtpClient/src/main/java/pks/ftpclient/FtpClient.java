package pks.ftpclient;

//import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileNotFoundException;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.InputStream;  
import java.io.OutputStream;  
import org.apache.commons.net.ftp.FTPClient;  
import org.apache.commons.net.ftp.FTPFile;  
import org.apache.commons.net.ftp.FTPReply;

public class FtpClient {
    private String server;
    private String username;
    private String password;
    private FTPClient ftp;
    private boolean binaryTransfer = false; 
  

    public FtpClient(String server) {  
        this.server = server;  
        ftp = new FTPClient();
    }  
  
    public boolean login(String username, String password){
        this.username=username;
        this.password=password;
        try{
        if (ftp.login(username, password)) {  
            ftp.enterLocalPassiveMode(); 
            System.out.println("Connected");
            return true;  
        }
        }catch (IOException e) {  
            if (ftp.isConnected()) {  
                try {  
                    ftp.disconnect();  
                } catch (IOException f) {  
                }  
            }  
            System.out.println("Could not connect to server."+e);  
        }
        return false;
    }
    
    public boolean connect() {  
        int reply;  
        ftp.connect(server);   
        reply = ftp.getReplyCode();  
  
        if (FTPReply.isPositiveCompletion(reply)) {  
            return true;
        } else {  
            ftp.disconnect();  
            System.out.println("FTP server refused connection."); 
            return false;  
        }
    }
    
    private boolean put(String remoteAbsoluteFile, String localAbsoluteFile) {  
        InputStream input = null;  
        try {  
                         // // Устанавливаем тип передачи файла  
            if (binaryTransfer) {  
                ftp.setFileType(FTPClient.BINARY_FILE_TYPE);  
            } else {  
                ftp.setFileType(FTPClient.ASCII_FILE_TYPE);  
            }  
                         // обрабатывать передачу  
            input = new FileInputStream(localAbsoluteFile);  
            ftp.storeFile(remoteAbsoluteFile, input);    
            input.close();   
            return true;  
        } catch (FileNotFoundException e) {  
            System.out.println("local file not found."+e);  
        } catch (IOException e1) {  
            System.out.println("Could put file to server."+ e1);  
        } finally {  
            try {  
                if (input != null) {  
                    input.close();  
                }  
            } catch (Exception e2) {  
            }  
        }  
  
        return false;  
    }  
   
    public boolean get(String remoteAbsoluteFile, String localAbsoluteFile,  
            boolean delFile) {  
        OutputStream output = null;  
        try {  
                         // Установить тип передачи файла  
            if (binaryTransfer) {  
                ftp.setFileType(FTPClient.BINARY_FILE_TYPE);  
            } else {  
                ftp.setFileType(FTPClient.ASCII_FILE_TYPE);  
            }  
                         // обрабатывать передачу  
            output = new FileOutputStream(localAbsoluteFile);  
            ftp.retrieveFile(remoteAbsoluteFile, output);  
            output.close();  
                         if (delFile) {// удалить удаленный файл  
                ftp.deleteFile(remoteAbsoluteFile);  
            }  
            return true;  
        } catch (FileNotFoundException e) {  
            System.out.println("local file not found."+e);  
        } catch (IOException e1) {  
            System.out.println("Could get file from server."+e1);  
        } finally {  
            try {  
                if (output != null) {  
                    output.close();  
                }  
            } catch (IOException e2) {  
            }  
        }  
        return false;  
    }  
  

    public String[] listNames(String remotePath) {  
        String[] fileNames = null;  
        try {  
            FTPFile[] remotefiles = ftp.listFiles(remotePath);  
            fileNames = new String[remotefiles.length];  
            for (int i = 0; i < remotefiles.length; i++) {  
                fileNames[i] = remotefiles[i].getName();  
            }  
  
        } catch (IOException e) {  
            System.out.println("Could not list file from server."+e);  
        }  
        return fileNames;  
    }  
    
    public void disconnect() {  
        try {  
            ftp.logout();  
            if (ftp.isConnected()) {  
                ftp.disconnect();
                System.out.println("Disconnected.");  
            }  
        } catch (IOException e) {  
            System.out.println("Could not disconnect from server."+ e);  
        }  
    }  

    public boolean isBinaryTransfer() {  
        return binaryTransfer;  
    }  
  
    
    public void setBinaryTransfer(boolean binaryTransfer) {  
        this.binaryTransfer = binaryTransfer;  
    }  
}
