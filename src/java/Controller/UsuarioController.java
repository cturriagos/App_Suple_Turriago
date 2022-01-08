/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.UsuarioDao;
import Models.UserSession;
import Models.Usuario;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author churri
 */
public class UsuarioController {

    UsuarioDao usdao;
    String message;

    public UsuarioController() {
        message = "";
    }

    /**
     * FUNCIÓN PARA INSERTAR USUARIOS
     * 
     * SE RECIBEN PARÁMETROS DE TIPO STRING, QUE LUEGO ES INSERTADO COMO 
     * ARGUMENTO DENTRO DE LAS PROPIEDADES DE LA CLASE USUARIO Y 
     * DEVUELVE UN STRING ACORDE A LA CONDICIÓN.
     * 
     * @param nombres
     * @param apellidos
     * @param nombre_tienda
     * @param estado
     * @param tipo_usuario
     * @param usuario
     * @param contrasenia
     * @return String
     */
    public String insertarUsuario(String nombres, String apellidos, 
                                  String nombre_tienda, String estado, 
                                  String tipo_usuario, String usuario, 
                                  String contrasenia) {
        usdao = new UsuarioDao();
        Usuario us = new Usuario();
        this.message = "Error en los parametros de entrada.";
        us.setNombres(nombres);
        us.setApellidos(apellidos);
        us.setNombre_tienda(nombre_tienda);
        us.setEstado(estado);
        us.setTipo_usuario(tipo_usuario);
        us.setUsuario(usuario);
        us.setContrasenia(contrasenia);
        if(usdao.insertarUsuario(us)){
            this.message = "Usuario registrado correctamente.";
        }else{
            this.message = "Erro al agregar un nuevo usuario.";
        }
        return this.message;
    }
    
    /**
     * FUNCIÓN PARA HABILITAR USUARIOS
     * 
     * SE RECIBEN PARÁMETROS DE TIPO STRING, QUE LUEGO ES INSERTADO COMO 
     * ARGUMENTO DENTRO DE LA PROPIEDAD DE LA CLASE USUARIO Y 
     * DEVUELVE UN STRING ACORDE A LA CONDICIÓN.
     * 
     * @param id_usuario
     * @return String
     */
    public String habilitarUsuario (String id_usuario){
        usdao = new UsuarioDao();
        Usuario us = new Usuario();
        this.message = "Error en los parametros de entrada.";
        us.setId_usuario(id_usuario);
        if(usdao.habilitar(us)){
            this.message = "Usuario habilitado correctamente";
        }else{
            this.message = "Error al habilitar el usuario";
        }
        return this.message;
    }
    
    /**
     * FUNCIÓN PARA LOGIN
     * 
     * SE RECIBEN PARÁMETROS DE TIPO STRING, QUE LUEGO ES INSERTADO COMO 
     * ARGUMENTO DENTRO DE LAS PROPIEDADES DE LA CLASE USERSESSION Y 
     * DEVUELVE UN VALOR DE TIPO USERSESSION ACORDE A LA CONDICIÓN
     * 
     * @param usuario
     * @param contrasenia
     * 
     * @return UserSession
     */
    public UserSession login(String usuario, String contrasenia){
        UserSession usr = null;
        usdao = new UsuarioDao();
        DefaultTableModel employeeresponse = usdao.login(usuario, contrasenia);
        if (employeeresponse.getRowCount() > 0) {
            usr = new UserSession();
            usr.setId_user(employeeresponse.getValueAt(0, 0).toString());
            usr.setNombre(employeeresponse.getValueAt(0, 1).toString());
            usr.setApellido(employeeresponse.getValueAt(0, 2).toString());
            usr.setNombre_usuario(employeeresponse.getValueAt(0, 6).toString());
            usr.setCargo(employeeresponse.getValueAt(0, 5).toString());
            usr.setEstado(employeeresponse.getValueAt(0, 4).toString());
        } else {
            usr = null;
        }
        return usr;
    }
    
    /**
     * FUNCIÓN PARA LISTAR USUARIOS
     * 
     * NO SE RECIBEN PARÁMETROS, SE CREA UN OBJETO DE TIPO USUARIODAO Y CON ÉL
     * SE LLAMA A LA FUNCIÓN DE LISTAR USUARIOS
     * 
     * @return UsuarioDao
     */
    public String listarUsuarios(){
        usdao = new UsuarioDao();
        return usdao.listarUsuarios();
    }

}
