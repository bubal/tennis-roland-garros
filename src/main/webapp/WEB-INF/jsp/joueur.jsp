<html>
	<head>
		<%@ include file="include/header.jsp" %>
		<title>Enregistrement d'un joueur</title>

	</head>

	<body>
	
			<%@ include file="include/menu.jsp" %>
		
		
			<div class="jumbotron">
				<div class="container">
					<h1>Enregistrement d'un joueur</h1>
						<p>Enregistrer un nouveau joueur en indiquant son nom, son prénom, son sexe et sa nationalité.</p>
				</div>
			</div>
		
		<div class="container">
			

			<div class="row">
			<div class="col-md-1"></div>
			<div class="col-md-3 form-background">
				<h2 class="text-center">Formulaire</h2>
				<form method="post" action="<c:url value='/joueurs/create' />" class="form-horizontal">
				
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
							<label for="classement">Classement :</label>
							<input type="number" name="classement" id="classement" class="form-control">
						</div>
						<div class="form-group">
							<label for="qualification" >Qualification :</label>
							
								<select name="qualification" id="qualification" class="form-control">
								<c:forEach var="qualification" items="${listeQualifications}">
									<option value="${qualification.id}">${qualification.nom}</option>
								</c:forEach>
								</select>
							
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
							<input type="submit" name="valider" id="valider" class="btn btn-success btn-block " value="Enregistrer le joueur" onclick="javascript:return ajouterJoueur('<c:url value="/api/joueurs" />',{'nom':'onlyText','prenom':'onlyText', 'classement':'number'},'msg','TabDyn');" />
						</div>
						<div class="alert" id="msg">${errorMsg}</div>
					</form>
			</div>
			<div class="col-md-1"></div>
			<div class="col-md-6">
			
				<h2 class="text-center">Liste des joueurs enregistrés</h2>

				<table class="table table-hover" >
					<thead>
		              	<tr>
		              		<th>Nom</th>
		              		<th>Prénom</th>
		              		<th>Classement</th>
		              		<th></th>
		              	</tr>
		             </thead>
		             <tbody id="TabDyn">
		             <c:forEach var="joueur" items="${listingJoueurs}">
		             	<tr id="${joueur.id}_TabDyn">
		             		<td>${joueur.nom}</td>
		             		<td>${joueur.prenom}</td>
		             		<td><c:choose><c:when test="${joueur.classement == 0}">Aucun</c:when><c:otherwise>${joueur.classement}</c:otherwise></c:choose></td>
		             		<td><button type="button" class="btn btn-danger btn-xs" onclick="javascript:delJoueur('<c:url value='/api/joueurs' />', '${joueur.id}_TabDyn','msg');">Delete</button></td>
		             	</tr>
					</c:forEach>
	              	</tbody>
				</table>
				


			</div>
			<div class="col-md-1"></div>
		</div>
	
		</div>

	<%@ include file="include/footer.jsp" %>
	</body>

</html>