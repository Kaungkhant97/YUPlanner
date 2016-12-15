package com.kaungkhantthu.yuplanner.mvp.mainmvp;

import android.content.Context;

import com.kaungkhantthu.yuplanner.MainActivity;
import com.kaungkhantthu.yuplanner.data.entity.Event;
import com.kaungkhantthu.yuplanner.data.entity.Subject;
import com.kaungkhantthu.yuplanner.mvp.eventmvp.Model.EventModel;
import com.kaungkhantthu.yuplanner.mvp.eventmvp.Model.EventModelImpl;
import com.kaungkhantthu.yuplanner.mvp.subjectmvp.Model.SubjectModelImpl;
import com.kaungkhantthu.yuplanner.mvp.subjectmvp.presenter.SubjectPresenterImpl;
import com.kaungkhantthu.yuplanner.utils.Constants;
import com.kaungkhantthu.yuplanner.utils.DateChangeNotifier;
import com.kaungkhantthu.yuplanner.utils.SPrefHelper;
import com.kaungkhantthu.yuplanner.utils.Utils;

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
    private  MainView mainview;
    private EventModel eventModel;
    private SubjectModelImpl subjectModel;

    public MainPresenterImpl(MainView v) {
        this.mainview =  v;
    }




    @Override
    public void onDateChange(Calendar c) {
        List<Event> eventlist = eventModel.getEventsFor(c.getTime());
        if (eventlist.size() == 0) {
            mainview.hideventtab();
        } else {
            mainview.showeventtab();
        }
    }

    @Override
    public void init(Context c) {
        this.eventModel = EventModelImpl.getInstance();

        if(!Utils.isNetworkAvailable(c)){
            mainview.showSnackBar("Internet Connection Not Available");
        }
        List<Event> e = Realm.getDefaultInstance().copyFromRealm(eventModel.getAllEventFromCache());
        if (e != null) {

            try {
                mainview.BindToCalendar(e);
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
        }

        this.subjectModel = SubjectModelImpl.getInstance();
        requestSubjectFromServer(c);

        requestEventsFromServer();

    }

    private void requestSubjectFromServer(final Context c) {
        String major = SPrefHelper.getString(c, Constants.MAJOR, "");
        String mClass = SPrefHelper.getString(c, Constants.CLASS, "");
        String year = SPrefHelper.getString(c, Constants.YEAR, "");
        subjectModel.getSubjectList(major, year, mClass, new SubjectModelImpl.Callback() {
            @Override
            public void onSuccess(RealmList<Subject> sbjs) {
                subjectModel.clearSubject();
                subjectModel.saveSubject(sbjs);

                DateChangeNotifier.getInstance().notifyAllView(DateChangeNotifier.getInstance().getcurrentSelectedDate());



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
