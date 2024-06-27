package daniel.brian.xpressapp.customer.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BookingDB extends SQLiteOpenHelper {
    public BookingDB(@Nullable Context context) {
        super(context, "Appointments.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "Create Table Appointments("+
                "service Text not null,"+
                "branch Text not null,"+
                "firstname Text not null,"+
                "phone Text not null PRIMARY KEY,"+
                "time Text not null,"+
                "date Text not null,"+
                "carReg Text not null unique," +
                "carModel Text not null,"+
                "constraint appointment unique (firstname,phone))"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists Appointments");
    }

    // function to book appointments
    public boolean bookAppointment(String service,
                                   String branch,
                                   String firstname,
                                   String phone,
                                   String time,
                                   String date,
                                   String carReg,
                                   String carModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("service",service);
        contentValues.put("branch",branch);
        contentValues.put("firstname",firstname);
        contentValues.put("phone",phone);
        contentValues.put("time",time);
        contentValues.put("date",date);
        contentValues.put("carReg",carReg);
        contentValues.put("carModel",carModel);

        long result = db.insert("Appointments",null,contentValues);
        return result != -1;
    }

    public Cursor getAllBookings(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from Appointments",null);
    }
}
