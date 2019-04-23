package lab3;

import javax.xml.ws.Endpoint;

//Endpoint publisher
public class SimpleBeanPublisher{

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:9999/ws/hello", new SimpleMessageBean());
    }

}
