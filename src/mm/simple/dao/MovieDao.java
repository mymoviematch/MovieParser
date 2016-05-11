package mm.simple.dao;

import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;



import mm.simple.model.Country;
import mm.simple.model.Genre;
import mm.simple.model.MapMoviePerson;
import mm.simple.model.Movie;
import mm.simple.model.Person;
import mm.simple.model.PersonHelper;
import mm.simple.model.Picture;
import mm.simple.model.helper.MovieHelper;
import mm.utils.HibernateSingleton;

public class MovieDao {
	
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
	
	public void addGenre(List<Genre> genres,Movie movie){
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
	
	public void addCountry(List<Country> countries,Movie movie){
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

	public void addPicture(List<Picture> pictures, Movie movie){
		 HibernateSingleton.getInstance();
		 Session session;
		 try{
			 session = HibernateSingleton.getSessionFactory().getCurrentSession();
		 }catch(Exception e){
			 session = HibernateSingleton.getSessionFactory().openSession();
		 }
        // 4. Starting Transaction
        Transaction transaction = session.beginTransaction();
        if(pictures!=null){
	        for(Picture picture:pictures){
	        	movie.addPicture(picture);
	        	session.save(picture);
	        }	
        }
        session.update(movie);
        transaction.commit();
        
        session.close();
	}
	
	public void addPerson(List<PersonHelper> persons,Movie movie){
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
	public Movie addMovie(Movie movie) {
	        try {
	          
	            System.out.println("sessionFactory start");
	            // 3. Get Session object
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
}
