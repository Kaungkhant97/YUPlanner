package com.kaungkhantthu.yuplanner.mvp.eventmvp.Model;


import com.kaungkhantthu.yuplanner.data.entity.Event;
import com.kaungkhantthu.yuplanner.data.entity.EventResponse;
import com.kaungkhantthu.yuplanner.network.api.RetrofitClient;
import com.kaungkhantthu.yuplanner.utils.Utils;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by kaungkhantthu on 11/16/16.
 */

public class EventModelImpl implements EventModel {

    private static EventModelImpl eventModel;
    private static Realm realm;

    private EventModelImpl() {

    }

    public static EventModel getInstance() {
        if (eventModel == null) {
            eventModel = new EventModelImpl();

            realm = Realm.getDefaultInstance();

        }
        return eventModel;
    }

    @Override
    public void saveEvent(RealmList<Event> events) {
        realm.beginTransaction();
        RealmList<Event> eventList = new RealmList<Event>();
        for (Event e : events) {
            try {
                Calendar c = Calendar.getInstance();
                c.setTime(Utils.formatDate(e.getPublishedDate()));
                c.setTimeZone(Calendar.getInstance().getTimeZone());
                e.setFormattedDate(c.getTime());
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
            eventList.add(e);
        }
        realm.copyToRealm(events);
        realm.commitTransaction();
    }


    @Override
    public void getAllEventFromServer(final Callback c) {
        RetrofitClient.getInstance().getService().getEventList().enqueue(new retrofit2.Callback<EventResponse>() {
            @Override
            public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getData().size() > 0) {
                        c.onSuccess(response.body().getData());
                    }
                }
            }

            @Override
            public void onFailure(Call<EventResponse> call, Throwable t) {
                c.onError();
            }
        });

    }

    @Override
    public void getEventFromServer(Callback c, Date d) {

    }

    @Override
    public void getEventsFromServerBetween(Callback c, Date startDate, Date endDate) {

    }

    @Override
    public RealmResults<Event> getEvent(String id) {
        realm.beginTransaction();
        RealmResults<Event> result = realm.where(Event.class).equalTo("id",id).findAll();
        realm.commitTransaction();
        return result;
    }


    @Override
    public void clearEvents() {
        realm.beginTransaction();
        realm.where(Event.class).findAll().deleteAllFromRealm();
        realm.commitTransaction();
    }

    @Override
    public RealmResults<Event> getAllEventFromCache() {
        realm.beginTransaction();
        RealmResults<Event> realmlist = realm.where(Event.class).findAll();

        realm.commitTransaction();

        return realmlist;
    }

    @Override
    public List<Event> getEventsFor(Date fromDate) {
        Calendar c = Calendar.getInstance();
        String mdate = Utils.formatDATEIntoYearMonthDay(fromDate);
        List<Event> mevents = new RealmList<>();
        c.set(fromDate.getYear(), fromDate.getMonth(), fromDate.getDate(), 23, 55, 50);
        Date toDate = c.getTime();
        realm.beginTransaction();
        RealmResults<Event> eventlist = realm.where(Event.class).findAll();
        for (Event e : eventlist) {
            //TODO check this shit
            /*Date d =e.getFormattedDate();
            if(d.before(toDate) && d.after(fromDate)){
                mevents.add(e);
            }*/
            if (Utils.formatDATEIntoYearMonthDay(e.getFormattedDate()).equals(mdate)) {
                mevents.add(e);
            }
        }

        realm.commitTransaction();
        mevents = realm.copyFromRealm(mevents);

        return mevents;
    }


    public interface Callback {
        void onSuccess(RealmList<Event> events);

        void onError();
    }
}
