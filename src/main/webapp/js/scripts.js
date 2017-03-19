
var crossError = "<div class='cross' onclick='javascript:HideMsg(\"msg\");'></div>"

function showMsg(id) {
	document.getElementById(id).style.display="block";
}

function HideMsg(id) {
	document.getElementById(id).style.display="none";
}

/* Contrôle des champs */
function control_champs(champs, idError){
			
	var success = true;
	var ListError = [];
	MessageError = "";

	/* key = le nom du champs à contrôler
	   check = type de contrôle à effectuer 
	   type de vérification :
			- notEmpty
			- onlyText
			- number
			- date
	*/

	for(var key in champs)
	{
		var check = champs[key];
		var idKey = "#"+key;
		switch (check){
			case "notEmpty" :
				if ($(idKey).val()==""){
					ListError.push("'"+ key + "'");
					success=false;
				}
				break;
			case "date" : /* Format xx/xx/xxxx */
				var regex = /^[0-9]{2}\/[0-9]{2}\/[0-9]{4}$/;
				if ((!regex.test($(idKey).val())) || ($(idKey).val()=="")) {
					ListError.push("'"+ key + "'");
					success=false;
				}
				break;
			case "onlyText" : /* Seule les caractère alpha sont autorisés. */
				var regex = /[^A-zÀ-ÿ_\-]/;
				if ((regex.test($(idKey).val())) || ($(idKey).val()=="")){
					ListError.push("'"+ key + "'");
					success=false;
				}
				break;
			case "number" :
				var regex = /[^0-9]/;
				if ((regex.test($(idKey).val())) || ($(idKey).val()=="")){
					ListError.push("'"+ key + "'");
					success=false;
				}
				break;
			default :
			success = false;
		}
	}
	if (!success){

		if (ListError.length > 1){
			MessageError +="Les champs ";
			MessageError += ListError.join(",");
			MessageError += " comportent des erreurs !";
		}
		else {
			MessageError +="Le champ ";
			MessageError += ListError.join(",");
			MessageError += " comporte des erreurs !";
		}
		
		$("#"+idError).html(MessageError);
		$("#"+idError).css({'background-color':'#c33c3c'});
		showMsg(idError);

	} else {
		$("#"+idError).html("");
		$("#"+idError).css({'background-color':'transparent'});
	}
	return success;
}


/* Fonctions ajax pour les arbitres */

/* Lister les arbitres */
function listeArbitres(url_json,idDiv){
	var listeDyn =[];
	$.ajax({
			url:url_json,
			type: "GET",
			dataType: "json",
		   	success:function(resultat){
		   		$.each( resultat, function( indice, valeurs ) {
		   			var buttons = "<button type='button' class='btn btn-danger btn-xs' onclick=\"javascript:delArbitre('"+ url_json +"', '" + valeurs.id + "_" + idDiv +"','msg');\">Delete</button></td></tr>";
		   			listeDyn.push( "<tr id='" + valeurs.id + "_" + idDiv + "'><td>" + valeurs.nom + "</td><td>" + valeurs.prenom + "</td><td>" + valeurs.niveau.nom +"</td><td>" + buttons );
		   		});
		   		$("#"+idDiv).html(listeDyn.join(""));
		   	},
		   	error:function(resultat){
		   		$("#"+idDiv).html("Impossible de lister les arbitres !");
		   	}
		});
}

/* Ajouter un arbitre */
function ajouterArbitre(url_json,champs,idError,idDiv){

	if (control_champs(champs, idError)){
		$.ajax({
			url: url_json,
		   	type: "POST",
		   	dataType: "json",
		   	data : {
		   		nom : $("#nom").val(),
		   		prenom : $("#prenom").val(),
		   		pays : $("#pays").val(),
		   		sexe : $('input[type=radio][name=sexe]:checked').attr('value'),
		   		niveau : $("#niveau").val(),
		   		},
		   	success:function(resultat){
		   		$("#"+idError).html("Arbitre Ajouté");
		   		listArbitre(url_json,idDiv)
		   	},
		   	error:function(resultat){
		   		$("#"+idError).html("Impossible d'ajouter l'arbitre !");
		   	},
		   	complete:function(){
		   		listeArbitres(url_json,idDiv);
		   	}
		});
	}
	return false;
}

