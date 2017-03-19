
<html>
<head>
<%@ include file="include/header.jsp"%>

<title>Planifier des matchs</title>

</head>

<body>
	<%@ include file="include/menu.jsp"%>

	<div class="jumbotron">
		<div class="container">
			<h1>Planifier des matchs</h1>
			<p>Pour créer un match, sélectionner deux joueurs, un arbitre, un
				court et une date.</p>
		</div>
	</div>


	<div class="container">

		<div class="row">
			<div class="col-md-1"></div>
			<div class="col-md-3 form-background">
				<h2 class="text-center">Formulaire</h2>

				<form method="post" action="<c:url value='/matchs/create' />"
					class="form-horizontal">
					
					<div class="form-group">
						<label for="tournoi">Tournoi :</label> 
						<select name="tournoi" id="tournoi" class="form-control">
							<c:forEach var="tournoi" items="${listeTournois}">
									<option value="${tournoi.id}">${tournoi.nom}</option>
							</c:forEach>
						</select>
					</div>

					<div class="form-group">
						<label for="joueur1">Joueur 1 :</label> <select name="joueur1"
							id="joueur1" class="form-control">
							<c:forEach var="joueur" items="${listingJoueurs}">
									<option value="${joueur.id}">${joueur.prenom} ${joueur.nom}</option>
							</c:forEach>
						</select>
					</div>

					<div class="form-group">
						<label for="joueur2">Joueur 2 :</label> <select name="joueur2"
							id="joueur2" class="form-control">
							<c:forEach var="joueur" items="${listingJoueurs}">
									<option value="${joueur.id}">${joueur.prenom} ${joueur.nom}</option>
							</c:forEach>
						</select>
					</div>

					<div class="form-group">
						<label for="arbitre">Arbitre :</label> <select name="arbitre"
							id="arbitre" class="form-control">
							<c:forEach var="arbitre" items="${listingArbitres}">
									<option value="${arbitre.id}">${arbitre.prenom} ${arbitre.nom}</option>
							</c:forEach>
						</select>
					</div>

					<div class="form-group">
						<label for="court">Court :</label> <select name="court" id="court"
							class="form-control">
							<c:forEach var="court" items="${listingCourts}">
									<option value="${court.id}">${court.nom}</option>
							</c:forEach>
						</select>
					</div>

					<div class="form-group">
						<label for="date"> Date : </label> 
						<input type="text" name="date" id="date" class="form-control" />
					</div>

					<div class="form-group">
						<input type="submit" name="valider" id="valider"
							class="btn btn-success btn-block " value="Enregistrer le match" onclick="javascript:return ajouterMatch('<c:url value='/api/matchs' />',{'date':'date'},'msg','TabDyn');"/>
					</div>
					<div class="alert" id="msg"></div>
				</form>

			</div>

			<div class="col-md-1"></div>
			<div class="col-md-6">
				<h2 class="text-center">Liste des matchs enregistrés</h2>
					<table class="table table-hover">
						<thead>
							<tr>
								<th>#</th>
								<th>Tournoi</th>
								<th>Court</th>
								<th>Joueur1</th>
								<th>Joueur2</th>
								<th>Date</th>
								<th></th>
							</tr>
						</thead>
						<tbody id="TabDyn">
							<c:forEach var="match" items="${listingMatchs}">
								<tr id="${match.id}_TabDyn">
									<td>${match.id}</td>
									<td>${match.tournoi.nom}</td>
									<td>${match.court.nom}</td>
									<td>${match.joueur1.prenom} ${match.joueur1.nom}</td>
									<td>${match.joueur2.prenom} ${match.joueur2.nom}</td>
									<td>${match.date}</td>
									<td><button type="button" class="btn btn-danger btn-xs" onclick="javascript:delMatch('<c:url value='/api/matchs' />', '${match.id}_TabDyn','msg');">Delete</button></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
			</div>
		</div>
	</div>

	<%@ include file="include/footer.jsp"%>

</body>

<script type="text/javascript">
    	  $( function() {
    		$( "#date" ).datepicker();
  		} );

	</script>



</html>