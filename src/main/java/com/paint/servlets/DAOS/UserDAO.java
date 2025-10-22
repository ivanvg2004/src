package com.paint.servlets.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase actúa como un Repositorio (DAO) para los usuarios.
 * Gestiona la "base de datos" estática.
 */
public class UserDAO {

    // La base de datos ahora almacena objetos "User"
    private static List<User> userDatabase = new ArrayList<>();

    // Bloque estático para añadir usuarios de prueba
    static {
        // Usamos la nueva clase User
        userDatabase.add(new User("Tom Cat", "tom", "12345"));
        userDatabase.add(new User("ivan", "ivaanvargaas", "20004"));
    }

    /**
     * Busca un usuario por su 'username' y verifica la contraseña.
     * Se usa para el Login.
     */
    public static boolean checkUser(String username, String password) {
        for (User user : userDatabase) {
            if (user.getUser().equals(username)) {
                // Si se encuentra, verificar su contraseña
                return user.getPassword().equals(password);
            }
        }
        // Si no se encuentra el usuario, retornar false
        return false;
    }

    /**
     * Comprueba si un nombre de usuario ya existe en la base de datos.
     * Se usa para el Registro.
     */
    public static boolean doesUsernameExist(String username) {
        for (User user : userDatabase) {
            if (user.getUser().equals(username)) {
                return true; // El usuario ya existe
            }
        }
        return false; // El usuario no existe
    }

    /**
     * Añade un nuevo usuario a la base de datos.
     * Se usa para el Registro.
     * 'synchronized' evita problemas si dos personas se registran a la vez.
     */
    public static synchronized void addUser(User newUser) {
        // Aquí es donde deberías hashear la contraseña antes de guardarla
        userDatabase.add(newUser);
    }
}