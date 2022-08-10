import org.hibernate.Session;
import org.hibernate.Transaction;

import com.samples.domain.Address;
import com.samples.domain.User;
import com.samples.utils.HibernateUtil;

public class CompositionTest {

	public static void main(String[] args) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction txn = session.getTransaction();

		try {

			txn.begin();

			Address billAddress = new Address("12", "Main Street", "Chennai", "612345");
			Address shipAddress = new Address("65/12", "Busy Street", "Trichy", "412385");
			User user = new User("Ajay", billAddress, shipAddress, 34);
			session.save(user);

			txn.commit();

		} catch (Exception ex) {
			if (txn != null) {
				txn.rollback();
			}
			ex.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
	}
	
}