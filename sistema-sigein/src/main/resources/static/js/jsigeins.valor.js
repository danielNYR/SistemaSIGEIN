$(document).ready(function(){
    var pathname = window.location.pathname;
    var ruta_editar = pathname.substring(0,pathname.length-1);
    console.log("Ruta Editar: "+ruta_editar);

    var route_compare="/admin/valores/editar/";
    if(route_compare == ruta_editar){
        deploymodal();
    }else{
        ruta_editar = pathname.substring(0, pathname.length-2);
        if(route_compare==ruta_editar){
           deploymodal();
        }
    }
});

function deploymodal(){
    $("#modalRegistrarValor").modal("show");
}
