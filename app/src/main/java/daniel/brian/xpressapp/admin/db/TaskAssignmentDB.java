package daniel.brian.xpressapp.admin.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class TaskAssignmentDB extends SQLiteOpenHelper {
    public TaskAssignmentDB(@Nullable Context context) {
        super(context, "Tasks.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table Tasks(" +
                "name Text not null,"+
                "carReg Text not null PRIMARY KEY,"+
                "carModel Text not null,"+
                "time Text not null,"+
                "service Text not null)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists Tasks");
    }

    public boolean createTask(String name,String carReg,String carModel,String time,String service){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("carReg",carReg);
        contentValues.put("carModel",carModel);
        contentValues.put("time",time);
        contentValues.put("service",service);
        long result = db.insert("Tasks",null,contentValues);
        return  result != -1;
    }

}
