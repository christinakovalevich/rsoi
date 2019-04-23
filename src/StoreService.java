import org.apache.axis.message.SOAPBodyElement;
import org.apache.axis.message.SOAPEnvelope;
import org.apache.axis.AxisFault;
import org.apache.axis.MessageContext;
import org.w3c.dom.*;
import org.w3c.dom.Node;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import javax.xml.transform.TransformerException;
import javax.xml.soap.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import java.io.IOException;

public class StoreService {

    public String getFileName(SOAPEnvelope req) throws AxisFault, SOAPException {
        SOAPBodyElement reqBody = (SOAPBodyElement) req.getBodyElements().get(0);
        String str = "";
        NodeList nodes = reqBody.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node currentNode = nodes.item(i);
            if (currentNode instanceof Element) {
                Element element = (Element) currentNode;
                Text textNode = (Text) element.getFirstChild();
                str = textNode.getData().trim();
            }
        }
        return str;
    }

    public SOAPEnvelope createSOAPResponse(String fileName, SOAPEnvelope resp) throws SOAPException, ParserConfigurationException,
            IOException, SAXException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = dbFactory.newDocumentBuilder();
        System.out.println(fileName);
//        Document document = builder.parse("C:\Users\chris\Downloads\apache-tomcat-9.0.16\webapps\axis\WEB-INF\classes" + fileName)
//        todo file path
        Document document = builder.parse(fileName);
        MessageContext msgCntxt = MessageContext.getCurrentContext();
        SOAPMessage respMess = msgCntxt.getMessage();

        SOAPPart soapPart = respMess.getSOAPPart();
        SOAPEnvelope envelope = (SOAPEnvelope) soapPart.getEnvelope();
        SOAPHeader header = resp.getHeader();

        SOAPBody body = resp.getBody();
        //header.detachNode();
        SOAPBodyElement docElement = (SOAPBodyElement) body.addDocument(document);
        return envelope;
    }

    public void storeService(SOAPEnvelope req, SOAPEnvelope resp) throws Exception, TransformerException,
            IOException {
        String fileName = getFileName(req);
        resp = createSOAPResponse(fileName, resp);
    }
}





