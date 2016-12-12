package com.kaungkhantthu.yuplanner.mvp.mainmvp;

import com.kaungkhantthu.yuplanner.data.entity.Event;
import com.kaungkhantthu.yuplanner.data.entity.Subject;
import com.kaungkhantthu.yuplanner.mvp.eventmvp.Model.EventModel;
import com.kaungkhantthu.yuplanner.mvp.eventmvp.Model.EventModelImpl;
import com.kaungkhantthu.yuplanner.mvp.subjectmvp.Model.SubjectModelImpl;

import java.text.ParseException;
import java.util.Calendar;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;

/**
 * Created by kaungkhantthu on 12/4/16.
 */

public class MainPresenterImpl implements MainPresenter {

    private static MainPresenter mainPresenter;
    private static MainView mainview;
    private EventModel eventModel;
    private SubjectModelImpl subjectModel;

    private MainPresenterImpl() {
    }

    public static MainPresenter getInstance(MainView v) {

        if (mainPresenter == null) {
            mainPresenter = new MainPresenterImpl();
            mainview = v;
        }
        return mainPresenter;
    }


    @Override
    public void onDateChange(Calendar c) {
        List<Event> eventlist = eventModel.getEventsFor(c.getTime());
        if(eventlist.size() == 0){
            mainview.hideventtab();
        }else{
            mainview.showeventtab();
        }
    }

    @Override
    public void init() {
        this.eventModel = EventModelImpl.getInstance();

        List<Event> e = Realm.getDefaultInstance().copyFromRealm(eventModel.getAllEventFromCache());
        if (e != null) {

            try {
                mainview.BindToCalendar(e);
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
        }

        this.subjectModel = SubjectModelImpl.getInstance();
        requestSubjectFromServer();

        requestEventsFromServer();

    }

    private void requestSubjectFromServer() {
        subjectModel.getSubjectList("CS", "4", "", new SubjectModelImpl.Callback() {
            @Override
            public void onSuccess(RealmList<Subject> sbjs) {
                subjectModel.clearSubject();
                subjectModel.saveSubject(sbjs);

            }

            @Override
            public void onError() {

            }
        });
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
