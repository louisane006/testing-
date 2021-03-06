package com.example.malumukendi.assignment6activities.repos.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.malumukendi.assignment6activities.domain.Manager;
import com.example.malumukendi.assignment6activities.repos.ManagerRepo;
import com.example.malumukendi.assignment6activities.conf.databases.DBConstants;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by louisane Malu on images4/20/2016.
 */
public class ManagerRepoImpl extends SQLiteOpenHelper implements ManagerRepo {

    public static final String TABLE_NAME = "managerDetails";
    private SQLiteDatabase db;


    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SURNAME = "surname";
    public static final String COLUMN_TASKNUM = "taskNum";

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME + " TEXT NOT NULL , "
            + COLUMN_SURNAME + " TEXT NOT NULL , "
            + COLUMN_TASKNUM + " TEXT ); ";

    public ManagerRepoImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public Manager findById(Long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_NAME,
                        COLUMN_SURNAME,
                        COLUMN_TASKNUM,},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Manager c = new Manager.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                    .surname(cursor.getString(cursor.getColumnIndex(COLUMN_SURNAME)))
                    .taskNum(cursor.getString(cursor.getColumnIndex(COLUMN_TASKNUM)))
                    .build();
            return c;
        } else {
            return null;
        }
    }

    @Override
    public Manager save(Manager entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_NAME, entity.getName());
        values.put(COLUMN_SURNAME, entity.getSurname());
        values.put(COLUMN_TASKNUM, entity.getTaskNumber());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Manager insertedEntity = new Manager.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }
    @Override
    public Manager update(Manager entity){
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_NAME, entity.getName());
        values.put(COLUMN_SURNAME, entity.getSurname());
        values.put(COLUMN_TASKNUM, entity.getTaskNumber());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }
    @Override
    public Manager delete(Manager entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Manager> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Manager> m = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Manager man = new Manager.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                        .surname(cursor.getString(cursor.getColumnIndex(COLUMN_SURNAME)))
                        .taskNum(cursor.getString(cursor.getColumnIndex(COLUMN_TASKNUM)))
                        .build();
                m.add(man);
            } while (cursor.moveToNext());
        }
        return m;
    }
    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = db.delete(TABLE_NAME,null,null);
        close();
        return rowsDeleted;
    }

    @Override
    public boolean insertData(String email, String password, String name, String surname) {
        return false;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}