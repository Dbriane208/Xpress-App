package daniel.brian.xpressapp.employee.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CompletedTasksDB extends SQLiteOpenHelper {
    public CompletedTasksDB(@Nullable Context context) {
        super(context, "CompletedTasks.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table completedTasks("+
                "employee Text not null,"+
                "carReg Text not null,"+
                "carModel Text not null,"+
                "product Text not null,"+
                "service Text not null,"+
                "totalAmount Text not null)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists completedTasks");
    }

    public boolean completedTasks(String employee,String carReg,String carModel,String product,String service,String totalAmount){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("employee",employee);
        contentValues.put("carReg",carReg);
        contentValues.put("carModel",carModel);
        contentValues.put("product",product);
        contentValues.put("service",service);
        contentValues.put("totalAmount",totalAmount);

        long result = db.insert("completedTasks",null,contentValues);
        return  result != -1;
    }

    public Cursor getAllBookings(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from completedTasks",null);
    }
}
