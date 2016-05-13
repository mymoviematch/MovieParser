package mm.simple.model;

import java.sql.Date;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Movie")
public class Movie extends MovieElement implements Comparable<Movie>{

	
public String nameOrig, nameCz, nameSk,
descriptionEn, descriptionCz, descriptionSk, csfdUrl;
public int year;
public String trailerUrl;
public int lenghtMin, csfdId;
public String imdbId;
public float imdbRating;
public Date releaseDate;
public float popularity;
private Set<Country> countries = new HashSet<Country>(0);
private Set<Genre> genres = new HashSet<Genre>(0);
private Set<MapMoviePerson> moviePerson = new HashSet<MapMoviePerson>(0);
private Set<Picture> pictures = new HashSet<Picture>(0);


public long movie_id;

public Movie(){
	
}

public void addGenre(Genre genre) {
    this.genres.add(genre);
}

public void addCountry(Country country) {
    this.countries.add(country);
}
public Movie(String nameOrig, String nameCz, String nameSk,
		String descriptionEn, String descriptionCz, String descriptionSk,
		String csfdUrl, int year, String trailerUrl, int lenghtMin, int csfdId,
		String imdbId, float imdbRating, Date releaseDate, int popularity,
		Set<Country> countries, long movie_id) {

	this.nameOrig = nameOrig;
	this.nameCz = nameCz;
	this.nameSk = nameSk;
	this.descriptionEn = descriptionEn;
	this.descriptionCz = descriptionCz;
	this.descriptionSk = descriptionSk;
	this.csfdUrl = csfdUrl;
	this.year = year;
	this.trailerUrl = trailerUrl;
	this.lenghtMin = lenghtMin;
	this.csfdId = csfdId;
	this.imdbId = imdbId;
	this.imdbRating = imdbRating;
	this.releaseDate = releaseDate;
	this.popularity = popularity;
	this.countries = countries;
	this.movie_id = movie_id;
}

@Id
@GeneratedValue
@Column(name = "movie_id")
public long getMovie_id() {
	return movie_id;
}
public void setMovie_id(long movie_id) {
	this.movie_id = movie_id;
}
@Column(unique = true)
public String getNameOrig() {
	return nameOrig;
}
public void setNameOrig(String nameOrig) {
	this.nameOrig = nameOrig;
}
public String getNameCz() {
	return nameCz;
}
public void setNameCz(String nameCz) {
	this.nameCz = nameCz;
}
public String getNameSk() {
	return nameSk;
}
public void setNameSk(String nameSk) {
	this.nameSk = nameSk;
}
@Column(name="descriptionEn", columnDefinition="TEXT")
public String getDescriptionEn() {
	return descriptionEn;
}
public void setDescriptionEn(String descriptionEn) {
	this.descriptionEn = descriptionEn;
}
@Column(name="descriptionCz", columnDefinition="TEXT")
public String getDescriptionCz() {
	return descriptionCz;
}
public void setDescriptionCz(String descriptionCz) {
	this.descriptionCz = descriptionCz;
}
public String getDescriptionSk() {
	return descriptionSk;
}
@Column(name="descriptionSk", columnDefinition="TEXT")
public void setDescriptionSk(String descriptionSk) {
	this.descriptionSk = descriptionSk;
}
public String getCsfdUrl() {
	return csfdUrl;
}
public void setCsfdUrl(String csfdUrl) {
	this.csfdUrl = csfdUrl;
}
public int getYear() {
	return year;
}
public void setYear(int year) {
	this.year = year;
}
public String getTrailerUrl() {
	return trailerUrl;
}
public void setTrailerUrl(String trailerUrl) {
	this.trailerUrl = trailerUrl;
}
public int getLenghtMin() {
	return lenghtMin;
}
public void setLenghtMin(int lenghtMin) {
	this.lenghtMin = lenghtMin;
}
public int getCsfdId() {
	return csfdId;
}
public void setCsfdId(int csfdId) {
	this.csfdId = csfdId;
}
public String getImdbId() {
	return imdbId;
}
public void setImdbId(String imdbId) {
	this.imdbId = imdbId;
}
public float getImdbRating() {
	return imdbRating;
}
public void setImdbRating(float imdbRating) {
	this.imdbRating = imdbRating;
}
public Date getReleaseDate() {
	return releaseDate;
}

@Column(name = "releaseDate")
@Temporal(TemporalType.DATE)
public void setReleaseDate(Date releaseDate) {
	this.releaseDate = releaseDate;
}

public float getPopularity() {
	return popularity;
}
public void setPopularity(float popularity) {
	this.popularity = popularity;
}

public void addMoviePerson(MapMoviePerson moviePerson){
	this.moviePerson.add(moviePerson);
}

@OneToMany(mappedBy = "movie")
public Set<MapMoviePerson> getMoviePerson() {
	return moviePerson;
}

public void setMoviePerson(Set<MapMoviePerson> moviePerson) {
	this.moviePerson = moviePerson;
}

@ManyToMany(cascade = CascadeType.ALL)
@JoinTable(
        name = "MapMovieCountries",
        joinColumns = @JoinColumn(name = "movie_id"),
        inverseJoinColumns = @JoinColumn(name = "country_id"))
public Set<Country> getCountries(){
	return countries;
}
public void setCountries(Set<Country> countries) {
	this.countries = countries;
}

@ManyToMany(cascade = CascadeType.ALL)
@JoinTable(
        name = "MapMovieGenre",
        joinColumns = @JoinColumn(name = "movie_id"),
        inverseJoinColumns = @JoinColumn(name = "genre_id"))
public Set<Genre> getGenres() {
	return genres;
}

public void setGenres(Set<Genre> genres) {
	this.genres = genres;
}

public void addPicture(Picture picture){
	pictures.add(picture);
}

@OneToMany(cascade = CascadeType.ALL)
public Set<Picture> getPictures() {
	return pictures;
}

public void setPictures(Set<Picture> pictures) {
	this.pictures = pictures;
}

@Override
public int compareTo(Movie o) {
    return Comparators.NAME.compare(this, o);
}	

public static class Comparators {

    public static Comparator<Movie> NAME = new Comparator<Movie>() {
        @Override
        public int compare(Movie o1, Movie o2) {
            return o1.nameOrig.compareTo(o2.nameOrig);
        }
    };
    public static Comparator<Movie> POPULARITY = new Comparator<Movie>() {
        @Override
        public int compare(Movie o1, Movie o2) {
             if(o1.popularity - o2.popularity > 0.0f){
            	 return -1;
             }
             else return 1;
        }
    };
    
}

}