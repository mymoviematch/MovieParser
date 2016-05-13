package mm.simple.dao.elements;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import mm.simple.dao.BaseDao;
import mm.simple.dao.IDao;
import mm.simple.model.MapMoviePerson;
import mm.simple.model.Movie;
import mm.simple.model.Person;
import mm.simple.model.PersonHelper;
import mm.utils.HibernateSingleton;

public class PersonDao extends BaseDao implements IDao<PersonHelper>{

	@Override
	public void saveList(List<PersonHelper> persons, Movie movie) {
		HibernateSingleton.getInstance();
		 Session session;
		 try{
			 session = HibernateSingleton.getSessionFactory().getCurrentSession();
		 }catch(Exception e){
			 session = HibernateSingleton.getSessionFactory().openSession();
		 }
       // 4. Starting Transaction
       Transaction transaction = session.beginTransaction();
       for(int i=0;i<persons.size();i++){
       	String personName = persons.get(i).person.name;
       try{
       
       	Criteria criteria = session.createCriteria(Person.class);
       	Person person = (Person) criteria.add(Restrictions.eq("name", personName))
       	                             .uniqueResult();
       	
       	MapMoviePerson moviePerson = new MapMoviePerson();
       	
       	moviePerson.role = persons.get(i).role;
       	moviePerson.orderNumber = persons.get(i).order;
       	moviePerson.movie = movie;
       	
       	session.save(moviePerson);
       	if(person==null){
       		session.save(persons.get(i).person);
       		moviePerson.person = persons.get(i).person;
       		System.out.println("got this name: " + personName);
       	}
       	else{
       		System.out.println("person is already in the db: " + person);
       		moviePerson.person = person;
       	}
       	
		 }catch(Exception ex){
			 System.out.println("an exception has occured: " + ex);
	     }
       }
       transaction.commit();
       session.close();
		
	}

	@Override
	public List<PersonHelper> getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(PersonHelper movieElement, Movie movie) {
		// TODO Auto-generated method stub
		
	}

}
