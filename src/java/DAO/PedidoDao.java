/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DataStatic.Conection;
import Models.Pedido;

/**
 *
 * @author churri
 */
public class PedidoDao {

    Conection conex;
    String sql = "";

    public PedidoDao() {
        conex = new Conection();
    }

    /**
     * FUNCIÓN PARA INSERTAR UN PEDIDO (SU ENCABEZADO)
     * 
     * SE RECIBE UN PARÁMETRO DE TIPO PEDIDO, PARA LUEGO FORMAR
     * UNA CADENA SQL CON SUS RESPECTIVAS CONCATENACIONES Y FINALMENTE,
     * METERLA COMO ARGUMENTO DENTRO DE UNA FUNCIÓN DE LA CLASE CONEXIÓN
     * QUE DEVUELVE UN VALOR BOOLEANO (VERDADERO SI LA EJECUCIÓN FUE EXITOSA, 
     * FALSO SI LA EJECUCIÓN FUE FALLIDA), TAMBIÉN HACE UNA IMPRESIÓN EN CONSOLA
     * PARA VERIFICAR LA SENTENCIA SQL.
     * 
     * @param pd
     * @return boolean
     */
    public boolean insertarPedido(Pedido pd) {
        sql = String.format("insert into encabezado_pedido (fecha_venta, estado, "
                + "usuario_id_usuario, total, descuento) values (now(),'%s',%s, "
                + "%s, %s)", pd.getEstado(), pd.getId_usuario(), pd.getTotal(), 
                pd.getDescuento());
        return conex.modifyBD(sql);
    }

    /**
     * FUNCIÓN PARA CALCULAR LAS VENTAS POR MES
     * 
     * SE RECIBE UN PARÁMETRO DE TIPO STRING, PARA LUEGO FORMAR
     * UNA CADENA SQL CON SUS RESPECTIVAS CONCATENACIONES Y FINALMENTE,
     * METERLA COMO ARGUMENTO DENTRO DE UNA FUNCIÓN DE LA CLASE CONEXIÓN
     * QUE DEVUELVE UN VALOR STRING, MOSTRANDO UNA CANTIDAD ESTILO COUNT
     * PRODUCTO DE LA SENTENCIA EJECUTADA
     * 
     * @param id_usuario
     * @return boolean
     */
    public String cantidadVentasPorMes(String id_usuario) {
        sql = String.format("select count(id_encapedido) \n"
                + "from encabezado_pedido where DATE_PART('day', now() - "
                + "fecha_venta) <= 30 and usuario_id_usuario = %s", id_usuario);
        return conex.fillString(sql);
    }

    /**
     * FUNCIÓN PARA CANCELAR UN PEDIDO
     * 
     * SE RECIBE UN PARÁMETRO DE TIPO STRING, PARA LUEGO FORMAR
     * UNA CADENA SQL CON SUS RESPECTIVAS CONCATENACIONES Y FINALMENTE,
     * METERLA COMO ARGUMENTO DENTRO DE UNA FUNCIÓN DE LA CLASE CONEXIÓN
     * QUE DEVUELVE UN VALOR BOOLEANO (VERDADERO SI LA EJECUCIÓN FUE EXITOSA, 
     * FALSO SI LA EJECUCIÓN FUE FALLIDA), TAMBIÉN HACE UNA IMPRESIÓN EN CONSOLA
     * PARA VERIFICAR LA SENTENCIA SQL.
     * 
     * @param pd
     * @return boolean
     */
    public boolean cancelarPedido(Pedido pd) {
        sql = String.format("delte from encabezado_pedido where "
                + "id_encapedido = %s", pd.getId_pedido());
        return conex.modifyBD(sql);
    }

    /**
     * FUNCIÓN PARA LISTAR PEDIDOS
     * 
     * SE RECIBE UN PARÁMETRO DE TIPO STRING, PARA LUEGO FORMAR
     * UNA CADENA SQL CON SUS RESPECTIVAS CONCATENACIONES Y FINALMENTE,
     * METERLA COMO ARGUMENTO DENTRO DE UNA FUNCIÓN DE LA CLASE CONEXIÓN
     * QUE DEVUELVE UN VALOR STRING, PERO EN REALIDAD ES UN JSON FORMATEADO.
     * 
     * @param id_usuario
     * @return String
     */
    public String listarPedidos(String id_usuario) {
        sql = "select distinct ep.id_encapedido,ep.fecha_venta, us.nombre_tienda"
                + ", ep.total, ep.descuento, ep.estado\n"
                + "from encabezado_pedido as ep inner join detalle_pedido as "
                + "dp on ep.id_encapedido = dp.encabezado_pedido_id_encapedido\n"
                + "inner join producto as pr on dp.producto_id_producto "
                + "= pr.id_producto \n"
                + "inner join usuario as us on pr.usuario_id_usuario "
                + "= us.id_usuario\n"
                + "where ep.usuario_id_usuario = " + id_usuario + "\n"
                + "group by ep.total, ep.id_encapedido, dp.id_detpedido, "
                + "pr.id_producto, us.id_usuario "
                + "order by ep.id_encapedido asc";
        return conex.getRecordsInJson(sql);
    }

