/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DetallePedidoDao;
import Models.DetallePedido;

/**
 *
 * @author churri
 */
public class DetallePedidoController {

    DetallePedidoDao detpdao;
    String message;

    public DetallePedidoController() {
        this.message = "";
    }

    /**
     * FUNCIÓN PARA INSERTAR DETALLES DE PEDIDOS
     * 
     * SE RECIBEN PARÁMETROS DE TIPO STRING, QUE SON INSERTADOS EN LAS 
     * PROPIEDADES DE LA CLASE DETALLEPEDIDO, FINALMENTE SE DEVUELVE UN VALOR 
     * STRING DEPENDIENDO DE QUÉ CONDICIÓN SE CUMPLA.
     * 
     * @param id_producto
     * @param cantidad
     * @param precio
     * @return String
     */
    public String insertarDetallePedido(String id_producto, String cantidad, 
                                        String precio) {
        detpdao = new DetallePedidoDao();
        DetallePedido detm = new DetallePedido();
        this.message = "Error en los parametros ingresados";

        detm.setCantidad(cantidad);
        detm.setPrecio(precio);
        detm.setId_producto(id_producto);

        if (detpdao.insertarDetallePedido(detm)) {
            this.message = "Pedido realizado con exito";
        } else {
            this.message = "error de base de datos";
        }
        return this.message;
    }
    
    /**
     * FUNCIÓN PARA DISMINUIR STOCK
     * 
     * SE RECIBEN PARÁMETROS DE TIPO STRING, QUE SON INSERTADOS EN LAS 
     * PROPIEDADES DE LA CLASE DETALLEPEDIDO, FINALMENTE SE DEVUELVE UN VALOR 
     * STRING DEPENDIENDO DE QUÉ CONDICIÓN SE CUMPLA.
     * 
     * @param id_producto
     * @param id_cantidad
     * @return String
     */
    public String disminuirStock(String id_producto, String id_cantidad) {
        detpdao = new DetallePedidoDao();
        DetallePedido detm = new DetallePedido();
        this.message = "Error en los parametros ingresados";

        detm.setId_producto(id_producto);
        detm.setCantidad(id_cantidad);

        if (detpdao.disminuirStock(detm)) {
            this.message = "Stock actualizado correctamente";
        } else {
            this.message = "error de base de datos";
        }
        return this.message;
    }
    
    /**
     * FUNCIÓN PARA AUMENTAR STOCK
     * 
     * SE RECIBEN PARÁMETROS DE TIPO STRING, QUE SON INSERTADOS EN LAS 
     * PROPIEDADES DE LA CLASE DETALLEPEDIDO, FINALMENTE SE DEVUELVE UN VALOR 
     * STRING DEPENDIENDO DE QUÉ CONDICIÓN SE CUMPLA.
     * 
     * @param id_producto
     * @param id_cantidad
     * @return String
     */
    public String aumentarStock(String id_producto, String id_cantidad) {
        detpdao = new DetallePedidoDao();
        DetallePedido detm = new DetallePedido();
        this.message = "Error en los parametros ingresados";

        detm.setId_producto(id_producto);
        detm.setCantidad(id_cantidad);

        if (detpdao.aumentarStock(detm)) {
            this.message = "Stock actualizado correctamente";
        } else {
            this.message = "error de base de datos";
        }
        return this.message;
    }

}
