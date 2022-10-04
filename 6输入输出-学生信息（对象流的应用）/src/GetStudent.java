/*
* 学生类的成员变量包括姓名（字符串）、院系（字符串）、年龄（整型）和平均成绩（实型）。
* 输入3个学生的信息，创建学生对象，使用对象流将其保存到文件中，
* 然后再从文件中读取并显示学生信息。*/
import java.io.*;
import java.util.Scanner;
/*
孙培文
控计
19
2.9
章
景观
5
3.9
*/
class Student implements Serializable
{
    String name,department;
    int age;
    float grade;
    Student(String name,String department,int age,float grade)
    {
        this.name=name;
        this.department=department;
        this.age=age;
        this.grade=grade;
    }
    String getName()
    {return name;}
    String getDepartment()
    {return department;}
    int getAge()
    {return  age;}
    float getGrade()
    {return  grade;}
}
class GetStudent
{
    int count=0;
    GetStudent()
    {
        File file=new File("D:\\Learn\\6输入输出-学生信息（对象流的应用）\\file.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));


                Student[] students = new Student[20];
                Scanner sc = new Scanner(System.in);
                System.out.println("输入姓名 学院 年龄 平均学分");
                System.out.println("以end结尾");
                for (int i = 0; ; i++)
                {
                    String a = sc.next();
                    if (a.equals("end"))
                    { break; }
                    students[i] = new Student(a, sc.next(), sc.nextInt(), sc.nextFloat());
                    count++;
                    objectOutputStream.writeObject(students[i]);
                }
                objectOutputStream.close();
            }
               catch (IOException e)
        {
            e.printStackTrace();
        }


        try {
            ObjectInputStream objectInputStream=new ObjectInputStream(new FileInputStream(file));
            for(int i=0;i<count;i++)
            {
                try {
                    Student stu=(Student) objectInputStream.readObject();
                    System.out.println("姓名"+stu.name+"学院"+stu.department+"年龄"+stu.age+"平均成绩"+stu.grade);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new GetStudent();
    }
}