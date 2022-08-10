import org.hibernate.Session;
import org.hibernate.Transaction;


import com.samples.domain.products;
import com.samples.utils.HibernateUtil;

public class addProdTest {

	public static void main(String[] args) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		session.beginTransaction();
		
		products prod = new products("7", "Dish-washer", "2"); 
			
		session.save(prod);
			
		session.getTransaction().commit();
			
		session.close();

		
		
		
	}
	
}
