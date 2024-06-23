package daniel.brian.xpressapp.auth.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AuthenticationDB extends SQLiteOpenHelper {
    public AuthenticationDB(@Nullable Context context) {
        super(context, "users.db", null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create Table users(" +
                "username Text not null," +
                "email Text not null unique," +
                "password Text not null, " +
                "constraint username unique (username,email))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop Table if exists users");
    }

    // function to register users
    public boolean registerUser(String username,String email,String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("email",email);
        contentValues.put("password",password);
        long result = db.insert("users",null,contentValues);
        return result != -1;
    }

    // function to login registered users
    public boolean loginUser(String email,String password){
        SQLiteDatabase db = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("select * from users where email = ? and password = ?",new String[]{email,password});
        return cursor.getCount() > 0;
    }

    // update password
    public boolean UpdatePassword(String email,String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("password",password);
        long forgotPass = db.update("users",contentValues,"email = ?",new String[]{email});
        return forgotPass != -1;
    }
}
