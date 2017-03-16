//package com.app.tennis.servlets;
//
//import java.lang.reflect.InvocationTargetException;
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.app.tennis.data.Arbitre;
//import com.app.tennis.exceptions.DAOException;
//import com.app.tennis.services.ArbitreService;
//import com.app.tennis.services.NiveauArbitreService;
//import com.app.tennis.services.PaysService;
//
//public class ArbitreSubController {
//	
//	@Autowired
//	private ArbitreService arbitreService;
//	@Autowired
//	private PaysService paysService;
//	@Autowired
//	private NiveauArbitreService niveauService;
//	
//	private Arbitre arbitre;
//	private List<Arbitre> listeArbitres;
//	private String json;
//
//	
//	public ArbitreSubController(HttpServletRequest request) throws Exception {
//		
//		String strAction = request.getParameter("action");
//		
//		if (strAction!=null){
//			
//			switch (strAction){
//			case "create" :
//				create(request);
//				//json = new Gson().toJson(arbitre);
//				break;
//			case "liste" :
//				this.listeArbitres = listAll();
//				//json = new Gson().toJson(this.listeArbitres);
//				break;
//			case "delete" :
//				delete(request);
//				//json = new Gson().toJson(listAll());
//				break;
//			default:
//			}
//		}
//		
//	}
//
//	private void delete(HttpServletRequest request) throws DAOException {
//		
//		int id_arbitre = Integer.parseInt(request.getParameter("id"));
//		arbitreService.deleteById(id_arbitre);
//		
//	}
//
//	private void create(HttpServletRequest request) throws Exception {
//		
//		Arbitre newarbitre = new Arbitre();
//		
//		
//		int id_pays = Integer.parseInt(request.getParameter("pays"));
//		int id_niveau = Integer.parseInt(request.getParameter("niveau"));
//	
//		newarbitre.setNom(request.getParameter("nom"));
//		newarbitre.setPrenom(request.getParameter("prenom"));
//		newarbitre.setSexe(request.getParameter("sexe"));
//		newarbitre.setPays(paysService.findById(id_pays));
//		newarbitre.setNiveau(niveauService.findById(id_niveau));
//		this.arbitre = arbitreService.create(newarbitre);
//		
//	}
//
//	public List<Arbitre> listAll() throws DAOException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
//		return arbitreService.listAll();
//	}
//	
//	public String getJson() {
//		return json;
//	}
//}
