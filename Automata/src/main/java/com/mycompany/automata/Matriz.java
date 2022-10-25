package com.mycompany.automata;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Jaime
 */
public class Matriz {
    
    private ArrayList<Integer> estadosFinales;
    private HashMap<Integer,HashMap<Character,Integer>> matriz;
        

    public Matriz() {
        estadosFinales = new ArrayList<>();
        matriz= new HashMap<>();
    }
    
    
    public ArrayList<Integer> getEstadosFinales() {
        return estadosFinales;
    }

    public HashMap<Integer, HashMap<Character, Integer>> getMatriz() {
        return matriz;
    }
    
    
    public void cargarDatos(String dir){
        rellenarMatriz(dir);
        System.out.println(matriz);
        encontrarEstadosFinales(dir);
    }

        
        
        
    public void rellenarMatriz(String direc){
        try{
            BufferedReader buffer = new BufferedReader(new FileReader(direc));
            String linea;
            String lineaEstadoOrigen=null;
            String lineaEstadoDestino=null;
            String caracterPuente=null;
            boolean encuentraFrom=false;
            boolean encuentraTo=false;
            boolean encuentraRead=false;
            while((linea=buffer.readLine())!=null){ //mientras que no se acabe el archivo
                if(linea.contains("from")){
                    //si encontramos from, eliminamos todo de esa línea menos el número, 
                    //que será el estado de origen, para luego cogerlo fácilmente
                    linea= linea.replace("			<from>","");
                    lineaEstadoOrigen=linea.replace("</from>&#13;","");
                    encuentraFrom=true;
                }
                if(linea.contains("to")){
                    linea=linea.replace("			<to>","");
                    lineaEstadoDestino=linea.replace("</to>&#13;","");
                    encuentraTo=true;
                }
                if(linea.contains("read")){
                    linea=linea.replace("			<read>","");
                    caracterPuente=linea.replace("</read>&#13;","");
                    encuentraRead=true;
                }
                if(encuentraFrom&&encuentraRead&&encuentraTo){
                    //si ya ha encontrado las 3 etiquetas, significa que ya estamos listos para coger sus valores
                    matriz.get(Integer.valueOf(lineaEstadoOrigen)).put(caracterPuente.charAt(0),Integer.valueOf(lineaEstadoDestino));
                    encuentraFrom=false;
                    encuentraTo=false;
                    encuentraRead=false;
                }
                
            }
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    
    
    public void encontrarEstadosFinales(String direc){
        try{
            BufferedReader buffer = new BufferedReader(new FileReader(direc));
            String linea1;
            String linea2=null;
            String linea3=null;
            String linea4=null;
            String linea5=null;
            String linea6=null;
            String estado=null;
            

            while((linea1=buffer.readLine())!=null){ //mientras que no se acabe el archivo
                if(linea1.contains("final")){
                    //si hemos encontrado una línea que ponga final, 4 lineas antes dice el número de dicho estado final
                    //preparamos la línea para que en ella se quede sólo el número
                    if (!linea2.contains("initial")) {
                        linea5= linea5.replace("		<state id=\"","");
                        linea5=linea5.replace("\" name=\"q","");
                        estado=linea5.replace("\">&#13;","");

                        //como ahora tenemos el número 2 veces, debemos eliminarlo una vez
                        estado= estado.substring(estado.length()/2, estado.length());
                        estadosFinales.add(Integer.valueOf(estado));
                    }else{
                        linea6= linea6.replace("		<state id=\"","");
                        linea6=linea6.replace("\" name=\"q","");
                        estado=linea6.replace("\">&#13;","");

                        //como ahora tenemos el número 2 veces, debemos eliminarlo una vez
                        estado= estado.substring(estado.length()/2, estado.length());
                        System.out.println(estado);
                        estadosFinales.add(Integer.valueOf(estado));
                    }
                    
                }
                linea6=linea5;
                linea5=linea4;
                linea4=linea3;
                linea3=linea2;
                linea2=linea1;
            }
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    
}
