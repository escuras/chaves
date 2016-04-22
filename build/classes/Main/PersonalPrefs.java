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
        
    }
    
    public void start() {
        Preferences userPrefs = Preferences.userNodeForPackage(getClass());
        int x = userPrefs.getInt("x",100);
        int y = userPrefs.getInt("y",100);
        int w = userPrefs.getInt("width",this.getWidth());
        int h = userPrefs.getInt("height",this.getHeight());
        String lang = userPrefs.get("lingua", "pt_PT");
        int max = userPrefs.getInt("maximizada", this.getExtendedState());
        this.setExtendedState(max);
        setBounds(x, y, w, h);
        PersonalPrefs.lingua = new Langs.Locale();
        PersonalPrefs.lingua.setLocale(lang); 
        this.pack();
    }
    
    public void stop(){
        Preferences userPrefs = Preferences.userNodeForPackage(getClass());
        userPrefs.putInt("x", getX());
        userPrefs.putInt("y", getY());
        userPrefs.putInt("width", this.getWidth());
        userPrefs.putInt("height", this.getHeight());
        userPrefs.putInt("maximizada", this.getExtendedState());
        userPrefs.put("lingua", PersonalPrefs.lingua.getLocale());
        userPrefs.putInt("userdivisor", PersonalPrefs.tdivisor);
    }

    
}
