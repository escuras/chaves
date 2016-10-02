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
public class UserNumberMaterial implements Comparable<UserNumberMaterial> {
    private Keys.Person pessoa;
    private Keys.TypeOfMaterial tipo;
    private int numero;
    
    public UserNumberMaterial(){
        this.pessoa = new Keys.Person();
        this.numero = 0;
        this.tipo = new Keys.TypeOfMaterial();
    }
    
    public UserNumberMaterial(Keys.Person user, Keys.TypeOfMaterial tipo, int numero){
        this.pessoa = user;
        this.numero = numero;
        this.tipo = tipo;
    }

    public Keys.Person getPerson() {
        return pessoa;
    }

    public void setPerson(Keys.Person pessoa) {
        this.pessoa = pessoa;
    }

    public int getNumber() {
        return numero;
    }

  
    public void setNumber(int numero) {
        this.numero = numero;
    }

    @Override
    public int compareTo(UserNumberMaterial o) {
        return o.getNumber() - this.getNumber();
    }

    /**
     * @return the tipo
     */
    public Keys.TypeOfMaterial getType() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setType(Keys.TypeOfMaterial tipo) {
        this.tipo = tipo;
    }

}
