//package com.app.tennis.servlets;
//
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.app.tennis.data.Court;
//import com.app.tennis.exceptions.DAOException;
//import com.app.tennis.services.CourtService;
//import com.google.gson.Gson;
//
//public class CourtSubController {
//	
//	@Autowired
//	private CourtService courtService;
//	
//	private Court court;
//	private List<Court> listeCourts;
//	private String json;
//
//	
//	public CourtSubController(HttpServletRequest request) throws Exception {
//		
//		String strAction = request.getParameter("action");
//		
//		if (strAction!=null){
//			
//			switch (strAction){
//			case "create" :
//				create(request);
//				json = new Gson().toJson(court);
//				break;
//			case "liste" :
//				this.listeCourts = listAll();
//				json = new Gson().toJson(this.listeCourts);
//				break;
//			case "delete" :
//				delete(request);
//				json = new Gson().toJson(listAll());
//				break;
//			default:
//			}
//		}
//	}
//
//	private void delete(HttpServletRequest request) throws DAOException {
//		int id_court = Integer.parseInt(request.getParameter("id"));
//		courtService.deleteById(id_court);
//	}
//
//	private void create(HttpServletRequest request) throws DAOException {
//		Court newcourt = new Court();
//		newcourt.setNom(request.getParameter("nom"));
//		this.court = courtService.create(newcourt);
//	}
//
//	public List<Court> listAll() throws Exception {
//		return courtService.listAll();
//	}
//	
//	public String getJson() {
//		return json;
//	}
//}
