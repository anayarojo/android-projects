package com.AndroidCamara;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ClaseSQL extends SQLiteOpenHelper{

    public ClaseSQL(Context context){
        super(context,"BDImagen",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
       String Tabla = "CREATE TABLE Imagen(ID VARCHAR(10) PRIMARY KEY, Image BLOB)";
       sqLiteDatabase.execSQL(Tabla);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
       sqLiteDatabase.execSQL("DROP TABLE IF EXIST Imagen");
       onCreate(sqLiteDatabase);
    }

    public void Insert(String ID, byte[] Image){
        ContentValues Valores = new ContentValues();
        Valores.put("ID",ID);
        Valores.put("Image",Image);
        this.getWritableDatabase().insert("Imagen",null,Valores);
    }

    public Cursor FiltroImagen(String ID){
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT Image FROM Imagen WHERE ID='"+ID+"'",null);
        return cursor;
    }

    public Cursor ConsultaID(String ID){
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM Imagen WHERE ID='"+ID+"'",null);
        return cursor;
    }

    public void Eliminar(String ID){
        this.getWritableDatabase().delete("Imagen","ID="+ID,null);
    }

    public void EliminarTodo(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL("DELETE FROM Imagen");
    }

}
