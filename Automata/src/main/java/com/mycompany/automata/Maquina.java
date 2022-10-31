
package com.mycompany.automata;

import java.util.ArrayList;

/**
 *
 * @author Jaime
 */
public class Maquina {
    private int estadoActual;
    private Automata afd;
    private ArrayList<String> validas; // array con las palabras válidas detectadas
    private ArrayList<Integer> listaBooleanos; //por cada caracter, guardará en orden un 0 si no lo detecta y un 1 si forma parte de una palabra válida
    private boolean enviadas;
    private ComprobadorTokens comp;
    private ArrayList<String> listaNombresAut; //cada vez que esta máquina detecte una palabra válida, guardará en la lista el nombre del autómata que la detectó
    private ArrayList<ArrayList<Integer>> listaPosChar; // se guardarán listas con la posición del primer y último caracter de la palabra detectada como válida.
    private String nombreAut; 
    private int posicionChar;
    private String cadenaM; //para hacer una copia de la cadena y pasarsela a la clase ComprobadorTokens
    private ArrayList<Character> alfabeto; //array con todas las letras del alfabeto de la ER que puede detectar.

    public Maquina(Automata afd, ComprobadorTokens comp) {
        this.afd=afd;
        afd.inicializadorDeTodo();
        this.comp=comp;
        validas= new ArrayList<>();
        listaBooleanos= new ArrayList<>();
        listaNombresAut= new ArrayList<>();
        listaPosChar= new ArrayList<>();
        enviadas=false;
        posicionChar=0;
        nombreAut=afd.getNombreAutomata();
        cadenaM=null;
        alfabeto= afd.getAlfabeto();
        
    }
    
    
    public void  inicializar(){
        //reinicia el estado actual para poder volver a empezar a detectar tokens
        estadoActual=afd.getEstadoInicial();
    }
    
    public boolean isFinal(){
        return afd.isFinal(estadoActual);
    }
    
    public boolean acepta(Character caracter, boolean bool){
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
        int contador=0;
        int contador_errores=0;
        ArrayList<Integer> intermedio= new ArrayList<>();
        ArrayList<Integer> copia;
        
        if(cadena.length()>0){
            for(char c:cadena.toCharArray()){
                if(this.acepta(c,true)){contador++;}

                else{
                    for(char x:((cadena.substring(0, contador)).toCharArray())){
                        contador_errores++;
                        posicionChar++;
                        listaBooleanos.add(0);
                    }
                    
                    preparacionCadena(cadena.substring(contador_errores, cadena.length()),false);
                    return;
                }
                
                if (isFinal()&& !acepta(cadena.toCharArray()[contador],false)){ //para que no me de index out of range aqui, añadi el $
                    validas.add(cadena.substring(0, contador));
                    listaNombresAut.add(nombreAut);
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
                    preparacionCadena(cadena.substring(contador, cadena.length()),false);
                    break;
                }
            }     
        }
        
        
        if (!enviadas){
            enviarListas();
            enviadas=true;
        }

    }
    
    
    
    public void preparacionCadena(String cadena, boolean primeraVez){
        //esta función será la encargada de detectar la gran mayoría de caracteres no válidos.
        //si encuentra algún caracter válido, lo delega en compruebaCadenaBuena
        if (primeraVez) {
            // la primera vez que llamamos a la función, le añado un signo que no vaya a detectar ningun automata
            //al final de la cadena para que si intento comprobar si el siguiente caracter es valido y la cadena se ha acabado ya,
            //no me salte error, esto es para el if de la linea 100
            cadena=cadena+"$";
            primeraVez=false;
        }
        inicializar();
        if(cadenaM==null){
            cadenaM=cadena; //para pasarle luego la cadena a la clase ComprobadorTokens
        }
        
        if(cadena.length()>0){
            if(this.acepta(cadena.toCharArray()[0],true)){compruebaCadenaBuena(cadena);}
            else{
                if(!"$".equals(cadena)){
                    posicionChar++;
                    listaBooleanos.add(0);
                    if (cadena.length()>1){
                        preparacionCadena(cadena.substring(1, cadena.length()),false);
                    }
                }else{
                    if (!enviadas){
                        enviarListas();
                        enviadas=true;
                    }
                }
                
            }        
        }   
    }
    
    
    public void enviarListas(){
        comp.añadirAListasValidas(validas);
        comp.añadirAListasBooleanos(listaBooleanos);
        comp.añadirAListasNombreAutomata(listaNombresAut);
        comp.añadirAListasPosiciones(listaPosChar);
        comp.añadirCadena(cadenaM);
        comp.añadirAlfabeto(alfabeto);
        
    }
    
    public boolean apartadoUnoCompruebaCadena(String cadena){
        //comprueba si la cadena completa del apartado 1 es válida y saca por pantalla este bool
        inicializar();
        int longitud=0;
        boolean valida=false;
            
            for(char c:cadena.toCharArray()){
                longitud++;
                if(!this.acepta(c,true)){
                    break;
                }
                
                if(longitud==cadena.length() && isFinal() ){
                    valida=true;
                }
            }
            
            System.out.println(valida);
            return valida;
}
    
    
    
    public void direccionarApartado(String cadena){
        //en este método redireccionaremos la cadena a una función u otra
        //dependiendo del apartado del ejercicio 
        switch (afd.getModo()) {
                case 0 -> { 
                    apartadoUnoCompruebaCadena(cadena);
                    preparacionCadena(cadena,true);
            }
                    
                default -> preparacionCadena(cadena,true);
            }
    }
    
}