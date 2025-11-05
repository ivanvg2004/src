package com.paint.servlets.DAOS;

import com.paint.servlets.models.Canvas;
import com.paint.servlets.models.User;

import java.util.HashMap;
import java.util.Map;

public class CanvasDAO {

    private final static Map<User, Map<Integer, Canvas>> drawsDatabase = new HashMap();
    private static int idGenerate = 0;

    public static int generarID() {
        int id = idGenerate;
        idGenerate++;
        return id;
    }
    private static String generarName(int id) {
        return "Dibuix" + id;
    }

    public static void guardarDibuix(User username, String name, String contingutJson) {
        int id = generarID();
        if (name.isEmpty()){
            name = generarName(id);
        }
        Canvas canvas = new Canvas(id, name, contingutJson);

        // 1. Obtén el mapa de dibujos PERSONAL de este usuario
        Map<Integer, Canvas> personalDrawings = drawsDatabase.get(username);

        // 2. Si no tiene uno (es su primer dibujo), crea un mapa NUEVO
        if (personalDrawings == null){
            personalDrawings = new HashMap<>();

            // 3. Asocia este mapa NUEVO al usuario
            drawsDatabase.put(username, personalDrawings);
        }
        // 4. Añade el dibujo al mapa PERSONAL de ese usuario
        personalDrawings.put(id, canvas);

        System.out.println("Username: " + username.getUser() +
                            " ID: " +  canvas.getId() +
                            " Nom: " + canvas.getName() +
                            " Dibuix: " + canvas.getContent());
    }

}
