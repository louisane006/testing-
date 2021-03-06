package com.example.malumukendi.assignment6activities.repos.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.malumukendi.assignment6activities.domain.Credit;
import com.example.malumukendi.assignment6activities.repos.CreditRepo;
import com.example.malumukendi.assignment6activities.conf.databases.DBConstants;

import java.util.HashSet;
import java.util.Set;


/**
 * Created by louisane Malu on images4/20/2016.
 */
public class CreditRepoImpl extends SQLiteOpenHelper implements CreditRepo {

    public static final String TABLE_NAME = "creditDetails";
    private SQLiteDatabase db;
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_AMOUNT = "amount";
    public static final String COLUMN_NAME = "name";

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_AMOUNT + " TEXT NOT NULL , "
            + COLUMN_NAME + " TEXT NOT NULL ); ";

    public CreditRepoImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }
    @Override
    public Credit findById(Long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        String.valueOf(COLUMN_AMOUNT),
                        COLUMN_NAME,},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Credit c = new Credit.Builder()
                    .identification(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .amount(cursor.getDouble(cursor.getColumnIndex(String.valueOf(COLUMN_AMOUNT))))
                    .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                    .build();
            return c;
        } else {
            return null;
        }
    }

    @Override
    public Credit save(Credit entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getIdentification());
        values.put(String.valueOf(COLUMN_AMOUNT), entity.getAmount());
        values.put(COLUMN_NAME, entity.getName());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Credit insertedEntity = new Credit.Builder()
                .copy(entity)
                .identification(new Long(id))
                .build();
        return insertedEntity;
    }
    @Override
    public Credit update(Credit entity){
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getIdentification());
        values.put(String.valueOf(COLUMN_AMOUNT), entity.getAmount());
        values.put(COLUMN_NAME, entity.getName());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getIdentification())}
        );
        return entity;
    }
    @Override
    public Credit delete(Credit entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getIdentification())});
        return entity;
    }

    @Override
    public Set<Credit> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Credit> c = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Credit credit = new Credit.Builder()
                        .identification(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .amount(cursor.getDouble(cursor.getColumnIndex(String.valueOf(COLUMN_AMOUNT))))
                        .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                        .build();
                c.add(credit);
            } while (cursor.moveToNext());
        }
        return c;
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