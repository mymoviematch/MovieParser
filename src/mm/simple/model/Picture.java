package mm.simple.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Picture")
public class Picture extends MovieElement{
public String type,link;
Long picture_id;
public String local;
public Movie movie;

public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}

public String getLocal() {
	return local;
}
public void setLocal(String local) {
	this.local = local;
}
@Id
@GeneratedValue
@Column(name = "picture_id")
public Long getPicture_id() {
	return picture_id;
}
public void setPicture_id(Long picture_id) {
	this.picture_id = picture_id;
}

public void setMovie(Movie movie) {
	this.movie = movie;
}

@ManyToOne(cascade = CascadeType.ALL)
@JoinColumn(name = "movie_id")
public Movie getMovie() {
	return movie;
}

public String getLink() {
	return link;
}
public void setLink(String link) {
	this.link = link;
}

}
