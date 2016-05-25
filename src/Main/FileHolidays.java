/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import TimeDate.DinamicHoliday;
import TimeDate.HolidaysList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author toze
 */
public class FileHolidays {

    private InputStream ioFeriados;

    public FileHolidays() {
        File file = new File(this.getClass().getResource("Recursos/feriados.dat").getFile());
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(FileHolidays.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            ioFeriados = new FileInputStream(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileHolidays.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public HolidaysList getHolidays() {
        if (ioFeriados != null) {
            return getHolidaysFromInputStream(ioFeriados);
        } else {
            return new TimeDate.HolidaysList(new TimeDate.Date().getYear());
        }
    }

    public void saveHolidays(TimeDate.HolidaysList list) {
        String aux = "";
        for (TimeDate.Holiday hol : list.getHolidays()) {
            if (hol instanceof DinamicHoliday) {
                DinamicHoliday d = (DinamicHoliday) hol;
                aux += hol.toString() + d.getName() + "\n";
            } else {
                aux += hol.toString() + "\n";
            }
        }
        try {
            /*
              File file = new File(getClass().getProtectionDomain().getCodeSource().getLocation().getPath()+"feriados.dat");
              if (!file.isFile()){
                file.createNewFile();
            }*/
            
            File file = new File(this.getClass().getResource("Recursos/feriados.dat").getFile());
            try (OutputStream os = new FileOutputStream(file)) {
                byte[] bytes = aux.getBytes();
                os.write(bytes);
                os.flush();
                os.close();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileHolidays.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileHolidays.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static TimeDate.HolidaysList getHolidaysFromInputStream(InputStream is) {
        BufferedReader br = null;
        String line;
        String[] valores;
        TimeDate.HolidaysList list = new TimeDate.HolidaysList(new TimeDate.Date().getYear());
        try {
            br = new BufferedReader(new InputStreamReader(is));
            TimeDate.Holiday holi;
            String aux;
            while ((line = br.readLine()) != null) {
                holi = new TimeDate.Holiday();
                if (line.contains("pascoa")) {
                    line = line.replace("pascoa", "");
                    aux = "pascoa";
                } else if (line.contains("sexta_feira")) {
                    line = line.replace("sexta_feira", "");
                    aux = "sexta_feira";
                } else if (line.contains("corpo_cristo")) {
                    line = line.replace("corpo_cristo", "");
                    aux = "corpo_cristo";
                } else if (line.contains("carnaval")) {
                    line = line.replace("carnaval", "");
                    aux = "carnaval";
                } else {
                    aux = "";
                }
                if (!line.equals("")) {
                    valores = line.split("/");
                    valores[0] = valores[0].trim();
                    valores[1] = valores[1].trim();
                    if (valores[0].substring(0, 1).equals("0")) {
                        valores[0] = valores[0].substring(1, 2);
                    }
                    if (valores[1].substring(0, 1).equals("0")) {
                        valores[1] = valores[1].substring(1, 2);
                    }
                    if (aux.equals("")) {
                        holi.setDay(Integer.valueOf(valores[0]));
                        holi.setMonth(Integer.valueOf(valores[1]));
                        list.addHoliday(holi);
                    } else {
                        holi.setDay(Integer.valueOf(valores[0]));
                        holi.setMonth(Integer.valueOf(valores[1]));
                        DinamicHoliday h = new DinamicHoliday(holi, aux);
                        list.addHoliday(h);
                    }
                }
            }
        } catch (java.io.IOException e) {
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (java.io.IOException e) {
                }
            }
        }
        return list;
    }

    public TimeDate.HolidaysList getDefaultHolidaysList() {
        File file = new File(this.getClass().getResource("Recursos/feriados_lista.dat").getFile());
        TimeDate.HolidaysList list = new TimeDate.HolidaysList(new TimeDate.Date().getYear());
        String aux;
        String[] valores;
        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                aux = scan.nextLine();
                valores = aux.split("/");
                valores[0] = valores[0].trim();
                valores[1] = valores[1].trim();
                if (valores[0].substring(0, 1).equals("0")) {
                    valores[0] = valores[0].substring(1, 2);
                }
                if (valores[1].substring(0, 1).equals("0")) {
                    valores[1] = valores[1].substring(1, 2);
                }
                list.addHoliday(Integer.valueOf(valores[0]), Integer.valueOf(valores[1]));
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileHolidays.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
