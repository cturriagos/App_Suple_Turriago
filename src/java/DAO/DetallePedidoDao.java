/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DataStatic.Conection;
import Models.DetallePedido;

/**
 *
 * @author churri
 */
public class DetallePedidoDao {

    Conection conex;
    String sql = "";

    public DetallePedidoDao() {
        conex = new Conection();
    }
    
    /** 
     * FUNCIÓN PARA INSERTAR EL DETALLE DE UN PEDIDO
     * 
     * SE RECIBE UN PARÁMETRO DE TIPO DETALLEPEDIDO, PARA LUEGO FORMAR
     * UNA CADENA SQL CON SUS RESPECTIVAS CONCATENACIONES Y FINALMENTE,
     * METERLA COMO ARGUMENTO DENTRO DE UNA FUNCIÓN DE LA CLASE CONEXIÓN
     * QUE DEVUELVE UN VALOR BOOLEANO (VERDADERO SI LA EJECUCIÓN FUE EXITOSA, 
     * FALSO SI LA EJECUCIÓN FUE FALLIDA)
     * 
     * @param detp
     * @return boolean
     */
    public boolean insertarDetallePedido(DetallePedido detp) {
        sql = String.format("insert into detalle_pedido (producto_id_producto, "
                + "cantidad, precio_unit, encabezado_pedido_id_encapedido) "
                + "values (%s, %s, %s, (select id_encapedido from "
                + "encabezado_pedido order by id_encapedido desc limit 1))", 
                detp.getId_producto(), detp.getCantidad(), detp.getPrecio());
        return conex.modifyBD(sql);
    }
    
    /** 
     * FUNCIÓN PARA INSERTAR LA CANCELACIÓN DE UN PEDIDO
     * 
     * SE RECIBE UN PARÁMETRO DE TIPO DETALLEPEDIDO, PARA LUEGO FORMAR
     * UNA CADENA SQL CON SU RESPECTIVA CONCATENACIÓN Y FINALMENTE,
     * METERLA COMO ARGUMENTO DENTRO DE UNA FUNCIÓN DE LA CLASE CONEXIÓN
     * QUE DEVUELVE UN VALOR BOOLEANO (VERDADERO SI LA EJECUCIÓN FUE EXITOSA, 
     * FALSO SI LA EJECUCIÓN FUE FALLIDA)
     * 
     * @param detp
     * @return boolean
     */
    public boolean cancelarPedido(DetallePedido detp) {
        sql = String.format("delte from detalle_pedido where "
                + "encabezado_pedido_id_encapedido = %s", detp.getId_pedido());
        return conex.modifyBD(sql);
    }

    /** 
     * FUNCIÓN PARA DISMINUIR EL STOCK
     * 
     * SE RECIBE UN PARÁMETRO DE TIPO DETALLEPEDIDO, PARA LUEGO FORMAR
     * UNA CADENA SQL CON SU RESPECTIVA CONCATENACIÓN Y FINALMENTE,
     * METERLA COMO ARGUMENTO DENTRO DE UNA FUNCIÓN DE LA CLASE CONEXIÓN
     * QUE DEVUELVE UN VALOR BOOLEANO (VERDADERO SI LA EJECUCIÓN FUE EXITOSA, 
     * FALSO SI LA EJECUCIÓN FUE FALLIDA)
     * 
     * @param detp
     * @return boolean
     */
    public boolean disminuirStock(DetallePedido detp) {
        sql = String.format("update producto set stock = stock - %s where "
                + "id_producto = %s", detp.getCantidad(), detp.getId_producto());
        return conex.modifyBD(sql);
    }
    
    /** 
     * FUNCIÓN PARA AUMENTAR EL STOCK
     * 
     * SE RECIBE UN PARÁMETRO DE TIPO DETALLEPEDIDO, PARA LUEGO FORMAR
     * UNA CADENA SQL CON SU RESPECTIVA CONCATENACIÓN Y FINALMENTE,
     * METERLA COMO ARGUMENTO DENTRO DE UNA FUNCIÓN DE LA CLASE CONEXIÓN
     * QUE DEVUELVE UN VALOR BOOLEANO (VERDADERO SI LA EJECUCIÓN FUE EXITOSA, 
     * FALSO SI LA EJECUCIÓN FUE FALLIDA)
     * 
     * @param detp
     * @return boolean
     */
    public boolean aumentarStock(DetallePedido detp) {
        sql = String.format("update producto set stock = stock + %s where "
                + "id_producto = %s", detp.getCantidad(), detp.getId_producto());
        return conex.modifyBD(sql);
    }

}
