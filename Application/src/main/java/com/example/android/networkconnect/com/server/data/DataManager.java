package com.example.android.networkconnect.com.server.data;

import android.annotation.SuppressLint;

import com.example.android.networkconnect.com.server.data.datas.Keyword;
import com.example.android.networkconnect.com.server.data.datas.Person;
import com.example.android.networkconnect.com.server.data.datas.Site;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class DataManager {

    public static final String SUCCESS_ADDING = "success_adding";
    public static final String FAILED_ADDING = "failed_adding";

    private HashSet<Person> personList = new HashSet<>();
    private HashSet<Site> siteList = new HashSet<>();

    private NetworkManager networkManager = new NetworkManager(this);
    DataManagerListener dataManagerListener;

    public DataManager(DataManagerListener dataManagerListener){
        this.dataManagerListener = dataManagerListener;
    }

    public void getSiteList(boolean wasUpdated){
        if (siteList.isEmpty()|| wasUpdated) networkManager.getDataAllSites();
        else dataManagerListener.updateListOfSites(siteList);
    }

    void siteListFromServer(List<Site> sites){
        if (!sites.isEmpty()) {
            if(!siteList.isEmpty()) siteList.clear();
            siteList.addAll(sites);
            dataManagerListener.updateListOfSites(siteList);
        }
        else dataManagerListener.updateListOfSites(null);
    }

    public void getPersonList(boolean wasUpdated){
        if (personList.isEmpty()|| wasUpdated) networkManager.getDataAllPerson(false);
        else dataManagerListener.updateListOfPersons(personList);
    }

    private HashMap<Integer,Person> bufPersonList = new HashMap<>();

    void personListFromServer(List<Person> persons){
        if (!persons.isEmpty()) {
            if (!personList.isEmpty()) personList.clear();
            for (Person p : persons) {
                bufPersonList.put(p.getId(), p);
                networkManager.getDataKeywordsByPerson(p.getId());
            }
        }
        else dataManagerListener.updateListOfPersons(personList);
    }

    synchronized void keywordsListFromServer(List<Keyword> keywords, int personId){
        Person p = bufPersonList.get(personId);
        p.setKeywordList(keywords);
        personList.add(p);
        bufPersonList.remove(personId);
        if (bufPersonList.isEmpty()) dataManagerListener.updateListOfPersons(personList);
    }

    private Person bufPerson;

    public void addOrEditPerson(Person person){
        if (person.getId() == null) {
            bufPerson = person;
            networkManager.addPerson(person.getName());
        }
    }

    void responseFromServerAddingPerson(String response){
        if (response == null) {
            bufPerson = null;
            dataManagerListener.userCreatingResponse(FAILED_ADDING);
            return;
        }
        networkManager.getDataAllPerson(true);
    }

    void listOfPersonToAddKeywords(List<Person> persons){
        if (persons.isEmpty()) {
            dataManagerListener.userCreatingResponse(FAILED_ADDING);
            return;
        }

        int neededFuckingId = 0;
        for (Person p : persons) {
            if (p.getName().equals(bufPerson.getName())) {
                neededFuckingId = p.getId();
                break;
            }
        }
        ArrayList<Keyword> arr = bufPerson.getKeywordList();
        if (!arr.isEmpty() && neededFuckingId!=0) {
            for(Keyword k: arr){
                networkManager.addKeyword(k.getName(),neededFuckingId);
            }
        }
        dataManagerListener.userCreatingResponse(SUCCESS_ADDING);
        bufPerson = null;
    }
}
