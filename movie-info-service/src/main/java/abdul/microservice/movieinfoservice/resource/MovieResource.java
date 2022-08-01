package abdul.microservice.movieinfoservice.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import abdul.microservice.movieinfoservice.model.Movie;

@RestController
@RequestMapping("/movie")
public class MovieResource {

	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping("{movieId}")
	public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
		
		return new Movie(movieId,"test");
	}
}
