package com.app.tennis.netbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.app.tennis.data.Acces;
import com.app.tennis.services.AccesService;

@ManagedBean
@SessionScoped
public class ConnexionBean {
	
	@ManagedProperty(value = "#{accesServiceImpl}")
	private AccesService accesService;
	
	private String login;
	private String password;
	private String msg;
	
	public String connecting(){
		String suite = "connexion";
		Acces user = new Acces(login);
		user = accesService.grantedAcces(login, password);
		if (user.isAcces()){
			msg = "Bravo!";
		} else {msg = user.getError();}
		
		return suite;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public AccesService getAccesService() {
		return accesService;
	}

	public void setAccesService(AccesService accesService) {
		this.accesService = accesService;
	}

	
	
	
}
