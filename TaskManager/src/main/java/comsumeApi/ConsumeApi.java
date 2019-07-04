package comsumeApi;

import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ConsumeApi {

//	private static final Logger l = LoggerFactory.getLogger(ConsumeApi.class);
//	private static final String apiEndpoint = "https://pixabay.com/api/?key=12546187-917a6f0406e6efeddf50f9728&q=basketball";
	
	static final String URL = "https://restcountries-v1.p.rapidapi.com/all";
	
	public static void main(String[] args) {
//		RestTemplate rt = new RestTemplate();
//		Player po = rt.getForObject(apiEndpoint, Player.class);
//		
//		l.info("Finally got through rest service. " + "ID: " + po.getTotal());
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(new MediaType[] {MediaType.APPLICATION_JSON}));
		headers.set("X-RapidAPI-Host", "restcountries-v1.p.rapidapi.com");
		headers.set("X-RapidAPI-Key", "7945eef772msh1fd4967ac8377e6p1861d4jsnf22254fd5d7f");
		
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		
		//Rest template
		RestTemplate restTemplate = new RestTemplate();
		
		//Send request with GET method, and headers
		ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.GET, entity, String.class);
		
		String result = response.getBody();
		
		System.out.println(result);
	}
}