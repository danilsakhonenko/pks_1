package pks.ftpclient;

public class MainClass {
    public static void main(String[] args) {  
        FtpClient ftp = new FtpClient("localhost", "admin", "kras77kvar48");  
        ftp.connect();  
        //String[] temp = ftp.listNames("/tuxlog/cbs");  
        //System.out.println("connect sucess");  
       // System.out.println(temp.length);  
        ftp.disconnect();  
    }  
}
