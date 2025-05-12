package com.example.registrovehiculos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class VehiculoDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "vehiculos.db";
    private static final int DB_VERSION = 1;

    public VehiculoDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE vehiculo (placa TEXT PRIMARY KEY, marca TEXT, color TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS vehiculo");
        onCreate(db);
    }

    public boolean registrarVehiculo(String placa, String marca, String color) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("placa", placa);
        values.put("marca", marca);
        values.put("color", color);
        long res = db.insert("vehiculo", null, values);
        return res != -1;
    }

    public Cursor buscarVehiculo(String placa) {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM vehiculo WHERE placa = ?", new String[]{placa});
    }

    public boolean actualizarVehiculo(String placa, String marca, String color) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("marca", marca);
        values.put("color", color);
        int res = db.update("vehiculo", values, "placa = ?", new String[]{placa});
        return res > 0;
    }

    public boolean eliminarVehiculo(String placa) {
        SQLiteDatabase db = getWritableDatabase();
        int res = db.delete("vehiculo", "placa = ?", new String[]{placa});
        return res > 0;
    }

    public Cursor listarVehiculos() {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM vehiculo", null);
    }
}