/* Effacer arbitre */
function delArbitre(url_json_base,asID,idError){
	
	var index = asID.substring(0,asID.indexOf("_"));
	var idDiv = asID.substring(asID.indexOf("_")+1);
	url_json = url_json_base + "/" + index;
	$.ajax({
	   	url: url_json,
		type: 'DELETE',
	   	dataType: "json",
	   	complete:function(){
	   		listeArbitres(url_json_base,idDiv);
	   	}
	});
}


/* Fonctions Ajax pour les joueurs */

/* Lister les joueurs */
function listeJoueurs(url_json,idDiv){
	var listeDyn =[];
	var classement="";
	$.ajax({
		   	url:url_json,
			type: "GET",
		   	dataType: "json",
		   	success:function(resultat){
		   		$.each( resultat, function( indice, valeurs ) {
		   			classement = valeurs.classement;
		   			if (classement == 0){classement="Aucun";}
		   			var buttons = "<button type='button' class='btn btn-danger btn-xs' onclick=\"javascript:delJoueur('"+ url_json +"', '" + valeurs.id + "_" + idDiv +"','msg');\">Delete</button></td></tr>";
		   			listeDyn.push( "<tr id='" + valeurs.id + "_" + idDiv + "'><td>" + valeurs.nom + "</td><td>" + valeurs.prenom + "</td><td>" + classement +"</td><td>" + buttons );
		   		});
		   		$("#"+idDiv).html(listeDyn.join(""));
		   	},
		   	error:function(resultat){
		   		$("#"+idDiv).html("Impossible de lister les joueurs !");
		   	}
		});
}

/* Ajouter un joueur */
function ajouterJoueur(url_json,champs,idError,idDiv){

	if (control_champs(champs, idError)){
		$.ajax({
		   	url: url_json,
		   	type: "POST",
		   	dataType: "json",
		   	data : {
		   		nom : $("#nom").val(),
		   		prenom : $("#prenom").val(),
		   		pays : $("#pays").val(),
		   		sexe : $('input[type=radio][name=sexe]:checked').attr('value'),
		   		classement : $("#classement").val(),
		   		qualification : $("#qualification").val()
		   		},
		   	success:function(){
		   		$("#"+idError).html("Joueur Ajouté");
		   	},
		   	error:function(){
		   		$("#"+idError).html("Impossible d'ajouter le joueur !");
		   	},
		   	complete:function(){
		   		listeJoueurs(url_json,idDiv);
		   	}
		});
	}
	return false;
}

/* Effacer joueur */
function delJoueur(url_json_base,asID,idError){
	
	var index = asID.substring(0,asID.indexOf("_"));
	var idDiv = asID.substring(asID.indexOf("_")+1);
	url_json = url_json_base + "/" + index;
	$.ajax({
	   	url: url_json,
		type: 'DELETE',
	   	dataType: "json",
	   	complete:function(){
	   		listeJoueurs(url_json_base,idDiv);
	   	}
	});
}


/* Fonctions Ajax pour les courts */

/* Lister les courts */
function listeCourts(url_json,idDiv){
	var listeDyn =[];
	$.ajax({
		   	url:url_json,
		   	type: "GET",
		   	dataType: "json",
		   	success:function(resultat){
		   		$.each( resultat, function( indice, valeurs ) {
		   			var buttons = "<button type='button' class='btn btn-danger btn-xs' onclick=\"javascript:delCourt('"+ url_json +"', '" + valeurs.id + "_" + idDiv +"','msg');\">Delete</button></td></tr>";
		   			listeDyn.push( "<tr id='" + valeurs.id + "_" + idDiv + "'><td>" + valeurs.nom + "</td><td>" + buttons );
		   		});
		   		$("#"+idDiv).html(listeDyn.join(""));
		   	},
		   	error:function(resultat){
		   		$("#"+idDiv).html("Impossible de lister les courts !");
		   	}
		});
}

