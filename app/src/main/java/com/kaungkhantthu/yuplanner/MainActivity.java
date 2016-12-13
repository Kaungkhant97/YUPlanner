package com.kaungkhantthu.yuplanner;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.kaungkhantthu.yuplanner.utils.EventDecorator;
import com.kaungkhantthu.yuplanner.recyclerView.TabPagerAdapter;
import com.kaungkhantthu.yuplanner.data.entity.Event;
import com.kaungkhantthu.yuplanner.data.entity.Subject;
import com.kaungkhantthu.yuplanner.mvp.mainmvp.MainPresenter;
import com.kaungkhantthu.yuplanner.mvp.mainmvp.MainPresenterImpl;
import com.kaungkhantthu.yuplanner.mvp.mainmvp.MainView;
import com.kaungkhantthu.yuplanner.mvp.subjectmvp.Model.SubjectModelImpl;
import com.kaungkhantthu.yuplanner.utils.DateChangeNotifier;
import com.kaungkhantthu.yuplanner.utils.Utils;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.format.TitleFormatter;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmList;

public class MainActivity extends AppCompatActivity implements OnDateSelectedListener, MainView {

    TabLayout tabLayout;
    Toolbar toolbar;
    ViewPager viewPager;
    MaterialCalendarView calendarView;
    private MainPresenter mainpresenter;
    private TabPagerAdapter pageradapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }


    private void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        calendarView = (MaterialCalendarView) findViewById(R.id.calendarView);
        this.mainpresenter = MainPresenterImpl.getInstance(this);
        mainpresenter.init();


        setupToolbar();
        setupViewPager();
        setupCalendarView();


    }


    private void setupCalendarView() {
        calendarView.setSelectionMode(MaterialCalendarView.SELECTION_MODE_SINGLE);
        calendarView.setSelectedDate(Calendar.getInstance().getTime());
        calendarView.setOnDateChangedListener(this);
        calendarView.setSelectedDate(Calendar.getInstance().getTime());
        calendarView.setTopbarVisible(false);
        this.onDateSelected(calendarView, new CalendarDay(Calendar.getInstance()), true);

    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
    }

    private void setupViewPager() {

        tabLayout.addTab(tabLayout.newTab().setText("Assignment"));
        tabLayout.addTab(tabLayout.newTab().setText("Schedule"));
        tabLayout.addTab(tabLayout.newTab().setText("Event"));
        pageradapter = new TabPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pageradapter);


        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.setCurrentItem(1);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
        DateChangeNotifier.getInstance().notifyAllView(date.getCalendar());
        DateChangeNotifier.getInstance().setcurrentSelectedDate(date.getCalendar());
        mainpresenter.onDateChange(date.getCalendar());
        String month = date.getCalendar().getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
        getSupportActionBar().setTitle(month+" "+ date.getCalendar().get(Calendar.DAY_OF_MONTH));
        //setTextTitle();

    }

    @Override
    public void BindToCalendar(List<Event> events) throws ParseException {
        int color = getResources().getColor(R.color.dark);
        ArrayList<CalendarDay> eventdateList = new ArrayList();
        for (Event e : events) {
            Date d = Utils.formatDate(e.getPublishedDate());
            eventdateList.add(new CalendarDay(d));
        }
        calendarView.addDecorator(new EventDecorator(color, eventdateList));

    }


    @Override
    public void showSnackBar(String s) {

    }

    @Override
    public void showeventtab() {
        if (tabLayout.getTabCount() != 3) {
            tabLayout.addTab(tabLayout.newTab().setText("Event"));
        }
    }

    @Override
    public void hideventtab() {
        if (tabLayout.getTabCount() == 3) {
            tabLayout.removeTabAt(2);
        }
    }
}
