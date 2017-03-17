
<html>
	<head>
		<%@ include file="include/header.jsp" %>
		
		<title>Enregistrement d'un court</title>

	</head>

	<body>
		<%@ include file="include/menu.jsp" %>

		<div class="jumbotron">
				<div class="container">
					<h1>Enregistrement d'un court</h1>
					<p>Enregistrer un nouveau court en indiquant son nom.</p>
				</div>
			</div>
		

		<div class="container">

			<div class="row">
				<div class="col-md-1"></div>
				<div class="col-md-3 form-background">
					<h2 class="text-center">Formulaire</h2>
					<form method="post" action="<c:url value='/courts/create' />" class="form-horizontal">
						
						<div class="form-group">
								<label for="nom">Nom :</label>
									<input type="text" name="nom" id="nom" class="form-control">
							</div>

						<input type="submit" name="valider" id="valider" class="btn btn-success btn-block " value="Enregistrer le court" onclick="javascript:return ajouterCourt('<c:url value='/AjaxServlet' />',{'nom':'onlyText'},'msg','TabDyn');" />
						<div class="alert" id="msg"> </div>
					</form>
				</div>

				<div class="col-md-1"></div>
				<div class="col-md-6">
					<h2 class="text-center">Liste des courts enregistrés</h2>
					<table class="table table-hover" >
						<thead>
			              	<tr>
			              		<th>Nom</th>
			              		<th></th>
			              	</tr>
			             </thead>
			             <tbody id="TabDyn">
			            	<c:forEach var="court" items="${listingCourts}">
		             			<tr id="${court.id}_TabDyn">
		             				<td>${court.nom}</td>
		             				<td><button type="button" class="btn btn-danger btn-xs" onclick="javascript:delCourt('<c:url value='/AjaxServlet' />', '${court.id}_TabDyn','msg');">Delete</button></td>
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