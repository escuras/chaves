/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Keys;

/**
 *
 * @author toze
 */
public class Software implements Comparable<Software>{
    private String nome;
    private String versao;
    private String ano;
    private String empresa;

    public Software(){
        nome = "";
        versao = "";
        ano =  "";
        empresa = "";
    }
    
    
    public Software(String nome, String versao, String ano, String empresa){
        this.nome = nome;
        this.versao = versao;
        this.ano = ano;
        this.empresa = empresa;
    }
    
    public Software(Software soft) {
        this.nome = soft.getName();
        this.versao = soft.getVersion();
        this.ano = soft.getYear();
        this.empresa = soft.getInterprise();
    }
    
    
    /**
     * @return the nome
     */
    public String getName() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setName(String nome) {
        this.nome = nome;
    }

    /**
     * @return the versao
     */
    public String getVersion() {
        return versao;
    }

    /**
     * @param versao the versao to set
     */
    public void setVersion(String versao) {
        this.versao = versao;
    }

   
    @Override
    public int compareTo(Software o) {
        int val;
        if((val = this.getName().toLowerCase().compareTo(o.getName().toLowerCase())) == 0) {
            if ((val = this.getVersion().toLowerCase().compareTo(o.getVersion().toLowerCase())) == 0) {
                return this.getInterprise().toLowerCase().compareTo(o.getInterprise().toLowerCase());
            }
        } 
        return val;
    }

    /**
     * @return the ano
     */
    public String getYear() {
        return ano;
    }

    /**
     * @param ano the ano to set
     */
    public void setYear(String ano) {
        this.ano = ano;
    }

    /**
     * @return the empresa
     */
    public String getInterprise() {
        return empresa;
    }

    /**
     * @param empresa the empresa to set
     */
    public void setInterprise(String empresa) {
        this.empresa = empresa;
    }
}