    /**
     * FUNCIÓN PARA LISTAR PEDIDOS CANCELADOS
     * 
     * SE RECIBE UN PARÁMETRO DE TIPO STRING, PARA LUEGO FORMAR
     * UNA CADENA SQL CON SUS RESPECTIVAS CONCATENACIONES Y FINALMENTE,
     * METERLA COMO ARGUMENTO DENTRO DE UNA FUNCIÓN DE LA CLASE CONEXIÓN
     * QUE DEVUELVE UN VALOR STRING, PERO EN REALIDAD ES UN JSON FORMATEADO.
     * 
     * @param id_usuario
     * @return String
     */
    public String listarPedidosCancelados(String id_usuario) {
        sql = "select distinct ep.id_pedeliminado, ep.fecha_pedido, "
                + "ep.fecha_pedido, us.nombre_tienda, "
                + "ep.total, ep.descuento, ep.descuento\n"
                + "from encabezado_pedido_eliminado as ep inner join "
                + "detalle_pedido_eliminado as dp on "
                + "ep.id_pedeliminado = dp.encabezado_pedido_eliminado_id_pedeliminado\n"
                + "inner join producto as pr on dp.producto_id_producto = pr.id_producto \n"
                + "inner join usuario as us on pr.usuario_id_usuario = us.id_usuario\n"
                + "where ep.usuario_id_usuario = " + id_usuario + "\n"
                + "group by ep.total, ep.id_pedeliminado, "
                + "dp.id_detalle_pedelimnado, pr.id_producto, us.id_usuario \n"
                + "order by ep.id_pedeliminado asc";
        return conex.getRecordsInJson(sql);
    }

    /**
     * FUNCIÓN PARA LISTAR PEDIDOS DE UNA TIENDA
     * 
     * SE RECIBE UN PARÁMETRO DE TIPO STRING, PARA LUEGO FORMAR
     * UNA CADENA SQL CON SUS RESPECTIVAS CONCATENACIONES Y FINALMENTE,
     * METERLA COMO ARGUMENTO DENTRO DE UNA FUNCIÓN DE LA CLASE CONEXIÓN
     * QUE DEVUELVE UN VALOR STRING, PERO EN REALIDAD ES UN JSON FORMATEADO, 
     * TAMBIÉN SE HACE UNA IMPRESIÓN EN CONSOLA PARA VERIFICAR LA SENTENCIA
     * 
     * @param pd
     * @return String
     */
    public String listarPedidosTienda(Pedido pd) {
        sql = "select distinct ep.id_encapedido,ep.fecha_venta, ep.total, "
                + "ep.descuento, ep.estado, ep.usuario_id_usuario\n"
                + "from encabezado_pedido as ep inner join detalle_pedido "
                + "as dp on ep.id_encapedido = dp.encabezado_pedido_id_encapedido\n"
                + "inner join producto as pr on dp.producto_id_producto "
                + "= pr.id_producto \n"
                + "inner join usuario as us on pr.usuario_id_usuario "
                + "= us.id_usuario\n"
                + "where us.id_usuario = " + pd.getId_usuario() + "\n"
                + "group by ep.total, ep.id_encapedido, "
                + "dp.id_detpedido, pr.id_producto, us.id_usuario \n"
                + "order by ep.id_encapedido asc";
        System.out.println(sql);
        return conex.getRecordsInJson(sql);
    }
    
    /**
     * FUNCIÓN PARA CANCELAR PEDIDOS
     * 
     * SE RECIBE UN PARÁMETRO DE TIPO STRING, PARA LUEGO FORMAR
     * UNA CADENA SQL CON SUS RESPECTIVAS CONCATENACIONES Y FINALMENTE,
     * METERLA COMO ARGUMENTO DENTRO DE UNA FUNCIÓN DE LA CLASE CONEXIÓN
     * QUE DEVUELVE UN VALOR BOOLEANO (VERDADERO SI LA EJECUCIÓN FUE EXITOSA, 
     * FALSO SI LA EJECUCIÓN FUE FALLIDA), TAMBIÉN HACE UNA IMPRESIÓN EN CONSOLA
     * PARA VERIFICAR LA SENTENCIA SQL.
     * 
     * @param id_pedido
     * @return boolean
     */
    public boolean cancelarPedido(String id_pedido) {
        sql = "delete from detalle_pedido where "
                + "encabezado_pedido_id_encapedido = " + id_pedido + "";
        System.out.println(sql);
        if (conex.modifyBD(sql)) {
            sql = "delete from encabezado_pedido "
                    + "where id_encapedido = " + id_pedido + "";
        }
        return conex.modifyBD(sql);
    }

    /**
     * FUNCIÓN PARA DESPACHAR PEDIDOS
     * 
     * SE RECIBE UN PARÁMETRO DE TIPO STRING, PARA LUEGO FORMAR
     * UNA CADENA SQL CON SUS RESPECTIVAS CONCATENACIONES Y FINALMENTE,
     * METERLA COMO ARGUMENTO DENTRO DE UNA FUNCIÓN DE LA CLASE CONEXIÓN
     * QUE DEVUELVE UN VALOR BOOLEANO (VERDADERO SI LA EJECUCIÓN FUE EXITOSA, 
     * FALSO SI LA EJECUCIÓN FUE FALLIDA), TAMBIÉN HACE UNA IMPRESIÓN EN CONSOLA
     * PARA VERIFICAR LA SENTENCIA SQL.
     * 
     * @param id_pedido
     * @return boolean
     */
    public boolean despacharPedido(String id_pedido) {
        sql = "update encabezado_pedido set estado = 'd' "
                + "where id_encapedido = " + id_pedido + "";
        System.out.println(sql);
        return conex.modifyBD(sql);
    }

}
