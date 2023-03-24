package ibf2022.batch2.ssf.frontcontroller.model;

import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Account {
    
    @NotNull(message="Please enter a valid username")
    @Size(min= 2, message="Your username should be at least 2 characters")
    private String username;

    @NotNull(message="Please enter a valid username")
    @Size(min= 2, message="Your username should be at least 2 characters")
    private String password;

    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}
    
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

    public static JsonObject toJSON(String str) {
        JsonReader reader = Json.createReader(new StringReader(str));
        return reader.readObject();
    }

    public static Account create(String str) {
        JsonObject json = toJSON(str);
        Account acc = new Account();
        acc.setUsername(json.getString("username"));
        acc.setPassword(json.getString("password"));
        return acc;
    }

    public JsonObject toJSON() {
        return Json.createObjectBuilder()
                .add("username", getUsername())
                .add("password", getPassword())
                .build();
    }
}
