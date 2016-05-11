package mm.simple.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "mapmovieperson")
public class MapMoviePerson {
public long movieperson_id;
public String role;
public Person person;
public Movie movie;
public String orderNumber;

@Id
@GeneratedValue
@Column(name = "movieperson_id")
public long getMovieperson_id() {
	return movieperson_id;
}
public void setMovieperson_id(long movieperson_id) {
	this.movieperson_id = movieperson_id;
}


public String getOrderNumber() {
	return orderNumber;
}
public void setOrderNumber(String orderNumber) {
	this.orderNumber = orderNumber;
}
public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
}

@ManyToOne(cascade = CascadeType.ALL)
@JoinColumn(name = "person_id")
public Person getPerson() {
	return person;
}
public void setPerson(Person person) {
	this.person = person;
}

@ManyToOne(cascade = CascadeType.ALL)
@JoinColumn(name = "movie_id")   
public Movie getMovie() {
	return movie;
}
public void setMovie(Movie movie) {
	this.movie = movie;
}




}
