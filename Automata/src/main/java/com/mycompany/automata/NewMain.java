package com.mycompany.automata;

/**
 *
 * @author Jaime
 */
public class NewMain {

    
    
    public static void main(String[] args) {
        
        ComprobadorTokens c =new ComprobadorTokens();

        Automata a1= new Automata(0,17,"M0","apartado1.jff");
        Maquina m_1 = new Maquina(a1,c);

        Automata a_leer= new Automata(1,8,"M1","leer.jff");
        Maquina m_leer = new Maquina(a_leer,c);
        
        Automata a_real= new Automata(2,8,"M2","real.jff");
        Maquina m_real = new Maquina(a_real,c);
        
        Automata a_hacer= new Automata(3,10,"M3","hacer.jff");
        Maquina m_hacer = new Maquina(a_hacer,c);
        
        Automata a_string= new Automata(4,3,"STRING","strconespacio.jff");
        Maquina m_string = new Maquina(a_string,c);
        
        Automata a_numeric= new Automata(5,41,"NUMERIC","numeric.jff");
        Maquina m_numeric = new Maquina(a_numeric,c);
        
        Automata a_boolean= new Automata(6,28,"BOOLEAN","boolean.jff");
        Maquina m_boolean = new Maquina(a_boolean,c);
        
        
        
        // comillas para el apartado 3: “ o ’
        //lo números decimales van con punto. ej: 5.6
        
        // aabcmbcn     abobm  abpbm   aabcccmbqccp (falsa)
        String cadena1="aabcccmbqccp";
        String cadena2="REalhaaahacerhacEr 12aaaleerbb";
        String cadena3="’arbol    ajaja’877868aaacc3.5falso";
 
       
//        m_1.direccionarApartado(cadena1);


//        m_leer.direccionarApartado(cadena2);
//        m_real.direccionarApartado(cadena2);
//        m_hacer.direccionarApartado(cadena2);

        
//        m_string.direccionarApartado(cadena3);
//        m_numeric.direccionarApartado(cadena3);
//        m_boolean.direccionarApartado(cadena3);
        
        
        c.verListas();
        

        
    
    }
}
