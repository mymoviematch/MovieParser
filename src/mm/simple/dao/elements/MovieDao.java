package mm.simple.dao.elements;

import java.util.List;





import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;






import mm.simple.dao.BaseDao;
import mm.simple.dao.IDao;
import mm.simple.model.Country;
import mm.simple.model.Genre;
import mm.simple.model.MapMoviePerson;
import mm.simple.model.Movie;
import mm.simple.model.MovieElement;
import mm.simple.model.Person;
import mm.simple.model.PersonHelper;
import mm.simple.model.Picture;
import mm.simple.model.helper.MovieHelper;
import mm.utils.HibernateSingleton;

public class MovieDao extends BaseDao implements IDao<Movie>{
	
	public List<Movie> getAllMovies(){
		HibernateSingleton.getInstance();
        Session session = HibernateSingleton.getSessionFactory().openSession();
        System.out.println("this is the session: " + session);
        // 4. Starting Transaction
        Transaction transaction = session.beginTransaction();
        
        List<Movie> movies = session.createCriteria(Movie.class).list();
        
        return movies;
	}

	public List<Picture> getAllPicturesByMovieId(long movie_id){
		HibernateSingleton.getInstance();
        Session session = HibernateSingleton.getSessionFactory().openSession();
        System.out.println("this is the session: " + session);
        // 4. Starting Transaction
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Picture.class);
        List<Picture> pictures  = criteria.add(Restrictions.eq("movie.movie_id", movie_id)).list();
        
        return pictures;
	}
	

	
	public Movie addMovie(Movie movie) {
	        try {

	            HibernateSingleton.getInstance();
	            Session session = HibernateSingleton.getSessionFactory().openSession();
	           
	            Transaction transaction = session.beginTransaction();
	          
	           	try{
	            session.save(movie);
	            transaction.commit();
	            session.close();
	           	}catch(Exception e){
	           		System.out.println("movie already in DB or: " + e);
	           		return null;
	           		}
	            return movie;
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	            System.out.println("errorr");
	        }
	       return null;
	    }
	
	public MovieHelper getMovie(Long movieId){
		MovieHelper movieInfo = new MovieHelper();
		List<MapMoviePerson> persons;
		List<Picture> pictures;
		Movie movie;
		
        HibernateSingleton.getInstance();
        Session session = HibernateSingleton.getSessionFactory().openSession();
       
        Transaction transaction = session.beginTransaction();
        
        Criteria criteria = session.createCriteria(MapMoviePerson.class);
        persons = criteria.add(Restrictions.eq("movie.movie_id", movieId)).list();
        
        criteria = session.createCriteria(Picture.class);
        pictures = criteria.add(Restrictions.eq("movie.movie_id", movieId)).list();
        
        criteria = session.createCriteria(Movie.class);
        movie = (Movie) criteria.add(Restrictions.eq("movie_id", movieId)).uniqueResult();
        
        movieInfo.setMovie(movie);
        movieInfo.setPersons(persons);

        return movieInfo;
	}

	@Override
	public void saveList(List<Movie> movieElements, Movie movie){
		
	}
	
	@Override
	public void save(Movie movieElements, Movie movie) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Movie> getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}
