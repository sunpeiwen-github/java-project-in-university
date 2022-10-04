import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class HalfFile {
    File file,newfile1,newfile2;
    FileWriter fw;
    FileReader fr;
    HalfFile()
    {
        file=new File("file://D:/Learn/6输入输出-halffile/file.text");
        file.createNewFile("file://D:/Learn/6输入输出-halffile/file.text");
    }
}
