/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import DataStatic.Conection;
import Models.PedidoEliminado;

/**
 * 
 * @author churri
 */
public class PedidoEliminadoDao {
    
    Conection conex;
    String sql = "";
    
    public PedidoEliminadoDao(){
        conex = new Conection();
    }
    
    /**
     * FUNCIÓN PARA INSERTAR UN PEDIDO ELIMINADO
     * 
     * SE RECIBE UN PARÁMETRO DE TIPO PEDIDOELIMINADO, PARA LUEGO FORMAR
     * UNA CADENA SQL CON SUS RESPECTIVAS CONCATENACIONES Y FINALMENTE,
     * METERLA COMO ARGUMENTO DENTRO DE UNA FUNCIÓN DE LA CLASE CONEXIÓN
     * QUE DEVUELVE UN VALOR BOOLEANO (VERDADERO SI LA EJECUCIÓN FUE EXITOSA, 
     * FALSO SI LA EJECUCIÓN FUE FALLIDA), TAMBIÉN HACE UNA IMPRESIÓN EN CONSOLA
     * PARA VERIFICAR LA SENTENCIA SQL.
     * 
     * @param pe
     * @return boolean
     */
    public boolean insertarPedidoEliminado(PedidoEliminado pe){
        sql = String.format("insert into encabezado_pedido_eliminado (fecha_eliminar, fecha_pedido, usuario_id_usuario, descuento, total) "
                + "values(now(), '%s', %s, %s, %s)",pe.getFecha_pedido(), pe.getId_usuario(), pe.getDescuento(), pe.getTotal());
        return conex.modifyBD(sql);
    }
}
