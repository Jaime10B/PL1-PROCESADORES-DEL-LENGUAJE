package com.mycompany.automata;

/**
 *
 * @author Jaime
 */
public class NewMain {

    
    
    public static void main(String[] args) {
        
//        String cadena="REalhaaahacerhacEr 12aaaleer2";
        String cadena="âverdaderoâ6666.45aaaa";

        
        ComprobadorTokens c =new ComprobadorTokens();

        Automata a1= new Automata(1,17,"M0","apartado1.jff");
        Maquina m_1 = new Maquina(a1,c);

        Automata a_leer= new Automata(1,8,"M1","leer.jff");
        Maquina m_leer = new Maquina(a_leer,c);
        
        
        Automata a_real= new Automata(1,8,"M2","real.jff");
        Maquina m_real = new Maquina(a_real,c);
        
        
        Automata a_hacer= new Automata(1,10,"M3","hacer.jff");
        Maquina m_hacer = new Maquina(a_hacer,c);
        
        
        Automata a_string= new Automata(1,3,"STRING","str.jff");
        Maquina m_string = new Maquina(a_string,c);
        
        Automata a_numeric= new Automata(1,41,"NUMERIC","numeric.jff");
        Maquina m_numeric = new Maquina(a_numeric,c);
        
        Automata a_boolean= new Automata(1,28,"BOOLEAN","boolean.jff");
        Maquina m_boolean = new Maquina(a_boolean,c);

        
        
//        m_1.direccionarApartado("abmbo");
//        m_leer.direccionarApartado(cadena);
//        m_real.direccionarApartado(cadena);
//        m_hacer.direccionarApartado(cadena);

        
        m_string.direccionarApartado(cadena);
        m_numeric.direccionarApartado(cadena);
        m_boolean.direccionarApartado(cadena);

        
        
        c.verListas();
        

        
    
    }
}
