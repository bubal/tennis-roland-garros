
<html>
	<head>
		<%@ include file="include/header.jsp" %>

		
		<title>Déconnexion</title>

	</head>

	<body class="connexion">
		<%@ include file="include/menu.jsp" %>

		<div class="container text-center">
			<div class="row">
			<div class="col-md-4"></div>
			<div class="col-md-4">
				<h1 class="invisible">Déconnexion</h1>
				<h2>Vous êtes déconnecté</h2>
				<a class="btn btn-lg btn-primary btn-block" href="<c:url value='/ControlServlet?task=connexion' />" role="button">Se connecter</a>
			</div>
			<div class="col-md-4"></div>
			</div>
		</div>

		<%@ include file="include/footer.jsp" %>

	</body>

</html>