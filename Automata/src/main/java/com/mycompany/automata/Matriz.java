package com.mycompany.automata;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Jaime
 */
public class Matriz {
    
    int modo;
        public ArrayList<Integer> estadosFinales;
        public HashMap<Integer,HashMap<Character,Integer>> matriz;
        

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
    
    
    public void eleccionDeModo(int modo){
        //dependiendo del apartado en que nos encontremos y los que queramos 
        //que pueda leer nuestro , cargaremos una matriz u otra
        switch(modo){
            case 0:
                cargarMatriz1();
                establecerQf1();
                break;
            case 1:
                cargarMatrizLeer();
                establecerQfLeer();
                break;
            case 2:
                cargarMatrizReal();
                establecerQfReal();
                break;
            case 3:
                cargarMatrizHacer();
                establecerQfHacer();
                break;
            
        }
    }

    
    
    
    
    public void cargarMatriz1(){
        matriz.get(0).put('a',1);
        matriz.get(1).put('a',2);
        matriz.get(1).put('b',3);
        matriz.get(2).put('a',2);
        matriz.get(2).put('b',3);
        matriz.get(3).put('c',6);
        matriz.get(3).put('m',7);
        matriz.get(3).put('n',4);
        matriz.get(3).put('o',5);
        matriz.get(3).put('p',9);
        matriz.get(3).put('q',8);
        matriz.get(4).put('b',10);
        matriz.get(5).put('b',10);
        matriz.get(6).put('c',6);
        matriz.get(6).put('m',7);
        matriz.get(6).put('n',4);
        matriz.get(6).put('o',5);
        matriz.get(6).put('p',9);
        matriz.get(6).put('q',8);
        matriz.get(7).put('b',10);
        matriz.get(8).put('b',10);
        matriz.get(9).put('b',10);
        matriz.get(10).put('c',13);
        matriz.get(10).put('m',14);
        matriz.get(10).put('n',11);
        matriz.get(10).put('o',12);
        matriz.get(10).put('p',16);
        matriz.get(10).put('q',15);
        matriz.get(11).put('b',10);
        matriz.get(12).put('b',10);
        matriz.get(13).put('c',13);
        matriz.get(13).put('m',14);
        matriz.get(13).put('n',11);
        matriz.get(13).put('o',12);
        matriz.get(13).put('p',16);
        matriz.get(13).put('q',15);
        matriz.get(14).put('b',10);
        matriz.get(15).put('b',10);
        matriz.get(16).put('b',10);
    }
        
        
    public void establecerQf1(){
        estadosFinales.add(4);
        estadosFinales.add(5);
        estadosFinales.add(7);
        estadosFinales.add(8);
        estadosFinales.add(9);
        estadosFinales.add(11);
        estadosFinales.add(12);
        estadosFinales.add(14);
        estadosFinales.add(15);
        estadosFinales.add(16);
    }
    
    
    public void cargarMatrizLeer(){
        matriz.get(0).put('l',1);
        matriz.get(0).put('L',2);
        matriz.get(1).put('e',4);
        matriz.get(1).put('E',3);
        matriz.get(2).put('e',4);
        matriz.get(2).put('E',3);
        matriz.get(3).put('e',6);
        matriz.get(3).put('E',5);
        matriz.get(4).put('e',6);
        matriz.get(4).put('E',5);
        matriz.get(5).put('r',7);
        matriz.get(5).put('R',8);
        matriz.get(6).put('r',7);
        matriz.get(6).put('R',8);
    }
    
    public void establecerQfLeer(){
        estadosFinales.add(7);
        estadosFinales.add(8);
    }
    
    public void cargarMatrizReal(){
        matriz.get(0).put('r',1);
        matriz.get(0).put('R',2);
        matriz.get(1).put('e',4);
        matriz.get(1).put('E',3);
        matriz.get(2).put('e',4);
        matriz.get(2).put('E',3);
        matriz.get(3).put('a',5);
        matriz.get(3).put('A',6);
        matriz.get(4).put('a',5);
        matriz.get(4).put('A',6);
        matriz.get(5).put('l',7);
        matriz.get(5).put('L',8);
        matriz.get(6).put('l',7);
        matriz.get(6).put('L',8);
    }
    
    public void establecerQfReal(){
        estadosFinales.add(7);
        estadosFinales.add(8);
    }
    
    public void cargarMatrizHacer(){
        matriz.get(0).put('h',2);
        matriz.get(0).put('H',1);
        matriz.get(1).put('a',3);
        matriz.get(1).put('A',4);
        matriz.get(2).put('a',3);
        matriz.get(2).put('A',4);
        matriz.get(3).put('c',6);
        matriz.get(3).put('C',5);
        matriz.get(4).put('c',6);
        matriz.get(4).put('C',5);
        matriz.get(5).put('e',7);
        matriz.get(5).put('E',8);
        matriz.get(6).put('e',7);
        matriz.get(6).put('E',8);
        matriz.get(7).put('r',10);
        matriz.get(7).put('R',9);
        matriz.get(8).put('r',10);
        matriz.get(8).put('R',9);
    }
    
    public void establecerQfHacer(){
        estadosFinales.add(9);
        estadosFinales.add(10);
    }
    
    
}
