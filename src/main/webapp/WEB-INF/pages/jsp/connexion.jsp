
<html>
	<head>

		<%@ include file="include/header.jsp" %>
		<title>Rlong Garros - Connexion</title>
		
	</head>

	<body class="connexion">
	
		<%@ include file="include/menu.jsp" %>
		
		<div class="container">
			<h1 class="invisible">Bienvenue dans l'interface de connexion</h1>
			
			<form method="post" action="<c:url value='/connexion' />" class="form-signin">
        		<h2 class="form-signin-heading">Connexion</h2>
        		<input type="hidden" name="task" value="connexion">
        		<label for="login" class="sr-only">Login</label>
        		<input type="text" id="login" name="login" class="form-control" placeholder="Login" required autofocus>
        		<label for="password" class="sr-only">Mot de passe</label>
        		<input type="password" id="password" name="password" class="form-control" placeholder="Password" required>
        		<div class="checkbox">
          			<label>
            		<input type="checkbox" name="cookie" id="cookie" value="remember-me"> Se souvenir de moi
          			</label>
        		</div>
        		<button class="btn btn-lg btn-primary btn-block" type="submit">Se connecter</button>
        		
        		<div class="alert" id="msg">${errorMsg}</div>
      		
      		</form>
      		
		</div>

		<%@ include file="include/footer.jsp" %>
	</body>
</html>