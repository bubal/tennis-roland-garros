package com.app.tennis.listeners;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.app.tennis.data.NiveauArbitre;
import com.app.tennis.data.Pays;
import com.app.tennis.data.Tournoi;
import com.app.tennis.data.TypeQualification;
import com.app.tennis.services.NiveauArbitreService;
import com.app.tennis.services.PaysService;
import com.app.tennis.services.TournoiService;
import com.app.tennis.services.TypeQualificationService;

@WebListener
public class InitialisationListener implements ServletContextListener {

	/* On ne peut pas utiliser Autowired dans le listener */
	/* On charge le contexte d'application de spring */
	private static ApplicationContext   applicationContext  = new ClassPathXmlApplicationContext("classpath:spring-application-context.xml");

	private PaysService paysService;
	private TournoiService tournoiService;
	private NiveauArbitreService niveauArbitreService;
	private TypeQualificationService typeQualificationService;

	List<Tournoi> listeTournois = null;
	List<TypeQualification> listeQualifications = null;
	List<NiveauArbitre> listeNiveauArbitre = null;
	List<Pays> listePays = null;


	public InitialisationListener() {
		
		/* On récupère les beans depuis le contexte d'application de spring */
		/* Equivalent à Autowired */
		
		paysService              = applicationContext.getBean(PaysService.class);
		tournoiService           = applicationContext.getBean(TournoiService.class);
		niveauArbitreService     = applicationContext.getBean(NiveauArbitreService.class);
		typeQualificationService = applicationContext.getBean(TypeQualificationService.class);

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
