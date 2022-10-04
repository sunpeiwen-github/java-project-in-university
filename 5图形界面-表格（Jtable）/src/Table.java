/*
创建表格，表格中有数据姓名、性别、年龄、政治面貌。 1）单击“添加行”按钮，输入新的一行数据，然后添加到表格中。 2）选中某一行，单击“删除行”按钮，弹出确认对话框，确认后再删除。
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class Table implements ActionListener
{
    JFrame jf;
    JTable jTable;
    DefaultTableModel tableModel;
    JTextField jTextField;
    JButton add,remove;
    JScrollPane jScrollPane;

    Table()
    {
        jf=new JFrame("表格");
        jf.setLocationRelativeTo(null);
        String[] columnName={"姓名","性别","年龄","政治面貌"};
        tableModel=new DefaultTableModel();
        tableModel.setColumnIdentifiers(columnName);

        jTable=new JTable(tableModel);
        jTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jTextField=new JTextField("输入区");
        add=new JButton("添加行");
        remove=new JButton("删除行");

        jScrollPane=new JScrollPane(jTable);
        jf.setSize(500,300);
        jf.setLayout(new BorderLayout());
        jf.add(BorderLayout.CENTER,jScrollPane);
        JPanel p1=new JPanel();
        p1.add(add);
        p1.add(remove);
        jf.add(BorderLayout.SOUTH,p1);


        jf.setDefaultCloseOperation(3);
        jf.setVisible(true);
        add.addActionListener( this);
        remove.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==add)
        {
            String[] data=new String[jTable.getColumnCount()];
            System.out.println("输入需要添加的数据"+" "+jTable.getColumnCount());
           for(int i=0;i<jTable.getColumnCount();i++)
           {
               data[i]=(new Scanner(System.in)).next();
           }
            tableModel.addRow(data);

        }
        else if(e.getSource()==remove)
        {
           JDialog jDialog=new JDialog(jf);
           jDialog.setSize(120,100);
           jDialog.setLayout(new FlowLayout());
           jDialog.setLocationRelativeTo(null);
           JButton ok=new JButton("确认");
           JButton no =new JButton("取消");
           jDialog.add(ok);
           jDialog.add(no);
           jDialog.setVisible(true);
           ok.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   int rownum=jTable.getSelectedRow();
                   tableModel.removeRow(rownum);
                   jDialog.setVisible(false);
               }
           });
           no.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   jDialog.setVisible(false);
               }
           });

        }
    }
    public static void main(String []args)
    {
        new Table();
    }
}
