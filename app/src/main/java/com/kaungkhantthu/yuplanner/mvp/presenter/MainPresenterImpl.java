package com.kaungkhantthu.yuplanner.mvp.presenter;

import com.kaungkhantthu.yuplanner.data.entity.Event;
import com.kaungkhantthu.yuplanner.mvp.Model.EventModel;
import com.kaungkhantthu.yuplanner.mvp.Model.EventModelImpl;
import com.kaungkhantthu.yuplanner.mvp.views.MainView;

import java.text.ParseException;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by kaungkhantthu on 12/4/16.
 */

public class MainPresenterImpl implements MainPresenter {

    private static MainPresenter mainPresenter;
    private static MainView mainview;
    private EventModel eventModel;

    private MainPresenterImpl() {
    }
    public static MainPresenter getInstance(MainView v){

        if(mainPresenter == null){
            mainPresenter = new MainPresenterImpl();
            mainview = v;
        }
        return mainPresenter;
    }


    @Override
    public void init() {
        this.eventModel = EventModelImpl.getInstance();

        List<Event> e = Realm.getDefaultInstance().copyFromRealm(eventModel.getEventFromCache());
        if(e != null){

            try {
                mainview.BindToCalendar(e);
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
        }

        requestEventsFromServer();

    }

    private void requestEventsFromServer() {
        eventModel.getAllEventFromServer(new EventModelImpl.Callback() {
            @Override
            public void onSuccess(RealmList<Event> events) {
                eventModel.clearEvents();
                eventModel.saveEvent(events);
                try {
                    mainview.BindToCalendar(events);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError() {
                mainview.showSnackBar("Cannot fetch new events From Server");
            }
        });
    }
}
