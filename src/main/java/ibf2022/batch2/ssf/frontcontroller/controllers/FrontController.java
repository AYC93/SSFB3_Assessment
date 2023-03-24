package ibf2022.batch2.ssf.frontcontroller.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ibf2022.batch2.ssf.frontcontroller.model.Account;
import ibf2022.batch2.ssf.frontcontroller.services.AuthenticationService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class FrontController {

	@Autowired
	public AuthenticationService auth;

	@GetMapping(path = "/")
	public String login(Model m, HttpSession sess) {
		sess.invalidate();
		m.addAttribute("account", new Account());
		return "view0";
	}

	@PostMapping(path = "/login")
	public String submitLogin(HttpSession sess, @Valid @ModelAttribute("account") Account account,
			BindingResult result) throws Exception {
		if (result.hasErrors()) {
			return "view0";
		}
		try {

			if (auth.authenticate(account.getUsername(), account.getPassword()) == true) {
				sess.setAttribute("account", account);
				return "view1";
			} else {
				return "view0";
			}
		} catch (Exception e) {
			return "view0";
		}
	}
}

// TODO: Task 2, Task 3, Task 4, Task 6
// @PostMapping(path="/login")
// public String authLogin(@RequestParam("username") String username,
// @RequestParam("password") String password, HttpSession sess, Model m){
// try {
// auth.authenticate(username, password);
// return "view1";
// } catch (Exception e){
// m.addAttribute("error", "Invalid") ;
// return "view1";
// }
// }
