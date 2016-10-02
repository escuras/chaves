/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Statistic;

public class UserNumber implements Comparable<UserNumber> {
    private Keys.Person pessoa;
    private int numero;
    
    public UserNumber(){
        this.pessoa = new Keys.Person();
        this.numero = 0;
    }
    
    public UserNumber(Keys.Person user, int numero){
        this.pessoa = user;
        this.numero = numero;
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
    public int compareTo(UserNumber o) {
        return o.getNumber() - this.getNumber();
    }
}
