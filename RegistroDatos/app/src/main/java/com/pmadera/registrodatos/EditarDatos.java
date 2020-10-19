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
import java.util.GregorianCalendar;

public class EditarDatos extends AppCompatActivity {
    private TextView tvNombre;
    private TextView tvFechaNacimiento;
    private TextView tvTelefono;
    private TextView tvEmail;
    private TextView tvDescripcionContacto;
    private Button btnEditar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_datos);


            obtenerDatos();

            btnEditar=(Button)findViewById(R.id.btnEditar);

            btnEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    editarDatos();
                }
            });

    }

    private void obtenerDatos() {

        try {
            Bundle parametros=getIntent().getExtras();
            String nombre=parametros.getString("nombre");
            String fechaNac=parametros.getString("fechaNac");
            String telefono=parametros.getString("telefono");
            String email=parametros.getString("email");
            String descripcion=parametros.getString("descripcion");


            tvNombre=(TextView)findViewById(R.id.tvNombre);
            tvFechaNacimiento=(TextView)findViewById(R.id.tvFechaNacimiento);
            tvTelefono=(TextView)findViewById(R.id.tvTelefono);
            tvEmail=(TextView)findViewById(R.id.tvEmail);
            tvDescripcionContacto=(TextView)findViewById(R.id.tvDescripcion);

            tvNombre.setText(nombre);
            tvFechaNacimiento.setText(fechaNac);
            tvTelefono.setText(telefono);
            tvEmail.setText(email);
            tvDescripcionContacto.setText(descripcion);
        }
        catch (Exception ex){
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }


    }


    private void editarDatos(){

        try {
            String nombre=tvNombre.getText().toString();
            String telefono= tvTelefono.getText().toString();
            String email=tvEmail.getText().toString();
            String descripcion=tvDescripcionContacto.getText().toString();
            String fecha=tvFechaNacimiento.getText().toString();

            Intent intent=new Intent(EditarDatos.this,MainActivity.class);
            intent.putExtra("nombre",nombre);
            intent.putExtra("fechaNac",fecha);
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

}