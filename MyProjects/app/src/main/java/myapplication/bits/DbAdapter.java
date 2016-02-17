package myapplication.bits;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Trainer on 12/24/2015.
 */
public class DbAdapter {
    private static final String MY_DB="mydb";
    private static int DB_VERSION = 1;
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_ID = "_id";
    private static final String TABLE_NAME = "users";
    private static final String TABLE_CREAT_SQL_QUERY = "create table "+ TABLE_NAME
            +" (_id integer primary key autoincrement, "
            + "name text not null, " + "email text not null);";
    private Context context;
    private DbHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;

    public DbAdapter(Context context){
        this.context = context;
        dbHelper = new DbHelper(context);
    }

    private static class DbHelper extends SQLiteOpenHelper{

        public DbHelper(Context context){
            super(context,MY_DB, null, DB_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(TABLE_CREAT_SQL_QUERY);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

    public void open(){
        sqLiteDatabase = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    public boolean insertUser(String name, String email){
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME, name);
        contentValues.put(KEY_EMAIL, email);
        return sqLiteDatabase.insert(TABLE_NAME, null, contentValues) > 0 ? true:false;
    }

    public Cursor getUsers(){
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, new String[]{KEY_NAME,KEY_EMAIL}, null, null, null,null,null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
        }
        return cursor;
    }

}
