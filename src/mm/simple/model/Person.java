package mm.simple.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Person")
public class Person {
public String name,picture_link;
Long person_id;
private Set<MapMoviePerson> moviePerson = new HashSet<MapMoviePerson>(0);



public String getPicture_link() {
	return picture_link;
}
public void setPicture_link(String picture_link) {
	this.picture_link = picture_link;
}


@Column(unique = true)
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}

@Id
@GeneratedValue
@Column(name = "person_id")
public Long getPerson_id() {
	return person_id;
}
public void setPerson_id(Long person_id) {
	this.person_id = person_id;
}

public void addMoviePerson(MapMoviePerson moviePerson){
	this.moviePerson.add(moviePerson);
}

@OneToMany(mappedBy = "person")
public Set<MapMoviePerson> getMoviePerson() {
	return moviePerson;
}

public void setMoviePerson(Set<MapMoviePerson> moviePerson) {
	this.moviePerson = moviePerson;
}

}
