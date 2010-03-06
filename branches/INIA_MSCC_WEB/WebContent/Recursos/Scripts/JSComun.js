// ***************************************************************************
// Valida que se puedan ingresar solamente letras, incluyendo tildes y 
// el espacio en blanco
// ***************************************************************************
function ValidarCampoLetras(obj, event) {
	var validarTexto = obj.value;
	switch (event.type) {
	case "keypress":
		validarTexto += String.fromCharCode(event.keyCode);
		break;

	case "paste":
		validarTexto += clipboardData.getData("Text");
		break;

	case "drop":
		validarTexto += event.dataTransfer.getData("Text");
		break;
	}

	if (!validarTexto.match('^[A-Z,a-z,�������������� ]+$')) {
		event.returnValue = false;
		event.cancel = true;
	}
}

// ***************************************************************************
// Valida que solo se puedan ingresar caracteres alfanum�ricos en un campo
// incluyendo � ,tildes, di�resis y el espacio en blanco.
// ***************************************************************************
function ValidarCampoAlfaNumericoConEspacio(obj, event) {
	var validarTexto = obj.value;
	switch (event.type) {
	case "keypress":
		validarTexto += String.fromCharCode(event.keyCode);
		break;

	case "paste":
		validarTexto += clipboardData.getData("Text");
		break;

	case "drop":
		validarTexto += event.dataTransfer.getData("Text");
		break;
	}

	if (!validarTexto.match('^[0-9,A-Z,a-z,�������������� ]+$')) {
		event.returnValue = false;
		event.cancel = true;
	}
}

// ***************************************************************************
// Valida que solo se puedan ingresar caracteres num�ricos en un campo.
// ***************************************************************************
function ValidarCampoNumerico(obj, event) {
	var validarTexto = obj.value;
	switch (event.type) {
	case "keypress":
		validarTexto += String.fromCharCode(event.keyCode);
		break;

	case "paste":
		validarTexto += clipboardData.getData("Text");
		break;

	case "drop":
		validarTexto += event.dataTransfer.getData("Text");
		break;
	}

	if (!validarTexto.match('^[0-9]+$')) {
		event.returnValue = false;
		event.cancel = true;
	}
}

// *****************************************************************************
// Valida que solo se puedan ingresar caracteres num�ricos y puntos en un campo.
// *****************************************************************************
function ValidarCampoNumericoDouble(obj, event) {
	debugger;
	var validarTexto = obj.value;
	switch (event.type) {
	case "keypress":
		validarTexto += String.fromCharCode(event.keyCode);
		break;

	case "paste":
		validarTexto += clipboardData.getData("Text");
		break;

	case "drop":
		validarTexto += event.dataTransfer.getData("Text");
		break;
	}
	var array = validarTexto.split('.');
	if (!validarTexto.match('^[0-9.]+$') || array.lenght > 2) {
		event.returnValue = false;
		event.cancel = true;
	}
}

// ***************************************************************************
// Valida que solo se puedan ingresar caracteres alfanum�ricos en un campo
// incluyendo � y sin espacio en blanco.
// ***************************************************************************
function ValidarCampoParaCodigo(obj, event) {
	var validarTexto = obj.value;
	switch (event.type) {
	case "keypress":
		validarTexto += String.fromCharCode(event.keyCode);
		break;

	case "paste":
		validarTexto += clipboardData.getData("Text");
		break;

	case "drop":
		validarTexto += event.dataTransfer.getData("Text");
		break;
	}

	if (!validarTexto.match('^[0-9A-Za-z��]+$')) {
		event.returnValue = false;
		event.cancel = true;
	}
}

// ***************************************************************************
// Valida que solo se puedan ingresar caracteres alfanum�ricos en un campo
// incluyendo � y sin espacio en blanco.
// ***************************************************************************
function ValidarCampoTelefono(obj, event) {
	var validarTexto = obj.value;
	switch (event.type) {
	case "keypress":
		validarTexto += String.fromCharCode(event.keyCode);
		break;

	case "paste":
		validarTexto += clipboardData.getData("Text");
		break;

	case "drop":
		validarTexto += event.dataTransfer.getData("Text");
		break;
	}

	if (!validarTexto.match('^[0-9,()- ]+$')) {
		event.returnValue = false;
		event.cancel = true;
	}
}

