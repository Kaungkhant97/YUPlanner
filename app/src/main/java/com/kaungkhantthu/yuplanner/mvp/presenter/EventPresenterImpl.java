package com.kaungkhantthu.yuplanner.mvp.presenter;

import com.kaungkhantthu.yuplanner.data.entity.Event;
import com.kaungkhantthu.yuplanner.mvp.Model.EventModel;
import com.kaungkhantthu.yuplanner.mvp.Model.EventModelImpl;
import com.kaungkhantthu.yuplanner.mvp.views.EventView;

import java.util.Calendar;
import java.util.List;

import io.realm.RealmList;

/**
 * Created by kaungkhantthu on 12/4/16.
 */

public class EventPresenterImpl implements EventPresenter {

    private static EventPresenterImpl eventPresenter;
    private static EventView eventView;
    private EventModel eventModel;

    private EventPresenterImpl() {
    }

    public static EventPresenterImpl getInstance(EventView e) {
        if (eventPresenter == null) {
            eventPresenter = new EventPresenterImpl();
            eventView = e;

        }
        return eventPresenter;
    }

    public void init(){
        this.eventModel =  EventModelImpl.getInstance();

        List<Event> eventList = eventModel.getEventFromCache();
        if(eventList == null){
            requestServer();

        }else{
            eventView.showEvent(eventList);
        }
    }

    private void requestServer() {
        eventModel.getAllEventFromServer(new EventModelImpl.Callback() {
            @Override
            public void onSuccess(RealmList<Event> events) {
                eventView.showEvent(events);
            }

            @Override
            public void onError() {
                eventView.showError();
            }
        });

    }

    @Override
    public void onDateChange(Calendar c) {

    }
}
