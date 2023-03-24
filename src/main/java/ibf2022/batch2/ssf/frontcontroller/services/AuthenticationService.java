package ibf2022.batch2.ssf.frontcontroller.services;

import java.io.StringReader;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.json.Json;
import jakarta.json.JsonObject;

@Service
public class AuthenticationService {

	@Value("${assessment.autheticate.api.url}")
	private String restAuthUrl;

	// TODO: Task 2
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write the authentication method in here
	public boolean authenticate(String username, String password) throws Exception {

		JsonObject json = Json.createObjectBuilder()
				.add("username", username)
				.add("password", password)
				.build();

		RestTemplate template = new RestTemplate();

		RequestEntity<String> req = RequestEntity.post(restAuthUrl)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.body(json.toString(), String.class);

		ResponseEntity<String> resp = template.exchange(req, String.class);

		JsonObject jsonResp = Json.createReader(new StringReader(resp.getBody())).readObject();
		System.out.println(jsonResp.toString());
		if (jsonResp.toString().contains("Authenticated")) {
			return true;
		} else {
			return false;
		}
	}

	// TODO: Task 3
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write an implementation to disable a user account for 30 mins
	public void disableUser(String username) {
	}

	// TODO: Task 5
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write an implementation to check if a given user's login has been disabled
	public boolean isLocked(String username) {
		return false;
	}
}
