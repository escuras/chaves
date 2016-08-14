/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clavis;

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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author toze
 */
public class FileBreakPeriods {

    private InputStream ioIntervalos;

    public FileBreakPeriods() {
        File file = new File(new File("").getAbsolutePath()
                + System.getProperty("file.separator")
                + "Resources" + System.getProperty("file.separator")
                + "Files" + System.getProperty("file.separator")
                + "intervalos.dat");
        if (!file.exists()) {
            try {
                File file2 = new File(new File("").getAbsolutePath()
                        + System.getProperty("file.separator")
                        + "Resources");
                if (!file2.exists()) {
                    file2.mkdir();
                }
                file2 = new File(new File("").getAbsolutePath()
                        + System.getProperty("file.separator")
                        + "Resources" + System.getProperty("file.separator")
                        + "Files");
                if (!file2.exists()) {
                    file2.mkdir();
                }
                file.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(FileBreakPeriods.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            ioIntervalos = new FileInputStream(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileHolidays.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public TimeDate.BreakPeriodList getBreakPeriods() {
        if (ioIntervalos != null) {
            return getBreakPeriodsFromInputStream(ioIntervalos);
        } else {
            return new TimeDate.BreakPeriodList();
        }
    }

    private static TimeDate.BreakPeriodList getBreakPeriodsFromInputStream(InputStream is) {
        BufferedReader br = null;
        String line;
        String[] aux;
        String[] valores;
        String[] valores2;
        String[] valores3;
        TimeDate.BreakPeriodList list = new TimeDate.BreakPeriodList();
        try {
            br = new BufferedReader(new InputStreamReader(is));
            TimeDate.Date date1;
            TimeDate.Date date2;
            TimeDate.BreakPeriod pr;
            String nome;
            while ((line = br.readLine()) != null) {
                aux = line.split(":");
                if (aux.length > 1) {
                    nome = aux[0].trim();
                    aux[1] = aux[1].trim();
                    valores = aux[1].split("-");
                    valores[0] = valores[0].trim();
                    valores[1] = valores[1].trim();
                    valores2 = valores[0].split("/");
                    valores2[0] = valores2[0].trim();
                    valores2[1] = valores2[1].trim();
                    valores2[2] = valores2[2].trim();
                    if (valores2[0].substring(0, 1).equals("0")) {
                        valores2[0] = valores2[0].substring(1, 2);
                    }
                    if (valores2[1].substring(0, 1).equals("0")) {
                        valores2[1] = valores2[1].substring(1, 2);
                    }
                    if (valores2[2].substring(0, 1).equals("0")) {
                        valores2[2] = valores2[2].substring(1, 2);
                    }
                    date1 = new TimeDate.Date(Integer.valueOf(valores2[0]), Integer.valueOf(valores2[1]), Integer.valueOf(valores2[2]));
                    valores3 = valores[1].split("/");
                    valores3[0] = valores3[0].trim();
                    valores3[1] = valores3[1].trim();
                    valores3[2] = valores3[2].trim();
                    if (valores3[0].substring(0, 1).equals("0")) {
                        valores3[0] = valores3[0].substring(1, 2);
                    }
                    if (valores3[1].substring(0, 1).equals("0")) {
                        valores3[1] = valores3[1].substring(1, 2);
                    }
                    if (valores3[2].substring(0, 1).equals("0")) {
                        valores3[2] = valores3[2].substring(1, 2);
                    }
                    date2 = new TimeDate.Date(Integer.valueOf(valores3[0]), Integer.valueOf(valores3[1]), Integer.valueOf(valores3[2]));
                    pr = new TimeDate.BreakPeriod(date1, date2, nome);
                    list.addBreakPeriod(pr);
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

    public void saveHolidays(TimeDate.BreakPeriodList list) {
        String aux = "";
        aux = list.getBreakPeriodList().stream().map((inter) -> inter.toString() + "\n").reduce(aux, String::concat);
        try {
            File file = new File(new File("").getAbsolutePath()
                + System.getProperty("file.separator")
                + "Resources" + System.getProperty("file.separator")
                + "Files" + System.getProperty("file.separator")
                + "intervalos.dat");
            try (OutputStream os = new FileOutputStream(file)) {
                byte[] bytes = aux.getBytes();
                os.write(bytes);
                os.flush();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileHolidays.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileHolidays.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
