package lab3;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class SimpleMessageClient {
    static int NUM_MSGS = 5;
    public static void main(String[] args) {
        Context jndiContext= null;
        try {
            jndiContext = new InitialContext();
            QueueConnectionFactory queueConnectionFactory = (QueueConnectionFactory)
                    jndiContext.lookup("java:comp/env/jms/MyQueueConnectionFactory");
            Queue queue = (Queue)jndiContext.lookup("java:comp/env/jms/QueueName");
            QueueConnection queueConnection =
                    queueConnectionFactory.createQueueConnection();
            QueueSession queueSession =
                    queueConnection.createQueueSession(false,
                            Session.AUTO_ACKNOWLEDGE);
            QueueSender queueSender = queueSession.createSender(queue);
            TextMessage message;
            message = queueSession.createTextMessage();
            for (int i = 0; i < NUM_MSGS; i++) {
                message.setText("This is message " + (i + 1));
                System.out.println("Sending message: " +
                        message.getText());
                queueSender.send(message);
            }

        } catch (NamingException e) {
            e.printStackTrace();
        } catch (JMSException e) {
            e.printStackTrace();
        }


    }
}
