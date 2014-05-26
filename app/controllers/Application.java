package controllers;

import com.google.gson.Gson;
import play.*;
import play.mvc.*;
import flexjson.JSONSerializer;
import java.util.*;

import models.*;

public class Application extends Controller {

    public static Gson g = new Gson();
    
    
    public static void index() {
        render();
    }
    
    
    public static void delProdToPedido(Long idUsu, Long idPe, Long idProd) {
        
        Pedido pedido = Pedido.findById(idPe);
        Producto producto = Producto.findById(idProd);
        
        pedido.delEntrada(producto);

        getPedidoUsu(idUsu);
        
    }
    
    public static void addProdToPedido(Long idUsu, Long idPe, Long idProd, int canti) {
        
        Pedido pedido = Pedido.findById(idPe);
        Producto producto = Producto.findById(idProd);
        
        pedido.addEntrada(producto, canti);
        pedido.save();
        
        getPedidoUsu(idUsu);
        //render();
    }
    
    public static void getPedidoUsu(Long id) {
        
        Usuario u = Usuario.findById(id);
        
        Pedido pedido = null;
        
        if(u.pedidos.size() > 0)
            pedido = u.pedidos.get(0);
        else
            u.addPedido("Pedido en Curso");
        
        
        renderJSON(new JSONSerializer()
                .include("id" ,
                            "total", 
                            "descripcion", 
                            "entradas.producto.id", 
                            "entradas.producto.name", 
                            "entradas.producto.price",
                            "entradas.cantidad", 
                            "entradas.importe"
                        )
                .exclude("*").serialize(pedido));
        
    }
    
    public static void getUsu() {
        
        Usuario usuP = g.fromJson(params.get("body"), Usuario.class);
        
        Usuario usu = 
                Usuario.find("byEmailAndPassword", usuP.email, usuP.password)
                        .first();
        
        
        renderJSON(new JSONSerializer()
                .include("id" ,"fullname", "email", "password")
                .exclude("*").serialize(usu));
        
    }
    
    public static void getOffers() {
    
        List<Offer> ofertas = Offer.findAll();        
        List<Producto> productosEnOferta = new ArrayList<Producto>();
        
        for (Offer offer : ofertas)            
            for (Producto producto : offer.productos)
                if(offer.porcentaje > 0)
                    productosEnOferta.add(producto);
               
               
        renderJSON(new JSONSerializer()
                .include("id" ,"name", "price", "descr", "img")
                .exclude("*").serialize(productosEnOferta));
        
    }
    
    public static void getSpecialOffer() {
        
        Long id = new Long(1);
        
        SpecialOffer so = SpecialOffer.findById(id);
        
        
        renderJSON(
                    new JSONSerializer()
                    .include("id" ,"name", "price", "descr", "img")
                    .exclude("*").serialize(so.offer.productos.get(0))
        );
        
    }
    
    public static void getProducts() {
        
        List<Producto> lista = Producto.findAll();
        
        renderJSON(
                    new JSONSerializer()
                            .include("id", "name", "price", "descr", "img")
                            .exclude("*")
                            .serialize(lista)
                  );   
    }
    
}