import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

/*
创建树，并显示在框架中
* */
class Tree
{
    JFrame jf;
    JTree jTree;
    Tree()
    {
        jf=new JFrame();
        DefaultMutableTreeNode root=new DefaultMutableTreeNode("华北电力大学");
        DefaultMutableTreeNode s1=new DefaultMutableTreeNode("控计学院");
        DefaultMutableTreeNode s2=new DefaultMutableTreeNode("经管学院");
        DefaultMutableTreeNode s3=new DefaultMutableTreeNode("能动学院");
        root.add(s1);
        root.add(s2);
        root.add(s3);
        jTree=new JTree(root);
        JScrollPane jScrollPane=new JScrollPane(jTree);
        jf.add(jScrollPane);
        jf.setSize(200,300);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(3);
    }
    public static void main(String[]args)
    {
        new Tree();
    }
}