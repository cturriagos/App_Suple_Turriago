/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.PedidoDao;
import Models.Pedido;

/**
 *
 * @author churri
 */
public class PedidoController {

    PedidoDao pedao;
    String message;

    public PedidoController() {
        this.message = "";
    }

    /**
     * FUNCIÓN PARA INSERTAR PEDIDOS
     * 
     * SE RECIBEN PARÁMETROS DE TIPO STRING, QUE SON INSERTADOS EN LAS 
     * PROPIEDADES DE LA CLASE PEDIDO, FINALMENTE SE DEVUELVE 
     * UN VALOR STRING DEPENDIENDO DE QUÉ CONDICIÓN SE CUMPLA.
     * 
     * @param estado
     * @param id_usuario
     * @param total
     * @return String
     */
    public String insertarPedido(String estado, String id_usuario, String total) {
        pedao = new PedidoDao();
        Pedido pe = new Pedido();

        this.message = "Error en los parametros de entrada";

        pe.setEstado(estado);
        pe.setId_usuario(id_usuario);
        pe.setTotal(total);
        
        //validacion de los descuentos
        int descuento = Integer.parseInt(pedao.cantidadVentasPorMes(id_usuario));
        pe.setDescuento(descuento == 0 ? descuento : (descuento > 0 
                        && descuento< 11) ? descuento : 10);

        if (pedao.insertarPedido(pe)) {
            this.message = "procesando...";
        } else {
            this.message = "error de base de datos";
        }
        return this.message;
    }

    /**
     * FUNCIÓN PARA LISTAR PEDIDOS
     * 
     * SE RECIBE UN PARÁMETRO DE TIPO STRING, QUE LUEGO ES INSERTADO COMO 
     * ARGUMENTO EN UNA FUNCIÓN DAO DE LOS PEDIDOS Y DEVUELVE UN STRING.
     * 
     * @param id_usuario
     * @return String
     */
    public String listarPedidos(String id_usuario) {
        pedao = new PedidoDao();
        return pedao.listarPedidos(id_usuario);
    }
    
    /**
     * FUNCIÓN PARA LISTAR PEDIDOS CANCELADOS
     * 
     * SE RECIBE UN PARÁMETRO DE TIPO STRING, QUE LUEGO ES INSERTADO COMO 
     * ARGUMENTO EN UNA FUNCIÓN DAO DE LOS PEDIDOS Y DEVUELVE UN STRING.
     * 
     * @param id_usuario
     * @return String
     */
    public String listarPedidosCancelados(String id_usuario) {
        pedao = new PedidoDao();
        return pedao.listarPedidosCancelados(id_usuario);
    }
    
    /**
     * FUNCIÓN PARA LISTAR PEDIDOS DE UNA DETERMINADA TIENDA
     * 
     * SE RECIBE UN PARÁMETRO DE TIPO STRING, QUE LUEGO ES INSERTADO COMO 
     * ARGUMENTO EN UNA FUNCIÓN DAO DE LOS PEDIDOS Y DEVUELVE UN STRING.
     * 
     * @param id_usuario
     * @return String
     */
    public String listarPedidosTienda(String id_usuario){
        pedao = new PedidoDao();
        Pedido pe = new Pedido();
        
        pe.setId_usuario(id_usuario);
        return pedao.listarPedidosTienda(pe);
    }
    
    /**
     * FUNCIÓN PARA CANCELAR PEDIDOS
     * 
     * SE RECIBE UN PARÁMETRO DE TIPO STRING, QUE LUEGO ES INSERTADO COMO 
     * ARGUMENTO EN UNA FUNCIÓN DAO DE LOS PEDIDOS Y DEVUELVE UN STRING.
     * 
     * @param id_usuario
     * @return String
     */
    public String cancelarPedido (String id_pedido){
        pedao = new PedidoDao();
        if(pedao.cancelarPedido(id_pedido)){
                this.message = "Pedido cancelado correctamente";
        }else{
            this.message = "error";
        }
        return this.message;
    }
    
    /**
     * FUNCIÓN PARA DEPSACHAR PEDIDOS
     * 
     * SE RECIBE UN PARÁMETRO DE TIPO STRING, QUE LUEGO ES INSERTADO COMO 
     * ARGUMENTO EN UNA FUNCIÓN DAO DE LOS PEDIDOS Y DEVUELVE UN STRING.
     * 
     * @param id_usuario
     * @return String
     */
    public String despacharPedido (String id_pedido){
        pedao = new PedidoDao();
        if(pedao.despacharPedido(id_pedido)){
                this.message = "Pedido despachado correctamente";
        }else{
            this.message = "error";
        }
        return this.message;
    }
    /**
     * FUNCIÓN PARA CALCULAR DESCUENTOS POR CANTIAD
     * 
     * SE RECIBE UN PARÁMETRO DE TIPO STRING, QUE LUEGO ES INSERTADO COMO 
     * ARGUMENTO EN UNA FUNCIÓN DAO DE LOS PEDIDOS Y DEVUELVE UN STRING.
     * 
     * @param id_usuario
     * @return String
     */
    
    public String cantidadDescuento(String id_usuario){
        pedao = new PedidoDao();
        int descuento = Integer.parseInt(pedao.cantidadVentasPorMes(id_usuario));
        int descuento_response = (descuento == 0 ? descuento : (descuento > 0 
                                  && descuento < 11) ? descuento : 10);
        return  String.valueOf(descuento_response);
    }
    
}
