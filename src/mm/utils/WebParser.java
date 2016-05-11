package mm.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status.Family;

import org.json.JSONException;
import org.json.JSONObject;


public class WebParser {
	  public static void parseJson(String json){
		  System.out.println(json);
	  }
	  private static String readAll(Reader rd) throws IOException {
		    StringBuilder sb = new StringBuilder();
		    int cp;
		    while ((cp = rd.read()) != -1) {
		      sb.append((char) cp);
		    }
		    return sb.toString();
		  }

		  public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
		    InputStream is = new URL(url).openStream();
		    try {
		      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
		      String jsonText = readAll(rd);
		      JSONObject json = new JSONObject(jsonText);
		      return json;
		    } finally {
		      is.close();
		    }
		  }
		  public String run(){
			  try {
				JSONObject json = readJsonFromUrl("https://api.themoviedb.org/3/movie/550?api_key=998ab3155e611a3207ddbd8c830703f4");
				return "blabla";
			  } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "zle!";
		  }
		  
	public String parse(){
		Form form = new Form();
		
		Client client = ClientBuilder.newClient();
	
		WebTarget resource = client.target("https://api.themoviedb.org/3/movie/550?api_key=998ab3155e611a3207ddbd8c830703f4");
	
		Builder request = resource.request();
		request.accept(MediaType.APPLICATION_JSON);
	
		Response response = request.get();
	
		if (response.getStatusInfo().getFamily() == Family.SUCCESSFUL) {
		    System.out.println("Success! " + response.getStatus());
		    System.out.println(response.getEntity());
		    return "mam";
		} else {
		    System.out.println("ERROR! " + response.getStatus());    
		    System.out.println(response.getEntity());
		    return "nist";
		}
	}
}
