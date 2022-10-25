
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
    private String numAutomata;
    private String dir;

    public Automata(int modo, int nE, String numAut, String dir) {
        mz = new Matriz();
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

    public String getNumAutomata() {
        return numAutomata;
    }

    public int getModo() {
        return modo;
    }
       
    
    public void cargarAlfabeto(){
        
        
    switch (modo) { 
        case 0 -> alfabetoApartado1();
        case 1 -> alfabetoLeer();
        case 2 -> alfabetoReal();
        case 3 -> alfabetoHacer();
        case 4 -> alfabetoStr();
        case 5 -> alfabetoNumeric();
        case 6 -> alfabetoBooleano();
    }
        
    }
    
    
    public void alfabetoApartado1(){
        alfabeto.add('a');
        alfabeto.add('b');
        alfabeto.add('c');
        alfabeto.add('m');
        alfabeto.add('n');
        alfabeto.add('o');
        alfabeto.add('p');
        alfabeto.add('q');
    }
    
    public void alfabetoHacer(){
        alfabeto.add('a');
        alfabeto.add('h');
        alfabeto.add('c');
        alfabeto.add('e');
        alfabeto.add('r');
        alfabeto.add('H');
        alfabeto.add('A');
        alfabeto.add('C');
        alfabeto.add('E');
        alfabeto.add('R');
    }
    
    public void alfabetoReal(){
        alfabeto.add('r');
        alfabeto.add('e');
        alfabeto.add('a');
        alfabeto.add('l');
        alfabeto.add('E');
        alfabeto.add('L');
        alfabeto.add('A');
        alfabeto.add('R');
    }
    
    public void alfabetoLeer(){
        alfabeto.add('l');
        alfabeto.add('e');
        alfabeto.add('r');
        alfabeto.add('L');
        alfabeto.add('E');
        alfabeto.add('R');
    }
    
    public void alfabetoStr(){
        alfabeto.add('"');
        alfabeto.add('\'');
        for (char c='a';c<='z';c++){
            alfabeto.add(c);
        }
        for (char c='A';c<='Z';c++){
            alfabeto.add(c);
        }
    }
    
    public void alfabetoBooleano(){
        alfabeto.add('v');
        alfabeto.add('e');
        alfabeto.add('d');
        alfabeto.add('a');
        alfabeto.add('r');
        alfabeto.add('o');
        alfabeto.add('A');
        alfabeto.add('V');
        alfabeto.add('E');
        alfabeto.add('R');
        alfabeto.add('D');
        alfabeto.add('O');
        alfabeto.add('f');
        alfabeto.add('l');
        alfabeto.add('s');
        alfabeto.add('F');
        alfabeto.add('L');
        alfabeto.add('S');
    }
    
    public void alfabetoNumeric(){
        for (char c='0';c<='9';c++){
            alfabeto.add(c);
        }
        alfabeto.add('.');
    }
    
    

    public ArrayList<Character> getAlfabeto() {
        return alfabeto;
    }
    
    
    
    public void cargarEstados(){
        for( int i=0; i<=numEstados;i++){
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
        mz.cargarDatos(dir); //cargamos la matriz y los estados finales
        
    }
    
}
