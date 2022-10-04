import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Student {
    public Student()throws Exception {

        Class.forName("com.mysql.cj.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/studnet2?serverTimezone=UTC";//设置url  IP地址+端口号+数据库名+？时区
        String name="sunpeiwen";
        String password="sunpeiwen";
        Connection connection=DriverManager.getConnection(url,name,password);
        String sql="select * from stu";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        ResultSet rs=preparedStatement.executeQuery();
        Vector column=new Vector<>();
        Vector data=new Vector<>();
        while(rs.next())
        {
            Vector vrow=new Vector<>();
            vrow.add(rs.getString(1));
            vrow.add(rs.getString(2));
            vrow.add(rs.getString(3));
            vrow.add(rs.getString(4));
            vrow.add(rs.getString(5));
            data.add(vrow);
        }
        column.add("name");
        column.add("sex");
        column.add("grade");
        column.add("date");
        column.add("department");

        DefaultTableModel tableModel=new DefaultTableModel(data,column);
       JTable jTable=new JTable(tableModel);
        JFrame jf=new JFrame();
        JScrollPane jScrollPane=new JScrollPane(jTable);
        jf.add(jScrollPane);
        jf.setSize(300,200);
        jf.setVisible(true);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(3);
    }
    public static void main(String []args)
    {
        try {
            new Student();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
