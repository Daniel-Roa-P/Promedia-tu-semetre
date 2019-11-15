package com.example.calculatusemestre;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.io.InputStream;
import java.net.URL;
import java.text.DecimalFormat;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.calculatusemestre.SecondActivity.lista;

public class ThirdActivity extends AppCompatActivity {

    private TextView valor,frase;
    private double notaFinal = 0;
    private ImageView imagen;
    private double denominador=100.0;
    private int indicadorFinal = 0;
    private DecimalFormat df = new DecimalFormat("#.000");
    private AdView mAdView;

    private String [] frases1 = {"Las cosas empiezan desde cero","optimista-0-1","Al menos no te estresaste","optimista-0-2"
            ,"Una nota no te define","optimista-0-3","Asiste un poco más a clases","optimista-0-4","Dedícale más tiempo a tu estudio"
            ,"optimista-0-5","ESTUDIEN VAGOS","sarcasmo-0-5","¿Cómo alguien como tu llego a la universidad?","sarcasmo-0-4"
            ,"¿no has pensado en cambiarte de carrera?","sarcasmo-0-3","Eres la definición de mediocridad","sarcasmo-0-2"
            ,"¿olvidaste que te matriculaste?","sarcasmo-0-1"};

    private String [] frases2 = {"El siguiente semestre es tuyo, vamos con toda ","optimista-1-1","Si te esfuerzan un poco más lo lograras"
            ,"optimista-1-2","Lo importante es que aprendiste","optimista-1-3","Ya casi lo vas a lograr","optimista-1-4",
            "Tan cerca y ten lejos","sarcasmo-1-1","Los casis no valen","sarcasmo-1-2","Sigue intentando","sarcasmo-1-3",
            "Llórelo papa","sarcasmo-1-4"};

    private String [] frases3 = {"Lo lograste, sigue luchando por tus sueños","optimista-2-1","Lo importantes es que ya pasaste"
            ,"optimista-2-2","Vales más que esa nota","optimista-2-3","Todo esfuerzo valió la pena","optimista-2-4"
            ,"Pasaste, que alivio no tener que volver a ver esa materia ","optimista-2-5","Gracias por hacer tu tarea ","sarcasmo-2-1"
            ,"¿Eres repitente?","sarcasmo-2-2","¿para eso se trasnocho?","sarcasmo-2-3","¿Debería felicitarte?","sarcasmo-2-4"
            ,"Vez que si se pudo","sarcasmo-2-5"};

    private String [] frases4 = {"Que buena nota, sigue así","optimista-3-1","Costo, pero valió la pena","optimista-3-2",
            "•\tYa puedes dormir tranquilo","optimista-3-3","El profesor regalo la materia","sarcasmo-3-1","•\tIgual en la vida esa nota no sirve para nada"
            ,"sarcasmo-3-2","Si no es 5, no vale","sarcasmo-3-3"};

    private String [] frases5 = {"El mundo necesita más personas como tu","optimista-4-1","Eres el mejor de la clase y lo sabes"
            ,"optimista-4-2","Ese eres tu … excelente","optimista-4-3","¿Qué haces para ser tan bueno?","optimista-4-4"
            ,"¿Usaste rodilleras?","sarcasmo-4-1","Gracias piernas sexys ","sarcasmo-4-2","¿Qué tanto le rogaste al profe?"
            ,"sarcasmo-4-3","¿socializar no es lo tuyo verdad?","sarcasmo-4-4"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.tercer_layout);

        valor = (TextView) findViewById(R.id.notaFinal);
        imagen = (ImageView) findViewById(R.id.imageView2);
        frase = (TextView) findViewById(R.id.frase);

        mAdView = (AdView) findViewById(R.id.anuncio4);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        denominador = getIntent().getDoubleExtra("denominadorFinal",100.0);
        indicadorFinal = getIntent().getIntExtra("indicadorFinal",100);

        frase.setText(String.valueOf(indicadorFinal));

        new DownLoadImageTask(imagen).execute("https://raw.githubusercontent.com/DanielRoa20171020077/Promedia-tu-semetre/master/imagenes/optimista-1-4.png");

        for(int i=0;i<lista.size();i++){

            notaFinal =  notaFinal + (Double.parseDouble(lista.get(i).getNota())*Integer.parseInt(lista.get(i).getPorcentaje()))/denominador;

        }

        valor.setText("Tu promedio es: "+ df.format(notaFinal));

    }

    private class DownLoadImageTask extends AsyncTask<String,Void,Bitmap>{
        ImageView imageView;

        public DownLoadImageTask(ImageView imageView){
            this.imageView = imageView;
        }

        protected Bitmap doInBackground(String...urls){
            String urlOfImage = urls[0];
            Bitmap logo = null;
            try{
                InputStream is = new URL(urlOfImage).openStream();

                logo = BitmapFactory.decodeStream(is);
            }catch(Exception e){
                e.printStackTrace();

            }
            return logo;
        }

        protected void onPostExecute(Bitmap result){
            imageView.setImageBitmap(result);
        }
    }
}

