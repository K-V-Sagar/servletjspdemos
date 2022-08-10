package hibernatetests;

import org.hibernate.Session;

import com.samples.domain.Message;
import com.samples.utils.HibernateUtil;

public class HibernateTest {

	public static void main(String[] args) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		session.beginTransaction();
		
		Message message = new Message("Hello world with Hibernate and annotations2"); 
		
		session.save(message);
		
		session.getTransaction().commit();
		
		session.close();
		
		
		/*
		 * To insert multiple messages:
		 * 
		 * Message message1 = new Message("Hello world with Hibernate and annotations1"); 
		 * Message message2 = new Message("Hello world with Hibernate and annotations2"); 
		 * Message message3 = new Message("Hello world with Hibernate and annotations3"); 
		 * 
		 * session.save(message1);
		 * session.save(message2);
		 * session.save(message3);
		 * 
		 * session.getTransaction().commit();
		 * 
		 * 
		 * To add multiple values to one row:
		 * 
		 * Message message = new Message(1,"Hello");
		 * 
		 */
		
	}
}