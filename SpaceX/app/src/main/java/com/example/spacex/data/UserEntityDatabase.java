package com.example.spacex.data;


import androidx.room.ColumnInfo;
import androidx.room.Dao;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.PrimaryKey;
import androidx.room.Query;

@Entity (indices = {@Index(value = {"Email"},unique = true)})
public class UserEntityDatabase {
    @PrimaryKey(autoGenerate = true)
    private int ID;
    @ColumnInfo(name = "Email")
    private String Email;
    @ColumnInfo(name = "Name")
    private String Name;
    @ColumnInfo(name = "Password")
    private String Password;

    public UserEntityDatabase(){
    }

    public UserEntityDatabase(String Name, String Email, String Password){
        this.Name =Name;
        this.Email =Email;
        this.Password = Password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}

@Dao
interface UserDao{

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public long InsertPerson(UserEntityDatabase userDatabase);

    @Query("SELECT Email FROM UserEntityDatabase WHERE Email =:email AND Password=:password")
    public String GetOneUser(String email, String password);

}
