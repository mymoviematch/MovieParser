package mm.simple.model.helper;

import java.util.List;

import mm.simple.model.MapMoviePerson;
import mm.simple.model.Movie;

public class MovieHelper {
	
private Movie movie;
private List<MapMoviePerson> persons;


public List<MapMoviePerson> getPersons() {
	return persons;
}
public void setPersons(List<MapMoviePerson> persons) {
	this.persons = persons;
}
public Movie getMovie() {
	return movie;
}
public void addPerson(MapMoviePerson person){
	persons.add(person);
}

public void setMovie(Movie movie){
	this.movie = movie;
}
}
