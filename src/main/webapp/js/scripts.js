
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

/* Ajouter un arbitre */
function ajouterArbitre(url_json,champs,idError,idDiv){

	if (control_champs(champs, idError)){
		
		$.ajax({
		   	url:url_json,
		   	dataType: "json",
		   	data : {
		   		ajax : "yes",
		   		action : "create",
		   		task : "arbitre",
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
		   		$("#"+idDiv).html("Impossible de récupérer la page!");
		   	}
		});
	}
	return false;
}

/* Effacer un arbitre */
function delArbitre(url_json,asID,idError){
	
	var index = asID.substring(0,asID.indexOf("_"));
	var idDiv = asID.substring(asID.indexOf("_")+1);
	$.ajax({
	   	url:url_json,
	   	dataType: "json",
	   	data : {
	   		ajax : "yes",
	   		action : "delete",
	   		task : "arbitre",
	   		id : index,
	   		},
	   	success:function(resultat){
	   		$("#"+idError).html("Arbitre supprimé");
	   		listArbitre(url_json,idDiv)
	   	},
	   	error:function(resultat){
	   		$("#"+idDiv).html("Impossible de récupérer la page!");
	   	}
	});
}

/* Lister les arbitres */
function listArbitre(url_json,idDiv){
	var listeDyn =[];
	$.ajax({
		   	url:url_json,
		   	dataType: "json",
		   	data : {
		   		ajax : "yes",
		   		action : "liste",
		   		task : "arbitre"
		   		},
		   	success:function(resultat){
		   		$.each( resultat, function( indice, valeurs ) {
		   			var buttons = "<button type='button' class='btn btn-danger btn-xs' onclick=\"javascript:delArbitre('AjaxServlet', '" + valeurs.id + "_" + idDiv +"','msg');\">Delete</button></th></tr>";
		   			listeDyn.push( "<tr id='" + valeurs.id + "_" + idDiv + "'><th>" + valeurs.nom + "</th><th>" + valeurs.prenom + "</th><th>" + valeurs.niveau.nom +"</th><th>" + buttons );
		   		});
		   		$("#"+idDiv).html(listeDyn.join(""));
		   	},
		   	error:function(resultat){
		   		$("#"+idDiv).html("Impossible de récupérer la page!");
		   	}
		});
}



/* Fonctions ajax pour les joueurs */


/* Ajouter un joueur */
function ajouterJoueur(url_json,champs,idError,idDiv){

	if (control_champs(champs, idError)){
		
		$.ajax({
		   	url:url_json,
		   	dataType: "json",
		   	data : {
		   		ajax : "yes",
		   		action : "create",
		   		task : "joueur",
		   		nom : $("#nom").val(),
		   		prenom : $("#prenom").val(),
		   		pays : $("#pays").val(),
		   		sexe : $('input[type=radio][name=sexe]:checked').attr('value'),
		   		classement : $("#classement").val(),
		   		qualification : $("#qualification").val()
		   		},
		   	success:function(resultat){
		   		$("#"+idError).html("Joueur Ajouté");
		   		listJoueur(url_json,idDiv)
		   	},
		   	error:function(resultat){
		   		$("#"+idDiv).html("Impossible de récupérer la page!");
		   	}
		});
	}
	return false;
}


/* Effacer un joueur */
function delJoueur(url_json,asID,idError){
	
	var index = asID.substring(0,asID.indexOf("_"));
	var idDiv = asID.substring(asID.indexOf("_")+1);
	$.ajax({
	   	url:url_json,
	   	dataType: "json",
	   	data : {
	   		ajax : "yes",
	   		action : "delete",
	   		task : "joueur",
	   		id : index,
	   		},
	   	success:function(resultat){
	   		$("#"+idError).html("Joueur supprimé");
	   		listJoueur(url_json,idDiv)
	   	},
	   	error:function(resultat){
	   		$("#"+idDiv).html("Impossible de récupérer la page!");
	   	}
	});
}


/* Lister les joueurs */
function listJoueur(url_json,idDiv){
	var listeDyn =[];
	var classement="";
	$.ajax({
		   	url:url_json,
		   	dataType: "json",
		   	data : {
		   		ajax : "yes",
		   		action : "liste",
		   		task : "joueur"
		   		},
		   	success:function(resultat){
		   		$.each( resultat, function( indice, valeurs ) {
		   			classement = valeurs.classement;
		   			if (classement == 0){classement="Aucun";}
		   			var buttons = "<button type='button' class='btn btn-danger btn-xs' onclick=\"javascript:delJoueur('AjaxServlet', '" + valeurs.id + "_" + idDiv +"','msg');\">Delete</button></th></tr>";
		   			listeDyn.push( "<tr id='" + valeurs.id + "_" + idDiv + "'><th>" + valeurs.nom + "</th><th>" + valeurs.prenom + "</th><th>" + classement +"</th><th>" + buttons );
		   		});
		   		$("#"+idDiv).html(listeDyn.join(""));
		   	},
		   	error:function(resultat){
		   		$("#"+idDiv).html("Impossible de récupérer la page!");
		   	}
		});
}


