<!DOCTYPE html>
<html>
	<head>
		<%@ include file="include/header.jsp" %>
		
		<title>Home</title>

	</head>

	<body>

			<%@ include file="include/menu.jsp" %>



		<div class="jumbotron">
				<div class="container">
					<h1>Gestion Roland Garros</h1>
						<p>Bienvenue dans l'interface de gestion des matchs de Roland Garros.</p>
						<p>Vous êtes identifié avec le compte <strong>${sessionScope.sessionUser}</strong></p>
				</div>
			</div>
		 <div class="container">
			
			<div class="row">
            <div class="col-xs-6 col-lg-4 text-center">
              <h2>Enregistrer des joueurs</h2>
              <a href="<c:url value='/joueurs' /> "> 
              <img src="css/img/joueur.jpg" alt="Enregistrer des joueurs" class="img-thumbnail" />
              </a>
            </div>
            <div class="col-xs-6 col-lg-4 text-center">
              <h2>Enregistrer des arbitres</h2>
               <a href="<c:url value='/arbitres' />">
              <img src="css/img/arbitre.jpg" alt="Enregistrer des arbitres" class="img-thumbnail"/>
              </a>
            </div>
            <div class="col-xs-6 col-lg-4 text-center">
              <h2>Enregistrer des courts</h2>
              <a href="<c:url value='/courts' />">
              <img src="css/img/court.jpg" alt="Enregistrer des courts" class="img-thumbnail" />
              </a>
            </div>
            <div class="col-xs-6 col-lg-4 text-center">
              <h2>Planifier des matchs</h2>
              <a href="<c:url value='/matchs' />">
              <img src="css/img/match.jpg" alt="Planifier des matchs" class="img-thumbnail"/>
              </a>
            </div>
            <div class="col-xs-6 col-lg-4 text-center">
              <h2>Résultats de matchs</h2>
              <a href="<c:url value='/resultats' />">
              <img src="css/img/resultats.jpg" alt="Enregistrer des résultats de matchs" class="img-thumbnail"/>
              </a>
             
            </div>
            <div class="col-xs-6 col-lg-4 text-center">
              <h2>Se déconnecter du site</h2>
              <a href="<c:url value='/connexion' />">
             <img src="css/img/logout.png" alt="Se déconnecter du site" class="img-thumbnail"/>
             </a>
            </div>
          </div>
			
		</div>

		<%@ include file="include/footer.jsp" %>
	</body>

</html>