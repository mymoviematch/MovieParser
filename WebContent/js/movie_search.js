$(document).ready(function(){

	$("#search").keyup(function(event){
	    if(event.keyCode == 13){
	        $("#element-search").click();
	    }
	});
	
	$("#movie-id").keyup(function(event){
	    if(event.keyCode == 13){
	        $("#getBtn").click();
	    }
	});
	
	$("#fixedbutton").on('click',function(){
		scrollToTop();
	});
	
	var opacity = (window.innerHeight + window.scrollY)/document.body.offsetHeight;
	$("#fixedbutton").css('opacity',opacity);
	
	$("#element-search").on('click',function(){
		scrollToElement($("#search").val());
	})
	
	$("#details").hide();
	 var config_url = 'http://api.themoviedb.org/3/configuration?api_key=998ab3155e611a3207ddbd8c830703f4';
	   $.ajax({
         url: config_url,
         dataType: "text",
         type: "GET",
         success: function(jsonConf) {
      	   var conf = JSON.parse(jsonConf);
      	   var psizes = conf.images.poster_sizes;
      	   var bsizes = conf.images.backdrop_sizes;
  		   var asizes = conf.images.profile_sizes;
      	   
  		 for(i=0;i<asizes.length;i++){
  	      	 $("select#actor_size").append( $("<option>")
  	      		    .val(asizes[i])
  	      		    .html(asizes[i])
  	      		);
  	         }
  		 
      	   for(i=0;i<psizes.length;i++){
      	 $("select#picture_size").append( $("<option>")
      		    .val(psizes[i])
      		    .html(psizes[i])
      		);
         }
      	   for(i=0;i<bsizes.length;i++){
      		 $("select#backdrop_size").append( $("<option>")
           		    .val(bsizes[i])
           		    .html(bsizes[i])
           		);
              }
      	   }
	   });
	
});



$("document").scroll(function(){

});



window.onscroll = function(ev) {
	var opacity = (window.innerHeight + window.scrollY)/document.body.offsetHeight;
	$("#fixedbutton").css('opacity',opacity);
	
    if ((window.innerHeight + window.scrollY) >= document.body.offsetHeight) {
    }
    if(window.scrollY > 20 && !$(".nav-justified").hasClass("not-calm")){
    	$(".nav-justified").addClass("not-calm");
    	$(".nav-justified").removeClass("calm");
    }
    if(window.scrollY < 20){
    	$(".nav-justified").removeClass("not-calm");
    	$(".nav-justified").addClass("calm");
    }
};

function scrollToElement(ElementId){
	 
		if(ElementId.charAt(0)!="#"){
			ElementId = "#"+ElementId;
 		}
     	var target = $(ElementId);
     	
     	if(target!=null){
	     	var top = target.offset().top;
	     	 $('html,body').animate({scrollTop: top-10}, 500);
	     }

     
}
function scrollToTop(){
	 $('html,body').animate({scrollTop: 0}, 500);
}

