<%@ page import="mm.simple.dao.MovieDao" %>
<%@ page import="mm.simple.model.helper.MovieHelper" %>
<%@ page import="mm.simple.model.Genre" %>
<%@ page import="mm.simple.model.Picture" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Random" %>
<%@ page import="mm.simple.model.Movie" %>

<html>
<%
String c= request.getParameter("movie_id");
System.out.println("the id is: " + c);
Long movie_id=Long.parseLong(c);
MovieDao md = new MovieDao();
List<Movie> movies = md.getAllMovies();
MovieHelper movie = md.getMovie(movie_id);
List<Picture> pictures = md.getAllPicturesByMovieId(movie_id);
%>

<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<script type="text/javascript" src="js/cookie/jquery.cookie.js"></script>

<script src="https://maps.googleapis.com/maps/api/js?callback=initMap"
        async defer></script>

<title><%= movie.getMovie().getNameOrig() %></title>

<link rel="stylesheet" type="text/css" href="css/main.css">
<link rel="stylesheet" type="text/css" href="css/movie_show.css">

<body>
<div>
	<ul id="navbar" class="nav nav-pills nav-justified calm">
	  	<li class="active"><a href="/">Movie</a></li>
	  	<li><a href="movie_list">Show all Movies</a></li>
	  	<li><a href="/MovieTime">Add movie</a></li>
	</ul>
</div>

<div class="container">
	<div class="left-invisible">
		<div class=" vertical" data-toggle="modal" data-target="#myModal">Trailer<span class="align_left">T</span></div>
		<div id="quiz-btn" class=" vertical" data-toggle="modal" data-target="#quizModal">Quiz<span class="align_left">Q</span></div>
		
		<div class=" jumbotron vertical map">
			<h2> Places </h2>
			<div id="map">
			</div>
		</div>
	</div>
</div>

<div class="center-no-margin">
<h1><%= movie.getMovie().getNameOrig() %></h1>
</div>


<div id="myCarousel" class="carousel slide" data-ride="carousel">
  <!-- Indicators -->
  <ol class="carousel-indicators">
  <% for(int i=0;i<pictures.size();i++){ %>
  	<img class="small-tile" src="<%=pictures.get(i).link %>" data-target="#myCarousel" data-slide-to=<%=i %> <%= (i==0) ? "class='active'" : ""  %>>
    
    <%} %>
  </ol>

  <!-- Wrapper for slides -->
  <div class="carousel-inner" role="listbox">
   <%
   int counter = 0;
   for(Picture picture : pictures){
	counter++;
	   if(counter==1){
		   %> 
   <div class="item active">
   <%}
   else{%>
   
	<div class="item">
	<% }%>
      <img src="<%=picture.link %>">
    </div>
   <%
   }
   %>
	
  </div>
</div>
</div>

<div class="container">

<!-- Modal -->
<div id="quizModal" class="modal fade" role="dialog">
  <div class="modal-dialog">
	
    <!-- Modal content-->
    <div class="modal-content trailer-window">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title"><%= movie.getMovie().nameOrig %> trailer</h4>
      </div>
       	<div class="row center">	
       	 <img id="quiz-img" class="img-responsive" src="" alt="Chania"> 
				QUIZ
		</div>
      <div class="modal-footer">
      	<button id="loadPicture" type="button" class="btn btn-default">LoadNew</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>

  </div>
</div>

<!-- Modal -->
<div id="myModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content trailer-window">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title"><%= movie.getMovie().nameOrig %> trailer</h4>
      </div>
       	<div class="row center">	
				<div class="video-player embed-responsive embed-responsive-16by9">
				<%
				String trailer = movie.getMovie().getTrailerUrl();
				
				if(!trailer.contains("http:")){
					trailer = "http://" + trailer;
				}
				System.out.println(trailer);
				%>
				<iframe class="embed-responsive-item" src="<%= trailer %>"></iframe>
			</div>
		</div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>

  </div>
</div>


