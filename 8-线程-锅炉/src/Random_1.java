//发电厂锅炉的数据包括温度和压力，保存在文件中，一共5个锅炉，编写两个线程。 
//    1）写数据线程：每隔20秒钟产生10个随机数，表示5个锅炉的压力和温度数据，存入文件。     
//2）读数据线程：每隔30秒钟从文件中读取每个锅炉的温度和压力，显示在表格中。
//package thread;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.Vector;

class SetRandom implements Runnable {
    private File file = new File("data.txt");

    public void run() {
        double random;
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (this) {
                for (int i = 0; i < 10; i++) {
                    random = Math.random() * 1000;
                    try (FileWriter writer = new FileWriter(file, true);
                         BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
                        bufferedWriter.write(random + "");
                        bufferedWriter.newLine();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }
}

class GetRandom implements Runnable {
    JTable table;
    DefaultTableModel tableModel;
    private File file = new File("data.txt");
    int i = 0;
    Vector vectordata = new Vector();
    Vector vector = new Vector();
    Vector vector1 = new Vector();
    JScrollPane scrollPane;
    JFrame frame;
    String s;

    public void run() {
        frame = new JFrame();
        vector1.add("温度");
        vector1.add("压力");
        tableModel = new DefaultTableModel(vectordata, vector1);

        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);

        frame.add(scrollPane);
        frame.setVisible(true);
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        while (true) {
            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (this) {
                try (FileReader reader = new FileReader(file);
                     BufferedReader bufferedReader = new BufferedReader(reader)) {
                    while (( s = bufferedReader.readLine()) != null) {
                        vector.add(s);
                        i++;
                        if (i % 2 == 0) {
                            Vector vector2 = new Vector(vector);
                            vectordata.add(vector2);
                            vector.removeAllElements();
                        }
                    }
                    Vector vectordata1 = new Vector(vectordata);
                    tableModel = new DefaultTableModel(vectordata1, vector1);
                    table.setModel(tableModel);
                    i = 0;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                vectordata.removeAllElements();
            }
        }
    }
}

public class Random_1 {
    public static void main(String[] args) {
        Thread thread = new Thread(new SetRandom());
        Thread thread1 = new Thread(new GetRandom());
        thread.start();
        thread1.start();
    }
}