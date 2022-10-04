/*
* 学生类的成员变量包括姓名（字符串）、院系（字符串）、年龄（整型）和平均成绩（实型）。
* 输入多个学生的信息，创建学生对象，使用数据流将其保存到文件中，然后再从文件中读取并显示学生信息。*/
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
class Student
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
class Students
{
    Student[] stu;
    int count=0;
    Students()
    {
        stu=new Student[20];
        System.out.println("请输入姓名 院系 年龄 平局成绩");
        System.out.println("以end结尾");
        Student s1;

        String name,department,s;
        int age;
        float grade;
        Scanner sc=new Scanner(System.in);
        int i=0;
        while (true)
        {
            s=sc.next();
            if(s.equals("end"))
            {
                System.out.println("跳出循环");
                break;
            }
            name=s;
            department=sc.next();
            age=sc.nextInt();
            grade=sc.nextFloat();
            count++;
            s1=new Student(name,department,age,grade);

            //  s1=this.in();
            stu[i]=s1;
            System.out.println(stu[i].name+stu[i].department+stu[i].age+stu[i].grade);
            i++;
        }

    }
    int getcount()
    {
        return count;
    }
}

public class GetStudent {
    File file;
    DataInputStream br;
    DataOutputStream bw;

    GetStudent()
    {
        file = new File("D:/java_Learn/6输入输出-学生信息（数据流）/file.text");//改过路径了
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("是否存在文件" + file.exists());
        Students students = new Students();

        System.out.println("开始输入");
        try {
            br = new DataInputStream(new FileInputStream(file));
            bw = new DataOutputStream(new FileOutputStream(file));
            int i = 0;
            try {
                while (i < (students.getcount()))//问题
                {System.out.println("输入的是"+students.stu[i].getName()+students.stu[i].getDepartment()+students.stu[i].getAge()+students.stu[i].getGrade());
                    bw.writeUTF(students.stu[i].getName());
                    bw.writeUTF(students.stu[i].getDepartment());
                    bw.writeInt(students.stu[i].getAge());
                    bw.writeFloat(students.stu[i].getGrade());
                    i++;
                    //System.out.println("i=" + i + "count=" + students.getcount());
                }
                bw.close();
            }catch (java.io.IOException e)
            {
                System.out.println("输入有问题");
            }
            try {
                for (int j = 0; j < students.getcount(); j++) {
                    System.out.println("输出的结果是");
                    System.out.println(br.readUTF() + br.readUTF() + br.readInt() + br.readFloat());
                }
                br.close();
            }
            catch (java.io.IOException e)
            {
                System.out.println("输出有问题");
            }
        } catch (FileNotFoundException e) {
            System.out.println("找不到文件");
        }

    }

    public static void main(String[] args) {
        new GetStudent();
    }
}