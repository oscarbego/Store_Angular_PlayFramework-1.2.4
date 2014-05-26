package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import play.db.jpa.Model;


@Entity
public class Entrada extends Model {
    
    @ManyToOne
    public Pedido pedido;
    
    @ManyToOne
    public Producto producto;
    
    public int cantidad;
    public double importe;

    
    public Entrada(Pedido pedido, Producto producto, int cantidad) {
        this.pedido = pedido;
        this.producto = producto;
        this.cantidad = cantidad;
        this.importe = cantidad * producto.price;
    }

    
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        this.importe = producto.price * this.cantidad;
    }
    
}
