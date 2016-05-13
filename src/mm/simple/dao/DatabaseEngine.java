package mm.simple.dao;

import java.util.List;

import mm.simple.dao.elements.MovieDao;
import mm.simple.model.Country;
import mm.simple.model.Genre;
import mm.simple.model.Movie;
import mm.simple.model.MovieElement;
import mm.simple.model.Person;
import mm.simple.model.PersonHelper;
import mm.simple.model.Picture;

public final class DatabaseEngine extends DaoFactory{
	static DatabaseEngine instance;
	MovieDao md = new MovieDao();
	
	private DatabaseEngine(){
		
	}
	
	public static DatabaseEngine getInstance(){
		if(instance == null){
			instance = new DatabaseEngine();
		} 
			return instance;
	}
	
	public void putList(List<? extends MovieElement> movieElements,Movie movie){
		if(movieElements.get(0) instanceof PersonHelper){
			getDao(Person.class).saveList(movieElements, movie);
		}
		else if(movieElements.get(0) instanceof Country){
			getDao(Country.class).saveList(movieElements,movie);
		}
		else if(movieElements.get(0) instanceof Genre){
			getDao(Genre.class).saveList(movieElements,movie);
		}
		else if(movieElements.get(0) instanceof Picture){
			getDao(Picture.class).saveList(movieElements,movie);
		}
	}
	
	public MovieDao getMovieDao(){
			return md;
	}
	
}
