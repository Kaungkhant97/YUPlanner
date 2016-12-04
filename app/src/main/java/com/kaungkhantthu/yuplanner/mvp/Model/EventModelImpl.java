package com.kaungkhantthu.yuplanner.mvp.Model;


import com.kaungkhantthu.yuplanner.data.entity.Event;
import com.kaungkhantthu.yuplanner.data.entity.EventResponse;
import com.kaungkhantthu.yuplanner.network.api.RetrofitClient;

import java.util.Date;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by kaungkhantthu on 11/16/16.
 */

public class EventModelImpl  implements EventModel {

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
    public void clearEvents() {
        realm.beginTransaction();
        realm.where(Event.class).findAll().deleteAllFromRealm();
        realm.commitTransaction();
    }

    @Override
    public RealmResults<Event> getEventFromCache() {
        realm.beginTransaction();
        RealmResults<Event> realmlist = realm.where(Event.class).findAll();
        realm.commitTransaction();
        return realmlist;
    }


    public interface Callback {
        void onSuccess(RealmList<Event> events);

        void onError();
    }
}
