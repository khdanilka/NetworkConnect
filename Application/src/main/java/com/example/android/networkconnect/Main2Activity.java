package com.example.android.networkconnect;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.android.networkconnect.com.server.data.DataManager;
import com.example.android.networkconnect.com.server.data.DataManagerListener;
import com.example.android.networkconnect.com.server.data.NetworkManager;
import com.example.android.networkconnect.com.server.data.datas.Keyword;
import com.example.android.networkconnect.com.server.data.datas.Person;
import com.example.android.networkconnect.com.server.data.datas.Site;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main2Activity extends FragmentActivity implements DataManagerListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        DataManager dataManager = new DataManager(this);
        //dataManager.getSiteList(false);
        //dataManager.getPersonList(false);


        Person p = new Person();
        p.setName("ГНОМИЩЕ");
        Keyword k = new Keyword();
        k.setName("гном");
        Keyword k1 = new Keyword();
        k1.setName("гном111");
        List<Keyword> key = Arrays.asList(k,k1);
        p.setKeywordList(key);
        dataManager.addOrEditPerson(p);

    }

    @Override
    public void updateListOfSites(HashSet<Site> sites) {
        if (sites!=null) {
            Iterator iterator = sites.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        }
    }

    @Override
    public void updateListOfPersons(HashSet<Person> persons) {
        if (persons!=null) {
            Iterator iterator = persons.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        }
    }

    @Override
    public void userCreatingResponse(String msg) {

        if (msg.equals(DataManager.SUCCESS_ADDING)) System.out.println("создали юзера");
        else if (msg.equals(DataManager.FAILED_ADDING)) System.out.println("юзер не создан");

    }



}
