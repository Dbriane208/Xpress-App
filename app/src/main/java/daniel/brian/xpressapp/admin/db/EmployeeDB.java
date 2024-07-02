package daniel.brian.xpressapp.admin.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class EmployeeDB  extends SQLiteOpenHelper {
    public EmployeeDB(@Nullable Context context) {
        super(context, "Employees.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table Employees("+
                "username Text not null,"+
                "email Text not null PRIMARY KEY,"+
                "phone Text not null unique,"+
                "password Text not null,"+
                "constraint employee unique(email,phone))"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists Employees");
    }

    public boolean registerEmployee(String name,String email,String phone,String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",name);
        contentValues.put("email",email);
        contentValues.put("phone",phone);
        contentValues.put("password",password);

        long result = db.insert("Employees",null,contentValues);
        return result != -1;
    }

    public boolean loginEmployee(String email,String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from Employees where email = ? and password = ?",new String[]{email,password});
        return cursor.getCount() > 0;
    }

    public boolean checkEmployee(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from Employees where username = ?",new String[]{name});
        return cursor.getCount() > 0;
    }

    public int getAllEmployees(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select count(*) from Employees",null);

        int count = 0;

        if(cursor.moveToFirst()){
            count = cursor.getInt(0);
        }

        cursor.close();
        return count;
    }

}
