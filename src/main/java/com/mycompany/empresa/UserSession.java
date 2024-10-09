
package com.mycompany.empresa;

public class UserSession {

    private static String usuario;
    private static String rol;  // Puedes agregar otros campos si lo necesitas

    public static void setUsuario(String usuario) {
        UserSession.usuario = usuario;
    }

    public static String getUsuario() {
        return usuario;
    }

    public static void setRol(String rol) {
        UserSession.rol = rol;
    }

    public static String getRol() {
        return rol;
    }

    public static void cerrarSesion() {
        usuario = null;
        rol = null;
    }
}

