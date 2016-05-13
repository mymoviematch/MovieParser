package mm.simple.dao.elements;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import mm.simple.dao.BaseDao;
import mm.simple.dao.IDao;
import mm.simple.model.Genre;
import mm.simple.model.Movie;
import mm.utils.HibernateSingleton;


public class GenreDao extends BaseDao implements IDao<Genre>{

	@Override
	public void saveList(List<Genre> genres, Movie movie) {
		 HibernateSingleton.getInstance();
         Session session = HibernateSingleton.getSessionFactory().openSession();
         System.out.println("this is the session: " + session);
         // 4. Starting Transaction
         Transaction transaction = session.beginTransaction();
         
         for(int i=0;i<genres.size();i++){
          	String genreName = genres.get(i).nameEn;
         try{
          	Criteria criteria = session.createCriteria(Genre.class);
          	Genre genreFound = (Genre) criteria.add(Restrictions.eq("nameEn", genreName))
          	                             .uniqueResult();
          	
          	
          	if(genreFound==null){
          		genres.get(i).addMovie(movie);    	
          		movie.addGenre(genres.get(i));
          		session.save(genres.get(i));
          		session.update(movie);
          	}
          	
          	else{
          		genreFound.addMovie(movie);
          		movie.addGenre(genreFound);
          		session.update(genreFound);
          		session.update(movie);
          		System.out.println("genre is already in the db -> from else");
          	}
          	}catch(Exception ex){
         	 System.out.println("erroreer: " + ex);
          }
          
          }
          transaction.commit();
		
	}

	@Override
	public List<Genre> getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Genre movieElement, Movie movie) {
		// TODO Auto-generated method stub
		
	}

}
