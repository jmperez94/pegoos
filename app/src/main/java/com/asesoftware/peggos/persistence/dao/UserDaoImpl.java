package com.asesoftware.peggos.persistence.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.asesoftware.peggos.persistence.dto.User;
import com.asesoftware.peggos.persistence.contract.Contract;

import java.util.ArrayList;

public class UserDaoImpl extends SQLiteOpenHelper implements UserDao {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "USERS.db";

    public static final String TABLE_NAME = "USERS";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_NOMBRE = "NOMBRE";


    public UserDaoImpl(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public ContentValues toContentValues(User user) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOMBRE, user.getNombre());
        return values;
    }

    public User getUser(Cursor cursor) {
        User user = new User();
        user.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
        user.setNombre(cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE)));
        return user;
    }

    @Override
    public ArrayList<User> getAllUsers() {
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<User> output = new ArrayList<>();
        Cursor c = db.query(
                TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);
        if (c.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                output.add(getUser(c));
            } while (c.moveToNext());
        }
        return output;
    }

    @Override
    public int updateUser(User user) {
        return getWritableDatabase().update(
                TABLE_NAME,
                toContentValues(user),
                COLUMN_ID + " LIKE ?",
                new String[]{"" + user.getId()}
        );
    }

    public int initDatabase(SQLiteDatabase db) {
        ArrayList<User> usersTest = new ArrayList<>();
        usersTest.add(new User("Juan"));
        usersTest.add(new User("Pedro"));
        usersTest.add(new User("Josefa"));
        usersTest.add(new User("Martina"));
        usersTest.add(new User("Felipa"));
        usersTest.add(new User("Dennis"));
        usersTest.add(new User("Lucas"));
        usersTest.add(new User("Roberto"));
        usersTest.add(new User("Federica"));
        usersTest.add(new User("Jorge"));
        usersTest.add(new User("Brayan"));
        usersTest.add(new User("Johan"));
        usersTest.add(new User("Luisa"));
        usersTest.add(new User("Camilo"));
        usersTest.add(new User("Yeisson"));
        usersTest.add(new User("Carlos"));
        usersTest.add(new User("asdasd"));

        for (User user : usersTest) {
            db.insert(
                    TABLE_NAME,
                    null,
                    toContentValues(user));
        }
        return usersTest.size();
    }

    @Override
    public int deleteUser(User user) {
        return getWritableDatabase().delete(
                TABLE_NAME,
                COLUMN_ID + " LIKE ?",
                new String[]{"" + user.getId()});
    }

    @Override
    public long addUser(User user) {
        return getWritableDatabase().insert(
                TABLE_NAME,
                null,
                toContentValues(user));
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " " + Contract.tipoDato.INTEGER + " " + Contract.PRIMARY + " " + Contract.IDENTITY
                + "," + COLUMN_NOMBRE + " " + Contract.tipoDato.TEXT + " " + Contract.nulleable.NOTNULL
                + "); ";
        db.execSQL(query);
        initDatabase(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
