package com.kaungkhantthu.yuplanner.mvp.eventmvp.presenter;

import com.kaungkhantthu.yuplanner.data.entity.Event;
import com.kaungkhantthu.yuplanner.mvp.eventmvp.Model.EventModel;
import com.kaungkhantthu.yuplanner.mvp.eventmvp.Model.EventModelImpl;
import com.kaungkhantthu.yuplanner.mvp.eventmvp.views.EventView;
import com.kaungkhantthu.yuplanner.utils.DateChangeNotifier;

import java.util.Calendar;
import java.util.List;

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
        Calendar calendar = DateChangeNotifier.getInstance().getcurrentSelectedDate();
        List<Event> eventList = eventModel.getEventsFor(calendar.getTime());
        if(eventList == null){
            eventView.showErrorView();

        }else{
            eventView.showEvent(eventList);
        }
    }



    @Override
    public void onDateChange(Calendar c) {
       List<Event> e= eventModel.getEventsFor(c.getTime());
        if(e == null ){
            eventView.showErrorView();
        }else{
            eventView.showEvent(e);
        }
    }
}
