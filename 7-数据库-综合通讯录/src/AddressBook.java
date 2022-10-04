import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

/*创建通讯录信息表，录入一些人的通讯信息。(手工) 编程实现通讯录的管理 窗口刚显示时，显示全部通讯录在表格中。
添加新朋友：输入姓名、性别、电话，点击“添加”按钮，将该人的信息写入表格中，同时写入到数据库中。
选中表格中的一行，单击“删除”按钮，弹出对话框确认是否真的删除。如果真的删除，则将该行从表格中删除，同时从数据库中删除。*/
public class AddressBook {
    public static void main(String []args)
    {

        try {
            new GUI(new Connector());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
class GUI
{
    Connector connector;
    JFrame jf;
    JTable jTable;
    DefaultTableModel tableModel;
    JButton add,delete;
    JPanel jPanelNorth,jPanelSouth;
    JLabel lName,lSex,lTelephone;
    JTextField tName,tSex,tTelephone;
    Vector columnName;
    GUI(Connector connector) throws Exception
    {
        this.connector=connector;
        jf=new JFrame("AddressBook");
        add=new JButton("添加");
        delete=new JButton("删除");
        jPanelSouth=new JPanel();
        jPanelSouth.add(add);
        jPanelSouth.add(delete);

        columnName=new Vector();
        columnName.add("姓名");
        columnName.add("性别");
        columnName.add("电话");
        tableModel=new DefaultTableModel(connector.getVector(),columnName);
        jTable=new JTable(tableModel);
        JScrollPane jScrollPane=new JScrollPane(jTable);
        lName=new JLabel("姓名");
        lSex=new JLabel("性别");
        lTelephone=new JLabel("电话号码");
        tName=new JTextField(10);
        tSex=new JTextField(10);
        tTelephone=new JTextField(10);
        jPanelNorth=new JPanel();
        jPanelNorth.add(lName);  jPanelNorth.add(tName);
        jPanelNorth.add(lSex);   jPanelNorth.add(tSex);
        jPanelNorth.add(lTelephone); jPanelNorth.add(tTelephone);

        jf.setLayout(new BorderLayout());
        jf.add(BorderLayout.SOUTH,jPanelSouth);
        jf.add(BorderLayout.CENTER,jScrollPane);
        jf.add(BorderLayout.NORTH,jPanelNorth);
        jf.setSize(500,300);
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(3);

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    connector.statement.executeUpdate("INSERT INTO  addressbook VALUES ('"+tName.getText()+"','"+tSex.getText()+"','"+tTelephone.getText()+"')");
                    try {
                        tableModel=new DefaultTableModel(connector.getVector(),columnName);
                        jTable.setModel(tableModel);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int count;
                count =jTable.getSelectedRow();
                System.out.println((String) jTable.getValueAt(count,0));
                try {
                    connector.preparedStatement.setString(1, (String) jTable.getValueAt(count,0));
                    connector.preparedStatement.executeUpdate();//两种方法都可以
                    //connector.statement.executeUpdate("DELETE FROM addressbook where 姓名='"+(String) jTable.getValueAt(count,0)+"'");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                tableModel.removeRow(count);

                jTable.setModel(tableModel);
            }
        });

    }
}
class Connector
{
    Statement statement;
    PreparedStatement preparedStatement;//单门用来使用删除
    Connector()throws Exception
    {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/student?serverTimezone=Asia/Shanghai";
        String name="sunpeiwen";
        String password="sunpeiwen";
        Connection connection= DriverManager.getConnection(url,name,password);
        statement=connection.createStatement();
        ResultSet rs=statement.executeQuery("SELECT * FROM addressbook");
        while(rs.next())
        {
            System.out.println(rs.getString(1));
        }
        preparedStatement=connection.prepareStatement("DELETE FROM addressbook where 姓名=?");
    }
    Vector getVector() throws Exception
    {
        Vector row_v,all_v;
        all_v=new Vector();
        ResultSet rs=statement.executeQuery("SELECT * FROM addressbook");
        while(rs.next())
        {
            row_v=new Vector();
            row_v.add(rs.getString(1));
            row_v.add(rs.getString(2));
            row_v.add(rs.getString(3));
            all_v.add(row_v);
        }
        return all_v;
    }
}
