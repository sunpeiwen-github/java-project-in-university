/*时钟程序：显示当前系统时间*/
import javax.swing.*;
import java.util.Calendar;

public class Watch {
    JFrame jf;
    JLabel jLabel;
    Watch()
    {
        jf=new JFrame("表");
        jf.setSize(150,100);
        jf.setLocationRelativeTo(null);
        jLabel=new JLabel("",JLabel.CENTER);
        jf.add(jLabel);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(3);

       while(true) {
           Calendar calendar=Calendar.getInstance();
           String s=calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE)+":"+calendar.get(Calendar.SECOND);
           System.out.println(s);
           jLabel.setText(s);
           try {
               Thread.sleep(1000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
    }
    public static void main(String []args)
    {
        new Watch();
    }
}
