package mm.simple.dao;

import java.util.List;

import mm.simple.model.Movie;
import mm.simple.model.MovieElement;

public interface IDao<T extends MovieElement> {

	public void save(T movieElement, Movie movie);
	public void saveList(List<T> movieElements,Movie movie);
	public List<T> getById(int id);
}
