package daniel.brian.xpressapp.admin.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class InvoiceDB extends SQLiteOpenHelper {
    public InvoiceDB(@Nullable Context context) {
        super(context, "Invoices.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table Invoices("+
                "owner Text not null,"+
                "phone Text not null unique,"+
                "carReg Text not null PRIMARY KEY,"+
                "carModel Text not null,"+
                "serviceDone Text not null,"+
                "date Text not null,"+
                "servedby Text not null,"+
                "totalAmount Text not null)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists Invoices");
    }

    public boolean createInvoice(String owner,String phone,String carReg,String carModel,String serviceDone,String date,String servedBy,String totalAmount){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("owner",owner);
        contentValues.put("phone",phone);
        contentValues.put("carReg",carReg);
        contentValues.put("carModel",carModel);
        contentValues.put("serviceDone",serviceDone);
        contentValues.put("date",date);
        contentValues.put("servedBy",servedBy);
        contentValues.put("totalAmount",totalAmount);

        long result = db.insert("Invoices",null,contentValues);
        return  result != -1;
    }

    public Cursor getAllInvoices(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from Invoices",null);
    }
}
