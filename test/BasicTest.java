import static controllers.Application.g;
import org.junit.*;
import java.util.*;
import play.test.*;
import models.*;

public class BasicTest extends UnitTest {
    
    
    
    
    @Test
    public void testPost() {
        Long idUsu = new Long(1);
        
        Usuario u = Usuario.findById(idUsu);
        assertNotNull(u);
        
        assertNotNull(u.pedidos.get(0));
        
        
        
        Pedido pedido = Pedido.find("byUsu", u).first();
        
        assertNotNull(pedido);
    }
    
//    @Test
//    public void testPost() {
//        
//        String json = "{\"email\":\"os@os.com\", \"password\":\"1234\"}";
//        System.out.println(json);
//        Usuario usuP = g.fromJson(json, Usuario.class);
//        assertNotNull(usuP);
//        
//        System.out.println("email: " + usuP.email + " password " + usuP.password );
//        
//        Usuario usu = Usuario.find("byEmailAndPassword", usuP.email, usuP.password).first();
//        assertNotNull(usu);
//        
//        System.out.println(usu.email);
//                  
//    }

//    @Test
//    public void aVeryImportantThingToTest() {
//        
//        Long id = new Long(1);
//
//        SpecialOffer so = SpecialOffer.findById(id);
//
//        assertNotNull(so);
//        System.out.println(so.titulo);
//        
//        assertNotNull(so.offer);
//        System.out.println(so.offer.titulo);
//        
//        List<Producto> lista_de_productos = so.offer.productos;
//        
//        System.out.println("El primer productos es: " + 
//                lista_de_productos.get(0).name
//        );           
//    }

}
