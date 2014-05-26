package models;
 
import java.util.*;
import javax.persistence.*;
 
import play.db.jpa.*;
 
@Entity
public class Usuario extends Model {
 
    public String email;
    public String password;
    public String fullname;
    
    @OneToMany(mappedBy="usu", cascade=CascadeType.ALL)
    public List<Pedido> pedidos;
    
    public Usuario(String email, String password, String fullname) {
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        pedidos = new ArrayList<Pedido>();
    }
    
    public void addPedido(String descri) {
        
        Pedido newPedido = new Pedido(this, descri).save();
        
        this.pedidos.add(newPedido);
        this.save();
    }
 
}