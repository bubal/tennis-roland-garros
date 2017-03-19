<!DOCTYPE html>
<html>
	<head>
		<%@ include file="include/header.jsp" %>
		
		<title>Enregistrement d'un arbitre</title>

	</head>

	<body>
		
			<%@ include file="include/menu.jsp" %>

		
			<div class="jumbotron">
				<div class="container">
					<h1>Enregistrement d'un arbitre</h1>
					<p>Enregistrer un nouvel arbitre en indiquant son nom, son prénom, sa nationalité et son niveau.</p>
				</div>
			</div>


		<div class="container">
			<div class="row">
				<div class="col-md-1"></div>
				<div class="col-md-3 form-background">
					<h2 class="text-center">Formulaire</h2>
					<form method="post" action="<c:url value='/arbitres/create' />" class="form-horizontal">
						
						<div class="form-group">
							<label for="nom">Nom :</label>
							<input type="text" name="nom" id="nom" class="form-control">
						</div>
						<div class="form-group">
							<label for="prenom">Prénom :</label>
							<input type="text" name="prenom" id="prenom" class="form-control">	
						</div>
						<div class="form-group">
							<label class="radio-inline">
	  							<input type="radio" name="sexe" id="sexeHomme" value="H" checked> Homme
							</label>
							<label class="radio-inline">
	  							<input type="radio" name="sexe" id="sexeFemme" value="F"> Femme
							</label>	
						</div>
						<div class="form-group">
							<label for="pays" >Pays :</label>
								<select name="pays" id="pays" class="form-control">
								<c:forEach var="pays" items="${listePays}">
									<option value="${pays.id}">${pays.nom}</option>
								</c:forEach>
								</select>
						</div>
						<div class="form-group">
						<label for="niveau">Niveau :</label>
								<select name="niveau" id="niveau" class="form-control">
								<c:forEach var="niveau" items="${listeNiveauArbitre}">
									<option value="${niveau.id}">${niveau.nom} - ${niveau.description}</option>
								</c:forEach>
								</select>
							</div>	
						<input type="submit" name="valider" id="valider" class="btn btn-success btn-block " value="Enregistrer l'arbitre" onclick="javascript:return ajouterArbitre('<c:url value='/api/arbitres' />',{'nom':'onlyText','prenom':'onlyText'},'msg','TabDyn');" />
						<div class="alert" id="msg"> </div>
					</form>
				</div>
				<div class="col-md-1"></div>
				<div class="col-md-6">
					<h2 class="text-center">Liste des arbitres enregistrés</h2>
					<table class="table table-hover" >
						<thead>
			              	<tr>
			              		<th>Nom</th>
			              		<th>Prénom</th>
			              		<th>Niveau</th>
			              		<th></th>
			              	</tr>
			             </thead>
			             <tbody id="TabDyn">
			              	<c:forEach var="arbitre" items="${listingArbitres}">
		             		<tr id="${arbitre.id}_TabDyn">
		             			<td>${arbitre.nom}</td>
		             			<td>${arbitre.prenom}</td>
		             			<td>${arbitre.niveau.nom}</td>
		             			<td><button type="button" class="btn btn-danger btn-xs" onclick="javascript:delArbitre('<c:url value='/api/arbitres' />', '${arbitre.id}_TabDyn','msg');">Delete</button></td>
		             		</tr>
							</c:forEach>
		              	</tbody>
					</table>
				</div>	
			</div>
		</div>

		<%@ include file="include/footer.jsp" %>

	</body>

</html>