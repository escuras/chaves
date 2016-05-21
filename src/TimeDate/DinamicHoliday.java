/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TimeDate;

/**
 *
 * @author toze
 */
public class DinamicHoliday extends TimeDate.Holiday {

    String nome;

    public DinamicHoliday(TimeDate.Holiday hol, String nome) {
        super(hol);
        this.nome = nome;
    }

    public String getName() {
        return nome;
    }
}

