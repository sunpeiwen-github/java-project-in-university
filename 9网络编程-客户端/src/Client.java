import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    Client()
    {int port=10000;
        try {
            while(true) {
                Socket socket = new Socket(InetAddress.getLocalHost(), port);
                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
                DataInputStream dataInputStream = new DataInputStream(inputStream);
                System.out.print("服务器:");
                System.out.println(dataInputStream.readUTF());
                Scanner sc = new Scanner(System.in);
                System.out.print("我:");
                String s = sc.next();
                dataOutputStream.writeUTF(s);
            }
           // socket.close();
            //dataInputStream.close();
            //dataOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[]args)
    {
        new Client();
    }
}
