package com.net;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageProducer;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

//Esempio per scaricare un'immagine, referenziato come risorsa 
//di un URL, utilizzando però un altro approccio che si avvale 
//del metodo getContent della classe URLConnection . Questo 
//metodo è utile perché ritorna un oggetto di tipo Object 
//che a run-time può contenere un tipo corrispondente a quello
//che rileva leggendo l’header Content-Type della risorsa da 
//ottenere. I tipi generalmente ritornati sono, infatti, i 
//seguenti: PlainTextInputStream (package sun.net.www.content.text ) 
//se la risorsa contiene del testo normale; HttpInputStream 
//(classe annidata nella classe HttpURLConnection del package 
//sun.net.www.protocol.http ) se la risorsa contiene del testo 
//HTML, XML e così via; ImageProducer (package java.awt.image ) 
//se la risorsa è un’immagine; AppletAudioClip 
//(package sun.applet ) se la risorsa è un file audio 
//( .wav , .aiff , .mid e così via). 
//
//
//NOTA: 
//Una strada alternativa all’utilizzo del metodo getContent 
//potrebbe essere quella di impiegare il metodo getContentType, 
//sempre della classe URLConnection , il quale ritorna come stringa 
//il MIME type della risorsa referenziata dal tipo URL corrente. 
//Nel nostro caso, dunque, avremmo potuto scrivere qualcosa come 
//String mime_type = u_conn.getContentType() e poi 
//if(mime_type.contains("image/jpeg")) { ... } .

/**
 * @author sabaja
 *
 */
public class URLGetContent_scaricare_un_immagine extends JFrame {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JLabel lbl_img;
    private ImageProducer the_image;

    public URLGetContent_scaricare_un_immagine() throws IOException {
        super("URLContent DEMO");
        String res = "http://www.pellegrinoprincipe.com/img/html5_css3_js_guida_completa_pub.jpg";
        URL url = new URL(res);
        URLConnection u_conn = url.openConnection(); // ottieni l'immagine
        Object o = u_conn.getContent(); // se sei un ImageProducer allora
        // assegna l'immagine scaricata alla
        // label
        if (o instanceof ImageProducer) {
            the_image = (ImageProducer) o;
            Image img = Toolkit.getDefaultToolkit().createImage(the_image);
            ImageIcon img_icon = new ImageIcon(img);
            lbl_img = new JLabel(img_icon);
        } else
            lbl_img = new JLabel("Immagine non rilevabile....");
        add(lbl_img, BorderLayout.CENTER);
    }

    public static void main(String[] args) throws IOException {
        URLGetContent_scaricare_un_immagine window = new URLGetContent_scaricare_un_immagine();
        window.setSize(600, 400);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        window.setLocationRelativeTo(null);
    }
}
