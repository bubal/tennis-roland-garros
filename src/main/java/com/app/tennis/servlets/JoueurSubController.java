//package com.app.tennis.servlets;
//
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.app.tennis.data.Joueur;
//import com.app.tennis.exceptions.DAOException;
//import com.app.tennis.services.JoueurService;
//import com.app.tennis.services.PaysService;
//import com.app.tennis.services.TypeQualificationService;
//import com.app.tennis.services.impl.JoueurServiceImpl;
//import com.google.gson.Gson;
//
//public class JoueurSubController {
//	
//	@Autowired
//	private JoueurService joueurService;
//	@Autowired
//	private PaysService paysService ;
//	@Autowired
//	private TypeQualificationService qualificationService;
//	
//	private Joueur joueur;
//	private List<Joueur> listeJoueurs;
//	private String json;
//	
//	
//	public JoueurSubController(HttpServletRequest request) throws Exception {
//		super();
//
//		/* Initialisation du Service */
//		joueurService = new JoueurServiceImpl();
//		
//		String strAction = request.getParameter("action");
//		
//		if (strAction!=null){
//			
//			switch (strAction){
//			case "create" :
//				create(request);
//				json = new Gson().toJson(joueur);
//				break;
//			case "liste" :
//				this.listeJoueurs = listAll();
//				json = new Gson().toJson(this.listeJoueurs);
//				break;
//			case "delete" :
//				delete(request);
//				json = new Gson().toJson(listAll());
//				break;
//			default:
//			}
//		}
//		
//	}
//
//	private void create(HttpServletRequest request) throws Exception {
//		
//		Joueur newjoueur = new Joueur();
//		
//		
//		int id_pays = Integer.parseInt(request.getParameter("pays"));
//		int id_qualification = Integer.parseInt(request.getParameter("qualification"));
//		
//	
//		int num;
//		try {
//			num = Integer.parseInt(request.getParameter("classement"));
//		} catch (NumberFormatException e) {
//			num = 0;
//		}
//	
//		newjoueur.setNom(request.getParameter("nom"));
//		newjoueur.setPrenom(request.getParameter("prenom"));
//		newjoueur.setSexe(request.getParameter("sexe"));
//		newjoueur.setPays(paysService.findById(id_pays));
//		newjoueur.setQualification(qualificationService.findById(id_qualification));
//		newjoueur.setClassement(num);
//		this.joueur = joueurService.create(newjoueur);
//	}
//
//	public List<Joueur> listAll() throws Exception {
//		return joueurService.listAll();
//	}
//
//	private void delete(HttpServletRequest request) throws DAOException {
//		
//		int id_joueur = Integer.parseInt(request.getParameter("id"));
//		joueurService.deleteById(id_joueur);
//		
//	}
//
//
//	public List<Joueur> getListeJoueurs() {
//		return listeJoueurs;
//	}
//
//	public String getJson() {
//		return json;
//	}
//	
//}
