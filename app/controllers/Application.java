package controllers;

import models.User;
import play.*;
import play.data.Form;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {
	static Form<User> newUser = new Form<User>(User.class);

	public static Result index() {
		String username = session("username");	
		if (username == null) {
			return ok(index.render("Welcome to bitBay!",username,""));
		}
			return redirect("/ureg");
		}


	public static Result recognize() {
		String username = newUser.bindFromRequest().get().username;
		if(!User.existsUsername(username)) {
			return redirect("/ureg");
		}
		addUser();
		session("username", username);
		return redirect("/sreg");
	}

	public static Result addUser() {
		String username = newUser.bindFromRequest().get().username;
		String password = newUser.bindFromRequest().get().password;
		User.create(username, password);
		return redirect("/sreg");
	}
	public static Result sReg(){
		String message = "Registration successful!";
		return ok(user.render(message));
	}
	public static Result uReg(){
		String message = "Registration unsuccessful!";
		return ok(user.render(message));
	}

}
