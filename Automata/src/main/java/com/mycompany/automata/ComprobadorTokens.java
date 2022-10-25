
package com.mycompany.automata;

import java.util.ArrayList;
import java.util.Collections;


public class ComprobadorTokens {
    
    private ArrayList<ArrayList<String>> listasValidas;
    private ArrayList<ArrayList<Integer>> listasBooleanos;
    private ArrayList<ArrayList<ArrayList<Integer>>> listasPosicionesValidas;
    private ArrayList<ArrayList<String>> listasNumAutomata;
    private ArrayList<String> listasOrdenadasNumAutomata;
    private ArrayList<Character> errores;
    private ArrayList<Integer> posicionesErrores;
    private ArrayList<String> validas;
    private String cadena;
    private ArrayList<String> todoJunto;

    public ComprobadorTokens() {
        listasValidas= new ArrayList<>();
        listasBooleanos= new ArrayList<>();
        listasPosicionesValidas= new ArrayList<>();
        listasNumAutomata= new ArrayList<>();
        listasOrdenadasNumAutomata= new ArrayList<>();
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
            listasPosicionesValidas.add(lista);
        }
        
    }
    
    public void añadirAListasNumAutomata(ArrayList<String> lista) {
        if(!lista.isEmpty()){
            listasNumAutomata.add(lista);
        }
        
    }
    
    public void añadirCadena(String c) {
        cadena=c;
        
    }
    

    
    public void verListas(){
        if(!listasBooleanos.isEmpty()){
            definirPosicionesErrores();
            detectarOverlapping();
            organizarTokens();
//            System.out.println("lista validas "+listasValidas);
//            System.out.println("lista posiciones validas "+listasPosicionesValidas);
//            System.out.println("lista numAutomata "+listasNumAutomata);
//            System.out.println("lista booleanos "+listasBooleanos);
//            System.out.println("lista errores "+errores);
//            System.out.println("lista posiciones errores "+posicionesErrores);
//            System.out.println("lista todo junto "+todoJunto); 
//            System.out.println(listasOrdenadasNumAutomata);
            imprimir();
        }
        
    }
    
    
    
    public void definirPosicionesErrores(){
        int sumador=0;
        for(int i=0;i<listasBooleanos.get(0).size();i++){
            for(ArrayList<Integer> a:listasBooleanos){
                sumador+=a.get(i);
            } 
          
            if (sumador==0) {
                errores.add(cadena.charAt(i));
                posicionesErrores.add(i);
            }
            sumador=0;
        
        }
    }
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void detectarOverlapping(){
        //miro a ver si hay alguna cadena válida que empieza en el mismo punto que otra
        //si esto ocurre llamaré a otros métodos para detectar cuál es la más corta y borrarla
        ArrayList<Integer> posComienzo= new ArrayList<>() ;
        ArrayList<Integer> posFinal= new ArrayList<>() ;
    
        for (ArrayList<ArrayList<Integer>> a: listasPosicionesValidas){
                    for (ArrayList<Integer> b: a){
                        //comprobamos si la posición de inicio ya está en la lista, en cuyo caso deberemos actuar
                        if(posComienzo.indexOf(b.get(0))!=-1){
                            borrarOverlaped(b.get(0),buscarYDeterminarLongitud(posComienzo,posFinal,b.get(0)));
                        }
                        //guardamos la posición de inicio y fin en listas
                        posComienzo.add(b.get(0));
                        posFinal.add(b.get(1));
                        
                    }
        }
    }
    
    
    
    public int buscarYDeterminarLongitud(ArrayList<Integer> posComienzo,ArrayList<Integer> posFinal, int posBuscada){
        //hacemos una lista solo con las posiciones finales de los que buscamos que inician a la vez
        //para asi posteriormente ver cual es el más largo de la lista
        ArrayList<Integer> finalesBuscados= new ArrayList<>() ;
        int contador=0;
        int masLargo;
        for(int i:posComienzo){
            if(i==posBuscada){
                finalesBuscados.add(posFinal.get(contador));
            }
            contador++;
        }
        
        masLargo=finalesBuscados.indexOf(Collections.max(finalesBuscados));
        return masLargo;
        
    }
    
    
    public void borrarOverlaped(int posInicialBuscada,int posFinalBuena){
        //una vez sabido la posición inicial del elemento que queremos borrar y la inicial y final del
        //que queremos conservar, borramos todos los que inicien en esa posición y la pos final sea menor
        int posArray1=0;
    
        for (ArrayList<ArrayList<Integer>> a: listasPosicionesValidas){
                    int posArray2=0;
                    for (ArrayList<Integer> b: a){
                        
                        if(b.get(0)==posInicialBuscada && b.get(1)<posFinalBuena){
                            listasValidas.get(posArray1).remove(posArray2);
                            listasPosicionesValidas.get(posArray1).remove(posArray2);
                        }
                        posArray2++;
                    }
            posArray1++;
        }
    }
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////   
    
    
    public void organizarTokens(){
        //pone en una lista, todos los tokens en orden de aparicion según la cadena original
        for(int posicion=0;posicion<cadena.length();posicion++){
            boolean flag=false;
            int contador=0;
            for(int n: posicionesErrores){
                if(posicion==n && !flag){
                    //si el token es un error
                    todoJunto.add(String.valueOf(errores.get(contador)));
                    flag=true; //hemos encontrado el token de la posicion querida
                }
                contador++;
            }
            
            if(!flag){
                //miramos en las listas de tokens validos
                int posArray1=0;
                
                for (ArrayList<ArrayList<Integer>> a: listasPosicionesValidas){
                    int posArray2=0;
                    for (ArrayList<Integer> b: a){
                        
                        if(b.get(0)==posicion){
                            todoJunto.add(listasValidas.get(posArray1).get(posArray2));
                            listasOrdenadasNumAutomata.add(listasNumAutomata.get(posArray1).get(posArray2));
                            int desplazamiento=(listasPosicionesValidas.get(posArray1).get(posArray2).get(1)-listasPosicionesValidas.get(posArray1).get(posArray2).get(0));
                            posicion=posicion+desplazamiento; //le sumamos a i, el número de caracteres de la palabra aceptada
                        }
                        posArray2++;
                    }
                    posArray1++;
                }
            }
        }
    }
    
    
    public void imprimir(){
        int contadorBuenas=0;
        for(String s: todoJunto){
            if(s.length()==1){
                System.out.println("<"+s+", ERROR>");
            }
            else{
                System.out.println("<"+s+", "+listasOrdenadasNumAutomata.get(contadorBuenas)+">");
                contadorBuenas++;
            }
        }
    }
        
    
}
