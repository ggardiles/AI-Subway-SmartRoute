import java.util.HashMap;
import java.util.Map;

/**
 * Created by gabriel on 25/11/16.
 */

public class MetroMonterrey {

    public static HashMap<String, Estacion> paradas = new HashMap<String, Estacion>(){{
        //Linea 1

        put("Talleres",     new Estacion("Talleres",1,new String[]{"San Bernabé"},false,0,0,100,61));
        put("San Bernabé",  new Estacion("San Bernabé",1,new String[]{"Talleres", "Unidad Modelo"},false,0,0,143,111));
        put("Unidad Modelo",new Estacion("Unidad Modelo",1,new String[]{"San Bernabé","Aztlán"},false,0,0,173,141));
        put("Aztlán",  new Estacion("Aztlán",1,new String[]{"Unidad Modelo" , "Penitenciaría" },false,0,0,197,167));
        put("Penitenciaría",  new Estacion("Penitenciaría",1,new String[]{"Aztlán" , "Alfonso Reyes" },false,0,0,224,203));
        put("Alfonso Reyes",  new Estacion("Alfonso Reyes",1,new String[]{"Penitenciaría" , "Mitras" },false,0,0,226,239));
        put("Mitras",  new Estacion("Mitras",1,new String[]{"Alfonso Reyes" , "Simón Bolívar" },false,0,0,225,271));
        put("Simón Bolívar",  new Estacion("Simón Bolívar",1,new String[]{"Mitras" , "Hospital" },false,0,0,227,307));
        put("Hospital",  new Estacion("Hospital",1,new String[]{"Simón Bolívar" , "Edison" },false,0,0,225,344));
        put("Edison",  new Estacion("Edison",1,new String[]{"Hospital" , "Central" },false,0,0,260,354));
        put("Central",  new Estacion("Central",1,new String[]{"Edison" , "Cuahtémoc" },false,0,0,300,356));
        put("Cuahtémoc",  new Estacion("Cuahtémoc",1,new String[]{"Central" , "Alameda" , "General Anaya" , "Del Golfo" },true,0,0,349,355));
        put("Del Golfo",  new Estacion("Del Golfo",1,new String[]{"Cuahtémoc" , "Félix U. Gómez" },false,0,0,397,355));
        put("Félix U. Gómez",  new Estacion("Félix U. Gómez",1,new String[]{"Del Golfo" , "Conchello" , "Adolfo Prieto" , "Parque Fundidora" },true,0,0,440,356));
        put("Parque Fundidora",  new Estacion("Parque Fundidora",1,new String[]{"Félix U. Gómez" , "Y Griega" },false,0,0,485,355));
        put("Y Griega",  new Estacion("Y Griega",1,new String[]{"Parque Fundidora" , "Eloy Cavazos" },false,0,0,525,355));
        put("Eloy Cavazos",  new Estacion("Eloy Cavazos",1,new String[]{"Y Griega" , "Lerdo de Tejada" },false,0,0,564,365));
        put("Lerdo de Tejada",  new Estacion("Lerdo de Tejada",1,new String[]{"Eloy Cavazos" , "Exposición" },false,0,0,606,366));
        put("Exposición",  new Estacion("Exposición",1,new String[]{"Lerdo de Tejada" },false,0,0,643,365));

        //Linea 2

        put("Sendero",  new Estacion("Sendero",2,new String[]{"Santiago Tapia" },false,0,0,365,19));
        put("Santiago Tapia",  new Estacion("Santiago Tapia",2,new String[]{"Sendero" , "San Nicolás" },false,0,0,362,58));
        put("San Nicolás",  new Estacion("San Nicolás",2,new String[]{"Santiago Tapia" , "Anáhuac" },false,0,0,365,95));
        put("Anáhuac",  new Estacion("Anáhuac",2,new String[]{"San Nicolás" , "Universidad" },false,0,0,364,134));
        put("Universidad",  new Estacion("Universidad",2,new String[]{"Anáhuac" , "Niños Héroes" },false,0,0,363,192));
        put("Niños Héroes",  new Estacion("Niños Héroes",2,new String[]{"Universidad" , "Regina" },false,0,0,363,224));
        put("Regina",  new Estacion("Regina",2,new String[]{"Niños Héroes" , "General Anaya" },false,0,0,351,261));
        put("General Anaya",  new Estacion("General Anaya",2,new String[]{"Regina" , "Cuahtémoc" },false,0,0,350,302));
        put("Alameda",  new Estacion("Alameda",2,new String[]{"Cuahtémoc" , "Fundadores" },false,0,0,350,423));
        put("Fundadores",  new Estacion("Fundadores",2,new String[]{"Alameda" , "Padre Mier" },false,0,0,350,466));
        put("Padre Mier",  new Estacion("Padre Mier",2,new String[]{"Fundadores" , "General I. Zaragoza" },false,0,0,364,495));
        put("General I. Zaragoza",  new Estacion("General I. Zaragoza",2,new String[]{"Padre Mier" , "Santa Lucía" },true,0,0,402,494));

        //Linea 3

        put("Santa Lucía",  new Estacion("Santa Lucía",3,new String[]{"General I. Zaragoza" , "Adolfo Prieto" },false,0,0,440,472));
        put("Adolfo Prieto",  new Estacion("Adolfo Prieto",3,new String[]{"Santa Lucía" , "Félix U. Gómez" },false,0,0,440,416));
        put("Conchello",  new Estacion("Conchello",3,new String[]{"Félix U. Gómez" , "Violeta (Col. Moderna)" },false,0,0,440,298));
        put("Violeta (Col. Moderna)",  new Estacion("Violeta (Col. Moderna)",3,new String[]{"Conchello" , "Ruiz Cortines (Ecovía)" },false,0,0,450,251));
        put("Ruiz Cortines (Ecovía)",  new Estacion("Ruiz Cortines (Ecovía)",3,new String[]{"Violeta (Col. Moderna)" , "Los Ángeles" },false,0,0,484,216));
        put("Los Ángeles",  new Estacion("Los Ángeles",3,new String[]{"Ruiz Cortines (Ecovía)" , "Hospital Metropolitano" },false,0,0,513,184));
        put("Hospital Metropolitano",  new Estacion("Hospital Metropolitano",3,new String[]{"Los Ángeles" },false,0,0,544,154));
    }};

}
