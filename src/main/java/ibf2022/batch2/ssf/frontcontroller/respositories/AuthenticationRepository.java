package ibf2022.batch2.ssf.frontcontroller.respositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import ibf2022.batch2.ssf.frontcontroller.model.Account;
import ibf2022.batch2.ssf.frontcontroller.services.AuthenticationService;
import jakarta.json.Json;
import jakarta.json.JsonObject;

public class AuthenticationRepository {
	
	@Autowired
	private RedisTemplate<String, String> template;

	public void save(Account acc){
		JsonObject json = Json.createObjectBuilder()
		.add("username", acc.getUsername())
		.add("password", acc.getPassword())
		.build();        
		
		this.template.opsForValue().set(acc.getUsername(), json.toString());
    }

}
	// TODO Task 5
	// Use this class to implement CRUD operations on Redis

