package com.paint.servlets.DAOS;

import com.paint.servlets.models.Canvas;

import java.util.HashMap;
import java.util.Map;

public class CanvasDAO {

    private final static Map<Integer, Canvas> drawsDatabase = new HashMap();
    private static int idGenerate = 0;

    public static boolean doesDrawExists(String name){
        return false;
    }

    public static int generarID() {
        int id = idGenerate;
        idGenerate++;
        return id;
    }

    public static Canvas guardarDibuix(String name, String contingutJson) {
        Canvas canvas = new Canvas(name, new String[]{contingutJson});
        drawsDatabase.put(generarID() ,canvas);
        return drawsDatabase.get(idGenerate-1);
    }
}
