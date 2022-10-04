import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
public class Boiler //锅炉
 {
     public static void main(String[]args)
     {
         File file=new File("D:\\Learn\\8线程-电厂锅炉的数据读入输出\\file.txt");
         if(!file.exists())
         {
             try {
                 file.createNewFile();
             } catch (IOException e) {
                 e.printStackTrace();
             }
         }
             try {
                 BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                 int a=0;
                 while(true) {
                     for (int i = 0; i < 5; i++) {
                         bw.write((int)(1+Math.random()*(10-1+1))+" "+(int)(1+Math.random()*(10-1+1)));
                         bw.newLine();
                     }
                     try {
                         Thread.sleep(20000);
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }
                     a++;
                     if(a>10)
                     {
                         break;
                     }
                 }
                 bw.close();
             } catch (IOException e) {
                 e.printStackTrace();
             }
     //    new OutputThread(file).start();
         new InputThread(file).start();
     }

}
class OutputThread extends Thread {
    File file;
    BufferedWriter bufferedWriter;

    OutputThread(File file) {
        this.file = file;
    }

    public void run() {
      /*  System.out.println(file.getAbsolutePath());
        try {
            bufferedWriter=new BufferedWriter(new FileWriter(file));
            while(true) {
                for (int i = 0; i < 5; i++) {
                    System.out.println("输入部分"+i);
                    bufferedWriter.write("d");
                    bufferedWriter.newLine();
                }
                try {
                    Thread.sleep(20000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int a=0;
                a++;
                if(a==10)
                {break;}
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            int a = 0;
            while (true) {
                for (int i = 0; i < 5; i++) {
                    bw.write("aaa" + i);
                    bw.newLine();
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                a++;
                if (a > 10) {
                    break;
                }
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
class InputThread extends Thread
{
    File file;
    BufferedReader bufferedReader;
    InputThread(File file)
    {
        this.file=file;
    }
    public void run()
    {
        JFrame jFrame=new JFrame("锅炉温度压力检测");
        JTable jTable;
        DefaultTableModel tableModel;
        String columnName[]={"温度","压力"};
        tableModel=new DefaultTableModel();
        jTable=new JTable(tableModel);
        JScrollPane jScrollPane=new JScrollPane(jTable);
        jFrame.add(jScrollPane);
        jFrame.setSize(200,300);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(3);
        String [][]data=new String[5][2];
        try {
            bufferedReader=new BufferedReader(new FileReader(file));
            while(true) {
                for (int i = 0; i < 5; i++) {
                    System.out.println(bufferedReader.readLine());
                    data[i]=bufferedReader.readLine().split(" ");
                }
                for(int j=0;j<5;j++)
                {
                    for(int i=0;i<2;i++)
                    {
                        System.out.println(data[j][i]);
                    }
                }
                tableModel=new DefaultTableModel(data,columnName);
                jTable.setModel(tableModel);
                try {
                    Thread.sleep(30000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int a=0;
                a++;
                if(a==10)
                {break;}
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
