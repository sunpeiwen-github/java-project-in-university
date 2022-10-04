/*
3.文件中存有一些人的通讯方式（姓名、性别、电话号码、类型），一个人的信息占一行。(手工)
 1）刚运行程序时，将全部通讯录信息显示在表格中。
2）输入姓名、性别、电话，点击“添加”按钮，将该人的信息添加到表格中。保存
3）选中表格中的一行，单击“删除”按钮，弹出对话框确认是否真的删除。如果真的删除，则将该行从表格中删除，同时从数据库中删除。
4）选择树状控件中的某个类型，在表格控件中只显示该类型的人员信息。
 5）点击“保存”按钮，将表格中的数据存入文件。（选做）
*/
//保存功能没有完成
import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class AddressBook implements ActionListener {
    JFrame jf;
    JTree jTree;
    DefaultMutableTreeNode root,node_friend,node_classmate,node_family;
    JTable jtable;
    DefaultTableModel tableModel;
    JPanel jPanel_north,jPanel_south,jPanel_right;
    JLabel name,sex,phoneNum;
    JTextField textName,textPhone;
    JRadioButton man,woman;
    JButton b_add,b_delete,b_save;
    File file;
    String allData;//保存原始数据

    AddressBook()
    {
        jf=new JFrame("通讯录");
        jf.setSize(700,500);
        root=new DefaultMutableTreeNode("所有人");
        node_classmate=new DefaultMutableTreeNode("同学");
        node_family=new DefaultMutableTreeNode("家人");
        node_friend=new DefaultMutableTreeNode("朋友");
        root.add(node_classmate);
        root.add(node_family);
        root.add(node_friend);
        jTree=new JTree(root);
        JScrollPane jScrollPane_tree=new JScrollPane(jTree);//tree的滚动条
        jf.setLayout(new BorderLayout());
        jf.add(BorderLayout.WEST,jScrollPane_tree);

        char []data=new char[1024];
        file=new File("D:\\Learn\\5图形界面-通讯录（综合）\\file.txt");
        try {
            FileReader fileReader=new FileReader(file);
            try {
                fileReader.read(data);
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        allData=new String(data);
        String s=allData;
        s+=" ";
        System.out.println(s);
        int count=4;//行数没有得到 这里有问题
        String [][]strings=new String[count][4];//数组的数目 行数
        for(int i=0;i<count;i++)
        {
            for(int j=0;j<4;j++) {
                int index;
                index = s.indexOf(" ");//就看空格输完一行后加空格
                /*if(j!=3)
                {
                    index = s.indexOf(" ");
                }
                else
                {
                    index = s.indexOf("\r\n");
                }*/
                if(index!=-1)
                {
                    strings[i][j] = s.substring(0, index);
                    s = s.substring(index + 1);
                }
                else
                {
                    strings[i][j]=s;
                }
            }
        }
        for(int i=0;i<count;i++)
        {
            for(int j=0;j<4;j++)
            {
                System.out.println(strings[i][j]);
            }
        }
        String[] columnName={"姓名","性别","电话号码","类型"};
        tableModel=new DefaultTableModel(strings,columnName);
        jtable=new JTable(tableModel);
        JScrollPane jScrollPane_table=new JScrollPane(jtable);//table的滚动条
        jPanel_north=new JPanel();
        name=new JLabel("姓名");
        sex=new JLabel("性别");
        phoneNum=new JLabel("电话号码");
        textName=new JTextField(15);
        textPhone=new JTextField(15);
        man=new JRadioButton("男");
        woman=new JRadioButton("女");
        ButtonGroup buttonGroup=new ButtonGroup();
        buttonGroup.add(man);
        buttonGroup.add(woman);
        jPanel_north.setLayout(new FlowLayout());
        jPanel_north.add(name);
        jPanel_north.add(textName);
        jPanel_north.add(sex);
        jPanel_north.add(man);
        jPanel_north.add(woman);
        jPanel_north.add(phoneNum);
        jPanel_north.add(textPhone);
        jPanel_south=new JPanel();
        jPanel_south.setLayout(new FlowLayout());
        b_add=new JButton("添加");
        b_delete=new JButton("删除");
        b_save=new JButton("保存");
        jPanel_south.add(b_add);
        jPanel_south.add(b_delete);
        jPanel_south.add(b_save);

        jPanel_right=new JPanel();
        jPanel_right.setLayout(new BorderLayout());
        jPanel_right.add(BorderLayout.NORTH,jPanel_north);
        jPanel_right.add(BorderLayout.CENTER,jScrollPane_table);
        jPanel_right.add(BorderLayout.SOUTH,jPanel_south);
        jf.add(BorderLayout.CENTER,jPanel_right);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(3);
        b_add.addActionListener(this);
        b_delete.addActionListener(this);
        b_save.addActionListener(this);
        jTree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode selectedNode=(DefaultMutableTreeNode) jTree.getLastSelectedPathComponent();
                if(selectedNode!=root)
                {
                    for(int i=0;i<tableModel.getRowCount();)
                    {
                        if(((String)(tableModel.getValueAt(i,3))).indexOf(selectedNode.toString())==-1)
                            tableModel.removeRow(i);
                        else
                            i++;
                        jtable.setModel(tableModel);
                    }
                }
                else if(selectedNode==root)
                {
                    allData=new String(data);
                    String s=allData;
                    s+=" ";
                    System.out.println(s);
                    int count=4;
                    String [][]strings=new String[count][4];//数组的数目 行数
                    for(int i=0;i<count;i++)
                    {
                        for(int j=0;j<4;j++) {
                            int index;
                            index = s.indexOf(" ");//就看空格输完一行后加空格
                /*if(j!=3)
                {
                    index = s.indexOf(" ");
                }
                else
                {
                    index = s.indexOf("\r\n");
                }*/
                            if(index!=-1)
                            {
                                strings[i][j] = s.substring(0, index);
                                s = s.substring(index + 1);
                            }
                            else
                            {
                                strings[i][j]=s;
                            }
                        }
                    }
                    for(int i=0;i<count;i++)
                    {
                        for(int j=0;j<4;j++)
                        {
                            System.out.println(strings[i][j]);
                        }
                    }
                    String[] columnName={"姓名","性别","电话号码","类型"};
                    tableModel=new DefaultTableModel(strings,columnName);
                    jtable.setModel(tableModel);
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==b_add)
        {
            String name1=textName.getText();
            String phone1=textPhone.getText();
            String sex1="";
            if(man.isSelected())
            { sex1="男";}
            else if(woman.isSelected())
            {sex1="女";}

            String relationship="";
            if((DefaultMutableTreeNode)jTree.getLastSelectedPathComponent() ==node_classmate)
            {
                relationship="朋友";
            }
            else if((DefaultMutableTreeNode)jTree.getLastSelectedPathComponent() ==node_family)
            {
                relationship="家人";
            }
            else if((DefaultMutableTreeNode) jTree.getLastSelectedPathComponent() ==node_classmate)
            {
                relationship="同学";
            }
            String[]s={name1,sex1,phone1,relationship};
            tableModel.addRow(s);
        }
        else if(e.getSource()==b_delete)
        {
            int count=jtable.getSelectedRow();
            tableModel.removeRow(count);
        }
        else if(e.getSource()==b_save)
        {
            /*Vector data=tableModel.getDataVector();
            //for(int i=0;i<data.size();i++)
            //System.out.print(data.elementAt(i));
            System.out.println(data);
            */
            String[][] data=new String[tableModel.getRowCount()][4];
            for(int i=0;i<tableModel.getRowCount();i++) {
                for (int j=0;j<4;j++)
                {
                    data[i][j]=(String) tableModel.getValueAt(i,j);
                    data[i][j]+=" ";
                }
            }
            for(int i=0;i<tableModel.getRowCount();i++) {
                for (int j=0;j<4;j++)
                {
                    System.out.println(data[i][j]);
                }
            }
            try {
                FileWriter fileWriter=new FileWriter(file);
                for(int i=0;i<tableModel.getRowCount();i++) {
                    for (int j=0;j<4;j++)
                    {
                        fileWriter.write(data[i][j]);
                    }
                }
                fileWriter.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }


        }
    }

    public static void main(String[]args)
    {
        new AddressBook();
    }
}
