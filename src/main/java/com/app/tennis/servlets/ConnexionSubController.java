package com.app.tennis.servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.app.tennis.data.Acces;
import com.app.tennis.services.AccesService;
import com.app.tennis.services.impl.AccesServiceImpl;

public class ConnexionSubController {

	private HttpServletRequest request;
	String pageVue;
	Acces user;

	public ConnexionSubController(HttpServletRequest request) {
		super();
		this.request = request;
	}

	public void connecting() {

		String login = request.getParameter("login");
		String password = request.getParameter("password");
		this.pageVue = "/connexion.jsp";

		HttpSession session = request.getSession();
		
		user = new Acces(login);
		session.setAttribute( "sessionUser", null );

		if (login != null){
		
			AccesService accesService = new AccesServiceImpl();
			user = accesService.grantedAcces(login, password);
			
			if (user.isAcces()){
				session.setAttribute( "sessionUser", user.getLogin() );
				this.pageVue = "index.jsp";
			} 
		}
	}

	public void deconnecting() {
		HttpSession session = request.getSession();
		session.setAttribute( "sessionUser", null );
		this.pageVue = "deconnexion.jsp";
	}

	public String getPageVue() {
		return pageVue;
	}

	public void setPageVue(String pageVue) {
		this.pageVue = pageVue;
	}

	public Acces getUser() {
		return user;
	}

	public void setUser(Acces user) {
		this.user = user;
	}




}
