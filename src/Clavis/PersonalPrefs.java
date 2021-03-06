/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clavis;

import java.util.prefs.Preferences;

/**
 *
 * @author toze
 */
public class PersonalPrefs extends KeyQuest {
    
    private static final long serialVersionUID =1L;
    
    public PersonalPrefs(){
        super();
        super.setFocusableWindowState(true);
        Preferences userPrefs = Preferences.userNodeForPackage(getClass());
        int x = userPrefs.getInt("x",100);
        int y = userPrefs.getInt("y",100);
        urlbduser = userPrefs.get("urlbduser", "");
        urlbdsenha = userPrefs.get("urlbdsenha", "");
        urlcsv = userPrefs.get("urlcsv", DEFAULT_URlCSV);
        urlbdaux = userPrefs.get("urlbdaux", DEFAULT_URlBD);
        String key = "umDois3.,mn/op##RTF%&45dcop90";
        String destino = Utils.CryptAES.decrypt(key, urlbdsenha);
        if (destino != null) {
             urlbd = "jdbc:mysql://"+urlbdaux+"?autoReconnect=false&useSSL=false&user="+urlbduser+"&password="+destino;
        } else {
            urlbd = "jdbc:mysql://"+urlbdaux+"?autoReconnect=false&useSSL=false&user="+urlbduser+"&password=";
        }
        int largura = userPrefs.getInt("width",1024);
        int altura = userPrefs.getInt("height",700);
        String lang = userPrefs.get("lingua", "pt_PT");
        int maximizada = userPrefs.getInt("maximizada", 0);
        tema = userPrefs.get("tema", "claro");
        tdivisor = userPrefs.getInt("userdivisor", 40);
        vista = userPrefs.getInt("vista", 0);
        bitpagina = userPrefs.getByteArray("bitarray", new byte[]{0,0});
        lingua = new Langs.Locale();
        lingua.setLocale(lang); 
        booleanBoxLanguage = userPrefs.getBoolean("booleanLanguage", true);
        scrollAtivo = userPrefs.getBoolean("scrollAtiv", true);
        int red = userPrefs.getInt("vermelho",this.COR_DIVISOR.getRed());
        int green = userPrefs.getInt("verde",this.COR_DIVISOR.getGreen());
        int blue = userPrefs.getInt("azul",this.COR_DIVISOR.getBlue());
        cordivisor = new java.awt.Color(red,green,blue);
        red = userPrefs.getInt("svermelho",177);
        green = userPrefs.getInt("sverde",184);
        blue = userPrefs.getInt("sazul",190);
        pesquisa = userPrefs.getInt("pesquisa", 0);
        opcaocor = userPrefs.getInt("opcaocompra", 0);
        systemColor = new Components.Color(red, green, blue);
        int idtipo = userPrefs.getInt("idtipo",1);
        String desctipo = userPrefs.get("desctipo","Sala de aula");
        int totaltipo = userPrefs.getInt("totaltipo",0);
        int livrestipo = userPrefs.getInt("livrestipo",0);
        String imagemtipo = userPrefs.get("imagemtipo","Sala");
        tipomaterial= new Keys.TypeOfMaterial(idtipo, desctipo, totaltipo, livrestipo, imagemtipo);
        init();
        caracteristicsJFrame();
        
        this.setPreferedSize(maximizada, x, y, largura, altura);
    }
    
    private void setPreferedSize(int max, int x, int y, int largura, int altura){
        this.setExtendedState(max);
        this.setBounds(x, y, largura, altura);
    }
    
    public void save(){
        Preferences userPrefs = Preferences.userNodeForPackage(getClass());
        userPrefs.putInt("x", getX());
        userPrefs.putInt("y", getY());
        userPrefs.putInt("width", this.getWidth());
        userPrefs.putInt("height", this.getHeight());
        userPrefs.putInt("maximizada", this.getExtendedState());
        userPrefs.put("lingua", lingua.getLocale());
        userPrefs.putInt("userdivisor", tdivisor);
        userPrefs.putBoolean("booleanLanguage", booleanBoxLanguage);
        userPrefs.putInt("vista", vista);
        userPrefs.put("tema", tema);
        userPrefs.putInt("opcaocor", opcaocor);
        userPrefs.putBoolean("scrollAtiv",scrollAtivo);
        userPrefs.putInt("vermelho",cordivisor.getRed());
        userPrefs.putInt("verde",cordivisor.getGreen());
        userPrefs.putInt("azul",cordivisor.getBlue());
        userPrefs.putInt("svermelho",systemColor.getRed());
        userPrefs.putInt("sverde",systemColor.getGreen());
        userPrefs.putInt("sazul",systemColor.getBlue());
        userPrefs.putByteArray("bitarray", bitpagina);
        userPrefs.putInt("pesquisa", pesquisa);
        userPrefs.putInt("idtipo", tipomaterial.getMaterialTypeID());
        userPrefs.put("desctipo", tipomaterial.getTypeOfMaterialName());
        userPrefs.putInt("totaltipo", tipomaterial.getTotal());
        userPrefs.putInt("livrestipo", tipomaterial.getFree());
        userPrefs.put("imagemtipo", tipomaterial.getTypeOfMaterialImage());
        userPrefs.put("urlbduser", urlbduser);
        if (urlbdsenha == null) {
            urlbdsenha = "";
        }
        userPrefs.put("urlbdsenha", urlbdsenha);
        userPrefs.put("urlbdaux", urlbdaux);
        userPrefs.put("urlcsv", urlcsv);
    }
    
    public String getUrlBD(){
        Preferences userPrefs = Preferences.userNodeForPackage(getClass());
        urlbduser = userPrefs.get("urlbduser", "");
        urlbdsenha = userPrefs.get("urlbdsenha", "");
        urlcsv = userPrefs.get("urlcsv", DEFAULT_URlCSV);
        urlbdaux = userPrefs.get("urlbdaux", DEFAULT_URlBD);
        String key = "umDois3.,mn/op##RTF%&45dcop90";
        String destino = Utils.CryptAES.decrypt(key, urlbdsenha);
        if (destino != null) {
            return "jdbc:mysql://"+urlbdaux+"?autoReconnect=false&useSSL=false&user="+urlbduser+"&password="+destino;
        }
        return "jdbc:mysql://"+urlbdaux+"?autoReconnect=false&useSSL=false&user="+urlbduser+"&password=";
    }
}
