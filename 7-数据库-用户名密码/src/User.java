import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class User {
    public static void main(String []args)
    {
        try{
     new UserGui();
         }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
class UserGui
{
    JFrame jf;
    JTextField jTextField;
    JPasswordField jPasswordField;
    JLabel userName,password;
    JPanel jPanel;
    JButton login;
    Connector connector;
    UserGui()throws Exception
    {   connector=new Connector();//创建连接类
        jf=new JFrame("登陆界面");
        jTextField=new JTextField(23);
        jPasswordField=new JPasswordField(23);
        userName=new JLabel("用户名");
        password=new JLabel("密码");
        login=new JButton("登录");
        jPanel=new JPanel();
        jPanel.setLayout(new FlowLayout());
        jPanel.add(userName);
        jPanel.add(jTextField);
        jPanel.add(password);
        jPanel.add(jPasswordField);
        jf.setLayout(new BorderLayout());
        jf.add(jPanel);
        JPanel jPanel1=new JPanel();
        jPanel1.setLayout(new FlowLayout());
        jPanel1.add(login);
        jf.add(BorderLayout.SOUTH,jPanel1);
        jf.setSize(300,200);
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(3);
     /*   System.out.println(connector.name(jTextField.getText()));
        System.out.println(connector.password(jPasswordField.getText()));*/
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(connector.judge(jTextField.getText(),jPasswordField.getText()))
                    {
                        jTextField.setText("");
                        jPasswordField.setText("");
                        JOptionPane.showMessageDialog(jf,"登陆成功","",JOptionPane.PLAIN_MESSAGE);
                    }
                    else
                    {
                        jTextField.setText("");
                        jPasswordField.setText("");
                        JOptionPane.showMessageDialog(jf,"信息错误","",JOptionPane.PLAIN_MESSAGE);
                    }
                    /*else if(connector.name(jTextField.getText()))
                    {
                        jTextField.setText("cuol");
                    }
                    else if(connector.password(jPasswordField.getText()))
                    {

                    }*/
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
    }
}
class Connector
{
    Statement statement;

    Connector()throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");//加载驱动器
        String url = "jdbc:mysql://localhost:3306/user?serverTimezone=Asia/Shanghai";//设置url  IP地址+端口号+数据库名+？时区
        String name = "sunpeiwen";
        String password = "sunpeiwen";
        Connection connection = DriverManager.getConnection(url, name, password);
        statement = connection.createStatement();
    }
    boolean judge(String s,String p)throws Exception
    {
        boolean a=false;
        ResultSet resultSet=statement.executeQuery("select * from users;");
        while(resultSet.next())
        {
            /*System.out.println(resultSet.getString(1));
        System.out.println(resultSet.getString(2));*/
            if(resultSet.getString("username").equals(s))
            {
               // System.out.println("第一");
                if(resultSet.getString("password").equals(p))
                {
                  //  System.out.println("第二");
                    a=true;
                }
                break;
            }
        }
        return a;
    }
}