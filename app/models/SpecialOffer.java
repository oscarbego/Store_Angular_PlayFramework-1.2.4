package models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import static javax.persistence.FetchType.LAZY;
import javax.persistence.OneToOne;
import play.db.jpa.Model;

@Entity
public class SpecialOffer extends Model{

    public String titulo;
    public String descripcion;

    @OneToOne(fetch=LAZY)
    public Offer offer;
    
    public SpecialOffer(String titulo) {
        
        this.titulo = titulo;
    }
    
}
