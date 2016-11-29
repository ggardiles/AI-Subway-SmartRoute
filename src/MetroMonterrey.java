import java.util.HashMap;
import java.util.Map;

/**
 * Created by gabriel on 25/11/16.
 */

//Version mejorada del proyecto
public class MetroMonterrey {

    public static HashMap<String, Estacion> paradas = new HashMap<String, Estacion>(){{
        put("Talleres",     new Estacion("Talleres",1,new String[]{"San Bernabé"},25.754237D, -100.365236D,false,0,0,0,0));
        put("San Bernabé",  new Estacion("San Bernabé",1,new String[]{"Talleres", "Unidad Modelo"},25.748655, -100.361680,false,0,0,0,0));
        put("Unidad Modelo",new Estacion("Unidad Modelo",1,new String[]{"Talleres","Cuauhtemoc"},25.738655, -100.351680,false,0,0,0,0));
        put("Cuauhtemoc",   new Estacion("Cuauhtemoc",1,new String[]{"Unidad Modelo"},25.748655, -100.361680,false,0,0,0,0));
    }};

}
