package com.example.pauliusklezys.birdwatching;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Observation implements Serializable {
    @SerializedName("BirdId") // Name of JSON attribute. Used for GSON de-serialization
    private int birdId;
    @SerializedName("Comment")
    private String comment;
    @SerializedName("Created")
    private String created;
    @SerializedName("Id")
    private int id;
    @SerializedName("Latitude")
    private String latitude;
    @SerializedName("Longitude")
    private String longitude;
    @SerializedName("Placename")
    private String placename;
    @SerializedName("Population")
    private int population;
    @SerializedName("UserId")
    private String userId;
    @SerializedName("NameDanish")
    private String nameDanish;
    @SerializedName("NameEnglish")
    private String nameEnglish;
    @SerializedName("PhotoUrl")
    private String photoUrl;


    public Observation() {
    }

    public Observation(int birdId, String comment, String created, int id, String latitude, String longitude, String placename, int population, String userId, String nameDanish, String nameEnglish, String photoUrl) {
        this.birdId = birdId;
        this.comment = comment;
        this.created = created;
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.placename = placename;
        this.population = population;
        this.userId = userId;
        this.nameDanish = nameDanish;
        this.nameEnglish = nameEnglish;
        this.photoUrl = photoUrl;
    }

    public void setBirdId(int birdId) {
        this.birdId = birdId;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setPlacename(String placename) {
        this.placename = placename;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setNameDanish(String nameDanish) {
        this.nameDanish = nameDanish;
    }

    public void setNameEnglish(String nameEnglish) {
        this.nameEnglish = nameEnglish;
    }

    public void setPhotoUrl(String photoUrl) { this.photoUrl = photoUrl; }


    public int getBirdId() {
        return birdId;
    }

    public String getComment() {
        return comment;
    }

    public String getCreated() {
        return created;
    }

    public int getId() {
        return id;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getPlacename() {
        return placename;
    }

    public int getPopulation() {
        return population;
    }

    public String getUserId() {
        return userId;
    }

    public String getNameDanish() {
        return nameDanish;
    }

    public String getNameEnglish() {
        return nameEnglish;
    }

    public String getPhotoUrl() { return photoUrl;}

    @Override
    public String toString() {
        return photoUrl + nameEnglish;
    }
}