/* Ajouter un court */
function ajouterCourt(url_json,champs,idError,idDiv){

	if (control_champs(champs, idError)){
		$.ajax({
			url: url_json,
		   	type: "POST",
		   	dataType: "json",
		   	data : {
		   		nom : $("#nom").val(),
		   		},
		   	success:function(){
		   		$("#"+idError).html("Court Ajouté");
		   	},
		   	error:function(){
		   		$("#"+idError).html("Impossible de d'ajouter le court !");
		   	},
		   	complete:function(){
		   		listeCourts(url_json,idDiv);
		   	}
		});
	}
	return false;
}

/* Effacer court */
function delCourt(url_json_base,asID,idError){
	
	var index = asID.substring(0,asID.indexOf("_"));
	var idDiv = asID.substring(asID.indexOf("_")+1);
	url_json = url_json_base + "/" + index;
	$.ajax({
	   	url: url_json,
		type: 'DELETE',
	   	dataType: "json",
	   	complete:function(){
	   		listeCourts(url_json_base,idDiv);
	   	}
	});
}



/* Fonctions ajax pour les matchs */

/* Lister les matchs */
function listeMatchs(url_json,idDiv){
	var listeDyn =[];
	$.ajax({
			url:url_json,
			type: "GET",
			dataType: "json",
		   	success:function(resultat){
		   		$.each( resultat, function( indice, valeurs ) {
		   			var buttons = "<th><button type='button' class='btn btn-danger btn-xs' onclick=\"javascript:delMatch('AjaxServlet', '" + valeurs.id + "_" + idDiv +"','msg');\">Delete</button></td></tr>";
		   			listeDyn.push( "<tr id='" + valeurs.id + "_" + idDiv + "'><td>" + valeurs.id + "</td><td>" + valeurs.tournoi.nom + "</td><td>" + valeurs.court.nom + "</td><td>" + valeurs.joueur1.prenom + "</td><td>" + valeurs.joueur2.prenom + "</td><td>" + valeurs.date + "</td>" + buttons );
		   		});
		   		$("#"+idDiv).html(listeDyn.join(""));
		   	},
		   	error:function(resultat){
		   		$("#"+idDiv).html("Impossible de récupérer la page!");
		   	}
		});
}

/* Ajouter un match */
function ajouterMatch(url_json_base,champs,idError,idDiv){

	if (control_champs(champs, idError)){
		$.ajax({
			url: url_json_base,
		   	type: "POST",
		   	dataType: "json",
		   	data : {
		   		tournoi : $("#tournoi").val(),
		   		joueur1 : $("#joueur1").val(),
		   		joueur2 : $("#joueur2").val(),
		   		court : $("#court").val(),
		   		date : $("#date").val(),
		   		arbitre : $("#arbitre").val()
		   		},
		   	success:function(){
		   		$("#"+idError).html("Match Ajouté");
		   	},
		   	error:function(){
		   		$("#"+idError).html("Impossible de d'ajouter le match !");
		   	},
		   	complete:function(){
		   		listeMatchs(url_json_base,idDiv);
		   	}
		});
	}
	return false;
}

/* Effacer match */
function delMatch(url_json_base,asID,idError){
	
	var index = asID.substring(0,asID.indexOf("_"));
	var idDiv = asID.substring(asID.indexOf("_")+1);
	url_json = url_json_base + "/" + index;
	$.ajax({
	   	url: url_json,
		type: 'DELETE',
	   	dataType: "json",
	   	complete:function(){
	   		listeMatchs(url_json_base,idDiv);
	   	}
	});
}


/* Fonctions Cookies */

function creerCookie(asNomCookie,asValeur,asTime) {
	var dExpires = new Date();
	dExpires.setTime(dExpires.getTime() + 3600 * 1000 * asTime);
	document.cookie = asNomCookie + "=" + asValeur + "; expires=" + dExpires.toGMTString();
}

function supprimerCookie(asNomCookie){
	creerCookie(asNomCookie,"no",-1);
}
