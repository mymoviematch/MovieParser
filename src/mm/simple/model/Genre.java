package mm.simple.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "Genre")
public class Genre extends MovieElement{
	public long genre_id;
	public String nameEn, nameCz,nameSk;
	private Set<Movie> movies = new HashSet<Movie>(0);
	

	 public void addMovie(Movie movie) {
	        this.movies.add(movie);
	    }
	 
    @Id
    @GeneratedValue
    @Column(name = "genre_id")
	public long getGenre_id() {
		return genre_id;
	}
	public void setGenre_id(long genre_id) {
		this.genre_id = genre_id;
	}
	@Column(unique = true)
	public String getNameEn() {
		return nameEn;
	}
	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}
	@Column(unique = true)
	public String getNameCz() {
		return nameCz;
	}
	
	public void setNameCz(String nameCz) {
		this.nameCz = nameCz;
	}
	@Column(unique = true)
	public String getNameSk() {
		return nameSk;
	}
	public void setNameSk(String nameSk) {
		this.nameSk = nameSk;
	}
	
	@ManyToMany(mappedBy = "genres")
	public Set<Movie> getMovies() {
		return movies;
	}
	
	public void setMovies(Set<Movie> movies) {
		this.movies = movies;
	}


}
