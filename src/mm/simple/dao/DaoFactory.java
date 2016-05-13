package mm.simple.dao;

import mm.simple.dao.elements.CountryDao;
import mm.simple.dao.elements.GenreDao;
import mm.simple.dao.elements.PersonDao;
import mm.simple.dao.elements.PictureDao;
import mm.simple.model.Country;
import mm.simple.model.Genre;
import mm.simple.model.MovieElement;
import mm.simple.model.Person;
import mm.simple.model.Picture;

public class DaoFactory {

public IDao getDao(Class type){
	if(type.isAssignableFrom(Person.class)) return new PersonDao();
	else if(type.isAssignableFrom(Country.class)) return new CountryDao();
	else if(type.isAssignableFrom(Genre.class)) return new GenreDao();
	else if(type.isAssignableFrom(Picture.class)) return new PictureDao();
	else return null;
}

}