// ***************************************************************************
// Valida que solo se tantos caractes como largoMaximo sea especificado.
// ***************************************************************************
function ValidarLargoMultiline(obj, event, largoMaximo) {
	var validarTexto = obj.value;
	switch (event.type) {
	case "keypress":
		validarTexto += String.fromCharCode(event.keyCode);
		break;

	case "paste":
		validarTexto += clipboardData.getData("Text");
		break;

	case "drop":
		validarTexto += event.dataTransfer.getData("Text");
		break;
	}

	if (validarTexto.length > largoMaximo) {
		event.returnValue = false;
		event.cancel = true;
	}
}

// ***************************************************************************
// Valida que se puedan ingresar caracteres alfanum�ricos y caracteres
// especiales incluyendo el espacio en blanco
// ***************************************************************************
function ValidarCampoConCaracteresEspeciales(obj, event) {
	var validarTexto = obj.value;
	switch (event.type) {
	case "keypress":
		validarTexto += String.fromCharCode(event.keyCode);
		break;

	case "paste":
		validarTexto += clipboardData.getData("Text");
		break;

	case "drop":
		validarTexto += event.dataTransfer.getData("Text");
		break;
	}

	if (!validarTexto
			.match('^[0-9,A-Z,a-z,��������������&#@,-.{}�?"%&/()=*;:�!� ]+$')
			|| (validarTexto.match('&#'))
			|| (validarTexto.match('&'))
			|| (validarTexto.match('#'))) {
		event.returnValue = false;
		event.cancel = true;
	}
}

//***************************************************************************
//Valida que se puedan ingresar caracteres alfanum�ricos y caracteres
//especiales incluyendo el espacio en blanco
//***************************************************************************
function ValidarCampoUsuarioLogin(obj, event) {
	var validarTexto = obj.value;
	switch (event.type) {
	case "keypress":
		validarTexto += String.fromCharCode(event.keyCode);
		break;

	case "paste":
		validarTexto += clipboardData.getData("Text");
		break;

	case "drop":
		validarTexto += event.dataTransfer.getData("Text");
		break;
	}

	if (!validarTexto.match('^[a-z,A-Z,0-9,.,_,-]+$')) {
		event.returnValue = false;
		event.cancel = true;
	}
}

// ***************************************************************************
// Valida que se puedan ingresar caracteres alfanum�ricos y caracteres
// especiales incluyendo el espacio en blanco
// ***************************************************************************
function ValidarCampoLogin(obj, event) {
	var validarTexto = obj.value;
	switch (event.type) {
	case "keypress":
		validarTexto += String.fromCharCode(event.keyCode);
		break;

	case "paste":
		validarTexto += clipboardData.getData("Text");
		break;

	case "drop":
		validarTexto += event.dataTransfer.getData("Text");
		break;
	}

	if (!validarTexto.match('^[a-z,A-Z,0-9,$%&,.,_,-]+$')) {
		event.returnValue = false;
		event.cancel = true;
	}
}

// ***************************************************************************
// Valida que se puedan ingresar solamente letras, incluyendo tildes y
// el espacio en blanco
// ***************************************************************************
function validarEmailKeyPress(obj, event) {
	var textoAgregado = "";
	if (event.keyCode != 0) {
		textoAgregado = String.fromCharCode(event.keyCode);
	} else {
		textoAgregado = clipboardData.getData("Text");
	}
	var textoCompleto = obj.value + textoAgregado;
	if (textoAgregado == ',') {
		event.returnValue = false;
		event.cancel = true;
	}
	if (!textoCompleto.match('^[a-zA-Z,0-9,@,.,_,-]+$')) {
		event.returnValue = false;
		event.cancel = true;
	}
}

function validarEmailBlur(obj, event) {
	var textoAgregado = "";
	if (event.keyCode != 0) {
		textoAgregado = String.fromCharCode(event.keyCode);
	}
	if (textoAgregado == ',') {
		event.returnValue = false;
		event.cancel = true;
	}
	if (/^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/
			.test(obj.value)) {
		event.returnValue = true;
		event.cancel = false;
	} else {
		alert("La direcci�n de email es incorrecta.");
		event.returnValue = false;
		event.cancel = true;
		obj.value = "";
	}
}
