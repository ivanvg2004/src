package com.paint.servlets.DAOS;

import com.paint.servlets.models.Canvas;
import com.paint.servlets.models.User;

import java.util.HashMap;
import java.util.Map;

public class CanvasDAO {

    private final static Map<User, Map<Integer, Canvas>> drawsDatabase = new HashMap();
    private final static Map<Integer, Canvas> dibuix = new HashMap<>();
    private static int idGenerate = 0;

    public static boolean doesDrawExists(String name){
        return false;
    }

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
        Canvas canvas = new Canvas(name, contingutJson);
        dibuix.put(id,canvas);
        drawsDatabase.put(username, dibuix);
        System.out.println("ID: " +  drawsDatabase +
                            " Nom: " + canvas.getName() +
                            " Dibuix: " + canvas.getContent());
    }

}
