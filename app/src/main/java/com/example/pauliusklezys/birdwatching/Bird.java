package com.example.pauliusklezys.birdwatching;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Bird implements Serializable {

    @SerializedName("Created")
    private String created;
    @SerializedName("Id")
    private int id;
    @SerializedName("NameDanish")
    private String nameDanish;
    @SerializedName("NameEnglish")
    private String nameEnglish;
    @SerializedName("PhotoUrl")
    private String photoUrl;
    @SerializedName("UserId")
    private int userId;

    public Bird() {
    }

    public Bird(String created, int id, String nameDanish, String nameEnglish, String photoUrl, int userId) {
        this.created = created;
        this.id = id;
        this.nameDanish = nameDanish;
        this.nameEnglish = nameEnglish;
        this.photoUrl = photoUrl;
        this.userId = userId;
    }

    public void setCreated(String created) {
        this.created = created;
    }
    public void setId(int  id) {
        this.id = id;
    }
    public void setNameDanish(String nameDanish) {this.nameDanish = nameDanish;}
    public void setNameEnglish(String nameEnglish) {this.nameEnglish = nameEnglish;}
    public void setPhotoUrl(String photoUrl) {this.photoUrl = photoUrl;}
    public void setUserId(int userId) {this.userId = userId;}

    public String getCreated() {
        return created;
    }
    public int getId() {return id;}
    public String getNameDanish() {return nameDanish;}
    public String getNameEnglish() {return nameEnglish;}
    public String getPhotoUrl() {return photoUrl;}
    public int getUserId() {return userId;}

    @Override
    public String toString() {
        return created + id + nameDanish + nameEnglish + photoUrl + userId;
    }

}
