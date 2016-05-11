
<html>
<head>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script type="text/javascript" src="lib/mustache.js" ></script>
<script type="text/javascript" src="js/movie_search.js"></script>

<title>User Manipulation</title>

<link rel="stylesheet" type="text/css" href="css/main.css">
<link rel="stylesheet" type="text/css" href="css/info.css">
<link rel="stylesheet" type="text/css" href="info.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

</head>
<body id="search-body">

	<ul id="navbar" class="nav nav-pills nav-justified calm">
	  <li><a href="movie_list">Show all Movies</a></li>
	  <li class="active"><a href="/MovieTime">Add movie</a></li>
	  <li>
	  <input type="text" name="search" id="search" list="find_data"/>
	<datalist id="find_data">
	  <option value="title">
	  <option value="actors">
	  <option value="overview">
	  <option value="title_cz">
	  <option value="title_sk">
	  <option value="genres">
	</datalist>
	 	<button id="element-search" type="button" class="btn-upper">
    		<span class="glyphicon glyphicon-search"></span>
  		</button>
	  </li>
	</ul>
	<div class="container">
	<div class="center">

<div class="jumbotron">
<h2>MovieDownload</h2>
	<a href="javascript:getMovie('tt2975590')">Superman</a>
	<label for="movie-id">ENTER MOVIE ID:</label>
	<input type="text" id="movie-id"/>

	<button class="btn-search" id="getBtn" onClick="getMovie()">GET MOVIE!</button><br>
    <div class="form-group">
      <label for="picture_size">Picture size:</label>
      <select onChange="getMovie(0)" class="form-control" id="picture_size">
      </select>

      <label for="backdrop_size">Backdrop size:</label>
      <select onChange="getMovie(0)" class="form-control" id="backdrop_size">
      </select>
      
      <label for="actor_size">Actor picture size</label>
      <select onChange="getMovie(0)" class="form-control" id="actor_size">
      </select>
      
      <br>
	</div>


<br>
<form action="save" method="post"> 
<div id="details">
	<div  class="jumbotron">
		<div class="center-no-margin">
		<h2 id="Main_tittle"> Movie Details</h2>
		
		 <div class="progress">
		  <div class="progress-bar progress-bar-success" id="popularity" role="progressbar" aria-valuenow="70"
		  aria-valuemin="0" aria-valuemax="100" style="width:70%">
		    70%
		  </div>
		</div>
		 
		 	<img src="" id="picture" class="img-rounded img-responsive center-block" alt="Cinque Terre">
		 	</div>
		 	<br>
		 	
				<label for="title">TITTLE:</label>
				<input class="form-control" id="title" name="title">
				
				<label for="original_title">ORIGINAL TITTLE:</label>
				<input class="form-control" id="original_title" name="original_title">
				
				<label for="overview">OVERVIEW:</label>
				<textarea class="form-control" rows="3" id="overview" name="overview"></textarea>
				
				<label for="title">GENRES:</label>	
				<input class="form-control" id="genres" name="genres">
				
				<label for="csfd_link">CSFD:</label>	
				<input class="form-control" id="csfd_link" name="csfd_link">
				
				<div class="row">
				<div class="col-sm-3">
					<label for="title">RELEASE DATE:</label>	
					<input class="form-control" id="release_date" name="release_date">
				</div>
				
				<div class="col-sm-3">
					<label for="imdb_id">IMDB ID:</label>	
					<input class="form-control" id="imdb_id" name="imdb_id">
				</div>
				
				<div class="col-sm-3">
					<label for="year">Budget:</label>	
					<input class="form-control" id="budget" name="budget">
				</div>
				
				<div class="col-sm-3">
					<label for="popularityNumber">Popularity:</label>	
				<input class="form-control" id="popularityNumber" name="popularityNumber">
				</div>
				
				</div>
				
				</div>
				<label for="backdrop">BackDrop:</label><br>
				<img src="" id="backdrop" class="img-rounded img-responsive center-block" alt="Cinque Terre">
				<br><br>
				<div class="row" id="back_div" >
				</div>
				<br>
				<div class="jumbotron">
				<br>
				
					<label for="videURL">Trailer from themoviedb:</label>	
					<input class="form-control" id="videURL" name="youtube_video">
					<br>
					<label for="videURL">Trailer from addicts:</label>	
					<input class="form-control" id="videURLaddict" name="addict_video">
					<br>	
		
		
		<a class="btn btn-block" data-toggle="collapse" data-target="#SK">SK</a>
				<div id="SK"  class="collapse">		
				<br>
				<abbr title="Preklad v slovenskom jazyku"><h1>SK</h1></abbr>
				<label for="title_sk">Title</label>
				<input class="form-control" id="title_sk" name="title_sk">
				
				<label for="overview_sk">Overview:</label>
				<textarea class="form-control" rows="3" id="overview_sk" name="overview_sk"></textarea>
				<br>
				
				<div>
					<label for="release_date_sk">RELEASE DATE:</label>	
					<input class="form-control" id="release_date_sk" name="release_date_sk">
				</div>
					<label for="videURL_sk">VIDEO:</label>	
					<input class="form-control" id="videURL_sk">		
					<br>		
				
					</div>
				<br>
				
				<a class="btn btn-block" data-toggle="collapse" data-target="#CS">CZ</a>
				<div id="CS"  class="collapse">		
				<abbr title="Preklad v českom jazyku"><h1>CZ</h1></abbr>
				<label for="title_cz">Title</label>
				<input class="form-control" id="title_cz" name="title_cz">
				
				<label for="overview_cz">Overview:</label>
				<textarea class="form-control" rows="3" id="overview_cz" name="overview_cz"></textarea>
				<br>
				<div >
					<label for="release_date_cz">RELEASE DATE:</label>	
					<input class="form-control" id="release_date_cz" name="release_date_cz">
				</div>
			
				<br>
					<label for="videURL_cz">VIDEO:</label>	
					<input class="form-control" id="videURL_cz">		
					<br>	
				
				<br>
				</div>
				<br>
				<div id="anchor_block">
					<a class="btn btn-block" data-toggle="collapse" data-target="#actors">Actors</a>
						<div id="actors"  class="collapse">
						</div>
					<br>
				
					<a class="btn btn-block" data-toggle="collapse" data-target="#crew">Crew</a>
						<div id="crew"  class="collapse">
						</div>
					<br>
					
					<a class="btn btn-block" data-toggle="collapse" data-target="#countries">Production Countries</a>
						<div id="countries"  class="collapse">
						</div>
				</div>
				</div>
				<br>
				<button class="btn" type="submit">SAVE</button>
				</div>
		</div>
	
 </form>
</div>
</div>

<button id="fixedbutton"><span class="glyphicon  glyphicon glyphicon-chevron-up"></span></button>
</body>


</html>