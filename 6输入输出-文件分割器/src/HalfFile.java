import java.io.*;
public class HalfFile {
    File file,newfile1,newfile2;
    FileWriter fw1,fw2;
    FileReader fr;
    HalfFile()
    {
        file=new File("D://Learn/6输入输出-halffile/file.text");
        newfile1=new File("D:/Learn/6输入输出-halffile/newfile1.text");
        newfile2=new File("D:/Learn/6输入输出-halffile/newfile2.text");
        try {
            file.createNewFile();
            newfile1.createNewFile();
            newfile2.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fr=new FileReader(file);
            fw1=new FileWriter(newfile1);
            fw2=new FileWriter(newfile2);
            char[] chars=new char[1024];//字符型数组
           //fr.read(chars);//将数据读到字符数组中的两种方法 实验
            char ch;
            int i;
            for( i=0;fr.ready();i++)
            {
                ch=(char)(fr.read());
                chars[i]=ch;
            }
            System.out.println(chars);//打印
            fw1.write(chars,0,(i+1)/2);
            fw2.write(chars,(i+1)/2-1,(i+1)/2);
            fr.close();
            fw1.close();
            fw2.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch(java.io.IOException e)
        {
            e.printStackTrace();
        }

    }
    public static void main(String[] args)
    {
        new HalfFile();
    }
}
