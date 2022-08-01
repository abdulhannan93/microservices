package abdul.microservice.moviecatalogservice.resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import abdul.microservice.moviecatalogservice.model.Catalog;
import abdul.microservice.moviecatalogservice.model.Movie;
import abdul.microservice.moviecatalogservice.model.Ratings;
import abdul.microservice.moviecatalogservice.model.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogServices {
	
	@Autowired
	private RestTemplate restTemplate;

	@SuppressWarnings("null")
	@RequestMapping("/{userId}")
	public List<Catalog> getCatalogList(@PathVariable("userId") String userId){
		
//		RestTemplate template = new RestTemplate();
			
		UserRating ratings= restTemplate.getForObject("http://rating-data-service/ratingsdata/user/"+ userId, UserRating.class);
		
		
//		List<Catalog> catalogList = new ArrayList<Catalog>();
		return ratings.getRatings().stream().map(rating ->{
			Movie movie = restTemplate.getForObject("http://movie-info-service/movie/haskj", Movie.class);
			return new Catalog(movie.getName(), "dummy desc", rating.getRating());
//			catalogList.add(catalog);
		}).collect(Collectors.toList());
		
//		return catalogList;
		
		
//	return Collections.singletonList(
//
//			new Catalog("Transormers", "123", 12)
//			);
				
				 
	}
}
