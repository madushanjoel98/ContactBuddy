package com.example.contactview.storage;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.contactview.dto.Contact;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ContactDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "contacts.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_CONTACTS = "contacts";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PHONE = "phone";

    public ContactDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_CONTACTS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                COLUMN_NAME + " TEXT NOT NULL, " +
                COLUMN_EMAIL + " TEXT, " +
                COLUMN_PHONE + " TEXT NOT NULL" +
                ")";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(db);
    }

    public long addContact(Contact contact) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, contact.getName());
        values.put(COLUMN_EMAIL, contact.getEmail());
        values.put(COLUMN_PHONE, contact.getPhoneNumber());
        long id = db.insert(TABLE_CONTACTS, null, values);
        db.close();
        return id;
    }

    public List<Contact> getAllContacts() {
        List<Contact> contacts = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_CONTACTS, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range")
                long id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                @SuppressLint("Range")
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                @SuppressLint("Range")
                String email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));
                @SuppressLint("Range")
                String phone = cursor.getString(cursor.getColumnIndex(COLUMN_PHONE));
                Contact contact = new Contact(id, name, email, phone);
                contacts.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return contacts;
    }

    public void deleteContact(long contactId) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_CONTACTS, COLUMN_ID + "=?", new String[]{String.valueOf(contactId)});
        db.close();
    }
    public void updateContact(Contact contact) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, contact.getName());
        values.put(COLUMN_EMAIL, contact.getEmail());
        values.put(COLUMN_PHONE, contact.getPhoneNumber());

        db.update(TABLE_CONTACTS, values, COLUMN_ID + "=?", new String[]{String.valueOf(contact.getId())});
        db.close();
    }
    public Contact getContactById(long contactId) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_CONTACTS, null, COLUMN_ID + "=?", new String[]{String.valueOf(contactId)}, null, null, null);

        Contact contact = null;
        if (cursor.moveToFirst()) {
            @SuppressLint("Range")
            long id = cursor.getLong(cursor.getColumnIndex(COLUMN_ID));
            @SuppressLint("Range")
            String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
            @SuppressLint("Range")
            String email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));
            @SuppressLint("Range")
            String phone = cursor.getString(cursor.getColumnIndex(COLUMN_PHONE));
            contact = new Contact(id, name, email, phone);
        }

        cursor.close();
        db.close();
        return contact;
    }
    public List<Contact> getAllContactsAlphabetical() {
        List<Contact> contacts = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_CONTACTS, null, null, null, null, null, COLUMN_NAME + " ASC");

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range")
                long id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                @SuppressLint("Range")
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                @SuppressLint("Range")
                String email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));
                @SuppressLint("Range")
                String phone = cursor.getString(cursor.getColumnIndex(COLUMN_PHONE));
                Contact contact = new Contact(id, name, email, phone);
                contacts.add(contact);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        // Sort contacts alphabetically
        Collections.sort(contacts, new Comparator<Contact>() {
            @Override
            public int compare(Contact contact1, Contact contact2) {
                return contact1.getName().compareToIgnoreCase(contact2.getName());
            }
        });

        return contacts;
    }
}
