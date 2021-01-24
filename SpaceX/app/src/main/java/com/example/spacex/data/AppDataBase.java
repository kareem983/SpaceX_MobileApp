package com.example.spacex.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {UserEntityDatabase.class},version = 1)
public abstract class AppDataBase extends RoomDatabase {

    public abstract UserDao userDao();

    public long InsertPerson(UserEntityDatabase person){
        return userDao().InsertPerson(person);
    }

    public String getPersonEmail(String email, String password){
        return userDao().GetUserEmail(email, password);
    }

    public String getPersonName(String email){
        return userDao().GetUserName(email);
    }

}