/* Fonctions ajax pour les courts */

/* Ajouter un court */
function ajouterCourt(url_json,champs,idError,idDiv){

	if (control_champs(champs, idError)){
		
		$.ajax({
		   	url:url_json,
		   	dataType: "json",
		   	data : {
		   		ajax : "yes",
		   		action : "create",
		   		task : "court",
		   		nom : $("#nom").val(),
		   		},
		   	success:function(resultat){
		   		$("#"+idError).html("Court Ajouté");
		   		listCourt(url_json,idDiv)
		   	},
		   	error:function(resultat){
		   		$("#"+idDiv).html("Impossible de récupérer la page!");
		   	}
		});
	}
	return false;
}

/* Effacer un court */
function delCourt(url_json,asID,idError){
	
	var index = asID.substring(0,asID.indexOf("_"));
	var idDiv = asID.substring(asID.indexOf("_")+1);
	$.ajax({
	   	url:url_json,
	   	dataType: "json",
	   	data : {
	   		ajax : "yes",
	   		action : "delete",
	   		task : "court",
	   		id : index,
	   		},
	   	success:function(resultat){
	   		$("#"+idError).html("Court supprimé");
	   		listCourt(url_json,idDiv)
	   	},
	   	error:function(resultat){
	   		$("#"+idDiv).html("Impossible de récupérer la page!");
	   	}
	});
}

/* Lister les courts */
function listCourt(url_json,idDiv){
	var listeDyn =[];
	$.ajax({
		   	url:url_json,
		   	dataType: "json",
		   	data : {
		   		ajax : "yes",
		   		action : "liste",
		   		task : "court"
		   		},
		   	success:function(resultat){
		   		$.each( resultat, function( indice, valeurs ) {
		   			var buttons = "<button type='button' class='btn btn-danger btn-xs' onclick=\"javascript:delCourt('AjaxServlet', '" + valeurs.id + "_" + idDiv +"','msg');\">Delete</button></th></tr>";
		   			listeDyn.push( "<tr id='" + valeurs.id + "_" + idDiv + "'><th>" + valeurs.nom + "</th><th>" + buttons );
		   		});
		   		$("#"+idDiv).html(listeDyn.join(""));
		   	},
		   	error:function(resultat){
		   		$("#"+idDiv).html("Impossible de récupérer la page!");
		   	}
		});
}


/* Fonctions ajax pour les matchs */


/* Ajouter un match */
function ajouterMatch(url_json,champs,idError,idDiv){

	if (control_champs(champs, idError)){
		
		$.ajax({
		   	url:url_json,
		   	dataType: "json",
		   	data : {
		   		ajax : "yes",
		   		action : "create",
		   		task : "match",
		   		tournoi : $("#tournoi").val(),
		   		joueur1 : $("#joueur1").val(),
		   		joueur2 : $("#joueur2").val(),
		   		court : $("#court").val(),
		   		date : $("#date").val(),
		   		arbitre : $("#arbitre").val()
		   		},
		   	success:function(resultat){
		   		$("#"+idError).html("Match Ajouté");
		   		listMatch(url_json,idDiv)
		   	},
		   	error:function(resultat){
		   		$("#"+idDiv).html("Impossible de récupérer la page!");
		   	}
		});
	}
	return false;
}


/* Effacer un match */
function delMatch(url_json,asID,idError){
	
	var index = asID.substring(0,asID.indexOf("_"));
	var idDiv = asID.substring(asID.indexOf("_")+1);
	$.ajax({
	   	url:url_json,
	   	dataType: "json",
	   	data : {
	   		ajax : "yes",
	   		action : "delete",
	   		task : "match",
	   		id : index,
	   		},
	   	success:function(resultat){
	   		$("#"+idError).html("Match supprimé");
	   		listMatch(url_json,idDiv)
	   	},
	   	error:function(resultat){
	   		$("#"+idDiv).html("Impossible de récupérer la page!");
	   	}
	});
}


/* Lister les matchs */
function listMatch(url_json,idDiv){
	var listeDyn =[];
	$.ajax({
		   	url:url_json,
		   	dataType: "json",
		   	data : {
		   		ajax : "yes",
		   		action : "liste",
		   		task : "match"
		   		},
		   	success:function(resultat){
		   		$.each( resultat, function( indice, valeurs ) {
		   			var buttons = "<th><button type='button' class='btn btn-danger btn-xs' onclick=\"javascript:delMatch('AjaxServlet', '" + valeurs.id + "_" + idDiv +"','msg');\">Delete</button></th></tr>";
		   			listeDyn.push( "<tr id='" + valeurs.id + "_" + idDiv + "'><th>" + valeurs.id + "</th><th>" + valeurs.tournoi.nom + "</th><th>" + valeurs.court.nom + "</th><th>" + valeurs.joueur1.prenom + "</th><th>" + valeurs.joueur2.prenom + "</th><th>" + valeurs.date + "</th>" + buttons );
		   		});
		   		$("#"+idDiv).html(listeDyn.join(""));
		   	},
		   	error:function(resultat){
		   		$("#"+idDiv).html("Impossible de récupérer la page!");
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
