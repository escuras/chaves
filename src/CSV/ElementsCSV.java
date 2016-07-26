package CSV;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author toze
 */
public class ElementsCSV  {
    private int diasemana;
    private int horaIni;
    private int minutoIni;
    private int horaFim;
    private int minutoFim;
    private String nomeperson;
    private String cdperson;
    private String nomedisciplina;
    private String atividade;
    private String cddisciplina;
    private String material;
    private String cdmaterial;
    private String cdturma;
    
    
    public ElementsCSV(){
        diasemana = 0;
        horaIni = 0;
        minutoIni = 0;
        horaFim = 0;
        minutoFim = 0;
        nomeperson = "";
        cdperson = "";
        nomedisciplina = "";
        cddisciplina = "";
        material = "";
        cdmaterial = "";
        atividade = "";
        cdturma = "";
    }
    
    public ElementsCSV(int diasemana, int horaIni, int horaFim, int minutoIni, int minutoFim, String nomeperson, String cdperson, String nomedisciplina, String cddisciplina, String material, String cdmaterial, String atividade,String cdturma){
        this.diasemana = diasemana;
        this.horaIni = horaIni;
        this.minutoIni = minutoIni;
        this.horaFim = horaFim;
        this.minutoFim = minutoFim;
        this.nomeperson = nomeperson;
        this.cdperson = cdperson;
        this.nomedisciplina = nomedisciplina;
        this.cddisciplina = cddisciplina;
        this.material = material;
        this.cdmaterial = cdmaterial;
        this.atividade = atividade;
        this.cdturma = cdturma;
    }
    
    public ElementsCSV(ElementsCSV csv)
    {
        this.diasemana = csv.getDayWeek();
        this.horaIni = csv.getHourIni();
        this.minutoIni = csv.getMinuteIni();
        this.horaFim = csv.getHourEnd();
        this.minutoFim = csv.getMinuteEnd();
        this.nomeperson = csv.getPersonName();
        this.cdperson = csv.getPersonCode();
        this.nomedisciplina = csv.getSubjectName();
        this.cddisciplina = csv.getCdSubject();
        this.material = csv.getMaterialDescription();
        this.cdmaterial = csv.getMaterialCode();
        this.atividade = csv.getActivity();
        this.cdturma = csv.getClassCode();
    }
   

    /**
     * @return the diasemana
     */
    public int getDayWeek() 
    {
        return this.diasemana;
    }

    /**
     * @param diasemana the diasemana to set
     */
    public void setDayWeek(int diasemana) {
        this.diasemana = diasemana;
    }

    /**
     * @return the horaIni
     */
    public int getHourIni() {
        return horaIni;
    }

    /**
     * @param horaIni the horaIni to set
     */
    public void setHourIni(int horaIni) {
        this.horaIni = horaIni;
    }

    /**
     * @return the minutoIni
     */
    public int getMinuteIni() {
        return minutoIni;
    }

    /**
     * @param minutoIni the minutoIni to set
     */
    public void setMinuteIni(int minutoIni) {
        this.minutoIni = minutoIni;
    }

    /**
     * @return the horaFim
     */
    public int getHourEnd() {
        return horaFim;
    }

    /**
     * @param horaFim the horaFim to set
     */
    public void setHourEnd(int horaFim) {
        this.horaFim = horaFim;
    }

    /**
     * @return the minutoFim
     */
    public int getMinuteEnd() {
        return minutoFim;
    }

    /**
     * @param minutoFim the minutoFim to set
     */
    public void setMinuteEnd(int minutoFim) {
        this.minutoFim = minutoFim;
    }

    /**
     * @return the nomeperson
     */
    public String getPersonName() {
        return nomeperson;
    }

    /**
     * @param nomeperson the nomeperson to set
     */
    public void setPersonName(String nomeperson) {
        this.nomeperson = nomeperson;
    }

    /**
     * @return the cddocente
     */
    public String getPersonCode() {
        return cdperson;
    }

    /**
     * @param cdperson the cddocente to set
     */
    public void setPersonCode(String cdperson) {
        this.cdperson = cdperson;
    }

    /**
     * @return the nomedisciplina
     */
    public String getSubjectName() {
        return nomedisciplina;
    }

    /**
     * @param nomedisciplina the nomedisciplina to set
     */
    public void setSubjectName(String nomedisciplina) {
        this.nomedisciplina = nomedisciplina;
    }

    /**
     * @return the cddisciplina
     */
    public String getCdSubject() {
        return cddisciplina;
    }

    /**
     * @param cddisciplina the cddisciplina to set
     */
    public void setCdSubject(String cddisciplina) {
        this.cddisciplina = cddisciplina;
    }

    /**
     * @return the sala
     */
    public String getMaterialDescription() {
        return material;
    }

    /**
     * @param material the sala to set
     */
    public void setMaterialDescription(String material) {
        this.material = material;
    }

    /**
     * @return the cdmaterial
     */
    public String getMaterialCode() {
        return cdmaterial;
    }

    /**
     * @param cdmaterial the cdmaterial to set
     */
    public void setCdClassRoom(String cdmaterial) {
        this.cdmaterial = cdmaterial;
    }

    /**
     * @return the atividade
     */
    public String getActivity() {
        return atividade;
    }

    /**
     * @param atividade the atividade to set
     */
    public void setActivity(String atividade) {
        this.atividade = atividade;
    }

    /**
     * @return the cdturma
     */
    public String getClassCode() {
        return cdturma;
    }

    /**
     * @param cdturma the cdturma to set
     */
    public void setClassCode(String cdturma) {
        this.cdturma = cdturma;
    }
}
