package com.paint.servlets.models;
// Podrías tener una lista de usuarios aquí
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private String name;
    private String user;
    private String password;
    // Lista "simulada" de usuarios (en lugar de una BD)
    private static List<UserDAO> userDatabase = new ArrayList<>();
    public UserDAO() {

    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    // Bloque estático para añadir usuarios de prueba
    static {
        UserDAO user1 = new UserDAO("Tom Cat", "tom", "12345");
        userDatabase.add(user1);

        UserDAO user2 = new UserDAO("ivan", "ivaanvargaas", "20004");
        userDatabase.add(user2);
    }

    public UserDAO(String name, String user, String password) {
        this.name = name;
        this.user = user;
        this.password = password;
    }

    /**
     * Busca un usuario por su 'username' y verifica la contraseña.
     */
    public boolean checkUser(String username, String password) {
        // 1. Buscar al usuario en la "base de datos"
        for (UserDAO user : userDatabase) {
            if (user.getUser().equals(username)) {
                // 2. Si se encuentra, verificar su contraseña
                return user.getPassword().equals(password);
            }
        }

        // 3. Si no se encuentra el usuario, retornar false
        return false;
    }

}