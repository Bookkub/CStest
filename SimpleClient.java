import java.net.*;
import java.io.*;
import java.util.Scanner;
public class SimpleClient{
    public static void main(String args[]) throws IOException{
        Socket s1 = new Socket("172.30.32.1",1234);
        OutputStream s1out = s1.getOutputStream();
        DataOutputStream dos = new DataOutputStream(s1out);
        System.out.print("Input your word : ");
        Scanner word_1 = new Scanner(System.in);
        String w_1 = word_1.nextLine();
        dos.writeUTF(w_1);
        dos.flush();
        System.out.print("What word do you want to find? : ");
        Scanner word_2 = new Scanner(System.in);
        String w_2 = word_2.nextLine();
        dos.writeUTF(w_2);
        InputStream s1In = s1.getInputStream();
        DataInputStream dis = new DataInputStream(s1In);
        String sen_1 = new String(dis.readUTF());
        System.out.print(sen_1);
        // dis.readUTF(sen_1);
        dos.close();
        s1out.close();
        s1.close();
    }
}