function soloLetras(campo){
    var valor=campo.value;
    var principio=valor.substr(0, valor.length-1);
    var ultimaLetra=valor.substr(valor.length-1, valor.length);
    if(ultimaLetra==" "){
        
        campo.value=campo.value.toUpperCase();
        return;
    }
    var bool=esNumero(ultimaLetra);
    if(bool){
        campo.value=principio;
    }else{
        campo.value=campo.value.toUpperCase();
        
    }
        
}


function soloNumeros(campo){
    var valor=campo.value;
    var principio=valor.substr(0, valor.length-1);
    var ultimaLetra=valor.substr(valor.length-1, valor.length);
    var bool=esNumero(ultimaLetra);
    if(bool){
        campo.value=campo.value;
    }else{
        campo.value=principio;
        
    }
}

function soloNumerosYPunto(campo){
    var valor=campo.value;
    var principio=valor.substr(0, valor.length-1);
    var ultimaLetra=valor.substr(valor.length-1, valor.length);
    var bool=esNumero(ultimaLetra);
    if(bool){
      
        campo.value=campo.value;
    }else{
        if(ultimaLetra=="."){
            campo.value=campo.value;    
        }else{
            
            campo.value=principio;
        
        }
    }
}

function pasarMayusculas(campo){
    campo.value=campo.value.toUpperCase();
}

function esNumero(valor){
    for(var t=0;t<10;t++){
        if(valor==t){
            return true;
        }
    }
    return false;
}


