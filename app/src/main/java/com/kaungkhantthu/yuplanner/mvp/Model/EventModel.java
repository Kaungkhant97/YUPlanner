package com.kaungkhantthu.yuplanner.mvp.Model;


import com.kaungkhantthu.yuplanner.data.entity.Event;

import java.util.Date;

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


    void clearEvents();
    RealmResults<Event> getEventFromCache();
}
