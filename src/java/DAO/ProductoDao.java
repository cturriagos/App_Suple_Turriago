/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import DataStatic.Conection;
import Models.Producto;

/**
 * 
 * @author churri
 */
public class ProductoDao {
    
    Conection conex;
    String sql = "";
    
    public ProductoDao(){
        conex = new Conection();
    }
    
    /**
     * FUNCIÓN PARA INSERTAR UN PRODUCTO
     * 
     * SE RECIBE UN PARÁMETRO DE TIPO PRODUCTO, PARA LUEGO FORMAR
     * UNA CADENA SQL CON SUS RESPECTIVAS CONCATENACIONES Y FINALMENTE,
     * METERLA COMO ARGUMENTO DENTRO DE UNA FUNCIÓN DE LA CLASE CONEXIÓN
     * QUE DEVUELVE UN VALOR BOOLEANO (VERDADERO SI LA EJECUCIÓN FUE EXITOSA, 
     * FALSO SI LA EJECUCIÓN FUE FALLIDA), TAMBIÉN HACE UNA IMPRESIÓN EN CONSOLA
     * PARA VERIFICAR LA SENTENCIA SQL.
     * 
     * @param pc
     * @return boolean
     */
    public boolean insertarProducto(Producto pc){
        sql = String.format("insert into producto(nombre, stock, "
                + "precio_unit, usuario_id_usuario) values('%s', %s, %s, %s)"
                ,pc.getNombre(), pc.getCantidad(), pc.getPrecio(), 
                pc.getId_usuario());
        return conex.modifyBD(sql);
    }
    
    /**
     * FUNCIÓN PARA LISTAR PRODUCTOS
     * 
     * SE RECIBE UN PARÁMETRO DE TIPO PRODUCTO, PARA LUEGO FORMAR
     * UNA CADENA SQL CON SUS RESPECTIVAS CONCATENACIONES Y FINALMENTE,
     * METERLA COMO ARGUMENTO DENTRO DE UNA FUNCIÓN DE LA CLASE CONEXIÓN
     * QUE DEVUELVE UN VALOR STRING, PERO EN REALIDAD ES UN JSON FORMATEADO.
     * 
     * @param pc
     * @return boolean
     */
    public String listarProductos(Producto pc){
        sql = "select * from producto where usuario_id_usuario "
                + "= "+pc.getId_usuario()+" and stock > 0 order by "
                + "id_producto asc";
        return conex.getRecordsInJson(sql);
    }
    
    /**
     * FUNCIÓN PARA LISTAR PRODUCTOS DE DETERMINADA TIENDA
     * 
     * SE RECIBE UN PARÁMETRO DE TIPO PRODUCTO, PARA LUEGO FORMAR
     * UNA CADENA SQL CON SUS RESPECTIVAS CONCATENACIONES Y FINALMENTE,
     * METERLA COMO ARGUMENTO DENTRO DE UNA FUNCIÓN DE LA CLASE CONEXIÓN
     * QUE DEVUELVE UN VALOR STRING, PERO EN REALIDAD ES UN JSON FORMATEADO.
     * 
     * @param pc
     * @return boolean
     */
    public String listarProductosTienda(Producto pc){
        sql = "select * from producto where usuario_id_usuario = "
                +pc.getId_usuario()+" and stock > 0 order by id_producto asc";
        return conex.getRecordsInJson(sql);
    }

}
