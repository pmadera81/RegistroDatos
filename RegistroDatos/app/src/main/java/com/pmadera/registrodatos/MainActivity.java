package com.pmadera.registrodatos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    private EditText etNombre;
    private DatePicker dpFecha;
    private EditText etTelefono;
    private EditText etEmail;
    private EditText etDescripcionContacto;
    private Button btnSiguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {


            etNombre = (EditText) findViewById(R.id.etNombre);
            dpFecha = (DatePicker) findViewById(R.id.dpFecha);
            etTelefono = (EditText) findViewById(R.id.etTelefono);
            etEmail = (EditText) findViewById(R.id.etEmail);
            etDescripcionContacto = (EditText) findViewById(R.id.etDescripcionContacto);
            btnSiguiente = (Button) findViewById(R.id.btnSiguiente);

            obtenerDatos();

        }
        catch (Exception ex){
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EnviarInformacion();
            }
        });



    }


    private void EnviarInformacion(){
        try {
            String nombre=etNombre.getText().toString();
            String telefono= etTelefono.getText().toString();
            String email=etEmail.getText().toString();
            String descripcion=etDescripcionContacto.getText().toString();

            //OBTENGO FECHA
            int dia=dpFecha.getDayOfMonth();
            int mes=dpFecha.getMonth();
            int año=dpFecha.getYear();

            Calendar fechaNac=new GregorianCalendar(año,mes,dia);


            SimpleDateFormat formatoFecha=new SimpleDateFormat("dd/MM/yyyy");
            String fechaFormateada=formatoFecha.format(fechaNac.getTime());

            if(nombre.isEmpty()){
                throw new Exception("DEBE INGRESAR UN NOMBRE");
            }

            if(telefono.isEmpty()){
                throw new Exception("DEBE INGRESAR UN TELEFONO");
            }

            if(email.isEmpty()){
                throw new Exception("DEBE INGRESAR UN E-MAIL");
            }

            if(descripcion.isEmpty()){
                throw new Exception("DEBE INGRESAR UNA DESCRIPCION");
            }

            Intent intent=new Intent(MainActivity.this,EditarDatos.class);
            intent.putExtra("nombre",nombre);
            intent.putExtra("fechaNac",fechaFormateada);
            intent.putExtra("telefono",telefono);
            intent.putExtra("email",email);
            intent.putExtra("descripcion",descripcion);

            startActivity(intent);

            finish();

        }
        catch (Exception ex){
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    private void obtenerDatos() {

        try {
            Bundle parametros=getIntent().getExtras();

            if(parametros!=null) {
                String nombre = parametros.getString("nombre");
                String fechaNac = parametros.getString("fechaNac");
                String telefono = parametros.getString("telefono");
                String email = parametros.getString("email");
                String descripcion = parametros.getString("descripcion");

                SimpleDateFormat formato=new SimpleDateFormat("dd/MM/yyyy");
                Date fec=formato.parse(fechaNac);
                Calendar cal= Calendar.getInstance();
                cal.setTime(fec);

                etNombre = (EditText) findViewById(R.id.etNombre);
                dpFecha.updateDate(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DATE));


                etTelefono = (EditText) findViewById(R.id.etTelefono);
                etEmail = (EditText) findViewById(R.id.etEmail);
                etDescripcionContacto = (EditText) findViewById(R.id.etDescripcionContacto);

                etNombre.setText(nombre);

                etTelefono.setText(telefono);
                etEmail.setText(email);
                etDescripcionContacto.setText(descripcion);
            }

        }
        catch (Exception ex){
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();


        }


    }

}