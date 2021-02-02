$(document).ready(function () {
    //Final del document function. 
    let param = "";
    console.log("funcionando");
    $('#inputNombreInstitucion').keyup(function () {
        let cadena = $('#inputNombreInstitucion').val();
        console.log(cadena);
        console.log(cadena.length);
        if (cadena.length > 5 && cadena.length < 50) {
            $('#inputNombreInstitucion').removeClass('is-invalid')
            $('#inputNombreInstitucion').addClass('is-valid');
        } else {
            $('#inputNombreInstitucion').removeClass('is-valid');
            $('#inputNombreInstitucion').addClass('is-invalid');
        }
    });
    $('#inputAcro').keyup(function () {
        let acro = $('#inputAcro').val();
        let parametro = /^[A-Z]{2,10}$/g;
        if (acro.match(parametro) == acro) {
            $('#inputAcro').removeClass('is-invalid');
            $('#inputAcro').addClass('is-valid');
        } else {
            $('#inputAcro').removeClass('is-valid');
            $('#inputAcro').addClass('is-invalid');
        }
    });
    //Inicio de validar coordenadas
    $('#inputLatitud').keyup(function(){
        let latitud = $('#inputLatitud').val();
        param = /-?[1-9][0-9.-]{5,20}$/g;
        if(latitud.match(param)==latitud){
            $('#inputLatitud').removeClass('is-invalid');
            $('#inputLatitud').addClass('is-valid');
        } else {
            $('#inputLatitud').removeClass('is-valid');
            $('#inputLatitud').addClass('is-invalid');
        }
    })
    $('#inputLongitud').keyup(function(){
        let longitud = $('#inputLongitud').val();
        param = /-?[1-9][0-9.-]{5,20}$/g;
        if(longitud.match(param)==longitud){
            $('#inputLongitud').removeClass('is-invalid');
            $('#inputLongitud').addClass('is-valid');
        } else {
            $('#inputLongitud').removeClass('is-valid');
            $('#inputLongitud').addClass('is-invalid');
        }
    })
    $('#inputFacebookIns').keyup(function(){
        let facebook_institucion = $('#inputFacebookIns').val();
        param = /^https?:\/\/[\w\-]+(\.[\w\-]+)+[/#?]?.*$/g; 
        if(facebook_institucion.match(param) == facebook_institucion){
            $('#inputFacebookIns').removeClass('is-invalid');
            $('#inputFacebookIns').addClass('is-valid');
        }else{
            $('#inputFacebookIns').removeClass('is-valid');
            $('#inputFacebookIns').addClass('is-invalid');
        }
    })
    $('#inputYoutubeIns').keyup(function(){
        let facebook_institucion = $('#inputYoutubeIns').val();
        param = /^https?:\/\/[\w\-]+(\.[\w\-]+)+[/#?]?.*$/g; 
        if(facebook_institucion.match(param) == facebook_institucion){
            $('#inputYoutubeIns').removeClass('is-invalid');
            $('#inputYoutubeIns').addClass('is-valid');
        }else{
            $('#inputYoutubeIns').removeClass('is-valid');
            $('#inputYoutubeIns').addClass('is-invalid');
        }
    })

});



///Expresion regular para validar coordenadas: [-][1-9][0-9.-]{1,12}$