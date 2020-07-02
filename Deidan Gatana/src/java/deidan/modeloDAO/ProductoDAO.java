/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deidan.modeloDAO;
import deidan.config.conexion;
import deidan.modelo.Producto;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author bryan
 */
public class ProductoDAO {
    Connection con;
    conexion cn= new conexion();
    PreparedStatement ps;
    ResultSet rs;
    int re;
    String msj;
    
    
    public Producto listarID(int id){
        String sql="select * from producto where idProducto="+id;
        Producto p=new Producto();
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()){
                p.setId(rs.getInt(1));
                p.setNombre(rs.getString(2));
                p.setFoto(rs.getBinaryStream(3));
                p.setDescripcion(rs.getString(4));
                p.setPrecio(rs.getDouble(5));
                p.setStock(rs.getInt(6));
            }
        }catch (Exception e){
            
        }
        return p;
    }
    
    
    public List listar(){
        List<Producto>productos=new ArrayList();
        String sql="select * from producto";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Producto p=new Producto();
                p.setId(rs.getInt(1));
                p.setNombre(rs.getString(2));
                p.setFoto(null); //p.setFoto(rs.getBinaryStream(3));
                p.setDescripcion(rs.getString(4));
                p.setPrecio(rs.getDouble(5));
                p.setStock(rs.getInt(6));
                productos.add(p);
            }
                    
        }catch (Exception e){
        }
        return productos; 
        }
    
    
    public void listarImg(int id, HttpServletResponse response){
        String sql="select * from producto where idProducto="+id;
        InputStream inputStream= null;
        OutputStream outputStream=null;
        BufferedInputStream bufferedInputStream= null;
        BufferedOutputStream bufferedOutputStream= null;
        try{
            outputStream=response.getOutputStream();   
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            if (rs.next()) {
                inputStream=rs.getBinaryStream("Foto");
            }
            bufferedInputStream=new BufferedInputStream(inputStream);
            bufferedOutputStream=new BufferedOutputStream(outputStream);
            int i = 0;

            while((i=bufferedInputStream.read())!=-1){
                bufferedOutputStream.write(i);
            }
        }catch (Exception e){

        }
    }
    
    
    public String agregarProducto(String nombre, String descripcion, Double precio, int stock){
        String sql = "insert into producto(Nombre, Foto, Descripcion, Precio, Stock) values(?, ?, ?, ?, ?)";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            
            Blob b = con.createBlob();
            
            ps.setString(1, nombre);
            ps.setBlob(2, b);
            ps.setString(3, descripcion);
            ps.setDouble(4, precio);
            ps.setInt(5, stock);
            re = ps.executeUpdate();
            if(re==1){
                msj = "Producto agregado con éxito";
            }
            else{
                msj = "No se ha podido agregar el producto";
            }
        }
        catch(Exception e){            
        }
        return msj;
    }
    
    
    public Producto seleccionProducto(int id){
        String sql = "select * from producto where idProducto="+id;
        Producto pro = new Producto();
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                pro.setId(rs.getInt("idProducto"));
                pro.setNombre(rs.getString("Nombre"));
                pro.setFoto(null);
                pro.setDescripcion(rs.getString("Descripcion"));
                pro.setPrecio(rs.getDouble("Precio"));
                pro.setStock(rs.getInt("Stock"));
            }
        }
        catch(Exception e){            
        }
        return pro;
    }
    
    public String editarProducto(int id, String nombre, String descripcion, double precio, int stock){
        String sql = "update producto set Nombre=?, Descripcion=?, Precio=?, Stock=? where idProducto="+id;
        try{
           con = cn.getConnection();
           ps = con.prepareStatement(sql);

           ps.setString(1, nombre);
           ps.setString(2, descripcion);
           ps.setDouble(3, precio);
           ps.setInt(4, stock);
           re = ps.executeUpdate();
           if(re==1){
               msj = "Producto actualizado";
           }
           else{
               msj = "No se ha podido actualizar el producto";
           }
        }
        catch(Exception e){            
        } 
        return msj;
    }
    
    public Producto eliminarProducto(int id){
        String sql = "Delete from producto where idProducto="+id;
        Producto pro = new Producto();
        
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            re = ps.executeUpdate();
        }
        catch(Exception e){            
        }
        return pro;
    }
}
    

