/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deidan.modeloDAO;

import deidan.config.conexion;
import deidan.modelo.Carrito;
import deidan.modelo.Compras;
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
    int re;
    String msj;
    int r = 0;
    
    public int GenerarCompra(Compras compras){
        int idcompras;
        String sql="insert into compras()values(?,?,?,?,?)";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1, compras.getCliente().getId());
            ps.setString(2, compras.getFecha());
            ps.setDouble(3, compras.getMonto());
            ps.setString(4, compras.getEstado());
            ps.setInt(5, compras.getIdpago());
            ps.executeUpdate();
            
            sql="Select @@IDENTITY AS idCompras";
            rs=ps.executeQuery(sql);
            rs.next();
            idcompras=rs.getInt("idcompras");
            rs.close();
            
            for (Carrito detalle : compras.getDetallecompra()) {
                sql="insert into detalle_compra(idProducto,idCompras,Cantidad,PrecioCompra)values(?,?,?,?)";
                ps=con.prepareStatement(sql);
                ps.setInt(1, detalle.getIdProducto());
                ps.setInt(2, idcompras);
                ps.setInt(3, detalle.getCantidad());
                ps.setDouble(4, detalle.getPrecioCompra());
                ps.executeUpdate();
            }
            
        }catch(Exception e){
            
        }
        
        
        return r;
    }
    
    
}
