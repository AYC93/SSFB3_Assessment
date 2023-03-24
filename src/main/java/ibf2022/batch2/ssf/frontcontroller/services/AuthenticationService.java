package ibf2022.batch2.ssf.frontcontroller.services;

import java.io.StringReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ibf2022.batch2.ssf.frontcontroller.model.Captcha;
import jakarta.json.Json;
import jakarta.json.JsonObject;

@Service
public class AuthenticationService {

	@Value("${assessment.autheticate.api.url}")
	private String restAuthUrl;

	private static final int LIMIT = 3;
	Captcha captch = new Captcha();

	// TODO: Task 2
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write the authentication method in here
	public boolean authenticate(String username, String password) throws Exception {
		Integer count = 0;


		JsonObject json = Json.createObjectBuilder()
				.add("username", username)
				.add("password", password)
				.build();

		System.out.println(username + " " + password);

		RestTemplate template = new RestTemplate();

		RequestEntity<String> req = RequestEntity.post(restAuthUrl)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.body(json.toString(), String.class);

		ResponseEntity<String> resp = template.exchange(req, String.class);
		
		JsonObject jsonResp = Json.createReader(new StringReader(resp.getBody())).readObject();
		//verify json package sent back
		System.out.println(jsonResp);
		for (count=0; count < LIMIT; count++){
		if (jsonResp.toString().toLowerCase().contains("authenticated")) {
			captch.setShowCaptcha(false);
			return true;
		} else if(jsonResp.toString().toLowerCase().contains("invalid")) {
			count++;
			captch.setShowCaptcha(true);
			return false;
		} else if (jsonResp.toString().toLowerCase().contains("incorrect")){
			count++;
			captch.setShowCaptcha(true);
		}
		while (captch.isShowCaptcha() == true){
			if(!checkCaptcha()){
				count++;
				captch.setShowCaptcha(true);
			}else{
			return true;
		}
		}
	}
		return false;
	}

	public boolean checkCaptcha(){
		if (captch.getInput() != captch.getAns()){
			return false;
		} else
		return true;
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