<div class="row jumbotron movie-details">
	<div class="col-sm-3">
		<div class="jumbotron overflow">
			<h2>Genre:</h2>
			<%
			Object[] genres = movie.getMovie().getGenres().toArray();
			for(int i=0; i<genres.length;i++){
			%>
			<%=((Genre)genres[i]).nameCz %>/
			<%=((Genre)genres[i]).nameSk %>/
			<%=((Genre)genres[i]).nameEn %><br>
			<%
				}%>
		</div>
	</div>
	
	<div class="col-sm-3">
		<div class="jumbotron overflow">
			<h3>Genre:</h3>
			<%
			for(int i=0; i<genres.length;i++){
			%>
			<%=((Genre)genres[i]).nameCz %>/
			<%=((Genre)genres[i]).nameSk %>/
			<%=((Genre)genres[i]).nameEn %><br>
			<%
				}%>
		</div>
	</div>
	
	<div class="col-sm-3">
		<div class="jumbotron overflow">
			<h2>Genre:</h2>
			<%
			for(int i=0; i<genres.length;i++){
			%>
			<%=((Genre)genres[i]).nameCz %>/
			<%=((Genre)genres[i]).nameSk %>/
			<%=((Genre)genres[i]).nameEn %><br>
			<%
				}%>
		</div>
	</div>
	
	<div class="col-sm-3">
		<div class="jumbotron overflow">
			<h2>Genre:</h2>
			<%
			for(int i=0; i<genres.length;i++){
			%>
			<%=((Genre)genres[i]).nameCz %>/
			<%=((Genre)genres[i]).nameSk %>/
			<%=((Genre)genres[i]).nameEn %><br>
			<%
				}%>
		</div>
	</div>
	</div>
<br>


	
<div class="jumbotron center" id="overviewEN">
	<h3><%= movie.getMovie().descriptionEn %></h3>
</div>

<div class="jumbotron center" id="overviewCZ">
	<h3><%= movie.getMovie().descriptionCz %></h3>
</div>

<div class="jumbotron center" id="actors">

<%
for(int i=0;i<movie.getPersons().size();i = i+3){
	%>
		<div class="actor">
			<div class="row">
			
		<%for(int j=0;j<3;j++){ %>
			<div class="col-sm-2">
			<% if(i+j == movie.getPersons().size()) break; %>
				<%= movie.getPersons().get(i+j).person.name%><br>
				<span class="person-role">
				<%= movie.getPersons().get(i+j).role%>
				</span>
				<br>
			</div>
			
			<div class="col-sm-2">
			<%
			String picture_link = movie.getPersons().get(i+j).getPerson().picture_link; 
			System.out.println(picture_link);
			
			if(picture_link.contains("null")){
				picture_link="https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcTurPscDyKtldQPbfp63C424mzcyTBPeU3UI_Bx-UVq2xLUgG-UX8TaJcz7";
			}
			%>
				<img src=<%= picture_link %> class="img-person img-rounded img-responsive"
				 alt="<%= movie.getPersons().get(i+j).role%>">
			</div>
	<%} %>
		</div>
		<br>
	</div>

	<% 
}
%>
</div>
<br>

</div>




</body>
<footer>
Created by ME
</footer>
<script>
$(document).ready(function(){
	console.log("calling init map");
	initMap();
	
	$("#quiz-btn").on('click',function(){
		setRandomPicture();
	})
	$("#loadPicture").on('click',function(){
		setRandomPicture();
	})
	
})

function setRandomPicture(){
	console.log("setting up a random picture, movie size: " + <%= movies.size() %>);
	api_link = "localhost:8080/getAllMovies";
	$.ajax({
                url: api_link,
                dataType: "text",
                type: "GET",
                crossDomain: true,
                success: function(json) {
                	console.log(json);
                }
        	})
}

function initMap() {
    var mapDiv = document.getElementById('map');
    var map = new google.maps.Map(mapDiv, {
      center: {lat: 44.540, lng: -78.546},
      zoom: 2
    });
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

var leftButton=$(".vertical");

leftButton.on('mouseover',this,function(){
	console.log("mouse is over!");
	$(this).stop();
	$(this).animate({ marginLeft: "220px"} , 500);
})

leftButton.on('mouseout',this,function(){
	console.log("mouse is out!");
	$(this).stop();
	if($(this).hasClass("map")){
		$(this).animate({ marginLeft: "0px"} , 500);
	}
	else{
		$(this).animate({ marginLeft: "0px"} , 500);
	}
})
</script>
</html>