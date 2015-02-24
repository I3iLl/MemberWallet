package com.example.i3ill.memberwallet;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by I3iLL on 24/2/2558.
 */
public class MemberDB extends SQLiteOpenHelper {
    private static final String name = "courses.sqlite3";
    private static final int version = 2;


    public MemberDB(Context ctx) {
        super(ctx, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE member (" +
                "_id integer primary key autoincrement," +
                "memName text not null," +
                "memID int not null," +
                "memOwner text default 0," +
                "memTel int default 0," +
                "validFrom int default 0," +
                "validThru int default 0," +
                "value real default 0.0);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS course;";
        db.execSQL(sql);
        this.onCreate(db);
    }
}