package com.kaungkhantthu.yuplanner;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.kaungkhantthu.yuplanner.mvp.eventmvp.Model.EventModelImpl;
import com.kaungkhantthu.yuplanner.utils.Constants;
import com.kaungkhantthu.yuplanner.utils.EventDecorator;
import com.kaungkhantthu.yuplanner.recyclerView.TabPagerAdapter;
import com.kaungkhantthu.yuplanner.data.entity.Event;
import com.kaungkhantthu.yuplanner.mvp.mainmvp.MainPresenter;
import com.kaungkhantthu.yuplanner.mvp.mainmvp.MainPresenterImpl;
import com.kaungkhantthu.yuplanner.mvp.mainmvp.MainView;
import com.kaungkhantthu.yuplanner.utils.DateChangeNotifier;
import com.kaungkhantthu.yuplanner.utils.SPrefHelper;
import com.kaungkhantthu.yuplanner.utils.Utils;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements OnDateSelectedListener, MainView {

    private static final String TAG = MainActivity.class.getName();
    private static final String TABCOUNT = "tabcount";
    private static final String SELECTEDTAB = "tabposition";
    TabLayout tabLayout;
    Toolbar toolbar;
    ViewPager viewPager;

    MaterialCalendarView calendarView;
    private MainPresenter mainpresenter;
    private TabPagerAdapter pageradapter;
    public FloatingActionButton fab;
    private CoordinatorLayout mainCoordinator;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e(TAG, "onCreate: ");
        init();


    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        outState.putInt(TABCOUNT, tabLayout.getTabCount());
        outState.putInt(SELECTEDTAB, tabLayout.getSelectedTabPosition());
        super.onSaveInstanceState(outState, outPersistentState);


    }

    @Override
    public void onSaveInstanceState (Bundle outState){
        outState.putInt(TABCOUNT, tabLayout.getTabCount());
        outState.putInt(SELECTEDTAB, tabLayout.getSelectedTabPosition());
        super.onSaveInstanceState(outState);

    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

    }

    public void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        calendarView = (MaterialCalendarView) findViewById(R.id.calendarView);
        mainCoordinator = (CoordinatorLayout)findViewById(R.id.mainCoordinator);
        fab =(FloatingActionButton)findViewById(R.id.fab);
        this.mainpresenter =new MainPresenterImpl(this);
        mainpresenter.init(this);
        if(!BuildConfig.DEBUG){
            mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.primary));
        }
        setupToolbar();
        setupViewPager();
        setupCalendarView();


    }


    public void setupCalendarView() {
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

    public void setupViewPager() {

        tabLayout.addTab(tabLayout.newTab().setText("Todo list"));
        tabLayout.addTab(tabLayout.newTab().setText("Schedule"));
        tabLayout.addTab(tabLayout.newTab().setText("Event"));
        pageradapter = new TabPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pageradapter);


        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.setCurrentItem(1);
        fab.hide();
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition() == 0 )
                {
                    fab.show();
                }
                else{
                    fab.hide();
                }
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
        Log.e(TAG, "onDateSelected: ");
        mainpresenter.onDateChange(date.getCalendar());
        String month = date.getCalendar().getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
        getSupportActionBar().setTitle(month + " " + date.getCalendar().get(Calendar.DAY_OF_MONTH));
        //setTextTitle();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_settings){
            SPrefHelper.putBoolean(this, Constants.FIRSTTIME,true);
            Intent i =new Intent(this,LauncherActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }

        return true;

    }

    @Override
    public void BindToCalendar(List<Event> events) throws ParseException {
        int color = getResources().getColor(R.color.dark);
        ArrayList<CalendarDay> eventdateList = new ArrayList();
        for (Event e : events) {
            Date d = Utils.formatDate(e.getPublishedDate());
            eventdateList.add(new CalendarDay(d));
        }
        calendarView.setSelectedDate(Calendar.getInstance());
        calendarView.addDecorator(new EventDecorator(color, eventdateList));

    }


    @Override
    public void showSnackBar(String s) {
        Snackbar.make(mainCoordinator,s,Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showeventtab() {
        Log.e(TAG, "hideventtab: "+tabLayout.getTabCount() );

        if (tabLayout.getTabCount() != 3) {
            tabLayout.addTab(tabLayout.newTab().setText("Event"));
            pageradapter.setCount(3);
            viewPager.setCurrentItem(1);
            fab.hide();
        }
    }

    @Override
    public void hideventtab() {
        Log.e(TAG, "hideventtab: "+tabLayout.getTabCount() );
        if (tabLayout.getTabCount() == 3) {
            pageradapter.setCount(2);
            tabLayout.removeTabAt(2);
            viewPager.setCurrentItem(1);
            fab.hide();

        }
    }

    @Override
    protected void onDestroy() {
        Log.e(TAG, "onDestroy: " );
        super.onDestroy();
    }
}
