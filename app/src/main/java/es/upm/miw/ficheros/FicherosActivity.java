package es.upm.miw.ficheros;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;


public class FicherosActivity extends AppCompatActivity {

    private static final String NOMBRE_FICHERO = "miFichero.txt";
    EditText lineaTexto;
    Button botonAniadir;
    TextView contenidoFichero;


    @Override
    protected void onStart() {
        super.onStart();
        mostrarContenido(contenidoFichero);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ficheros);

        lineaTexto       = (EditText) findViewById(R.id.textoIntroducido);
        botonAniadir     = (Button)   findViewById(R.id.botonAniadir);
        contenidoFichero = (TextView) findViewById(R.id.contenidoFichero);
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
            mostrarContenido(contenidoFichero);
            Log.i("FICHERO", "Click botón Añadir -> AÑADIR al fichero");
        } catch (Exception e) {
            Log.e("FILE I/O", "ERROR: " + e.getMessage());
            e.printStackTrace();
        }

    }

    /**
     * Se pulsa sobre el textview -> mostrar contenido del fichero
     * Si está vacío -> mostrar un Toast
     *
     * @param textviewContenidoFichero TextView contenido del fichero
     */
    public void mostrarContenido(View textviewContenidoFichero) {
        boolean hayContenido = false;
        try {
            BufferedReader fin =
                    new BufferedReader(new InputStreamReader(openFileInput(NOMBRE_FICHERO)));
            contenidoFichero.setText("");
            String linea = fin.readLine();
            while (linea != null) {
                hayContenido = true;
                contenidoFichero.append(linea + '\n');
                linea = fin.readLine();
            }
            fin.close();
            Log.i("FICHERO", "Click contenido Fichero -> MOSTRAR fichero");
        } catch (Exception e) {
            Log.e("FILE I/O", "ERROR: " + e.getMessage());
            e.printStackTrace();
        }
        if (!hayContenido) {
            Toast.makeText(this, getString(R.string.txtFicheroVacio), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    /**
     * Añade el menú con la opcion de vaciar el fichero
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        menu.add(Menu.NONE, 1, Menu.NONE, R.string.opcionVaciar)
                .setIcon(android.R.drawable.ic_menu_delete); // sólo visible android < 3.0

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                borrarContenido();
                break;
        }

        return true;
    }

    /**
     * Vaciar el contenido del fichero, la línea de edición y actualizar
     *
     */
    public void borrarContenido() {
        try {  // Vaciar el fichero
            FileOutputStream fos = openFileOutput(NOMBRE_FICHERO, Context.MODE_PRIVATE);
            fos.close();
            Log.i("FICHERO", "opción Limpiar -> VACIAR el fichero");
            lineaTexto.setText(""); // limpio la linea de edición
            mostrarContenido(contenidoFichero);
        } catch (Exception e) {
            Log.e("FILE I/O", "ERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
