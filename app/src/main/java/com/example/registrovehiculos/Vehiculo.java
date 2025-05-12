package com.example.registrovehiculos;

public class Vehiculo {
    String placa, marca, color;

    public Vehiculo(String placa, String marca, String color) {
        this.placa = placa;
        this.marca = marca;
        this.color = color;
    }

    public String getPlaca() { return placa; }
    public String getMarca() { return marca; }
    public String getColor() { return color; }
}

