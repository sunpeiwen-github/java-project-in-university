/*
* 学生信息包括姓名（字符串）、院系（字符串）、年龄（整型）和平均成绩（实型）。
* 输入多个学生的信息，使用缓冲流将其保存到文件中，一个学生信息占一行，然后再从文件中读取并显示出来。
* */
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
/*    Student in()
    {
        String name,department,s;
        int age;
        float grade;
        Scanner sc=new Scanner(System.in);
        s=sc.nextLine();
        if(s.equals("end"))
        {return null;}
        name=s;
        department=sc.nextLine();
        age=sc.nextInt();
        grade=sc.nextFloat();
        count++;
        return new Student(name,department,age,grade);
    }
   */
    String tooString(Student s)
    {
        String a=s.name+" "+s.department+" "+s.age+" "+s.grade;
        return a;
    }
    int getcount()
    {
        return count;
    }
}

public class GetStudent {
    File file;
    BufferedReader br;
    BufferedWriter bw;

    GetStudent()
    {
        file = new File("D:/java_Learn/6输入输出-学生信息（缓冲流的应用）/file.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("是否存在文件" + file.exists());
        Students students = new Students();

        System.out.println("开始输入");
        try {
            br = new BufferedReader(new FileReader(file));
            System.out.println(file.getAbsolutePath());
            bw = new BufferedWriter(new FileWriter(file));
            int i = 0;
            while (i<(students.getcount()))//问题
            {
                System.out.println(students.tooString(students.stu[i]));
                bw.write(students.tooString(students.stu[i]));
                bw.newLine();
                i++;
                System.out.println("i="+i+"count="+students.getcount());
            }
            bw.close();
            System.out.println("结果是");
            String str;
            while((str=br.readLine())!=null)
            {
                System.out.println(str);
            }
           // System.out.println(br.readLine());

          /*  char[] put=new char[1024];
                br.read(put);
            System.out.println(put.toString());//输出有问题
*/

            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("找不到文件");
        }
        catch (IOException e) {
            System.out.println("输入或输出错误");
        }
    }

    public static void main(String[] args) {
        new GetStudent();
    }
 }