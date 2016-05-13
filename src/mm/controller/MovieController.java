package mm.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mm.simple.dao.DatabaseEngine;
import mm.simple.dao.elements.MovieDao;
import mm.simple.model.Country;
import mm.simple.model.Genre;
import mm.simple.model.Movie;
import mm.simple.model.MovieElement;
import mm.simple.model.PersonHelper;
import mm.simple.model.Picture;
import mm.simple.model.helper.Backdrop;
import mm.simple.model.helper.Const;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;



@Controller
@EnableWebMvc
public class MovieController {
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public ModelAndView saveMovie(HttpServletRequest request, HttpServletResponse response){
		
		DatabaseEngine engine = DatabaseEngine.getInstance();
		Movie movie = new Movie();
		
		List<PersonHelper> persons = new ArrayList<PersonHelper>();
		List<Country> countries = new ArrayList<Country>();
		List<Genre> genresAll = new ArrayList<Genre>();
		List<Backdrop> backdrops = new ArrayList<Backdrop>();
		List<Picture> pictures = new ArrayList<Picture>();
		
		int i = 0;
		String genres;

		
		genres = request.getParameter("genres");
		
		movie.nameOrig = request.getParameter("title");
		movie.descriptionEn = request.getParameter("overview");
		movie.releaseDate = Date.valueOf(request.getParameter("release_date"));
		movie.imdbId = request.getParameter("imdb_id");
		movie.trailerUrl = request.getParameter("addict_video");
		movie.nameSk = request.getParameter("title_sk");
		movie.descriptionSk = request.getParameter("overview_sk");
		movie.nameCz = request.getParameter("title_cz");
		movie.descriptionCz = request.getParameter("overview_cz");
		movie.popularity = Float.valueOf(request.getParameter("popularityNumber"));
		movie.csfdUrl = request.getParameter("csfd_link");
		
		//String release_date_sk = request.getParameter("release_date_sk");
		//String budget = request.getParameter("budget");
		//String release_date_cz = request.getParameter("release_date_cz");
		//String youtube_video = request.getParameter("youtube_video");
		//TODO:
		movie.year = 1212;
		movie.imdbRating = 13;
		movie.lenghtMin = 80;
		
		movie = engine.getMovieDao().addMovie(movie);
		if(movie==null){
			return new ModelAndView("result","info","movie already in DB!");
		}
		while(request.getParameter("backdrop"+i)!=null){
			Backdrop bd = new Backdrop();
			
			bd.setLink(request.getParameter("backdrop"+i));
			bd.setDownload(Boolean.valueOf(request.getParameter("backdrop"+i+"d")));

			if(bd.isDownload()){
				Picture picture = new Picture();
				picture.link = bd.getLink();
				picture.type="backdrop";
				picture.movie = movie;
				pictures.add(picture);
			}
			backdrops.add(bd);
			System.out.println(bd.isDownload());
			i++;
		}
		engine.putList(pictures,movie);
		i=0;
		
		while((request.getParameter("actor_name"+i))!=null){
			PersonHelper person = new PersonHelper();
			//person.id = Long.valueOf(request.getParameter("actor_id"+i));
			person.role = request.getParameter("actor_character"+i);
			person.person.name=request.getParameter("actor_name"+i);
			person.person.picture_link = request.getParameter("actor_picture"+i);
			person.order = request.getParameter("actor_order"+i);
			persons.add(person);
			i++;
		}
		i=0;
		//Crew crew_member = new Crew();
		while((request.getParameter("crew_name"+i))!=null){
			PersonHelper person = new PersonHelper();
			//person.id = Long.valueOf(request.getParameter("crew_id"+i));
			person.person.name = request.getParameter("crew_name"+i);
			person.role = request.getParameter("crew_job"+i);
			person.person.picture_link = request.getParameter("crew_picture"+i);
			person.order = request.getParameter("crew_order"+i);
			persons.add(person);
			i++;
		}
		engine.putList(persons,movie);
		
		i=0;
		
		while((request.getParameter("country"+i))!=null)
		{
			Country country = new Country();
			country.iso_639_1=request.getParameter("country"+i);
			country.nameCz = request.getParameter("country"+i);
			country.nameSk = request.getParameter("country"+i);
			country.nameEn = request.getParameter("country"+i);
			countries.add(country);
			i++;
		}
		engine.putList(countries,movie);
		
		try{
		String[] genresS = genres.split(",");
		for(String gen : genresS){
			Genre genr = new Genre();
			i=0;
			while(!Const.genres[i][0].equals(gen)){
				i++;
			}
			genr.nameCz = Const.genres[i][1];
			genr.nameEn = gen;
			genr.nameSk = Const.genres[i][2];
			genresAll.add(genr);
		}
		
		engine.putList(genresAll,movie);
		
		}
		catch(Exception e)
		{
			
		}
		return new ModelAndView("result","info","succesfully added!");
	}

	@RequestMapping("/show_movies")
	public ModelAndView showMovie(@RequestParam("movie_id") long movie_id){
		System.out.println(movie_id);
		//MovieDao md = new MovieDao();
		//MovieHelper movie = md.getMovie(movie_id);
		return new ModelAndView("show_movies","movie",movie_id);
	}
	
	@RequestMapping("/movie_list")
	public ModelAndView showAllMovies(){
		return new ModelAndView("movie_list");
	}
	
}
