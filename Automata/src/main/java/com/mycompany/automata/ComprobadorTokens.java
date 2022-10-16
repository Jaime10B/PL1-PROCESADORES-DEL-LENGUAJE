
package com.mycompany.automata;

import java.util.ArrayList;


public class ComprobadorTokens {
    
    private ArrayList<ArrayList<String>> listasValidas;
    private ArrayList<ArrayList<Integer>> listasBooleanos;
    private ArrayList<ArrayList<ArrayList<Integer>>> listasPosiciones;
    private ArrayList<ArrayList<Integer>> listasNumAutomata;
    private ArrayList<Character> errores;
    private ArrayList<Integer> posicionesErrores;
    private ArrayList<String> validas;
    private String cadena;
    private ArrayList<String> todoJunto;

    public ComprobadorTokens() {
        listasValidas= new ArrayList<>();
        listasBooleanos= new ArrayList<>();
        listasPosiciones= new ArrayList<>();
        listasNumAutomata= new ArrayList<>();
        todoJunto= new ArrayList<>();
        errores= new ArrayList<>();
        validas= new ArrayList<>();
        posicionesErrores= new ArrayList<>();
    }



    public void añadirAListasValidas(ArrayList<String> lista) {
        if(!lista.isEmpty()){
            listasValidas.add(lista);
        }
        
    }
    
    public void añadirAListasBooleanos(ArrayList<Integer> lista) {
        if(!lista.isEmpty()){
            listasBooleanos.add(lista);
        }
        
    }
    
    public void añadirAListasPosiciones(ArrayList<ArrayList<Integer>> lista) {
        if(!lista.isEmpty()){
            listasPosiciones.add(lista);
        }
        
    }
    
    public void añadirAListasNumAutomata(ArrayList<Integer> lista) {
        if(!lista.isEmpty()){
            listasNumAutomata.add(lista);
        }
        
    }
    
    public void añadirCadena(String c) {
        cadena=c;
        
    }
    

    
    public void verListas(){
        if(!listasBooleanos.isEmpty()){
            //para que no salte en el apartado 1
           encontrarErroresYOrganizarTokens();
            System.out.println(listasValidas);
            System.out.println(listasBooleanos);
            System.out.println(listasPosiciones);
            System.out.println(listasNumAutomata);
            System.out.println(errores);
            System.out.println(todoJunto); 
        }
        
    }
        
        
//    public void imprimirListas(){
//        if(!errores.isEmpty()){
//            for (char i:errores){
//                System.out.println("<'"+i+"', error>");
//            }
//            }
//        if(!validas.isEmpty()){
//            for (String i:validas){
//                System.out.println("<'"+i+"', Token>");
//            }
//        }
//    }
    
    
    public void encontrarErroresYOrganizarTokens(){
        //esta función es un intento de imprimir las cosas ordenadas pero sin hacer uso de
        //las listas de localizaciones ni palabras válidas previamente guardadas
        int sumador=0;
        String intermedia="";
        for(int i=0;i<listasBooleanos.get(0).size();i++){
            for(ArrayList<Integer> a:listasBooleanos){
                sumador+=a.get(i);
            } 
            switch (sumador) {
                case 0:
                    
                    if(intermedia!=""){
                        //caso de que intermedia haya sido rellenado, se añade, ya que será
                        //un token válido y se vuelve a vaciar para el siguiente
                        todoJunto.add(intermedia);
                        intermedia="";
                    }
                    else{
                        errores.add(cadena.charAt(i));
                        todoJunto.add(String.valueOf(cadena.charAt(i)));
                        posicionesErrores.add(i);
                    }
                    break;
                case 1:
                    intermedia+=cadena.charAt(i);
                    
                    break;
                default:
                    break;
            }
            sumador=0;
        }
        
    }
    
    
}
