package daniel.brian.xpressapp.admin.db;

import android.content.ContentValues;
import android.content.Context;
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
                "phone not null unique,"+
                "password not null,"+
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
}
