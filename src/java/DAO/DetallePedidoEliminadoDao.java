/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import DataStatic.Conection;
import Models.DetallePedidoEliminado;
import javax.swing.table.DefaultTableModel;

/**
 * 
 * @author churri
 */
public class DetallePedidoEliminadoDao {
    
    Conection conex;
    String sql = "";
    
    public DetallePedidoEliminadoDao(){
        conex = new Conection();
    }
    
    /** 
     * FUNCIÓN PARA INSERTAR EL DETALLE DE UN PEDIDO ELIMINADO
     * 
     * SE RECIBE UN PARÁMETRO DE TIPO DETALLEPEDIDOELIMINADO, PARA LUEGO FORMAR
     * UNA CADENA SQL CON SUS RESPECTIVAS CONCATENACIONES Y FINALMENTE,
     * METERLA COMO ARGUMENTO DENTRO DE UNA FUNCIÓN DE LA CLASE CONEXIÓN
     * QUE DEVUELVE UN VALOR BOOLEANO (VERDADERO SI LA EJECUCIÓN FUE EXITOSA, 
     * FALSO SI LA EJECUCIÓN FUE FALLIDA)
     * 
     * @param detpe
     * @return boolean
     */
    public boolean insertarDetallePedidoEliminado(DetallePedidoEliminado detpe){
        sql = String.format("insert into detalle_pedido_eliminado "
                + "(cantidad, precio_unit, "
                + "encabezado_pedido_eliminado_id_pedeliminado, "
                + "producto_id_producto)"
                + "values(%s, %s, (select id_pedeliminado from "
                + "encabezado_pedido_eliminado order by "
                + "id_pedeliminado desc limit 1), %s)",detpe.getCantidad(), 
                detpe.getPrecio(), 
                detpe.getId_producto());
        return conex.modifyBD(sql);
    }
    
    /** 
     * 
     * FUNCIÓN PARA OBTENER EL DETALLE DE UN PEDIDO (SUS PRODUCTOS)
     * 
     * SE RECIBE UN PARÁMETRO DE TIPO STRING, PARA LUEGO FORMAR
     * UNA CADENA SQL CON SUS RESPECTIVAS CONCATENACIONES Y FINALMENTE,
     * METERLA COMO ARGUMENTO DENTRO DE UNA FUNCIÓN DE LA CLASE CONEXIÓN
     * QUE DEVUELVE UN VALOR DE TIPO DEFAULTTABLEMODEL, SE HACE TAMBIÉN
     * UNA IMPRESIÓN EN CONSOLA.
     * 
     * @param id_pedido
     * @return DefaultTableModel
     */
    public DefaultTableModel getProductos (String id_pedido){
        sql = "select * from detalle_pedido where "
                + "encabezado_pedido_id_encapedido = "+id_pedido+"";
        System.out.println(sql);
        return conex.returnRecord(sql);
    }
    
}
