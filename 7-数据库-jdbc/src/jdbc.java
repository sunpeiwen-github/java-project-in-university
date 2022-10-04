import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
/*先将connector复制进程序
* 文件->项目结构->‘+’->jars->选connector
* */
public class jdbc {
    jdbc()throws Exception
    {
        Class.forName("com.mysql.cj.jdbc.Driver");//加载驱动器
        String url="jdbc:mysql://localhost:3306/student?serverTimezone=Asia/Shanghai";//设置url  IP地址+端口号+数据库名+？时区
        String name="sunpeiwen";
        String password="sunpeiwen";
        Connection connection= DriverManager.getConnection(url,name,password);
        Statement statement=connection.createStatement();
        String sql="select * from studentgrade1 ";
        ResultSet resultSet=statement.executeQuery(sql);
        while(resultSet.next()) {
            System.out.println(resultSet.getString("ID"));
        }
    }
    public static void main(String []args)
    {
        try {
            new jdbc();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
