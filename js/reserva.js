function diaNaoOcupado(dia){

    //Obtem versao string da data
    var data = dia.getDate() + "/" + ("0" + (dia.getMonth()+1)).slice(-2) + "/" + dia.getFullYear();
    var css = "";
    var ocupado = true;

    //Verifica se o dia esta ocupado
    for (var i=0; i<diasOcupados.length; i++){
        if (data === diasOcupados[i]){
            ocupado = false;
        }
    }

    if (!ocupado){
        css = "dia_invalido";
    }else{
        css = "dia_valido";
    }

    return [ocupado,css];

}

$(document).ready(function () {

    $("#dataEntradaInput").datepicker({ 
        dateFormat: 'dd/mm/yy' ,
        minDate: +2,
        beforeShowDay: diaNaoOcupado
    });
    $("#dataSaidaInput").datepicker({ 
        dateFormat: 'dd/mm/yy' ,
        changeYear: true,
        minDate: +4,
        beforeShowDay: diaNaoOcupado
    });
});