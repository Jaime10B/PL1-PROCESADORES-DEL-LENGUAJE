
package com.mycompany.automata;

import java.util.ArrayList;

/**
 *
 * @author Jaime
 */
public class Maquina {
    private int estadoActual;
    private Automata afd;
    private ArrayList<String> validas;
    private ArrayList<Integer> listaBooleanos;
    private boolean enviadas;
    private ComprobadorTokens comp;
    private ArrayList<Integer> listaNumAut;
    private ArrayList<ArrayList<Integer>> listaPosChar;

    private int numAut;
    private int posicionChar;
    private String cadenaM;

    public Maquina(Automata afd, ComprobadorTokens comp) {
        this.afd=afd;
        afd.inicializadorDeTodo();
        this.comp=comp;
        validas= new ArrayList<>();
        listaBooleanos= new ArrayList<>();
        listaNumAut= new ArrayList<>();
        listaPosChar= new ArrayList<>();
        enviadas=false;
        posicionChar=0;
        numAut=afd.getNumAutomata();
        cadenaM=null;
    }
    
    
    public void  inicializar(){
        //reinicia el estado actual para poder volver a empezar a detectar tokens
        estadoActual=afd.getEstadoInicial();
    }
    
    public boolean isFinal(){
        return afd.isFinal(estadoActual);
    }
    
    public boolean acepta(char caracter, boolean bool){
        //devuelve un booleano que nos indica si el caracter recibido por parámetro
        //es válido y coherente con lo anterior
        // tiene dos modos, si bool es false tan sólo comprueba si el siguiente caracter
        // sería congruente con la cadena, pero si bool es true, ademas en caso de ser congruente
        //cambia el estado actual ya que reconoce el caracter
        Integer estadoTemporal= afd.getSiguienteEstado(caracter, estadoActual);
        
        if(bool){
            if(estadoTemporal!=null){
                estadoActual=estadoTemporal;
                return true;
            }else{
                return false;
            }
        }
        else{
            return estadoTemporal!=null;
        }
        
    }

    public void compruebaCadenaBuena(String cadena){
        //esta función es llamada cuando se encuentra un caracter que es válido, para seguir
        //comprobando los consecutivos y dictaminar si encuentra un token válido.
        //si en algún momento encuentra un caracter erróneo, delega en preparacionCadena
        //También envía las listas a la clase ComprobadorTokens, una vez que estas están ya completas.
        inicializar();
        boolean valida=false;
        int contador=0;
        int contador_errores=0;
        ArrayList<Integer> intermedio= new ArrayList<>();
        ArrayList<Integer> copia;
        
        if(cadena.length()>0){
            for(char c:cadena.toCharArray()){
                if(this.acepta(c,true)){contador++;}

                else{
                    if(!valida){
                        for(char x:((cadena.substring(0, contador)).toCharArray())){
                            contador_errores++;
                            posicionChar++;
                            listaBooleanos.add(0);
                        }
                    }
                    preparacionCadena(cadena.substring(contador_errores, cadena.length()));
                    return;
                }
                
                if (isFinal()){
                    validas.add(cadena.substring(0, contador));
                    listaNumAut.add(numAut);
                    intermedio.add(posicionChar);
                    for(int i=0;i<contador;i++){
                        posicionChar++;
                        listaBooleanos.add(1);
                    }
                    //añado a (intermedio) la posicion del primer y ultimo caracter de la cadena valida
                    //y para guardarlo en una lista de listas (listaPosChar), tengo que hacer una copia, meter la copia
                    //y luego borrar los elementos de la lista intermedia
                    intermedio.add(posicionChar-1);
                    copia=(ArrayList<Integer>) intermedio.clone();
                    listaPosChar.add(copia);
                    intermedio.remove(0);
                    intermedio.remove(0);
                    valida=true;
                }
                
                
                if(valida && contador<cadena.length() && contador<cadena.length() && !acepta(cadena.toCharArray()[contador],false) ){
                    preparacionCadena(cadena.substring(contador, cadena.length()));
                    break;
                } 
                
            }     
        }
        
        if ((contador>0)&&(!valida)){
         //Para el caso de que al final de la cadena acepte un par de letras pero no lleguen a formar ningun token 
        //válido, como no ha fallado, no las añadiría a errores sin este if
        
            for(char x:((cadena.substring(0, contador)).toCharArray())){
                contador_errores++;
                posicionChar++;
                listaBooleanos.add(0);
            }
        }
        
        if (!enviadas){
            enviarListas();
            enviadas=true;
        }

    }
    
    
    
    public void preparacionCadena(String cadena){
        //esta función será la encargada de detectar la gran mayoría de caracteres no válidos.
        //si encuentra algún caracter válido, lo delega en compruebaCadenaBuena
        
        inicializar();
        if(cadenaM==null){
            cadenaM=cadena; //para pasarle luego la cadena a la clase ComprobadorTokens
        }
        
        if(cadena.length()>0){
            if(this.acepta(cadena.toCharArray()[0],true)){compruebaCadenaBuena(cadena);}
            else{
                posicionChar++;
                listaBooleanos.add(0);
                if (cadena.length()>1){
                    preparacionCadena(cadena.substring(1, cadena.length()));
                }
            }        
        }   
    }
    
    
    public void enviarListas(){
        comp.añadirAListasValidas(validas);
        comp.añadirAListasBooleanos(listaBooleanos);
        comp.añadirAListasNumAutomata(listaNumAut);
        comp.añadirAListasPosiciones(listaPosChar);
        comp.añadirCadena(cadenaM);
        
    }
    
    public boolean apartadoUnoCompruebaCadena(String cadena){
        //comprueba si la cadena completa del apartado 1 es válida y saca por pantalla este bool
        inicializar();
        int longitud=0;
        boolean valida=false;
            
            for(char c:cadena.toCharArray()){
                longitud++;
                this.acepta(c,true);
                
                if(longitud==cadena.length() && isFinal() ){
                    valida=true;
                }
            }
            
            System.out.println(valida);
            return valida;
}
    
    
    
    public void direccionarApartado(String cadena){
        //en este método redireccionaremos la cadena a una función u otra
        //dependiendo del apartado del ejercicio para el que la cadena sea válida
        switch (afd.getModo()) {
                case 0 -> apartadoUnoCompruebaCadena(cadena);
                case 1,2,3 -> preparacionCadena(cadena);
                //case 4 -> ;//aun no he llegado
            }
    }
    
}