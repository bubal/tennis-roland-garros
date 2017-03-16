//package com.app.tennis.servlets;
//
//import java.io.IOException;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//
//public class AjaxServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//       
//    
//    public AjaxServlet() {
//        super();
//    }
//
//	
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
//		String task = request.getParameter("task");
//		String json="";
//		
//		switch (task){
//		case "joueur" :
//			JoueurSubController joueurSubControl;
//			try {
//				joueurSubControl = new JoueurSubController(request);
//				json = joueurSubControl.getJson();
//			} catch (Exception e) {
//				request.setAttribute("errorMsg", e.getMessage());
//			}
//			
//			break;
//		case "arbitre" :
//			ArbitreSubController arbitreSubControl;
//			try {
//				arbitreSubControl = new ArbitreSubController(request);
//				json = arbitreSubControl.getJson();
//			} catch (Exception e) {
//				request.setAttribute("errorMsg", e.getMessage());
//			}
//			
//			break;
//		case "court" :
//			try {
//				CourtSubController courtControl = new CourtSubController(request);
//				json = courtControl.getJson();
//			} catch (Exception e) {
//				request.setAttribute("errorMsg", e.getMessage());
//			}
//			break;
//		case "match" :
//			try {
//				MatchSubController matchControl = new MatchSubController(request);
//				json = matchControl.getJson();
//			} catch (Exception e) {
//				request.setAttribute("errorMsg", e.getMessage());
//			}
//			break;
//		default:
//		}
//		
//		response.setContentType("application/json");
//		response.setCharacterEncoding("UTF-8");
//		response.getWriter().write(json);
//	
//
//	}
//
//	
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doGet(request, response);
//	}
//
//}
