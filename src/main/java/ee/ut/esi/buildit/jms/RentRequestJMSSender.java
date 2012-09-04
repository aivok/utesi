package ee.ut.esi.buildit.jms;

import javax.jms.Queue;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.ObjectMessage;

import ee.ut.esi.buildit.model.EquipmentRentRequest;

@Stateless
public class RentRequestJMSSender {
	
	  @Resource(mappedName = "jms/RentRequestConnectionFactory")
	  private ConnectionFactory connectionFactory;
	  @Resource(mappedName = "jms/RentRequestQueue")
	  private Queue queue;
	  
	  public void sendMessage(EquipmentRentRequest request){
		  MessageProducer messageProducer;
		  ObjectMessage message;
		  try {
			  Connection connection = connectionFactory.createConnection();
		      Session session = connection.createSession(false,
		        Session.AUTO_ACKNOWLEDGE);
		     
		      messageProducer = session.createProducer(queue);
		
		      message = session.createObjectMessage();
		      
		      message.setObject(request);
		      
		      messageProducer.send(message);
		      
		      messageProducer.close();
		      session.close();
		      connection.close();
	      
		  } catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		  }
	  }

}
