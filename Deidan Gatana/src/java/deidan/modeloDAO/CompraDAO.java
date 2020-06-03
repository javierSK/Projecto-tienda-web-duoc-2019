/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deidan.modeloDAO;

import deidan.config.conexion;
import deidan.modelo.Carrito;
import deidan.modelo.Compra;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author bryan
 */
public class CompraDAO {
    
    Connection con;
    conexion cn= new conexion();
    PreparedStatement ps;
    ResultSet rs;
    int r=0;
    
    public int GenerarCompra(Compra compra){
        
        int idcompras;
        String sql="insert into compras(idCliente,FechaCompras,Monto,Estado,idPago) values(?,?,?,?,?)";
        try{
        con=cn.getConnection();
        ps=con.prepareStatement(sql);
        ps.setInt(1, compra.getCliente().getId());
        ps.setString(2, compra.getFecha());
        ps.setDouble(3, compra.getMonto());
        ps.setString(4, compra.getEstado());
        ps.setInt(5, compra.getIdpago());
        ps.executeUpdate();
        
        sql="Select @@IDENTITY AS idCompras";
        rs=ps.executeQuery(sql);
        rs.next();
        idcompras=rs.getInt("idcompras");
        rs.close();
         for (Carrito detalle : compra.getDetallecompras()){
         sql="insert into detalle_compras(idProducto,idCompras,Cantidad,PrecioCompra)values(?,?,?,?)";
         ps=con.prepareStatement(sql);
         ps.setInt(1, detalle.getIdProducto());
         ps.setInt(2, idcompras);
         ps.setInt(3, detalle.getCantidad());
         ps.setDouble(4, detalle.getPrecioCompra());
         r=ps.executeUpdate();
         }
        
        
        
        }catch (Exception e){
        
        }
        
        return r;
    }
    
    
}
