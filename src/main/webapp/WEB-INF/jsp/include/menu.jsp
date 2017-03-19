<header>
<nav class="navbar navbar-inverse">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand logo" href="#"><img src=" <c:url value='css/img/logo.png'/>"
				alt="Logo de Roland Garros" /> Roland Garros</a>
		</div>
		<div id="navbar" class="collapse navbar-collapse">
			<ul class="nav navbar-nav">
				<li class="active"><a href="<c:url value='/' />">Home</a></li>
				<li><a href="#about">About</a></li>
				<li><a href="#contact">Contact</a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">Actions<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="<c:url value='/joueurs' />">Ajouter un joueur</a></li>
						<li><a href="<c:url value='/arbitres' />">Ajouter un arbitre</a></li>
						<li><a href="<c:url value='/courts' />">Ajouter un court</a></li>
						<li><a href="<c:url value='/matchs' />">Ajouter un match</a></li>
					</ul></li>
			</ul>
		</div>
	</div>
</nav>
</header>