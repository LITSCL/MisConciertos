package cl.inacap.misconciertos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint; //Este importe es necesario para que no genere error el recurso de color.
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cl.inacap.misconciertos.dao.EventoDAO;
import cl.inacap.misconciertos.dto.Evento;

public class MainActivity extends AppCompatActivity {
    private EditText nombreArtistaEt;
    private Button fechaBtn;
    private TextView generoMusicalTv;
    private Spinner generoMusicalSp;
    private EditText fechaTxt;
    private EditText valorEntradaEt;
    private TextView calificacionTv;
    private Spinner calificacionSp;
    private Button registrarBtn;
    private int dia, mes, year;
    private String nombreIngresado;
    private String fechaIngresada;
    private String generoIngresado;
    private int calificacionIngresada;
    private ListView itemLv;
    private String diaString = "";
    private String mesString = "";
    private int valorEntradaIngresado;
    private Adaptador adaptador;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //1. Se define el layout (Se esta utilizando el layout del XML utilizando el id).
        LinearLayout nombreArtistaLayout = (LinearLayout)findViewById(R.id.nombreArtistaLayout);
        LinearLayout fechaLayout = (LinearLayout)findViewById(R.id.fechaLayout);
        LinearLayout generoMusicalLayout = (LinearLayout)findViewById(R.id.generoMusicalLayout);
        LinearLayout precioEventoLayout = (LinearLayout)findViewById(R.id.precioEventoLayout);
        LinearLayout calificacionLayout = (LinearLayout)findViewById(R.id.calificacionLayout);
        LinearLayout registrarLayout = (LinearLayout)findViewById(R.id.registrarLayout);
        LinearLayout listaLayout = (LinearLayout)findViewById(R.id.listaLayout);

        //2. Se crean los View necesarios.
        this.nombreArtistaEt = new EditText(this);
        this.fechaBtn = new Button(this);
        this.fechaTxt = new EditText(this);
        this.generoMusicalTv = new TextView(this);
        this.generoMusicalSp = new Spinner(this);
        this.registrarBtn = new Button(this);
        this.valorEntradaEt = new EditText(this);
        this.calificacionSp = new Spinner(this);
        this.calificacionTv = new TextView(this);
        this.itemLv = new ListView(this);

        //3. Se añaden propiedades a los View.
        nombreArtistaEt.setHint("Nombre del Artista");
        fechaBtn.setText(R.string.letraBotonFecha);
        fechaBtn.setBackgroundColor(R.color.colorPrimaryDark);
        generoMusicalTv.setText(R.string.letraTextViewGeneroMusical);
        generoMusicalTv.setGravity(Gravity.CENTER_HORIZONTAL);
        registrarBtn.setText(R.string.letraBotonRegistrar);
        registrarBtn.setBackgroundColor(R.color.colorPrimaryDark);
        fechaTxt.setGravity(Gravity.CENTER_HORIZONTAL);
        fechaTxt.setEnabled(false);
        valorEntradaEt.setHint("Precio del Evento");
        valorEntradaEt.setInputType(InputType.TYPE_CLASS_NUMBER); //Aca se le cambia el tipo de input al EditText.
        calificacionTv.setText(R.string.letraTextViewCalificacion);
        calificacionTv.setGravity(Gravity.CENTER_HORIZONTAL);

        //4. Se añaden los View al Layout.
        nombreArtistaLayout.addView(nombreArtistaEt);
        fechaLayout.addView(fechaBtn);
        fechaLayout.addView(fechaTxt);
        generoMusicalLayout.addView(generoMusicalTv);
        generoMusicalLayout.addView(generoMusicalSp);
        precioEventoLayout.addView(valorEntradaEt);
        calificacionLayout.addView(calificacionTv);
        calificacionLayout.addView(calificacionSp);
        registrarLayout.addView(registrarBtn);
        listaLayout.addView(itemLv);

        //5. Cargar con datos a los View que lo necesiten.
        ArrayList<String> listaGeneros = new ArrayList<>();
        listaGeneros.add("Rock");
        listaGeneros.add("Jazz");
        listaGeneros.add("Pop");
        listaGeneros.add("Reguetoon");
        listaGeneros.add("Salsa");
        listaGeneros.add("Metal");

        ArrayList<Integer> listaCalificaciones = new ArrayList<>();
        listaCalificaciones.add(1);
        listaCalificaciones.add(2);
        listaCalificaciones.add(3);
        listaCalificaciones.add(4);
        listaCalificaciones.add(5);
        listaCalificaciones.add(6);
        listaCalificaciones.add(7);

