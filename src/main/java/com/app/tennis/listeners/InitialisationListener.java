package com.app.tennis.listeners;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.tennis.data.NiveauArbitre;
import com.app.tennis.data.Pays;
import com.app.tennis.data.Tournoi;
import com.app.tennis.data.TypeQualification;
import com.app.tennis.services.NiveauArbitreService;
import com.app.tennis.services.PaysService;
import com.app.tennis.services.TournoiService;
import com.app.tennis.services.TypeQualificationService;

public class InitialisationListener implements ServletContextListener {

	@Autowired
	private PaysService paysService;
	@Autowired
	private TournoiService tournoiService;
	@Autowired
	private NiveauArbitreService niveauArbitreService;
	@Autowired
	private TypeQualificationService typeQualificationService;

	List<Tournoi> listeTournois = null;
	List<TypeQualification> listeQualifications = null;
	List<NiveauArbitre> listeNiveauArbitre = null;
	List<Pays> listePays = null;


	public InitialisationListener() {

	}

	public void contextInitialized(ServletContextEvent e) {

		ServletContext application = e.getServletContext();

		listeTournois             = tournoiService.listAll();
		listePays                 = paysService.listAll();
		listeQualifications       = typeQualificationService.listAll();
		listeNiveauArbitre        = niveauArbitreService.listAll();

		application.setAttribute("listePays",listePays);
		application.setAttribute("listeTournois",listeTournois);
		application.setAttribute("listeQualifications",listeQualifications);
		application.setAttribute("listeNiveauArbitre",listeNiveauArbitre);

	}

	public void contextDestroyed(ServletContextEvent e)  { 

	}

}
