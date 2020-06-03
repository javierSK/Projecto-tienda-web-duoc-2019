$(document).ready(function(){
    $("tr #btnDelete").click(function(){
        var idp=$(this).parent().find("#idp").val();
        swal({
            title: "Estas seguro?",
            text: "Una vez eliminado no podras recuperar este elemento!",
            icon: "warning",
            buttons: true,
            dangerMode: true,
        })
                .then((willDelete) => {
                    if (willDelete) {
                        eliminar(idp);
                swal("Poof! tu item ha sido eliminado!", {
                    icon: "success",
                }).then((willDelete)=>{
                    if(willDelete){
                        parent.location.href="Controlador?accion=carrito";
                    }
                });
            } else {
                swal("Registro no eliminado");
            }
        });
        
    });
    function eliminar(idp){
        var url="Controlador?accion=Delete";
        $.ajax({
            Type: 'Post',
            url: url,
            data: "idp="+idp,
            success: function(data, textStatus, jqXHR){
                
            }
        });
    }
    
    $("tr #Cantidad").click(function(){
        
        var idp=$(this).parent().find("#idpro").val();
        var cantidad=$(this).parent().find("#Cantidad").val();
        var url="Controlador?accion=ActualizarCantidad";
        
        $.ajax({
            type: 'POST',
            url: url,
            data: "idp="+idp+"&Cantidad="+cantidad,
            succes: function (data, textStatus, jqXHR){
                location.href="Controlador?accion=Carrito";
            }
            
        });
        
        
    });
    
    
});


