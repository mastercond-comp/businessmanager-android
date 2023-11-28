package ru.mastercond;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import android.util.Log;
 
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPSClient;

public class FTPsql {

public void UploadDatabase(String Server, int Port, String User, String Pass, String LocalFile, String RemoteFile) {

    FTPClient ftpClient = new FTPClient();
    
        try {
            
            ftpClient.connect(Server, Port);
            Log.d("ru.mastercond", "Соединение с ftp установлено");
            ftpClient.login(User, Pass);
            Log.d("ru.mastercond", "Авторизация выполнена");
            ftpClient.enterLocalPassiveMode();
 
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            
            // APPROACH #1: uploads first file using an InputStream
            //File firstLocalFile = new File("D:/Test/Projects.zip");
 
            //String firstRemoteFile = "Projects.zip";
            InputStream inputStream = new FileInputStream(new File(LocalFile));
 
            //System.out.println("Start uploading first file");
            boolean done = ftpClient.storeFile(RemoteFile, inputStream);
            inputStream.close();


            if (done) {
                Log.d("ru.mastercond", "Файл успешно выгружен на сервер");
            }
 
           
 
        } catch (Exception ex) {
            //System.out.println("Error: " + ex.getMessage());
           // ex.printStackTrace();
        Log.d("ru.mastercond", ex.toString());

        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
               Log.d("ru.mastercond", ex.toString());;
            }
        }
       
  } 

public void DownloadDatabase(String Server, int Port, String User, String Pass, String LocalFile, String RemoteFile) {
        //String server = "www.myserver.com";
        //int port = 21;
        //String user = "user";
        //String pass = "pass";
 
        FTPClient ftpClient = new FTPClient();
        try {
 
            ftpClient.connect(Server, Port);
            ftpClient.login(User, Pass);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
 
            // APPROACH #1: using retrieveFile(String, OutputStream)
            //String remoteFile1 = "/test/video.mp4";
            //File downloadFile1 = new File("D:/Downloads/video.mp4");
            OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(new File(LocalFile)));
            boolean success = ftpClient.retrieveFile(RemoteFile, outputStream1);
            outputStream1.close();
 
            if (success) {

                Log.d("ru.mastercond", "Файл успешно скачан с сервера");
            }
 
            
 
        } catch (Exception ex) {
           Log.d("ru.mastercond", ex.toString());

        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                //return ex.toString();
            }
        }
      
   }



public void UploadDatabaseSecure(String Server, int Port, String User, String Pass, String LocalFile, String RemoteFile) {

        //String server = "www.myserver.com";
        //int port = 21;
        //String user = "user";
        //String pass = "pass";
 
        FTPSClient ftpClient = new FTPSClient();
        try {
 
            ftpClient.connect(Server, Port);
            ftpClient.login(User, Pass);
            ftpClient.enterLocalPassiveMode();
 
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
 
            // APPROACH #1: uploads first file using an InputStream
            //File firstLocalFile = new File("D:/Test/Projects.zip");
 
            //String firstRemoteFile = "Projects.zip";
            InputStream inputStream = new FileInputStream(new File(LocalFile));
 
            //System.out.println("Start uploading first file");
            boolean done = ftpClient.storeFile(RemoteFile, inputStream);
            inputStream.close();
            if (done) {
                //System.out.println("The first file is uploaded successfully.");
            }
 
           
 
        } catch (IOException ex) {
            //System.out.println("Error: " + ex.getMessage());
           // ex.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                //ex.printStackTrace();
            }
        }
    }
       
   

public void DownloadDatabaseSecure(String Server, int Port, String User, String Pass, String LocalFile, String RemoteFile) {
        //String server = "www.myserver.com";
        //int port = 21;
        //String user = "user";
        //String pass = "pass";
 
        FTPSClient ftpClient = new FTPSClient();
        try {
 
            ftpClient.connect(Server, Port);
            ftpClient.login(User, Pass);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
 
            // APPROACH #1: using retrieveFile(String, OutputStream)
            //String remoteFile1 = "/test/video.mp4";
            //File downloadFile1 = new File("D:/Downloads/video.mp4");
            OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(LocalFile));
            boolean success = ftpClient.retrieveFile(RemoteFile, outputStream1);
            outputStream1.close();
 
            if (success) {

                //return "Файл базы успешно загружен с FTP-сервера";
            }
 
            
 
        } catch (IOException ex) {
           // return ex.toString();

        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                //return ex.toString();
            }
        }
      
   }

}
