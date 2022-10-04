/*
文本编辑器
1.打开文件：弹出打开文件对话框，读取文件中的内容，显示在文本区中。
 2.保存按钮：弹出文件保存对话框，将文本区的内容保存到文件中。
  3.关闭按钮：弹出对话框询问是否要保存，如果要保存，则弹出文件保存对话框，将文本区的内容保存到文件中。清空文本区。
*/
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Text {
    JFrame jf;
    Text()
    {
        jf=new JFrame("记事本");
        jf.setSize(300,500);
        JTextArea jTextArea=new JTextArea();
        JScrollPane jScrollPane=new JScrollPane(jTextArea);
        jf.add(jScrollPane);
        JMenuBar jMenuBar=new JMenuBar();
        JMenu jMenu=new JMenu("菜单");
        JMenuItem fileOpen=new JMenuItem("打开");
        JMenuItem fileSave=new JMenuItem("保存");
        JMenuItem fileClose=new JMenuItem("关闭");
        jf.setJMenuBar(jMenuBar);
        jMenuBar.add(jMenu);
        jMenu.add(fileOpen);
        jMenu.add(fileSave);
        jMenu.add(fileClose);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(3);
        fileOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser=new JFileChooser("D:\\Learn\\6输入输出-记事本");
                if(jFileChooser.showOpenDialog(jf)==JFileChooser.APPROVE_OPTION)
                {
                    File file=jFileChooser.getSelectedFile();
                    try {
                        FileReader fileReader=new FileReader(file);
                        char[] buf=new char[1024];
                        int count=0;
                        jTextArea.setText("");
                        while((count=fileReader.read(buf))!=-1)
                        {
                            jTextArea.append(new String(buf,0,count));
                        }
                        fileReader.close();
                    } catch (FileNotFoundException fileNotFoundException) {
                        fileNotFoundException.printStackTrace();
                    }
                    catch (IOException e1)
                    {
                        e1.printStackTrace();
                    }
                }
            }
        });
        fileSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser=new JFileChooser("D:\\Learn\\6输入输出-记事本");
                if(jFileChooser.showSaveDialog(jf)==JFileChooser.APPROVE_OPTION)
                {
                    File file=jFileChooser.getSelectedFile();
                    try {
                        FileWriter fileWriter=new FileWriter(file);
                        String s=jTextArea.getText();
                        fileWriter.write(s);
                        fileWriter.close();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }

            }
        });
        fileClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane jOptionPane=new JOptionPane();
                if(jOptionPane.showConfirmDialog(jf,"是否保存")==JOptionPane.YES_OPTION)
                {
                    JFileChooser jFileChooser=new JFileChooser("D:\\Learn\\6输入输出-记事本");
                    if(jFileChooser.showSaveDialog(jf)==JFileChooser.APPROVE_OPTION)
                    {
                        File file=jFileChooser.getSelectedFile();
                        try {
                            FileWriter fileWriter=new FileWriter(file);
                            String s=jTextArea.getText();
                            fileWriter.write(s);
                            fileWriter.close();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                }
                else
                {
                    System.exit(0);
                }

            }
        });
    }
    public static void main(String[]args)
    {
        new Text();
    }
}
