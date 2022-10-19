
package com.mycompany.automata;

import java.util.ArrayList;


public class ComprobadorTokens {
    
    private ArrayList<ArrayList<String>> listasValidas;
    private ArrayList<ArrayList<Integer>> listasBooleanos;
    private ArrayList<ArrayList<ArrayList<Integer>>> listasPosiciones;
    private ArrayList<ArrayList<Integer>> listasNumAutomata;
    private ArrayList<Integer> listasOrdenadasNumAutomata;
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
            definirPosicionesErrores();
            organizarTokens();
//            System.out.println(listasValidas);
//            System.out.println(listasPosiciones);
//            System.out.println(listasNumAutomata);
//            System.out.println(listasBooleanos);
//            System.out.println(errores);
//            System.out.println(posicionesErrores);
//            System.out.println(todoJunto); 
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
    
    
    public void organizarTokens(){
        for(int posicion=0;posicion<cadena.length();posicion++){
            boolean flag=false;
            int contador=0;
            for(int n: posicionesErrores){
                if(posicion==n && !flag){
                    todoJunto.add(String.valueOf(errores.get(contador)));
                    flag=true;
                }
                contador++;
            }
            
            if(!flag){
                int posArray1=0;
                
                for (ArrayList<ArrayList<Integer>> a: listasPosiciones){
                    int posArray2=0;
                    for (ArrayList<Integer> b: a){
                        
                        if(b.get(0)==posicion){
                            todoJunto.add(listasValidas.get(posArray1).get(posArray2));
                            listasOrdenadasNumAutomata.add(listasNumAutomata.get(posArray1).get(posArray2));
                            int desplazamiento=(listasPosiciones.get(posArray1).get(posArray2).get(1)-listasPosiciones.get(posArray1).get(posArray2).get(0));
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
                System.out.println("<"+s+", M"+listasOrdenadasNumAutomata.get(contadorBuenas)+">");
                contadorBuenas++;
            }
        }
    }
        
    
}
