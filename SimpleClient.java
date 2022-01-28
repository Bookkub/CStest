import java.net.*;
import java.io.*;
import java.util.Scanner;
public class SimpleClient{
    public static void main(String args[]) throws IOException{
        try {
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
        } catch (Exception e) {
            ServerSocket s = new ServerSocket(1234);
            Socket s1 = s.accept();
            InputStream s1In = s1.getInputStream();
            DataInputStream dis = new DataInputStream(s1In);
            String word_1 = new String(dis.readUTF());
            String word_2 = new String(dis.readUTF());
            int i, k=0, flag = 0, len_1 = 0, len_2 = 0;
            len_1 = word_1.length();
            len_2 = word_2.length();
            word_1 = word_1.toLowerCase();
            word_2 = word_2.toLowerCase();
            char[] ch_1 = word_1.toCharArray();
            char[] ch_2 = word_2.toCharArray();
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
            
            OutputStream s1out = s1.getOutputStream();
            DataOutputStream dos = new DataOutputStream(s1out);
            if (flag == 1) {
                System.out.println("These are Kangaroo words.");
                dos.writeUTF("These are Kangaroo words.");
            } else if (flag == 2) {
                System.out.println("Finding words character less than main words.");
                dos.writeUTF("Finding words character less than main words.");
            } else {
                System.out.println("These are not Kangaroo words.");
                dos.writeUTF("These are not Kangaroo words.");
            }
            dis.close();
            s1In.close();
            s1.close();
        }
    }
      
}
