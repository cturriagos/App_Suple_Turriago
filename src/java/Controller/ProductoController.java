/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import DAO.ProductoDao;
import Models.Producto;

/**
 * 
 * @author churri
 */
public class ProductoController {
    
    ProductoDao pdao;
    String message;
    
    public ProductoController (){
        this.message = "";
    }
    
    public String insertarProducto(String nombre, String stock, 
                                   String precio_unit, String id_usuario){
        pdao = new ProductoDao();
        Producto pd = new Producto();
        this.message = "Error en los parametros ingresados";
        pd.setNombre(nombre);
        pd.setCantidad(stock);
        pd.setPrecio(precio_unit);
        pd.setId_usuario(id_usuario);
        if(pdao.insertarProducto(pd)){
            this.message = "Producto agregado correctamente";
        }else{
            this.message = "Error al agregar producto.";
        }
        return this.message;
    }
    
    public String listarProductos(String id_usuario){
        pdao = new ProductoDao();
        Producto pd = new Producto();
        pd.setId_usuario(id_usuario);
        return pdao.listarProductos(pd);
    }
    
    public String listarProductosTienda(String id_usuario){
        pdao = new ProductoDao();
        Producto pd = new Producto();
        pd.setId_usuario(id_usuario);
        return pdao.listarProductosTienda(pd);
    }
    
}