var Languages =["EN","CS","SK"];
var base_url;
var VideoCounter;
function getMovie(movieId) {
	VideoCounter = 0;
	if($("#movie-id").val().length > 0 || movieId != null){
  	   var config_url = 'http://api.themoviedb.org/3/configuration?api_key=998ab3155e611a3207ddbd8c830703f4';
  	   $.ajax({
           url: config_url,
           dataType: "text",
           type: "GET",
           success: function(jsonConf) {
        	   var conf = JSON.parse(jsonConf);
        	   getMovieDetails(conf,movieId);
           }
  	   });
	}
	else{
		console.log("no movie was selected yet");
	}
       }

        function getMovieDetails(conf,movieIdTest){
        	
        	base_url = conf.images.base_url;
        	var movieId = $("#movie-id").val();
        	
        	if(movieIdTest !=null){
        		movieId = movieIdTest;
        	}
        	
        	
        	$("#overview_cz").val("");
			$("#title_cz").val("");	
        	$("#overview_sk").val("");
			$("#title_sk").val("");		
			
            var url = "https://api.themoviedb.org/3/movie/"+movieId; 
            var api = "?api_key=998ab3155e611a3207ddbd8c830703f4";
			var trailers = "http://api.traileraddict.com/?imdb=";
			
            $("#videURL").val("");
            $("#videURL_cz").val("");
            $("#videURL_sk").val("");
            
            var input = $('#movie').val(),
            
               movieName = encodeURI(input);
            	   
                  $.ajax({
                      url: url+api,
                      dataType: "text",
                      type: "GET",
                      success: function(json) {
                      	var data = JSON.parse(json);
                      	var genre = "";
                      	var image_url = base_url;
                      	var picture_size = $('#picture_size').find(":selected").text();
                      	var backdrop_size = $('#backdrop_size').find(":selected").text();
                      	
                      	$("#details").show();
          				for(j=0;j<data.genres.length;j++){
          					if(genre == "")
                      			genre += data.genres[j].name;	
          					else
          						genre += "," +data.genres[j].name;	
                      	}
          				$("#Main_tittle").html(data.original_title);
                      	$("#overview").val(data.overview);
                      	$("#title").val(data.title);
						$("#original_title").val(data.original_title);
						$("#genres").val(genre);
						$("#imdb_id").val(data.imdb_id);
						$("#budget").val(data.budget);
						$("#picture").attr("src",image_url+picture_size+data.poster_path);
						$("#backdrop").attr("src",image_url+backdrop_size+data.backdrop_path);
						$("#release_date").val(data.release_date);
						$("#popularityNumber").val(data.popularity);
						
						var search_name = data.original_title.replace(/\s/g, '-');
						//setOtherLanguages(url+"/alternative_titles"+api,"title");
						setOtherLanguages(url+"/release_dates"+api,"releases");
						setOtherLanguages(url+api,"CZ");
					 	setCountries(data.production_countries);
						setPopularity(data.vote_average);
						getActors(url+"/credits"+api);
						setTrailersAdicts(trailers+data.imdb_id.replace("tt",""));
						setVideos(url+"/videos"+api);
						setBackDrops(url+"/images"+api,backdrop_size);
						getCsfdLink(data.title);
						
						scrollToElement("#details");
						
						$('#actors').on('show hide', function() {
						    $(this).css('height', 'auto');
						});
                      }
                  });
    }
        
       
        
        function changeBackDrop(path){
        	$("#backdrop").attr("src",path);
        }
        
        function getCsfdLink(title){
        	var api_link = "http://csfdapi.cz/movie?search=\""+title+"\"";
        	
        	$.ajax({
                url: api_link,
                dataType: "text",
                type: "GET",
                crossDomain: true,
                success: function(json) {
                	var data = JSON.parse(json);
                  	$("#csfd_link").val(data[0].csfd_url);
                }
        	})
        }
        
        function setTrailersAdicts(url){
        	console.log(url);
        	$.ajax({
                url: url,
                dataType: "text",
                type: "GET",
                crossDomain: true,
                success: function(json) {
                	
                	var videoPattern = "src=\"//";
                	
                	var xml = $.parseXML(json),
                	$xml = $( xml ),
                	embed = $xml.find('embed');
                	var xmlFile = embed.text();
                	
                	var res = xmlFile.substring(xmlFile.indexOf(videoPattern)+videoPattern.length,xmlFile.indexOf("allowfullscreen")-2);
                	$("#videURLaddict").val(res);
                	console.log(xmlFile);
                	$("#videURLaddictV").append(xmlFile);
                	console.log("video: " + res);
                	console.log("end");
                
                },
        		error: function (xhr, status) {
        			if(VideoCounter == 10){
		        		Videocounter++;
		        		setTrailersAdicts(url)
        			}
        			
            }
        	});
        }
        function setBackDrops(url,backdrop_size){
       	 $.ajax({
                url: url,
                dataType: "text",
                type: "GET",
               
                success: function(json) {
                	var data = JSON.parse(json);
                	var back_div = $("#back_div");
                	back_div.empty();
                	for(i=0;i<data.backdrops.length;i++){
                		var fullPath = base_url +backdrop_size +data.backdrops[i].file_path;
                		
                		"backdrop"+i+"d"
                		var label = " <a id='backdrop"+i+"a' onClick='removeBackDrop(\"backdrop"+i+"\")' onmouseover='changeBackDrop(\""+fullPath+"\")' >"+
                		"<img class='img-active' id='backdrop"+i+"b' src='"+fullPath+"' height='42' width='42'> </a>"
                		+"<input type='hidden' class='form-control' id='backdrop"+i+"'value='"+fullPath+"' name='backdrop"+i+"'>"
                		+"<input type='hidden' class='form-control' id='backdrop"+i+"d' value='true' name='backdrop"+i+"d'>";   
                		//
                		back_div.append(label);
                	}

                }
       	 });
        }

        function removeBackDrop(anchorId){
        	imgId = anchorId+"b";
        	downloadId = anchorId+"d";
        	if($("#"+imgId).hasClass("img-active")){
        		$("#"+imgId).removeClass("img-active");
        		$("#"+imgId).addClass("img-innactive");
        		$("#"+downloadId).val(false);
        		//alert("active");
        	}
        	else{
        		//$("#"+imgId).addClass("img-innactive");
        		$("#"+imgId).removeClass("img-innactive");
        		$("#"+imgId).addClass("img-active");
        		$("#"+downloadId).val(true);
        	}
        }
        function setVideos(url){
        	
        	for(i=0;i<Languages.length;i++){
        		var Lang = Languages[i];
	        	 $.ajax({
	                 url: url+"&Language="+Languages[i],
	                 dataType: "text",
	                 type: "GET",
	                
	                 success: function(json) {
	                 	var data = JSON.parse(json);
	                 	if(data.results[0]!="undefined"){
	                 	var VideLink = "http://www.youtube.com/embed/"+data.results[0].key;
	                 	
	                 	if(data.results[0].iso_639_1 == "en"){
	                 		$("#videURL").val(VideLink);
	                 	}
	                 	
	                 	else if(data.results[0].iso_639_1 == "cs"){
	                 		$("#videURL_cz").val(VideLink);
	                 	}
	                 	
	                 	else if(data.results[0].iso_639_1 == "sk"){
	                 		$("#videURL_sk").val(VideLink);
	                 	}
	                 	}
	                 	
	                 }
	        	 });
	        	}
        }
        function setOtherLanguages(url,type){
      	 
        	if(type=="CZ"){
        		url += "&Language=CS";
        	}
        	
        	if(type=="SK"){
        		url += "&Language=SK";
        	}
        	
        	 $.ajax({
                 url: url,
                 dataType: "text",
                 type: "GET",
                
                 success: function(json) {
                 	var data = JSON.parse(json);
                 	if(type=="CZ"){                		
                 		$("#overview_cz").val(data.overview);
                 		$("#title_cz").val(data.title);
                 	}
                 	if(type=="SK"){
                 		$("#overview_sk").val(data.overview);
                 		$("#title_sk").val(data.title);
                 	}
                 	if(type=="releases"){
                 		for(i=0;i<data.results.length;i++){
                 			if(data.results[i].iso_3166_1 == "CS" ||
                 					data.results[i].iso_3166_1 == "SK"){
                 				$("#release_date_cz").val(data.results[i].release_dates[0].release_date);
                 			}
                 			else if(data.results[i].iso_3166_1 == "SK"){
                 				$("#release_date_sk").val(data.results[i].release_dates[0].release_date);
                 			}
                 		}
                 	}
                 	                 	             
                 }
        	 });
        }
        
        function setCountries(countries){
        	var div = $("#countries");
        	div.empty();
        	for(i=0;i<countries.length;i++){
        		var index = i+1;
        		var label = "<div class='col-sm-12'> <label for='"+countries[i].iso_3166_1+"1'> Country"+index+" :</label> <input class='form-control' id='"
         		 +countries[i].iso_3166_1+"1' value='"+countries[i].iso_3166_1+"' name='country"+i+"'>" ;            		 
        		div.append(label);  
        	}
        }
        
        function setPopularity(popularity){
      		popularity *= 10;
        	$("#popularity").attr("aria-valuenow",popularity)
        	.attr("style","width:"+popularity+"%")
        	.html(popularity+"%");
        	
        	if(popularity > 80){
        		$("#popularity").attr("aria-valuenow",popularity)
            	.attr("class","progress-bar progress-bar-success");
        	}
        	else if(popularity > 50 && popularity < 80){
        		$("#popularity").attr("aria-valuenow",popularity)
            	.attr("class","progress-bar progress-bar-warning");
        	}
        	else if(popularity<50){
        		$("#popularity").attr("aria-valuenow",popularity)
            	.attr("class","progress-bar progress-bar-danger");
        	}
        }
        
        function getActors(url){
        	var actor_size = $('#actor_size').find(":selected").text();
        	var actors_div = $("#actors");
        	var crew_div = $("#crew");
        	
        	actors_div.empty();
        	crew_div.empty();
        	//var fc = div.getFirstChildren();
        	//fc.empty();
        	
        	 $.ajax({
                 url: url,
                 dataType: "text",
                 type: "GET",
                 success: function(jsonConf) {
              	   var actors = JSON.parse(jsonConf);
              	 	var div = $("#actors");
              	 	
              	   for(i=0;i<actors.cast.length;i++){
              		 
              		 var label0 = "<div class='col-sm-2'> <label for='"+actors.cast[i].id+"1'> Id:</label> <input class='form-control' id='"
              		 +actors.cast[i].id+"1' value='"+actors.cast[i].id+"' name='actor_id"+i+"'>" ;            		 
              		 
              		 var label = "<div class='col-sm-2'> <label for='"+actors.cast[i].id+"1'> Character:</label> <input class='form-control' id='"
              		 +actors.cast[i].id+"1' value='"+actors.cast[i].character+"' name='actor_character"+i+"'>" ;            		 
              		
              		 var label2 = "<div class='col-sm-2'> <label for='"+actors.cast[i].id+"2'> Actor:</label> <input class='form-control' id='"
              		 +actors.cast[i].id+"2' value='"+actors.cast[i].name+"' name='actor_name"+i+"'> </div>";
              		
              		 var label3="<div class='col-sm-2'> <label for='"+actors.cast[i].id+"3'> Order:</label> <input class='form-control' id='"
              		 +actors.cast[i].id+"3' value='"+actors.cast[i].order+"' name='actor_order"+i+"'> </div>";
              		 
              		 var picture = "<div class='col-sm-4'> <label for='"+actors.cast[i].id+"3'> Actor Picture:</label> <input class='form-control' id='"
             		 +actors.cast[i].id+"3' value='"+base_url+actor_size+actors.cast[i].profile_path+"' name='actor_picture"+i+"'> </div>";
                  	 
              		 	div.append(label0);
                     	div.append(label);  
                  		div.append(label2);
                  		div.append(label3)
                  		div.append(picture);
              	   }
              	   
              	 for(i=0;i<actors.crew.length;i++){
              		 
              		var div = $("#crew");
              		
              		var label0 = "<div class='col-sm-2'> <label for='"+actors.crew[i].id+"1'> Id:</label> <input class='form-control' id='"
            		 +actors.crew[i].id+"1' value='"+actors.crew[i].id+"' name='crew_id"+i+"'>" ;            		 
            		
              		var label = "<div class='col-sm-2'> <label for='"+actors.crew[i].id+"1'> Job:</label> <input class='form-control' id='"
             		 +actors.crew[i].id+"1' value='"+actors.crew[i].job+"' name='crew_job"+i+"'>" ;            		 
             		
             		 var label2 = "<div class='col-sm-2'> <label for='"+actors.crew[i].id+"2'> Name:</label> <input class='form-control' id='"
             		 +actors.crew[i].id+"2' value='"+actors.crew[i].name+"' name='crew_name"+i+"'> </div>";
             		 
             		var label3="<div class='col-sm-2'> <label for='"+actors.crew[i].id+"3'> Order:</label> <input class='form-control' id='"
             		 +actors.cast[i].id+"3' value='"+actors.crew[i].order+"' name='crew_order"+i+"'> </div>";
             		 
             		 
             		 var picture = "<div class='col-sm-4'> <label for='"+actors.crew[i].id+"3'> Actor Picture:</label> <input class='form-control' id='"
             		 +actors.crew[i].id+"3' value='"+base_url+actor_size+actors.crew[i].profile_path+"' name='crew_picture"+i+"'> </div>";
             		 	
             		div.append(label0); 
             		div.append(label);  
              		div.append(label2);
              		div.append(label3);
              		div.append(picture);
              	 }
                 }
        	   });

        }