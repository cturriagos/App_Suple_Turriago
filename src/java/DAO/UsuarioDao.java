/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import DataStatic.Conection;
import Models.Usuario;
import javax.swing.table.DefaultTableModel;

/**
 * 
 * @author churri
 */
public class UsuarioDao {
    
    Conection conex;
    String sql = "";
    
    public UsuarioDao() {
        conex = new Conection();
    }
    
    /**
     * FUNCIÓN PARA INSERTAR UN USUARIO
     * 
     * SE RECIBE UN PARÁMETRO DE TIPO USUARIO, PARA LUEGO FORMAR
     * UNA CADENA SQL CON SUS RESPECTIVAS CONCATENACIONES Y FINALMENTE,
     * METERLA COMO ARGUMENTO DENTRO DE UNA FUNCIÓN DE LA CLASE CONEXIÓN
     * QUE DEVUELVE UN VALOR BOOLEANO (VERDADERO SI LA EJECUCIÓN FUE EXITOSA, 
     * FALSO SI LA EJECUCIÓN FUE FALLIDA), TAMBIÉN HACE UNA IMPRESIÓN EN CONSOLA
     * PARA VERIFICAR LA SENTENCIA SQL.
     * 
     * @param us
     * @return boolean
     */
    public boolean insertarUsuario(Usuario us){
        sql = String.format("insert into usuario (nombres, apellidos, "
                + "nombre_tienda, estado, tipo_usuario,"
                + "usuario, contrasenia) "
                + "values('%s','%s','%s','%s','%s','%s','%s')",us.getNombres(),
                us.getApellidos(), us.getNombre_tienda(), us.getEstado(), 
                us.getTipo_usuario(), us.getUsuario(), us.getContrasenia());
        return conex.modifyBD(sql);
    }
    
    /**
     * FUNCIÓN PARA HABILITAR UN USUARIO
     * 
     * SE RECIBE UN PARÁMETRO DE TIPO USUARIO, PARA LUEGO FORMAR
     * UNA CADENA SQL CON SUS RESPECTIVAS CONCATENACIONES Y FINALMENTE,
     * METERLA COMO ARGUMENTO DENTRO DE UNA FUNCIÓN DE LA CLASE CONEXIÓN
     * QUE DEVUELVE UN VALOR BOOLEANO (VERDADERO SI LA EJECUCIÓN FUE EXITOSA, 
     * FALSO SI LA EJECUCIÓN FUE FALLIDA), TAMBIÉN HACE UNA IMPRESIÓN EN CONSOLA
     * PARA VERIFICAR LA SENTENCIA SQL.
     * 
     * @param us
     * @return boolean
     */
    public boolean habilitar(Usuario us){
        sql = "update usuario set estado = 'a' where id_usuario = "
                +us.getId_usuario()+"";
        return conex.modifyBD(sql);
    }
    
    /**
     * FUNCIÓN PARA HACER UN LOGIN
     * 
     * SE RECIBEN PARÁMETROS DE TIPO STRING, PARA LUEGO FORMAR
     * UNA CADENA SQL CON SUS RESPECTIVAS CONCATENACIONES Y FINALMENTE,
     * METERLA COMO ARGUMENTO DENTRO DE UNA FUNCIÓN DE LA CLASE CONEXIÓN
     * QUE DEVUELVE UN VALOR DEFAULTTABLEMODEL Y TAMBIÉN SE HACE UNA IMPRESIÓN
     * EN CONSOLA PARA VERIFICAR LA SENTENCIA.
     * 
     * @param nombre_user 
     * @param contrasenia
     * @return DefaultTableModel
     */
    public DefaultTableModel login(String nombre_user, String contrasenia){
        sql = "select * from usuario where usuario = '"+nombre_user+"' "
                + "and contrasenia = '"+contrasenia+"'";
        System.out.println(sql);
        return conex.returnRecord(sql);
    }
    
    /**
     * FUNCIÓN PARA LISTAR USUARIOS
     * 
     * NO SE RECIBEN PARÁMETROS
     * SE HACE UNA CADENA SQL Y FINALMENTE, SE DEVUELVE UN VALOR STRING 
     * QUE ES UN JSON FORMATEADO.
     * 
     * @return String
     */
    public String listarUsuarios(){
        sql = "select * from usuario order by id_usuario";
        return conex.getRecordsInJson(sql);
    }
   
    
}
