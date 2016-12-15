package com.kaungkhantthu.yuplanner;

import android.app.TimePickerDialog;

import android.app.DatePickerDialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.kaungkhantthu.yuplanner.data.entity.TodoTask;
import com.kaungkhantthu.yuplanner.mvp.todolistmvp.TodolistModel;
import com.kaungkhantthu.yuplanner.mvp.todolistmvp.TodolistModelImpl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import io.realm.Realm;

/**
 * Created by Administrator's user on 13-Dec-16.
 */

public class AddTodolistDialogFragment extends DialogFragment implements View.OnClickListener, View.OnFocusChangeListener {

    private submitButtonClickListener listener;

    public void setListener(submitButtonClickListener listener) {
        this.listener = listener;
    }

    private static final String SIMPLE_DATE_FORMATE = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    private TextView btnSubmit;
    private EditText etName;
    private EditText etTime;
    private EditText etDate;
    private EditText etNote;
    private CheckBox cbAlarm;
    private ImageView btnEdit;
    private ImageView btnDelete;
    private ImageView btnFinished;
    private ImageView btnSetAlarm;

    int gyear;
    int gmonth;
    int gday;
    int ghour;
    int gmin;

    TodolistModel todolistModel;
    TodoTask todoTask;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        todolistModel = TodolistModelImpl.getInstance();
        todoTask = new TodoTask();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment_add_to_do_task, container, false);

        btnSubmit = (TextView) view.findViewById(R.id.btn_submit);
        etName = (EditText) view.findViewById(R.id.et_name);
        etTime = (EditText) view.findViewById(R.id.et_time);
        etDate = (EditText) view.findViewById(R.id.et_date);
        etNote = (EditText) view.findViewById(R.id.et_note);
        cbAlarm = (CheckBox) view.findViewById(R.id.cb_alarm);

        btnSubmit.setOnClickListener(this);
        etTime.setOnClickListener(this);
        etDate.setOnClickListener(this);
        etTime.setFocusable(false);
        etTime.setOnKeyListener(null);
        etDate.setFocusable(false);
        etDate.setOnKeyListener(null);
        etTime.setOnFocusChangeListener(this);

        etDate.setOnFocusChangeListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_submit:
                saveToDoTask();

                break;
            case R.id.et_date:
                showDatePicker();
                break;
            case R.id.et_time:
                showTimePicker();
                break;
        }
    }

    private void showTimePicker() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int min) {
                ghour = hour;
                gmin = min;

                String shour = "" + hour;
                String smin = "" + min;
                if (hour < 10) {
                    shour = "0" + hour;
                }
                if (min < 10) {
                    smin = "0" + min;
                }
                etTime.setText(shour + ":" + smin);
            }
        }, hour, minute, true);

        timePickerDialog.show();

    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                gyear = year;
                gmonth = month;
                gday = day;
                etDate.setText(year + "-" + month + "-" + day);
            }
        }, year, month, day);

        datePickerDialog.show();
    }

    private void saveToDoTask() {
        if (etTime.getText().toString() == null || etDate.getText().toString() == null) {
            Toast.makeText(getContext(), "please complete the blanks", Toast.LENGTH_SHORT).show();
        } else {

            todoTask.setName(etName.getText().toString());
            todoTask.setTime(etTime.getText().toString());
            todoTask.setDate(etDate.getText().toString());
            todoTask.setNote(etNote.getText().toString());
            todoTask.setId(todoTask.hashCode() + "");
            todoTask.setFormattedDate(getPublishedDate());
            todolistModel.saveTask(todoTask);
            listener.onSubmit();
            dismiss();
        }

    }

    @Override
    public void onFocusChange(View view, boolean b) {
        switch (view.getId()) {
            case R.id.et_time:
                if (b)
                    showTimePicker();
                break;
            case R.id.et_date:
                if (b)
                    showDatePicker();
                break;
        }
    }

    public Date getPublishedDate() {
        SimpleDateFormat format = new SimpleDateFormat(SIMPLE_DATE_FORMATE);

        Calendar c = Calendar.getInstance();
        c.set(gyear, gmonth, gday, ghour, gmin);
        format.setTimeZone(c.getTimeZone());

        return c.getTime();
    }

    public interface submitButtonClickListener {
        void onSubmit();
    }
}
