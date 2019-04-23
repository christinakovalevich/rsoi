package lab3;

import sun.plugin2.message.Message;

import javax.jms.JMSException;
import javax.jms.TextMessage;

public class SimpleMessageBean {
    public void onMessage(Message inMessage) {
        TextMessage msg = null;
        try {
            if (inMessage instanceof TextMessage) {
                msg = (TextMessage) inMessage;
                System.out.println
                        ("MESSAGE BEAN: Message received: "
                                + msg.getText());
            } else {
                System.out.println
                        ("Message of wrong type: "
                                + inMessage.getClass().getName());
            }
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (Throwable te) {
            te.printStackTrace();
        }
    }

    public void ejbCreate() {

    }

    public void ejbRemove() {

    }


}
