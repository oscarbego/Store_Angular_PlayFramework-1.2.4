package models;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import play.db.jpa.Model;


@Entity
public class Offer extends Model{
    
    public String titulo;
    public String descripcion;
    public int porcentaje;
    
    @OneToMany(mappedBy="offer")
    public List<Producto> productos;

    public Offer(String titulo, String descripcion, int porcentaje) {
        
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.porcentaje = porcentaje; 
        
        this.productos = new ArrayList<Producto>();
    }
    
    
    public List<Producto> getProductosList() {
    
        List<Producto> lp = Producto.find("byOffer", this).fetch();
                
        return productos = lp;
    }
    
    public void addProducto(Producto p) {
    
        this.productos.add(p);
        this.save();    
    } 
    
    
}
