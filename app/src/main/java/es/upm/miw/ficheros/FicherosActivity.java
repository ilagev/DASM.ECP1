package es.upm.miw.ficheros;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileOutputStream;


public class FicherosActivity extends AppCompatActivity {

    private static final String NOMBRE_FICHERO = "miFichero.txt";
    EditText lineaTexto;
    Button botonAniadir;


    @Override
    protected void onStart() {
        super.onStart();
        // TODO mostrarContenido(contenidoFichero);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ficheros);

        lineaTexto       = (EditText) findViewById(R.id.textoIntroducido);
        botonAniadir     = (Button)   findViewById(R.id.botonAniadir);
    }

    /**
     * Al pulsar el botón añadir -> añadir al fichero.
     * TODO Después de añadir -> mostrarContenido()
     *
     * @param v Botón añadir
     */
    public void accionAniadir(View v) {
        try {  // Añadir al fichero
            FileOutputStream fos = openFileOutput(NOMBRE_FICHERO, Context.MODE_APPEND);
            fos.write(lineaTexto.getText().toString().getBytes());
            fos.write('\n');
            fos.close();
            Log.i("FICHERO", "Click botón Añadir -> AÑADIR al fichero");
        } catch (Exception e) {
            Log.e("FILE I/O", "ERROR: " + e.getMessage());
            e.printStackTrace();
        }

    }

    /**
     * TODO Se pulsa sobre el textview -> mostrar contenido del fichero
     *
     * @param textviewContenidoFichero TextView contenido del fichero
     */
//    public void mostrarContenido(View textviewContenidoFichero) {
//
//    }

    /**
     * TODO Vaciar el contenido del fichero y actualizar
     *
     */
//    public void borrarContenido() {
//
//    }

}
