import java.io.*;
import java.util.*;

public class Employees{
    public String position_name(String position){

        String name= new String();
        String line;
        try{
            FileReader fr = new FileReader("D:/employee1s.txt");
            BufferedReader in = new BufferedReader(fr);
            while ((line=in.readLine())!=null){
                String[] str = line.split(" ");
                if (str[1].equals(position)){
                    name=str[0];
                    break;
                }
                else
                    name="NO SUCH POSITION";
            }
        }catch(IOException e){
            e.printStackTrace();
        }

        return name;
    }
    public String name_position(String name){

        String position= new String();;
        String line;
        try{
            FileReader fr = new FileReader("D:/employee1s.txt");
            BufferedReader in = new BufferedReader(fr);

            while ((line=in.readLine())!=null){
                String[] str = line.split(" ");
                if (str[0].equals(name)){ position=str[1]; break;}else
                    position="NO SUCH NAME";
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return position;
    }
}

