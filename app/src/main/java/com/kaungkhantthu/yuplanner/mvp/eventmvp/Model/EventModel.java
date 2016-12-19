package com.kaungkhantthu.yuplanner.mvp.eventmvp.Model;


import com.kaungkhantthu.yuplanner.data.entity.Event;

import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by HeinHtetOo on 14/11/2016.
 */

public interface EventModel {
    void saveEvent(RealmList<Event> events);



    void getAllEventFromServer(EventModelImpl.Callback c);

    void getEventFromServer(EventModelImpl.Callback c, Date d);
    void getEventsFromServerBetween(EventModelImpl.Callback c,Date startDate,Date endDate);


    RealmResults<Event> getEvent(String id);
    void clearEvents();
    RealmResults<Event> getAllEventFromCache();
    List<Event> getEventsFor(Date d);
}
