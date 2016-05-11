package mm.simple.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name = "Country")
public class Country {

	public long country_id;
	public String nameEn;
	public String nameCz;
	public String nameSk;
	public String iso_639_1;
	private Set<Movie> movies = new HashSet<Movie>(0);
	
	public Country(){
		
	}
	 public void addMovie(Movie movie) {
	        this.movies.add(movie);
	    }
	 
    @Id
    @GeneratedValue
    @Column(name = "country_id")
	public long getCountry_id() {
		return country_id;
	}

	public void setCountry_id(long country_id) {
		this.country_id = country_id;
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
	@Column(unique = true)
	public String getIso_639_1() {
		return iso_639_1;
	}
	public void setIso_639_1(String iso_639_1) {
		this.iso_639_1 = iso_639_1;
	}
	
	public Country(long country_id, String nameEn, String nameCz,
			String nameSk, String iso_639_1) {
		super();
		this.country_id = country_id;
		this.nameEn = nameEn;
		this.nameCz = nameCz;
		this.nameSk = nameSk;
		this.iso_639_1 = iso_639_1;
	}

	@ManyToMany(mappedBy = "countries")
	public Set<Movie> getMovies() {
		return movies;
	}

	public void setMovies(Set<Movie> movies) {
		this.movies = movies;
	}
	
}