package mm.simple.dao.elements;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import mm.simple.dao.BaseDao;
import mm.simple.dao.IDao;
import mm.simple.model.Country;
import mm.simple.model.Movie;
import mm.utils.HibernateSingleton;


public class CountryDao extends BaseDao implements IDao<Country>{

	@Override
	public void saveList(List<Country> countries, Movie movie) {
		 HibernateSingleton.getInstance();
         Session session = HibernateSingleton.getSessionFactory().openSession();
         System.out.println("this is the session: " + session);
         // 4. Starting Transaction
         Transaction transaction = session.beginTransaction();
         for(int i=0;i<countries.size();i++){
         	String countryName = countries.get(i).iso_639_1;
         try{
         	Criteria criteria = session.createCriteria(Country.class);
         	Country country = (Country) criteria.add(Restrictions.eq("iso_639_1", countryName))
         	                             .uniqueResult();
         	
         	
         	if(country==null){
         		System.out.println("Country was not found!");
         		countries.get(i).addMovie(movie);    	
          		movie.addCountry(countries.get(i));
          		session.save(countries.get(i));
          		session.update(movie);
         	}
         	else{
         		country.addMovie(movie);
          		movie.addCountry(country);
          		session.update(country);
          		session.update(movie);
         		System.out.println("Country is alerady in the db -> from else");
         	}
         	
         	}catch(Exception ex){
        	 System.out.println("country is already in the db: " + ex);
         }
         
         }
         transaction.commit();
         session.close();
		
	}

	@Override
	public List<Country> getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Country movieElement, Movie movie) {
		// TODO Auto-generated method stub
		
	}

}
