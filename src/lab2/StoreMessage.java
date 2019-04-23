package lab2;

import javax.xml.soap.*;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.net.MalformedURLException;

public class StoreMessage {

    static String fileXml;
    static SOAPMessage reqMess, respMess;

    public void createSOAPRequest() throws SOAPException, TransformerException {
        MessageFactory factory = MessageFactory.newInstance();
        reqMess = factory.createMessage();
        reqMess.getMimeHeaders().addHeader("SOAPAction", "");
        SOAPPart soapPart = reqMess.getSOAPPart();
        SOAPEnvelope envelope = soapPart.getEnvelope();
        SOAPBody body = envelope.getBody();
        Name bodyName = envelope.createName("GetFile");
        SOAPBodyElement bodyElement = body.addBodyElement(bodyName);
        Name name = envelope.createName("file");
        SOAPElement fileName = bodyElement.addChildElement(name);
        fileName.addTextNode(fileXml);
    }

    public static void displayMessage(SOAPMessage mess) throws SOAPException {
        SOAPBody body = mess.getSOAPBody();
        Transformer transformer = null;
        try {
            transformer = TransformerFactory.newInstance().newTransformer();
            try {
                transformer.transform(new DOMSource(body), new StreamResult(System.out));
            } catch (TransformerException e) {
                e.printStackTrace();
            }
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Enter the file XML:");
            fileXml = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SOAPException, MalformedURLException, TransformerException {
        StoreMessage client = new StoreMessage();
        client.start();
        client.createSOAPRequest();

        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection connection = soapConnectionFactory.createConnection();

        URL endpoint = new URL("http://localhost:8080/axis/services/StoreService");

        respMess = connection.call(reqMess, endpoint);

        displayMessage(respMess);
    }
}


