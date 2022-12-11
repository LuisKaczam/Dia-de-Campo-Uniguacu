package com.uniguacu.dia_de_campo.banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.uniguacu.dia_de_campo.Codigo;
import com.uniguacu.dia_de_campo.Usuario;

import java.util.ArrayList;
import java.util.List;

public class BancoDados extends SQLiteOpenHelper {
    public BancoDados(@Nullable Context context) {
        super(context, "DiaCampo", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlPessoa = "CREATE TABLE IF NOT EXISTS Usuarios (id VARCHAR(29), nome VARCHAR(20), pnts INTEGER, foto INTEGER);";
        String sqlCodigo = "CREATE TABLE IF NOT EXISTS Codigos (codId  VARCHAR(70), codigo VARCHAR(10) UNIQUE);";
        sqLiteDatabase.execSQL(sqlPessoa);
        sqLiteDatabase.execSQL(sqlCodigo);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sqlP = "DROP TABLE IF EXISTS Usuarios";
        String sqlC = "DROP TABLE IF EXISTS Codigos";
        sqLiteDatabase.execSQL(sqlP);
        sqLiteDatabase.execSQL(sqlC);
        onCreate(sqLiteDatabase);

    }

    public void InsereUser(Usuario pessoa){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", pessoa.getId());
        contentValues.put("nome", pessoa.getNome());
        contentValues.put("pnts", pessoa.getPnts());
        contentValues.put("foto", pessoa.getFoto());
        databaseReference.child("Usuarios").child(pessoa.getId()).setValue(pessoa);
        database.insert("Usuarios", null, contentValues);
    }
    public void InsereCodigo(Codigo codigo){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("codId", codigo.getIdCod());
        contentValues.put("codigo", codigo.getCod());
        database.insert("Codigos", null, contentValues);
    }

    public List<Usuario> DadosUser(){
        SQLiteDatabase database = getReadableDatabase();
        List<Usuario> lista = new ArrayList<Usuario>();
        Cursor cursor = database.rawQuery("SELECT * FROM Usuarios", null);
        while (cursor.moveToNext()){
            Usuario usuario = new Usuario();
            usuario.setId(cursor.getString(0));
            usuario.setNome(cursor.getString(1));
            usuario.setPnts(cursor.getInt(2));
            usuario.setFoto(cursor.getInt(3));
            lista.add(usuario);
        }
        return lista;
    }
    public List<Codigo> DadosCodigo(){
        SQLiteDatabase database = getReadableDatabase();
        List<Codigo> lista = new ArrayList<Codigo>();
        Cursor cursor = database.rawQuery("SELECT * FROM Codigos", null);
        while (cursor.moveToNext()){
            Codigo codigo = new Codigo();
            codigo.setIdCod(cursor.getString(0));
            codigo.setCod(cursor.getString(1));
            lista.add(codigo);
        }
        return lista;
    }

    public void UpgradePnts(String id, int pnts){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Usuarios");
        SQLiteDatabase database = getWritableDatabase();
        String update = "UPDATE Usuarios SET pnts = " +pnts;
        database.execSQL(update);
        databaseReference.child(id).child("pnts").setValue(pnts);
    }

}
