package DaoImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import Dao.UserDao;
import Model.User;
@Repository

@Service
public class UserDaoImpl implements UserDao{
	@Autowired
    SessionFactory sessionFactory;
@Autowired
	public  UserDaoImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
		
		
	}
	
	public   UserDaoImpl() {
		System.out.println("user dao successfully created");
	}
	
	public void insertUser(User user) {
		// TODO Auto-generated method stubSession session =sessionFactory.openSession();
		Session session =sessionFactory.openSession();
		session.beginTransaction();
		session.saveOrUpdate(user);
		session.getTransaction().commit();
		
		
	}
	
	public List<User> getAllUser() {
		// TODO Auto-generated method stubSession session =sessionFactory.openSession();
		Session session =sessionFactory.openSession();
		Query query=session.createQuery("from User");
		query.list();
		List<User> user=query.list();
		
		return user;
	}
	
	public User getUser(int id) {
		// TODO Auto-generated method stubpublic User getUser(int  id) {
		Session session =sessionFactory.openSession();
		User user=(User)session.get(User.class, id);
		return user;
	
	}
	public void updateUser(User user) {
		// TODO Auto-generated method stubSession session =sessionFactory.openSession();
		Session session =sessionFactory.openSession();
		session.beginTransaction();
		session.update(user);
		session.getTransaction().commit();
		
		
	}
	public void deleteUser(User user) {
		// TODO Auto-generated method stubSession session =sessionFactory.openSession();
		Session session =sessionFactory.openSession();
		session.beginTransaction();
		session.delete(user);
		session.getTransaction().commit();
		
	}
	public User getUserById(String email) {
		// TODO Auto-generated method stubSession session=sessionFactory.openSession();
		Session session =sessionFactory.openSession();
		Query query=session.createQuery("from User where email=:emailid");
		query.setParameter("emailid",email);
	//	User user=(User)session.get(User.class,email);
		User user=(User) query.uniqueResult();
		session.close();
		return user;
		
	}

}
