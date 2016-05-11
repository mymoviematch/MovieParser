<%@ page import="mm.simple.model.Movie" %>
<%@ page import="mm.simple.model.Genre" %>
<%@ page import="mm.simple.model.Picture" %>
<%@ page import="mm.simple.dao.MovieDao" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.Random" %>

<html>
<!-- webpage to show all the movies in a table -->
<head>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<script type="text/javascript" src="js/cookie/jquery.cookie.js"></script>

<title>All movies in the DB</title>

<link rel="stylesheet" type="text/css" href="css/main.css">
<link rel="stylesheet" type="text/css" href="css/movie_list.css">

</head>

<body>
<% 
MovieDao md = new MovieDao();
List<Movie> movies = md.getAllMovies();
Collections.sort(movies,Movie.Comparators.POPULARITY);
%>


	<ul class="nav nav-pills nav-justified calm">
		<li class="active"><a href="movie_list">Show all Movies</a></li>
		<li><a href="/MovieTime">Add movie</a></li>
	</ul>
	
<div class="container">
	<div class="jumbotron table-area movie-list">
		<table class="table table-condensed">
	    <thead>
	      <tr>
	        <th class="tableText">Movie Name</th>
	        <th class="tableText">Genre</th>
	        <th class="tableText">Popularity</th>
	      </tr>
	    </thead>
	    <tbody>
	    <% 
	    for(Movie movie:movies){
	    	Object[] genres =movie.getGenres().toArray();
	    	StringBuilder genreString = new StringBuilder();
	    	for(Object o:genres){
				genreString.append(((Genre)o).nameEn+", ");
	    	}
	    	
	    	if(genres.length>0){
	    		genreString.deleteCharAt(genreString.lastIndexOf(","));
	    	}
	    %>
	   
	      <tr class="clickable" onClick="javascript:showMovie(<%= movie.movie_id %>)">
	        <td class="tableText"><%= movie.getNameOrig() %></td>
	        <td class="tableText"><%= genreString.toString()  %></td>
	        <td class="tableText"><%= movie.getPopularity() %></td>
	      </tr>
	      
	       <%	
	    }
	    %>
	    </tbody>
	  </table>
	</div>
</div>
</body>
<script>
$("document").ready(function(){
	<%
	long mostPopularId = movies.get(0).movie_id;
	
	List<Picture> pictures = md.getAllPicturesByMovieId(mostPopularId);
	Random rand = new Random();
	int  picture_no = rand.nextInt(pictures.size());
	String pictureLink = pictures.get(picture_no).link;
	%>
	console.log("<%= pictureLink%>");
	$("body").css("background-image","url(<%= pictureLink%>)");
	
	lastMovieId = $.cookie("movie_id");
	
})

function showMovie(movie_id){
	console.log(movie_id);
	$.cookie("movie_id", movie_id);
	window.location.href = "show_movies?movie_id="+movie_id;
}

window.onscroll = function(ev) {
    if ((window.innerHeight + window.scrollY) >= document.body.offsetHeight) {
        console.log("bottom");
    }
    if(window.scrollY > 20){
    	console.log("adding calm class");
    	$(".nav-justified").addClass("not-calm");
    	$(".nav-justified").removeClass("calm");
    }
    if(window.scrollY < 20){
    	$(".nav-justified").removeClass("not-calm");
    	$(".nav-justified").addClass("calm");
    }
    console.log(window.scrollY);
};
</script>
</html>