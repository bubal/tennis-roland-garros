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

		if (login == null){
			session.setAttribute( "sessionUser", null );

		} else {

			try {
				AccesService accesService = new AccesServiceImpl();
				try {
					user = accesService.findByLogin(login);
				} catch (Exception e) {
					session.setAttribute( "sessionUser", null );
					user.setError("Le login "+ user.getLogin() + " n'existe pas!");
					user.setExist(false);
				}

			} catch (Exception e) {
				user.setError(e.getMessage());
			}

			if(user.isExist()){
				if (user.isAcces(password)){
					session.setAttribute( "sessionUser", user.getLogin() );
					this.pageVue = "index.jsp";
				} 
				else{
					session.setAttribute( "sessionUser", null );
					user.setError("Mot de passe érroné !");
				}
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
