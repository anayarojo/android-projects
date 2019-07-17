package com.BarCodeProductos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ClaseSQL extends SQLiteOpenHelper{

    public ClaseSQL(Context context){
        super(context,"BDScannerProductos",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String Tabla = "CREATE TABLE Producto(Codigo CHAR(20) PRIMARY KEY, Producto CHAR(80), Precio CHAR(20))";
        sqLiteDatabase.execSQL(Tabla);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXIST Producto");
        onCreate(sqLiteDatabase);
    }

    public void Insert(String Codigo, String Producto, String Precio){
        ContentValues Valor = new ContentValues();
        Valor.put("Codigo",Codigo);
        Valor.put("Producto",Producto);
        Valor.put("Precio",Precio);
        this.getWritableDatabase().insert("Producto",null,Valor);
    }

    public Cursor ConsultaGeneral(){
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM Producto ORDER BY Producto",null);
        return cursor;
    }

    public Cursor ConsultaCount(){
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT COUNT(*)as Cont FROM Producto",null);
        return cursor;
    }

    public Cursor ConsultaFiltro(String Codigo){
      Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM Producto WHERE Codigo = '"+Codigo+"'",null);
      return cursor;
    }

    public Cursor ConsultaFiltroProducto(String Producto){
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM Producto WHERE Producto = '"+Producto+"'",null);
        return cursor;
    }

    public void ModificarProducto(SQLiteDatabase sqLiteDatabase, String Codigo, String Producto, String Precio){
        String Modificar = "UPDATE Producto SET Producto = '"+Producto+"', Precio = '"+Precio+"' WHERE Codigo = '"+Codigo+"'";
        sqLiteDatabase.execSQL(Modificar);
    }

    public void EliminarProducto(String Codigo){
        this.getReadableDatabase().delete("Producto","Codigo="+Codigo,null);
    }

    public void DeleteFromTable(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL("DELETE FROM Producto");
    }
}
