package com.example.registrovehiculos;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etPlaca, etMarca, etColor;
    Button btnRegistrar, btnBuscar, btnActualizar, btnEliminar, btnListar;
    RecyclerView recyclerVehiculos;

    VehiculoDBHelper dbHelper;
    ArrayList<Vehiculo> listaVehiculos;
    VehiculoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etPlaca = findViewById(R.id.etPlaca);
        etMarca = findViewById(R.id.etMarca);
        etColor = findViewById(R.id.etColor);

        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnBuscar = findViewById(R.id.btnBuscar);
        btnActualizar = findViewById(R.id.btnActualizar);
        btnEliminar = findViewById(R.id.btnEliminar);
        btnListar = findViewById(R.id.btnListar);

        recyclerVehiculos = findViewById(R.id.recyclerVehiculos);
        recyclerVehiculos.setLayoutManager(new LinearLayoutManager(this));

        dbHelper = new VehiculoDBHelper(this);

        // REGISTRAR
        btnRegistrar.setOnClickListener(v -> {
            String placa = etPlaca.getText().toString();
            String marca = etMarca.getText().toString();
            String color = etColor.getText().toString();

            if (dbHelper.registrarVehiculo(placa, marca, color)) {
                Toast.makeText(this, getString(R.string.msg_registrado), Toast.LENGTH_SHORT).show();
                limpiarCampos();
            } else {
                Toast.makeText(this, getString(R.string.msg_error_registro), Toast.LENGTH_SHORT).show();
            }
        });

        // BUSCAR
        btnBuscar.setOnClickListener(v -> {
            String placa = etPlaca.getText().toString();
            Cursor cursor = dbHelper.buscarVehiculo(placa);
            if (cursor.moveToFirst()) {
                etMarca.setText(cursor.getString(1));
                etColor.setText(cursor.getString(2));
                Toast.makeText(this, getString(R.string.msg_encontrado), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, getString(R.string.msg_no_encontrado), Toast.LENGTH_SHORT).show();
            }
            cursor.close();
        });

        // ACTUALIZAR
        btnActualizar.setOnClickListener(v -> {
            String placa = etPlaca.getText().toString();
            String marca = etMarca.getText().toString();
            String color = etColor.getText().toString();

            if (dbHelper.actualizarVehiculo(placa, marca, color)) {
                Toast.makeText(this, getString(R.string.msg_actualizado), Toast.LENGTH_SHORT).show();
                limpiarCampos();
            } else {
                Toast.makeText(this, getString(R.string.msg_error_actualizar), Toast.LENGTH_SHORT).show();
            }
        });

        // ELIMINAR
        btnEliminar.setOnClickListener(v -> {
            String placa = etPlaca.getText().toString();
            if (dbHelper.eliminarVehiculo(placa)) {
                Toast.makeText(this, getString(R.string.msg_eliminado), Toast.LENGTH_SHORT).show();
                limpiarCampos();
            } else {
                Toast.makeText(this, getString(R.string.msg_error_eliminar), Toast.LENGTH_SHORT).show();
            }
        });

        // LISTAR
        btnListar.setOnClickListener(v -> {
            listaVehiculos = new ArrayList<>();
            Cursor cursor = dbHelper.listarVehiculos();
            while (cursor.moveToNext()) {
                listaVehiculos.add(new Vehiculo(cursor.getString(0), cursor.getString(1), cursor.getString(2)));
            }
            cursor.close();

            adapter = new VehiculoAdapter(listaVehiculos);
            recyclerVehiculos.setAdapter(adapter);
        });
    }

    private void limpiarCampos() {
        etPlaca.setText("");
        etMarca.setText("");
        etColor.setText("");
    }
}