        ArrayAdapter<CharSequence> adapterGeneroMusicalSp = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaCalificaciones); //Creación del adaptador (Es similar a un modelo en Swing).
        calificacionSp.setAdapter(adapterGeneroMusicalSp); //Al Spinner se le añade el adaptador (Modelo).

        ArrayAdapter<CharSequence> adapterCalificacionSp = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaGeneros); //Creación del adaptador (Es similar a un modelo en Swing).
        generoMusicalSp.setAdapter(adapterCalificacionSp); //Al Spinner se le añade el adaptador (Modelo).

        //6. Agregar los listeners a los View que lo necesiten.
        this.fechaBtn.setOnClickListener((View.OnClickListener) new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar ca = Calendar.getInstance();
                dia = ca.get(Calendar.DAY_OF_MONTH);
                mes = ca.get(Calendar.MONTH);
                year = ca.get(Calendar.YEAR);


                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener(){
                    @Override
                    public void onDateSet(DatePicker view, int year, int mes, int dia) {
                        mes = mes + 1;

                        if (dia < 10) {
                            diaString = "0" + dia;
                        }
                        else {
                            diaString = Integer.toString(dia);
                        }
                        if (mes < 10) {
                            mesString = "0" + mes;
                        }
                        else {
                           mesString = Integer.toString(mes);
                        }

                        fechaTxt.setText(diaString + "/" + mesString + "/" + year);
                        fechaIngresada = diaString + "/" + mesString + "/" + year;

                    }
                }, dia, mes, year);
                datePickerDialog.show(); //Aca es donde se hace visible el DatePickerDialog.
            }
        });

        generoMusicalSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                generoIngresado = parent.getItemAtPosition(position).toString(); //Aca se almacena el genero elegido por el usuario.
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        calificacionSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                calificacionIngresada = Integer.parseInt(parent.getItemAtPosition(position).toString()); //Aca se almacena la calificación elegida por el usuario.
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        this.registrarBtn.setOnClickListener((View.OnClickListener) new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> errores = new ArrayList<>();

                nombreIngresado = nombreArtistaEt.getText().toString().trim();
                if (nombreIngresado.isEmpty()) {
                    errores.add("El nombre esta vacío");
                }

                try {
                    if (fechaIngresada.length() == 0) {

                    }
                } catch (Exception ex) {
                    errores.add("No has ingresado la fecha");
                }

                try {
                    valorEntradaIngresado = Integer.parseInt(valorEntradaEt.getText().toString().trim());
                    if (valorEntradaIngresado <= 0) {
                        errores.add("El precio no es válido");
                    }
                } catch (Exception ex) {
                    errores.add("El precio no es válido");
                }

                if (errores.size() != 0) {
                    mostrarErrores(errores);
                }
                else {
                    Evento ev = new Evento();
                    ev.setNombreArtista(nombreIngresado);
                    ev.setFechaEvento(fechaIngresada);
                    ev.setGeneroMusical(generoIngresado);
                    ev.setValorEntrada(valorEntradaIngresado);
                    ev.setCalificacion(calificacionIngresada);

                    EventoDAO daoEventos = new EventoDAO();
                    daoEventos.create(ev);

                    Toast.makeText(MainActivity.this, "Evento agregado correctamente", Toast.LENGTH_SHORT).show();

                    cargarLista();
                }
        }
        });
    }

    private void cargarLista() {
        adaptador = new Adaptador(this, getArrayItems()); //Aca se llama al método que llena el adaptador de la lista.
        itemLv.setAdapter(adaptador); //Se añade el adaptador a la lista.
    }

    private void mostrarErrores(List<String> errores) {
        String mensaje = "";
        for (String e : errores) {
            mensaje+="-" + e + "\n";
        }
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this); //En el argumento se establece que Activity mostrará el mensaje.
        alertBuilder
                .setTitle("Error de validación") //Define el titulo.
                .setMessage(mensaje) //Define el mensaje del dialogo.
                .setPositiveButton("Aceptar",null) //Agrega el botón aceptar.
                .create() //Crea el Alert.
                .show(); //Se muestra el Alert.
    }

    private ArrayList<Item> getArrayItems() {
        ArrayList<Item> listItems = new ArrayList<>();
        EventoDAO daoEventos = new EventoDAO();
        List<Evento> eventos = daoEventos.getAll(); //Aca nos traemos la lista con todos los eventos agregados.
        for (Evento ev : eventos) {
            if (ev.getCalificacion() >= 1 && ev.getCalificacion() <= 3) {
                listItems.add(new Item(R.drawable.malo, ev.getNombreArtista(), "Fecha: " + ev.getFechaEvento(), "Valor: " + Integer.toString(ev.getValorEntrada())));
            }
            if (ev.getCalificacion() >= 4 && ev.getCalificacion() <= 5) {
                listItems.add(new Item(R.drawable.intermedio, ev.getNombreArtista(), "Fecha: " + ev.getFechaEvento(), "Valor: " + Integer.toString(ev.getValorEntrada())));
            }
            if (ev.getCalificacion() >= 6 && ev.getCalificacion() <= 7) {
                listItems.add(new Item(R.drawable.bueno, ev.getNombreArtista(), "Fecha: " + ev.getFechaEvento(), "Valor: " + Integer.toString(ev.getValorEntrada())));
            }

        }
        return listItems;
    }

}



