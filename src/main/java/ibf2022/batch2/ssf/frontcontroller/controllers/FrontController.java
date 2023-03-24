package ibf2022.batch2.ssf.frontcontroller.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ibf2022.batch2.ssf.frontcontroller.model.Account;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class FrontController {

	@GetMapping(path = "/")
    public String login(Model m, HttpSession sess) {
        m.addAttribute("account", new Account());
        return "view0";
    }

	@PostMapping(path="/login")
	public String submitLogin(Model m, HttpSession sess, @Valid Account account, BindingResult result){
		if (result.hasErrors()) {
            return "index";
        }		
		sess.setAttribute("account", account);
		m.addAttribute(null, result);
		return "view1";
	}

	// TODO: Task 2, Task 3, Task 4, Task 6
	
}
