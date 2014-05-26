package models;
 
import java.util.*;
import javax.persistence.*;
import play.db.jpa.*;
 
@Entity
public class Pedido extends Model {
 
    public Date postedAt;
    public String descripcion;
    public double total;
    
    @ManyToOne
    public Usuario usu;
    
    @OneToMany(mappedBy="pedido", cascade=CascadeType.ALL)
    public List<Entrada> entradas;
    
    public Pedido(Usuario usu, String descripcion) {
        
        this.usu = usu;
        this.descripcion = descripcion;        
        this.postedAt = new Date();
        entradas = new ArrayList<Entrada>();
    }
    
    public Pedido delEntrada(Producto p) {
        
        for (Entrada entrada : entradas) {

            if(entrada.producto.name == p.name)
            {
                this.total-=entrada.importe;
                
                entradas.remove(entrada);
                entrada.delete();
                this.save();
                break;
            }
                
        }
        
        return this;
    }
    
    
    public Pedido addEntrada(Producto p, int canti) {
    
        boolean esta = false;
        Entrada newEntrada = new Entrada(this, p, canti);
        
        for (Entrada entrada : entradas) {
            
            if(entrada.producto.name == p.name)
            {
                total-=entrada.importe;
                entrada.setCantidad(newEntrada.cantidad);
                total+=entrada.importe;
                entrada.save();
                esta = !esta;
                break;
            }
                
        }
        
        if(!esta)
        {
            this.entradas.add(newEntrada);
            total+=newEntrada.importe;
            
        }
        
        this.save();
        return this;
    }
 
}