package mm.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.web.servlet.ModelAndView;


public class ApiController {

	@Path("/movieList2")
	public ModelAndView showMovie(){
		System.out.println("dasda");
		return new ModelAndView("show_movies","movie",2);
	}
	
	
	@Path("/hello")
	@GET
    @Produces("text/html")
    public String sayHello() {
        return "Hello World";
    }
}
