package com.example.android.networkconnect.com.server.data.datas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Keyword {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("personId")
    @Expose
    private Integer personId;
    @SerializedName("name")
    @Expose
    private String name;

    private boolean toDelete;

    public boolean isToDelete() {
        return toDelete;
    }

    public void setToDelete(boolean toDelete) {
        this.toDelete = toDelete;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Keyword{" +
                "personId=" + personId +
                ", name='" + name + '\'' +
                '}';
    }
}