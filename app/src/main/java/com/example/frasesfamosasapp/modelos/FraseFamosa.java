package com.example.frasesfamosasapp.modelos;

public class FraseFamosa {
    private String texto;
    private String autor;
    private String fecha;

    private long id; // El ID de la BD

    public FraseFamosa(String texto, String autor, String fecha) {
        this.texto = texto;
        this.autor = autor;
        this.fecha = fecha;
    }

    // Constructor para cuando instanciamos desde la BD
    public FraseFamosa(String texto, String autor, long id, String fecha) {
        this.texto = texto;
        this.autor = autor;
        this.id = id;
        this.fecha = fecha;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "FraseFamosa{" +
                "texto='" + texto + '\'' +
                ", autor='" + autor + '\'' +
                ", fecha='" + fecha + '\'' +
                ", id=" + id +
                '}';
    }
}