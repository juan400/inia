
//*****************************************************************************
//Valida que solo se puedan ingresar caracteres numÈricos y puntos en un campo.
//*****************************************************************************

function ValidarCampoNumericoDouble(obj, event)
{ 
 var validarTexto = obj.value;
 switch (event.type)
 {
     case "keypress":
         validarTexto += String.fromCharCode(event.keyCode);
     break;
     
     case "paste":
         validarTexto += clipboardData.getData("Text");
     break;
     
     case "drop":
         validarTexto += event.dataTransfer.getData("Text")
     break;
 }
 var array = validarTexto.split('.');
 if (! validarTexto.match( '^[0-9.]+$' ) || array.lenght > 2)
 {
     event.returnValue = false;
     event.cancel = true; 
 }
} 

//***************************************************************************
//Valida que solo se puedan ingresar caracteres numÈricos en un campo.
//***************************************************************************

function ValidarCampoNumerico(obj, event)
{ 
 var validarTexto = obj.value;
 switch (event.type)
 {
     case "keypress":
         validarTexto += String.fromCharCode(event.keyCode);
     break;
     
     case "paste":
         validarTexto += clipboardData.getData("Text");
     break;
     
     case "drop":
         validarTexto += event.dataTransfer.getData("Text")
     break;
 }
 
 if (! validarTexto.match( '^[0-9]+$' ))
 {
     event.returnValue = false;
     event.cancel = true; 
 }
} 


//***************************************************************************
//Valida que solo se puedan ingresar caracteres alfanumÈricos en un campo
//incluyendo Ò ,tildes, diÈresis y el espacio en blanco.
//***************************************************************************

function ValidarCampoAlfaNumericoConEspacio(obj, event)
{ 
 var validarTexto = obj.value;
 switch (event.type)
 {
     case "keypress":
         validarTexto += String.fromCharCode(event.keyCode);
     break;
     
     case "paste":
         validarTexto += clipboardData.getData("Text");
     break;
     
     case "drop":
         validarTexto += event.dataTransfer.getData("Text")
     break;
 }
 
 if (! validarTexto.match( '^[0-9A-Za-z—Ò¡…Õ”⁄·ÈÌÛ˙‹¸ ]+$' ))
 {
     event.returnValue = false;
     event.cancel = true; 
 }
}

//***************************************************************************
//Valida que solo se puedan ingresar caracteres alfanumÈricos en un campo
//incluyendo Ò y sin espacio en blanco.
//***************************************************************************
function ValidarCampoParaCodigo(obj, event)
{ 
 var validarTexto = obj.value;
 switch (event.type)
 {
     case "keypress":
         validarTexto += String.fromCharCode(event.keyCode);
     break;
     
     case "paste":
         validarTexto += clipboardData.getData("Text");
     break;
     
     case "drop":
         validarTexto += event.dataTransfer.getData("Text")
     break;
 }
 
 if (! validarTexto.match( '^[0-9A-Za-z—Ò]+$' ))
 {
     event.returnValue = false;
     event.cancel = true; 
 }
}

//***************************************************************************
//Valida que solo se tantos caractes como largoMaximo sea especificado.
//***************************************************************************
function ValidarLargoMultiline(obj, event, largoMaximo)
{
 var validarTexto = obj.value;
 switch (event.type)
 {
     case "keypress":
         validarTexto += String.fromCharCode(event.keyCode);
     break;
     
     case "paste":
         validarTexto += clipboardData.getData("Text");
     break;
     
     case "drop":
         validarTexto += event.dataTransfer.getData("Text")
     break;
 }
 
 if (validarTexto.length > largoMaximo)
 {
     event.returnValue = false;
     event.cancel = true; 
 }
}

//***************************************************************************
//Valida que se puedan ingresar caracteres alfanumÈricos y caracteres 
//especiales incluyendo el espacio en blanco
//***************************************************************************
function ValidarCampoConCaracteresEspeciales(obj, event)
{
 var validarTexto = obj.value;
 switch (event.type)
 {
     case "keypress":
         validarTexto += String.fromCharCode(event.keyCode);
     break;
     
     case "paste":
         validarTexto += clipboardData.getData("Text");
     break;
     
     case "drop":
         validarTexto += event.dataTransfer.getData("Text")
     break;
 }
 
if (! validarTexto.match( '^[0-9A-Za-z—Ò¡…Õ”⁄·ÈÌÛ˙‹¸&#@,-.{}ø?"%&/()=*;:∞!° ]+$')
    || (validarTexto.match('&#')) || (validarTexto.match('&')) || (validarTexto.match('#')))
  {
     event.returnValue = false;
     event.cancel = true; 
 }
}   

//***************************************************************************
//Valida que se puedan ingresar solamente letras, incluyendo tildes y 
//el espacio en blanco
//***************************************************************************
function ValidarCampoLetras(obj, event)
{ 
 var validarTexto = obj.value;
 switch (event.type)
 {
     case "keypress":
         validarTexto += String.fromCharCode(event.keyCode);
     break;
     
     case "paste":
         validarTexto += clipboardData.getData("Text");
     break;
     
     case "drop":
         validarTexto += event.dataTransfer.getData("Text")
     break;
 }
 
 if (! validarTexto.match( '^[A-Za-z—Ò¡…Õ”⁄·ÈÌÛ˙‹¸ ]+$' ))
 {
     event.returnValue = false;
     event.cancel = true; 
 }
}


//***************************************************************************
//Valida que se puedan ingresar solamente letras, incluyendo tildes y 
//el espacio en blanco
//***************************************************************************
function validarEmail(obj, event)
{
	debugger;
    var textoAgregado = "";
    if (event.keyCode != 0)
    {
        textoAgregado = String.fromCharCode(event.keyCode);
    }
    else
    {
        textoAgregado = clipboardData.getData("Text");
    }
    var textoCompleto = obj.value + textoAgregado;
    if (textoAgregado == ',')
    {
        event.returnValue = false;
        event.cancel = true;
    }
    if(! textoCompleto.match( '^[a-z,0-9,@,.,_,-]+$' ))
    {
        event.returnValue = false;
        event.cancel = true;
    }
}

//MÈtodo que valida que un string corresponda a un mail correcto
function esValidoEmail(oSrc, args) 
{
    correcto = true;        
    var textBox = document.getElementById('txtEmail');
    var expresionRegular = /^.+@[^\.].*\.[a-z]{2,}$/;
    if (!expresionRegular.test(textBox.value)) 
    {
        correcto = false;
    }
    args.IsValid = correcto;
}

function sonValidosCaracteres(pEmail) 
{
    var verificacion = true;
    var caracteresValidos = "abcdefghijklmnopqrstuvwxyz0123456789@.-_";
    for (var i=0; i < pEmail.length; i++) 
    {
        var letra = pEmail.charAt(i).toLowerCase();
        if (caracteresValidos.indexOf(letra) != -1)
        continue;
        verificacion = false;
        break;
    }
    return verificacion;
}
