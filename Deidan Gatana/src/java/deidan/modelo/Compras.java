/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deidan.modelo;

import java.util.List;


/**
 *
 * @author bryan
 */
public class Compras{
    private int Id;
    private Cliente cliente;
    private int Idpago;
    private String Fecha;
    private Double Monto;
    private String Estado;
    
    private List<Carrito>detallecompra;

    public Compras() {
    }

    public Compras(Cliente cliente, int Idpago, String Fecha, Double Monto, String Estado, List<Carrito> detallecompra) {
        this.cliente = cliente;
        this.Idpago = Idpago;
        this.Fecha = Fecha;
        this.Monto = Monto;
        this.Estado = Estado;
        this.detallecompra = detallecompra;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getIdpago() {
        return Idpago;
    }

    public void setIdpago(int Idpago) {
        this.Idpago = Idpago;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public Double getMonto() {
        return Monto;
    }

    public void setMonto(Double Monto) {
        this.Monto = Monto;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public List<Carrito> getDetallecompra() {
        return detallecompra;
    }

    public void setDetallecompra(List<Carrito> detallecompra) {
        this.detallecompra = detallecompra;
    }
 
    
   
    

}
