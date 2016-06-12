package FileIOAux;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;
import sun.misc.BASE64Decoder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author toze
 */
public class ImageAux {

    public static String extensao;

    public static BufferedImage getImageFromFileChooser(java.awt.Component comp, Langs.Locale locale) {
        String titulo = setUILanguage(locale);
        JFileChooser fimagem = new JFileChooser();
        fimagem.setDialogTitle(titulo);
        fimagem.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                }
                final String name = f.getName();
                return name.endsWith(".png") || name.endsWith(".jpg");
            }

            @Override
            public String getDescription() {
                return "*.png,*.jpg";
            }
        });
        fimagem.getLocation();
        int retVal = fimagem.showOpenDialog(comp);
        BufferedImage bimagem = null;
        if (retVal == JFileChooser.APPROVE_OPTION) {
            extensao = verifyExtension(fimagem.getSelectedFile());
            System.out.println(extensao);
            try {
                if (validateExtension(extensao)) {
                    bimagem = ImageIO.read(fimagem.getSelectedFile());
                }
            } catch (IOException ex) {
            }
        }
        return bimagem;
    }

    public static BufferedImage getImageFromFile(java.io.File file) {
        BufferedImage bimagem = null;
        extensao = verifyExtension(file);
        InputStream input;
        try {
            if (validateExtension(extensao)) {
                input = new FileInputStream(file);
                ImageInputStream imageInput = ImageIO.createImageInputStream(input);
                bimagem = ImageIO.read(imageInput);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ImageAux.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ImageAux.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bimagem;
    }

    public static String verifyExtension(java.io.File file) {
        InputStream input;
        try {
            input = new FileInputStream(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ImageAux.class.getName()).log(Level.SEVERE, null, ex);
            input = null;
        }
        if (input != null) {
            ImageInputStream iis;
            try {
                iis = ImageIO.createImageInputStream(input);
                java.util.Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);
                ImageReader reader = iter.next();
                extensao = reader.getFormatName();
            } catch (IOException ex) {
                extensao = "NaNFile";
            } catch (java.util.NoSuchElementException ex) {
                extensao = "NaNImagem";
            }
        } else {
            extensao = "NaNFile";
        }
        return extensao;
    }
    
   
    
    private static boolean validateExtension(String file) {

        if (file.equals("png")) {
            return true;
        } else if (file.equals("jpg")) {
            return true;
        } else if (file.equals("jpeg")) {
            return true;
        } else if (file.equals("JPEG")) {
            return true;
        } else if (file.equals("JPG")) {
            return true;
        } else if (file.equals("PNG")) {
            return true;
        } else if (file.equals("Jpg")) {
            return true;
        } else if (file.equals("Png")) {
            return true;
        } else if (file.equals("GIF")) {
            return true;
        } else if (file.equals("gif")) {
            return true;
        } else if (file.equals("BMP")) {
            return true;
        } else if (file.equals("bmp")) {
            return true;
        } else if (file.equals("WBMP")) {
            return true;
        } else {
            return file.equals("wbmp");
        }
    }

    public static BufferedImage resize(BufferedImage imagem, int largura, int altura) {
        BufferedImage img = null;
        if (imagem != null) {
            int l = imagem.getWidth();
            int a = imagem.getHeight();

            if (imagem.getType() > 0) {
                img = new BufferedImage(largura, altura, imagem.getType());
            } else {
                img = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);
            }
            Graphics2D g = img.createGraphics();
            g.drawImage(imagem, 0, 0, largura, altura, 0, 0, l, a, null);
            g.dispose();
        }
        return img;
    }

    public static BufferedImage makeWhiter(BufferedImage imagem, int valor) {
        BufferedImage img = null;
        if (imagem != null) {
            int largura = imagem.getWidth();
            int altura = imagem.getHeight();

            if (imagem.getType() > 0) {
                img = new BufferedImage(largura, altura, imagem.getType());
            } else {
                img = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);
            }
            for (int x = 0; x < largura; x++) {
                for (int y = 0; y < altura; y++) {
                    Color cor = new Color(imagem.getRGB(x, y));
                    int red = cor.getRed() + valor;
                    if (red > 255) {
                        red = 255;
                    }
                    int green = cor.getGreen() + valor;
                    if (green > 255) {
                        green = 255;
                    }
                    int blue = cor.getBlue() + valor;
                    if (blue > 255) {
                        blue = 255;
                    }
                    Color au = new Color(red, green, blue);
                    img.setRGB(x, y, au.getRGB());
                }
            }
        }
        return img;
    }

    public static BufferedImage makeDarker(BufferedImage imagem, int valor) {
        BufferedImage img = null;
        if (imagem != null) {
            int largura = imagem.getWidth();
            int altura = imagem.getHeight();

            if (imagem.getType() > 0) {
                img = new BufferedImage(largura, altura, imagem.getType());
            } else {
                img = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);
            }
            for (int x = 0; x < largura; x++) {
                for (int y = 0; y < altura; y++) {
                    Color cor = new Color(imagem.getRGB(x, y));
                    int red = cor.getRed() - valor;
                    if (red < 0) {
                        red = 0;
                    }
                    int green = cor.getGreen() - valor;
                    if (green < 0) {
                        green = 0;
                    }
                    int blue = cor.getBlue() - valor;
                    if (blue < 0) {
                        blue = 0;
                    }
                    Color au = new Color(red, green, blue);
                    img.setRGB(x, y, au.getRGB());
                }
            }
        }
        return img;
    }

    public static BufferedImage makeRoundedCorner(BufferedImage image, int cornerRadius) {
        int largura = image.getWidth();
        int altura = image.getHeight();
        BufferedImage output = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = output.createGraphics();
        g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        g2.fill(new RoundRectangle2D.Float(0, 0, largura, altura, cornerRadius, cornerRadius));
        g2.setComposite(java.awt.AlphaComposite.SrcAtop);
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
        return output;
    }
    
    public static BufferedImage transformFromBase64IntoImage(String imagem) {
        byte[] p = java.util.Base64.getDecoder().decode(imagem);
        java.io.ByteArrayInputStream in = new java.io.ByteArrayInputStream(p);
        BufferedImage image = null;
        try {
            image = ImageIO.read(in);
        } catch (IOException ex) {}
        return image;
    }   

    private static String setUILanguage(Langs.Locale locale) {
        ResourceBundle rb;
        String[] im = locale.getLocale().split("_");
        try {
            rb = ResourceBundle.getBundle("Langs.FileChooser", new Locale(im[0], im[1]));
        } catch (Exception e) {
            rb = ResourceBundle.getBundle("Langs.FileChooser", new Locale("pt", "PT"));
        }
        String titulo = rb.getString("EscolherImagem");
        UIManager.put("FileChooser.lookInLabelText", rb.getString("lookInLabelText"));
        UIManager.put("FileChooser.filesOfTypeLabelText", rb.getString("filesOfTypeLabelText"));
        UIManager.put("FileChooser.upFolderToolTipText", rb.getString("upFolderToolTipText"));
        /*
        
         FileChooser.fileNameLabelText
         FileChooser.homeFolderToolTipText
         FileChooser.newFolderToolTipText
         FileChooser.listViewButtonToolTipTextlist
         FileChooser.detailsViewButtonToolTipText
         FileChooser.saveButtonText=Save
         FileChooser.openButtonText=Open
         FileChooser.cancelButtonText=Cancel
         FileChooser.updateButtonText=Update
         FileChooser.helpButtonText=Help
         FileChooser.saveButtonToolTipText=Save
         FileChooser.openButtonToolTipText=Open
         FileChooser.cancelButtonToolTipText=Cancel
         FileChooser.updateButtonToolTipText=Update
         FileChooser.helpButtonToolTipText=Help

         Almost all Swing widgets can be customize this way. You can
         examine the Swing sources to get these values or check
         http://www.gargoylesoftware.com/papers/plafdiff.html for
         a list of them.
        
        
        FileChooser.lookInLabelText",
"FileChooser.lookInLabelMnemonic",
"FileChooser.fileNameLabelText",
"FileChooser.fileNameLabelMnemonic",
"FileChooser.filesOfTypeLabelText",
"FileChooser.filesOfTypeLabelMnemonic",
"FileChooser.upFolderToolTipText",
"FileChooser.upFolderAccessibleName",
"FileChooser.homeFolderToolTipText",
"FileChooser.homeFolderAccessibleName",
"FileChooser.newFolderToolTipText",
"FileChooser.newFolderAccessibleName",
"FileChooser.listViewButtonToolTipText",
"FileChooser.listViewButtonAccessibleName",
"FileChooser.detailsViewButtonToolTipText",
"FileChooser.detailsViewButtonAccessibleName",
"FileChooser.cancelButtonText",
"FileChooser.cancelButtonMnemonic",
"FileChooser.cancelButtonToolTipText",
"FileChooser.openButtonText",
"FileChooser.openButtonMnemonic",
"FileChooser.openButtonToolTipText",
"FileChooser.saveButtonText",
"FileChooser.saveButtonMnemonic",
"FileChooser.saveButtonToolTipText",
"FileChooser.acceptAllFileFilterText",
"FileChooser.openDialogTitleText",
"FileChooser.saveDialogTitleText",
"FileChooser.homeFolderToolTipText",
"FileChooser.newFolderAccessibleName",
"FileChooser.viewMenuLabelText",
"FileChooser.refreshActionLabelText",
"FileChooser.newFolderActionLabelText",
"FileChooser.goupFolderActionLabelText",
"FileChooser.listViewActionLabelText",
"FileChooser.detailsViewActionLabelText",
"FileChooser.foldersLabelText",
         */
        return titulo;

    }

}
