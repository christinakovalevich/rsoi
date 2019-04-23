package lab1;

import org.apache.axis.client.Service;
import org.apache.axis.client.Call;

import javax.xml.rpc.ServiceException;
import java.net.URL;
import java.net.MalformedURLException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;


public class EmployeesApp {
    public static void main(String[] args) throws ServiceException, MalformedURLException {
        String endpoint = "http://localhost:8080/axis/Employees.jws";
        Service service = new Service();
        Call call = (Call) service.createCall();
        call.setTargetEndpointAddress(new URL(endpoint));
        System.out.println("1 - enter the position");
        System.out.println("2 - enter the name");
        System.out.println("3 - exit");

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            String line = null;
            line = in.readLine();
            while (!line.equals("3")) {
                if (line.equals("3")) break;
                if (line.equals("1")) {
                    String position = in.readLine();
                    Object[] param1 = new Object[]{position};
                    String response = (String) call.invoke("position_name", param1);
                    System.out.println("POSITION=" + position + "\n" + "NAME=" + response);

                }
                if (line.equals("2")) {
                    String name = in.readLine();
                    Object[] param2 = new Object[]{name};
                    String response = (String) call.invoke("name_position", param2);
                    System.out.println("NAME=" + name +
                            "\n" + "POSITION=" + response);
                }
                System.out.println("1 - enter the position");
                System.out.println("2 - enter the name");
                System.out.println("3 - exit");
                line = in.readLine();
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
