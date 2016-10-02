/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Statistic;

/**
 *
 * @author toze
 */
public class TypeNumber implements Comparable<TypeNumber> {
    private Keys.Material tipo;
    private int numero;
    
    public TypeNumber(){
        this.tipo = new Keys.Material();
        this.numero = 0;
    }
    
    public TypeNumber(Keys.Material tipo, int numero){
        this.tipo = tipo;
        this.numero = numero;
    }

    /**
     * @return the tipo
     */
    public Keys.Material getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(Keys.Material tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the numero
     */
    public int getNumber() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumber(int numero) {
        this.numero = numero;
    }

    @Override
    public int compareTo(TypeNumber o) {
        return o.getNumber() - this.getNumber();
    }
}
