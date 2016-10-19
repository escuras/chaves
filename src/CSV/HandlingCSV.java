/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSV;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author toze
 */
public class HandlingCSV {

    private static final int[] DEFAULT_VALUES = {3, 4, 6, 5, 7, 10, 12, 14, 13, 11, 15, 9, 1};
    private static final int DEFAULT_SIZE = 16;
    private static final String DEFAULT_DOCUMENT_NAME = "horario_disciplinas.csv";
    private final List<ElementsCSV> elementos;
    private String url;
    private boolean novo;
    private int[] valores;
    private int tamanho;
    private static String doc_nome;

    public HandlingCSV() {
        this.url = null;
        elementos = new ArrayList<>();
        novo = false;
        valores = HandlingCSV.DEFAULT_VALUES;
        tamanho = HandlingCSV.DEFAULT_SIZE;
        doc_nome = HandlingCSV.DEFAULT_DOCUMENT_NAME;
    }

    public HandlingCSV(String url) {
        this.url = url;
        elementos = new ArrayList<>();
        novo = false;
        valores = HandlingCSV.DEFAULT_VALUES;
        tamanho = HandlingCSV.DEFAULT_SIZE;
        doc_nome = HandlingCSV.DEFAULT_DOCUMENT_NAME;
    }

    public void searchElements() {
        CSVReader reader;
        List<String[]> entradas = new ArrayList<>();
        try {
            try {
                if ((this.url == null) || (!this.verifyConnectUrl())) {
                    File file = new File(new File("").getAbsolutePath()
                            + System.getProperty("file.separator")
                            + "Resources" + System.getProperty("file.separator")
                            + "Download" + System.getProperty("file.separator") + doc_nome);
                    if (!file.exists()) {
                        File diretoria = new File(new File("").getAbsolutePath()
                                + System.getProperty("file.separator")
                                + "Resources" + System.getProperty("file.separator")
                                + "Download");
                        if (!diretoria.exists()) {
                            diretoria.mkdirs();
                        }
                        file.createNewFile();
                    }
                    InputStream input = new FileInputStream(file);
                    InputStreamReader r = new InputStreamReader(new FileInputStream(file));
                    reader = new CSVReader(new InputStreamReader(input, r.getEncoding()), ';');
                    if (reader.verifyReader()) {
                        entradas = reader.readAll();
                    }
                } else {
                    InputStream input;
                    try {
                        URL urla = new URL(url);
                        if (urla.openConnection().getContentLength() >= 0) {
                            input = new URL(url).openStream();
                            reader = new CSVReader(new InputStreamReader(input), ';');
                            if (reader.verifyReader()) {
                                entradas = reader.readAll();
                                File file = new File(new File("").getAbsolutePath()
                                        + System.getProperty("file.separator")
                                        + "Resources" + System.getProperty("file.separator")
                                        + "Download" + System.getProperty("file.separator") + doc_nome);
                                if (!file.exists()) {
                                    File diretoria = new File(new File("").getAbsolutePath()
                                            + System.getProperty("file.separator")
                                            + "Resources" + System.getProperty("file.separator")
                                            + "Download");
                                    if (!diretoria.exists()) {
                                        diretoria.mkdirs();
                                    }
                                    file.createNewFile();
                                    novo = true;
                                    try (CSVWriter scv = new CSVWriter(new FileWriter(file), ';')) {
                                        scv.writeAll(entradas);
                                        scv.flush();
                                    }
                                } else {
                                    File file2 = new File(new File("").getAbsolutePath()
                                            + System.getProperty("file.separator")
                                            + "Resources" + System.getProperty("file.separator")
                                            + "Download" + System.getProperty("file.separator")
                                            + "horario_disciplinas_temp.csv");
                                    try (CSVWriter scv = new CSVWriter(new FileWriter(file2), ';')) {
                                        scv.writeAll(entradas);
                                        scv.flush();
                                    }
                                    if (FileUtils.contentEquals(file, file2)) {
                                        file2.delete();
                                    } else {
                                        try (CSVWriter scv = new CSVWriter(new FileWriter(file), ';')) {
                                            file2.delete();
                                            scv.writeAll(entradas);
                                            scv.flush();
                                            novo = true;
                                        }
                                    }
                                }
                            }
                        }
                    } catch (MalformedURLException ex) {
                        Logger.getLogger(HandlingCSV.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(HandlingCSV.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(HandlingCSV.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(HandlingCSV.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception ex) {
        }
        if (!entradas.isEmpty()) {
            ElementsCSV elemento;
            for (int i = 1; entradas.size() > i; i++) {
                if (entradas.get(i).length >= this.tamanho) {
                    elemento = new ElementsCSV(Integer.valueOf(entradas.get(i)[valores[0]]), Integer.valueOf(entradas.get(i)[valores[1]]), Integer.valueOf(entradas.get(i)[valores[2]]), Integer.valueOf(entradas.get(i)[valores[3]]), Integer.valueOf(entradas.get(i)[valores[4]]), entradas.get(i)[valores[5]], entradas.get(i)[valores[6]], entradas.get(i)[valores[7]], entradas.get(i)[valores[8]], entradas.get(i)[valores[9]], entradas.get(i)[valores[10]], entradas.get(i)[valores[11]], entradas.get(i)[valores[12]]);
                    elementos.add(elemento);
                }
            }
        }

        // tratamento para elementos contíguos,no entanto, apurados como separados
        if (!elementos.isEmpty()) {
            for (int k = 0; k < elementos.size(); k++) {
                for (int j = 0; j < elementos.size(); j++) {
                    if (elementos.get(k).getMaterialCode().equals(elementos.get(j).getMaterialCode())) {
                        if (elementos.get(k).getCdSubject().equals(elementos.get(j).getCdSubject())) {
                            if (elementos.get(k).getPersonCode().equals(elementos.get(k).getPersonCode())) {
                                if (elementos.get(k).getActivity().equals(elementos.get(j).getActivity())) {
                                    if (elementos.get(k).getDayWeek() == elementos.get(j).getDayWeek()) {
                                        if (elementos.get(k).getHourEnd() == elementos.get(j).getHourIni()) {
                                            if (elementos.get(k).getMinuteEnd() == elementos.get(j).getMinuteIni()) {
                                                elementos.get(k).setHourEnd(elementos.get(j).getHourEnd());
                                                elementos.get(k).setMinuteEnd(elementos.get(j).getMinuteEnd());
                                                elementos.remove(j);
                                                k = 0;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean verifyConnectUrl() {
        HttpURLConnection connection = null;
        try {
            URL u = new URL(this.url);
            connection = (HttpURLConnection) u.openConnection();
            connection.setRequestMethod("HEAD");
            int code = connection.getResponseCode();
            return true;
        } catch (MalformedURLException e) {
            return false;
        } catch (IOException e) {
            return false;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    public List<ElementsCSV> getElements() {
        return elementos;
    }

    public boolean isEmpty() {
        if (elementos == null) {
            return true;
        } else {
            return elementos.isEmpty();
        }
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isNew() {
        return novo;
    }

    /**
     * @return the index values
     */
    public int[] getIndexValues() {
        return valores;
    }

    /**
     * @param valores the index values to set
     */
    public void setIndexValues(int[] valores) {
        this.valores = valores;
    }

    /**
     * @return the index size
     */
    public int getIndexSize() {
        return tamanho;
    }

    /**
     * @param tamanho the index size to set
     */
    public void setIndexSize(int tamanho) {
        this.tamanho = tamanho;
    }

    /**
     * @return the document name
     */
    public String getDocumentName() {
        return doc_nome;
    }

    /**
     * @param doc_nome the document name to set
     */
    public void setDocumentName(String doc_nome) {
        HandlingCSV.doc_nome = doc_nome;
    }

    public static boolean isNew(String url, String nome) throws MalformedURLException, IOException {
        File file = new File(new File("").getAbsolutePath()
                + System.getProperty("file.separator")
                + "Resources" + System.getProperty("file.separator")
                + "Download" + System.getProperty("file.separator") + nome);
        if (!file.exists()) {
            File diretoria = new File(new File("").getAbsolutePath()
                    + System.getProperty("file.separator")
                    + "Resources" + System.getProperty("file.separator")
                    + "Download");
            if (!diretoria.exists()) {
                diretoria.mkdirs();
            }
            file.createNewFile();
            System.out.println("momo");
            return true;
        } else {
            File file2 = new File(new File("").getAbsolutePath()
                    + System.getProperty("file.separator")
                    + "Resources" + System.getProperty("file.separator")
                    + "Download" + System.getProperty("file.separator")
                    + "horario_disciplinas_temp.csv");
            InputStream input = new URL(url).openStream();
            CSVReader reader;
            List<String[]> entradas;
            reader = new CSVReader(new InputStreamReader(input), ';');
            if (reader.verifyReader()) {
                entradas = reader.readAll();
                try (CSVWriter scv = new CSVWriter(new FileWriter(file2), ';')) {
                    scv.writeAll(entradas);
                    scv.flush();
                }
            }
            if (FileUtils.contentEquals(file, file2)){
	    	file2.delete();
		return true;
	    } else {
		file2.delete();
		return false;
	    }	
        }
    }
}
