import java.net.*;
import java.io.*;
import java.util.Scanner;
public class SimpleClient{
    public static void main(String args[]) throws IOException{
        Socket s1 = new Socket("172.25.16.1",1234);
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

        ServerSocket s = new ServerSocket(1234);
        Socket s2 = s.accept();
        InputStream s2In = s2.getInputStream();
        DataInputStream d = new DataInputStream(s2In);
        String a1 = new String(d.readUTF());
        String a2 = new String(d.readUTF());
        int i, k=0, flag = 0, len_1 = 0, len_2 = 0;
        len_1 = a1.length();
        len_2 = a2.length();
        a1 = a1.toLowerCase();
        a2 = a2.toLowerCase();
        char[] ch_1 = a1.toCharArray();
        char[] ch_2 = a2.toCharArray();
        if(len_1<len_2)
        {
            flag = 2;
        }
        else
        {
            for (i = 0; i < len_1; i++) 
            {
                if (ch_1[i] == ch_2[k]) 
                {
                    k++;
                    if (k == len_2) {
                        flag = 1;
                        break;
                    }
                }
            }
        }
        
        OutputStream s2out = s2.getOutputStream();
        DataOutputStream v = new DataOutputStream(s2out);
        if (flag == 1) {
            System.out.println("These are Kangaroo words.");
            v.writeUTF("These are Kangaroo words.");
        } else if (flag == 2) {
            System.out.println("Finding words character less than main words.");
            v.writeUTF("Finding words character less than main words.");
        } else {
            System.out.println("These are not Kangaroo words.");
            v.writeUTF("These are not Kangaroo words.");
        }
        d.close();
        s2In.close();
        s2.close();
    }
}