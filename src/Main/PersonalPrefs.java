/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

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
        int w = userPrefs.getInt("width",1024);
        int h = userPrefs.getInt("height",700);
        String lang = userPrefs.get("lingua", "es_ES");
        int max = userPrefs.getInt("maximizada", 0);
        tdivisor = userPrefs.getInt("userdivisor", 20);
        this.setExtendedState(max);
        setBounds(x, y, w, h);
        lingua = new Langs.Locale();
        lingua.setLocale(lang); 
        booleanBoxLanguage = userPrefs.getBoolean("booleanLanguage", true);
        init();
        caracteristicsJFrame();
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
    }

    
}
