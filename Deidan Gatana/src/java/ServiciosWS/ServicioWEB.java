/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServiciosWS;

import deidan.modelo.Producto;
import deidan.modeloDAO.ProductoDAO;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
/**
 *
 * @author Bastian
 */
@WebService(serviceName = "Servicios")
public class ServicioWEB {

    ProductoDAO dao = new ProductoDAO();
    
    @WebMethod(operationName = "ListarProductos")
    public List<Producto> ListarProductos() {
        List productos = dao.listar();
        return productos;
    }

    @WebMethod(operationName = "AgregarProductos")
    public String AgregarProductos(@WebParam(name = "Nombre") String Nombre, @WebParam(name = "Descripcion") String Descripcion, @WebParam(name = "Precio") double Precio, @WebParam(name = "Stock") int Stock) {
        String mensaje = dao.agregarProducto(Nombre, Descripcion, Precio, Stock);
        return mensaje;
    }
    
    @WebMethod(operationName = "ListarSeleccion")
    public Producto ListarSeleccion(@WebParam(name = "id") int id) {
        Producto pro = dao.seleccionProducto(id);
        return pro;
    }
    
    @WebMethod(operationName = "EditarProductos")
    public String EditarProductos(@WebParam(name = "id") int id, @WebParam(name = "nombre") String nombre, @WebParam(name = "descripcion") String descripcion, @WebParam(name = "precio") double precio, @WebParam(name = "stock") int stock) {
        String edit = dao.editarProducto(id, nombre, descripcion, precio, stock);
        return edit;
    }

    @WebMethod(operationName = "EliminarProductos")
    public Producto EliminarProductos(@WebParam(name = "id") int id) {
        Producto pro = dao.eliminarProducto(id);
        return pro;
    }
}
