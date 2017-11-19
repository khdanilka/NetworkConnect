package com.example.android.networkconnect.com.server.data.datas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Person {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("active")
    @Expose
    private Boolean active;

    ArrayList<Keyword> keywordList = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", active=" + active +
                ", keywordList=" + keywordList +
                '}';
    }

    public void setKeywordList(List<Keyword> l){
        if (!keywordList.isEmpty()) keywordList.clear();
        keywordList.addAll(l);
    }

    public ArrayList<Keyword> getKeywordList(){
        return keywordList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        return id != null ? id.equals(person.id) : person.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
