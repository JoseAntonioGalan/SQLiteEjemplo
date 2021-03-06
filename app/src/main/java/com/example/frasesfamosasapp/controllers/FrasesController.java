package com.example.frasesfamosasapp.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import com.example.frasesfamosasapp.AyudanteBaseDeDatos;
import com.example.frasesfamosasapp.modelos.FraseFamosa;

public class FrasesController {

    private AyudanteBaseDeDatos ayudanteBaseDeDatos;
    private String NOMBRE_TABLA = "frasesfamosas";

    public FrasesController(Context contexto) {
        ayudanteBaseDeDatos = new AyudanteBaseDeDatos(contexto);
    }

    public int eliminarFrase(FraseFamosa frase) {
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        String[] argumentos = {String.valueOf(frase.getId())};
        return baseDeDatos.delete(NOMBRE_TABLA, "id = ?", argumentos);
    }

    public long nuevaFrase(FraseFamosa frase) {
        // writable porque vamos a insertar
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        ContentValues valoresParaInsertar = new ContentValues();
        valoresParaInsertar.put("texto", frase.getTexto());
        valoresParaInsertar.put("autor", frase.getAutor());
        valoresParaInsertar.put("fecha", frase.getFecha());
        return baseDeDatos.insert(NOMBRE_TABLA, null, valoresParaInsertar);
    }

    public int guardarCambios(FraseFamosa fraseEditada) {
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        ContentValues valoresParaActualizar = new ContentValues();
        valoresParaActualizar.put("texto", fraseEditada.getTexto());
        valoresParaActualizar.put("autor", fraseEditada.getAutor());
        valoresParaActualizar.put("fecha", fraseEditada.getFecha());
        // where id...
        String campoParaActualizar = "id = ?";
        // ... = idFrase
        String[] argumentosParaActualizar = {String.valueOf(fraseEditada.getId())};
        return baseDeDatos.update(NOMBRE_TABLA, valoresParaActualizar, campoParaActualizar, argumentosParaActualizar);
    }

    public ArrayList<FraseFamosa> obtenerFrases() {
        ArrayList<FraseFamosa> frases = new ArrayList<>();
        // readable porque no vamos a modificar, solamente leer
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getReadableDatabase();
        // SELECT texto, autor, id
        String[] columnasAConsultar = {"texto", "autor", "id","fecha"};
        Cursor cursor = baseDeDatos.query(
                NOMBRE_TABLA,//from frasesfamosas
                columnasAConsultar,
                null,
                null,
                null,
                null,
                "texto"
        );

        if (cursor == null) {
        /*
        Salimos aqu?? porque hubo un error, regresar
        lista vac??a
        */
            return frases;
        }
        // Si no hay datos, igualmente regresamos la lista vac??a
        if (!cursor.moveToFirst())
        {
            return frases;
        }

        // En caso de que s?? haya, iteramos y vamos agregando los
        // datos a la lista de frases
        do
        {
            // El 0 es el n??mero de la columna, como seleccionamos
            // texto, autor,id entonces el texto es 0, autor 1 e id es 2
            String textoObtenidoDeBD = cursor.getString(0);
            String autorObtenidoDeBD = cursor.getString(1);
            String fechaObtenidoDeBD = cursor.getString(3);
            long idFrase = cursor.getLong(2);
            FraseFamosa fraseObtenidaDeBD = new FraseFamosa(textoObtenidoDeBD,
                    autorObtenidoDeBD, idFrase, fechaObtenidoDeBD);
            frases.add(fraseObtenidaDeBD);
        }while (cursor.moveToNext());

        // Fin del ciclo. Cerramos cursor y regresamos la lista de frases :)
        cursor.close();
        return frases;
    }
}