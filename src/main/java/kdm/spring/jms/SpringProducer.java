package kdm.spring.jms;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.*;

/**
 * Created by Kasun Dinesh on 10-Aug-16.
 */
public class SpringProducer {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        JmsTemplate jmsTemplate = (JmsTemplate) context.getBean("jmsTemplate");
        jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) {
                TextMessage textMessage = null;
                try {
                    textMessage = session.createTextMessage();
                    textMessage.setText("hello");
                } catch (JMSException e) {
                    e.printStackTrace();
                }
                return textMessage;
            }
        });
        System.out.println("Message sent");
    }
}
