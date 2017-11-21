package com.example.android.networkconnect.com.server.data;


import com.example.android.networkconnect.com.server.data.datas.CommonStatistic;
import com.example.android.networkconnect.com.server.data.datas.DaylyStatistic;
import com.example.android.networkconnect.com.server.data.datas.Person;
import com.example.android.networkconnect.com.server.data.datas.Site;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.SimpleTimeZone;

public interface DataManagerListener {

    void updateListOfSites(HashSet<Site> sites);
    void updateListOfPersons(HashSet<Person> sites);
    void userCreatingResponse(String msg);
    void updateGeneralStatistic(ArrayList<CommonStatistic> commonStatistics);
    void updateDaylyStatistic(ArrayList<DaylyStatistic> daylyStatistics);
}
