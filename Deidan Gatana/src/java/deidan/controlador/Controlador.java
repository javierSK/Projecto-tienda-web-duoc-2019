/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deidan.controlador;

import deidan.config.Fecha;
import deidan.modelo.Carrito;
import deidan.modelo.Cliente;
import deidan.modelo.Compras;
import deidan.modelo.Pago;
import deidan.modelo.Producto;
import deidan.modeloDAO.CompraDAO;
import deidan.modeloDAO.ProductoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author bryan
 */
public class Controlador extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    ProductoDAO pdao=new ProductoDAO();
    Producto p=new Producto();
    List<Producto> productos=new ArrayList<>();
    
    List<Carrito> listaCarrito=new ArrayList<>();
    int item;
    double totalPagar=0.0;
    int cantidad=1;
    int idp;
    Carrito car;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
       String accion=request.getParameter("accion");
       productos=pdao.listar();
       switch(accion){
           case "Comprar":
               totalPagar=0;
               idp=Integer.parseInt(request.getParameter("id"));
               p=pdao.listarID(idp);
               item=item+1;
               car=new Carrito();
               car.setItem(item);
               car.setIdProducto(p.getId());
               car.setNombres(p.getNombre());
               car.setDescripcion(p.getDescripcion());
               car.setPrecioCompra(p.getPrecio());
               car.setCantidad(cantidad);
               car.setSubTotal(cantidad*p.getPrecio());
               listaCarrito.add(car);
               for (int i=0; i<listaCarrito.size(); i++){
                   totalPagar=totalPagar+listaCarrito.get(i).getSubTotal();
               }
               request.setAttribute("totalPagar", totalPagar);
               request.setAttribute("carrito", listaCarrito);
               request.setAttribute("contador", listaCarrito.size());
               request.getRequestDispatcher("carrito.jsp").forward(request, response);
               break;
           case "AgregarCarrito":
               idp=Integer.parseInt(request.getParameter("id"));
               p=pdao.listarID(idp);
               item=item+1;
               car=new Carrito();
               car.setItem(item);
               car.setIdProducto(p.getId());
               car.setNombres(p.getNombre());
               car.setDescripcion(p.getDescripcion());
               car.setPrecioCompra(p.getPrecio());
               car.setCantidad(cantidad);
               car.setSubTotal(cantidad*p.getPrecio());
               listaCarrito.add(car);
               request.setAttribute("contador", listaCarrito.size());
               request.getRequestDispatcher("Controlador?accion=home").forward(request, response);
               break;
            case "Carrito":
               totalPagar=0.0;
               request.setAttribute("carrito", listaCarrito);
               for (int i=0; i<listaCarrito.size(); i++){
                   totalPagar=totalPagar+listaCarrito.get(i).getSubTotal();
               }
               request.setAttribute("totalPagar", totalPagar);
               request.getRequestDispatcher("carrito.jsp").forward(request, response);
               
               break;
             case "Delete":
               
               int idproducto=Integer.parseInt(request.getParameter("idp"));
               for (int i=0; i<listaCarrito.size();i++){
                   if(listaCarrito.get(i).getIdProducto()==idproducto){
                        listaCarrito.remove(i);
                   }
               }
               break;
             case "ActualizarCantidad":
               int idpro=Integer.parseInt(request.getParameter("idp"));
               int cant=Integer.parseInt(request.getParameter("Cantidad"));
               for (int i = 0; i < listaCarrito.size(); i++) {
                   if (listaCarrito.get(i).getIdProducto()==idpro) {
                       listaCarrito.get(i).setCantidad(cant);
                       double st=listaCarrito.get(i).getPrecioCompra()*cant;
                       listaCarrito.get(i).setSubTotal(st);
                   }
               }
               
               break;
            case "GenerarCompra":
                    Cliente cliente=new Cliente();
                    cliente.setId(1);
                    CompraDAO dao= new CompraDAO();
                    Compras compras=new Compras(cliente, 1, Fecha.FechaBD(), totalPagar, "Cancelado", listaCarrito);
                    int res=dao.GenerarCompra(compras);
                    if (res!=0&&totalPagar>0) {
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }else{
                    
                    request.getRequestDispatcher("mensaje.jsp").forward(request, response);
                    }
                break;
           default:
               request.setAttribute("productos", productos);
               request.getRequestDispatcher("index.jsp").forward(request, response);
       }
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
