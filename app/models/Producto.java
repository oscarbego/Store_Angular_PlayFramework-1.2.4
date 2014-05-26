package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import play.db.jpa.Model;

@Entity
public class Producto extends Model {
    
    public String name;
    public double price;
    public String descr;
    public String img;
    
    @ManyToOne
    public Offer offer;

    public Producto(String name, double price) {
        
        this.name = name;
        this.price = price;        
    }
    
    
    
}
