
package com.mycompany.automata;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Jaime
 */
public class Automata {

    private ArrayList<Character> alfabeto;
    private ArrayList<Integer> estados;
    private int estadoInicial;
    private ArrayList<Integer> estadosFinales;
    private HashMap<Integer,HashMap<Character,Integer>> matriz;
    private int modo;
    private Matriz mz;
    private int numEstados;
    private int numAutomata;
    private String dir;

    public Automata(int modo, int nE, int numAut, String dir) {
        mz = new Matriz(dir);
        estadosFinales = mz.getEstadosFinales();
        estados= new ArrayList<>();
        alfabeto= new ArrayList<>();
        matriz= mz.getMatriz();
        estadoInicial = 0;
        this.modo=modo;
        this.numEstados=nE;
        this.numAutomata=numAut;
        this.dir=dir;

    }

    public int getNumAutomata() {
        return numAutomata;
    }

    public int getModo() {
        return modo;
    }
       
    
    public void cargarAlfabeto(){
        for (char c='a';c<='z';c++){
            alfabeto.add(c);
        }
    }
    
    
    public void cargarEstados(){
        for( int i=0; i<numEstados;i++){
            this.estados.add(i);
        }
    }
    
    
    public void establecerQi(int z){
        estadoInicial=z;
    }
    
    
    public Integer getSiguienteEstado(Character caracter, int estado){
        return this.matriz.get(estado).get(caracter);
    }
    
    public Integer getEstadoInicial(){
        return(this.estadoInicial);
    }
    
    public boolean isFinal(int estado){
        return this.estadosFinales.contains(estado);
    }
    
    
    public void iniciarMatriz(){
        for (int i=0; i<=estados.size();i++){
            HashMap<Character,Integer> h=new HashMap<>();
            matriz.put(i, h);
        }
    }
    
    
    public void inicializadorDeTodo(){
        establecerQi(estadoInicial);
        cargarAlfabeto();
        cargarEstados();
        iniciarMatriz();
        mz.eleccionDeModo(modo); //cargamos la matriz y los estados finales
    }
    
}
