package com.mycompany.automata;

/**
 *
 * @author Jaime
 */
public class NewMain {

    
    
    public static void main(String[] args) {
        
        String cadena="REalhaaahacerhacEr aaaleer";

        
        ComprobadorTokens c =new ComprobadorTokens();

        Automata a1= new Automata(0,17,0);
        Maquina m_1 = new Maquina(a1,c);

        Automata a_leer= new Automata(1,8,1);
        Maquina m_leer = new Maquina(a_leer,c);
        
        
        Automata a_real= new Automata(2,8,2);
        Maquina m_real = new Maquina(a_real,c);
        
        
        Automata a_hacer= new Automata(3,10,3);
        Maquina m_hacer = new Maquina(a_hacer,c);
        
        
        m_1.direccionarApartado("abmbo");
        m_leer.direccionarApartado(cadena);
        m_real.direccionarApartado(cadena);
        m_hacer.direccionarApartado(cadena);
        
        
        c.verListas();
        
        
        
    
    }
}
