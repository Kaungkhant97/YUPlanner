package com.kaungkhantthu.yuplanner;

import android.app.TimePickerDialog;

import android.app.DatePickerDialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

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
    private ImageView btnClose;
    private TextInputLayout tilDate;
    private TextInputLayout tilTime;
    private TextInputLayout tilName;

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
        btnClose = (ImageView) view.findViewById(R.id.btn_close);
        tilTime = (TextInputLayout) view.findViewById(R.id.til_time);
        tilDate = (TextInputLayout) view.findViewById(R.id.til_date);
        tilName = (TextInputLayout) view.findViewById(R.id.til_name);

        btnSubmit.setOnClickListener(this);
        etTime.setOnClickListener(this);
        etDate.setOnClickListener(this);
        etTime.setFocusable(false);
        etTime.setOnKeyListener(null);
        etDate.setFocusable(false);
        etDate.setOnKeyListener(null);
        btnClose.setOnClickListener(this);

        etTime.setOnFocusChangeListener(this);

        etDate.setOnFocusChangeListener(this);

        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().length() > 0) {
                    tilName.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        etTime.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().length() > 0) {
                    tilTime.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        etDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().length() > 0) {
                    tilDate.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

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
            case R.id.btn_close:
                dismiss();
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
        String name, time, date, note;
        name = etName.getText().toString();
        time = etTime.getText().toString();
        date = etDate.getText().toString();
        note = etNote.getText().toString();

        if (name.isEmpty() || time.isEmpty() || date.isEmpty() || note.isEmpty()) {
            if (name.isEmpty()) {
                tilName.setError("Name cannot be empty");
            }
            if (time.isEmpty()) {
                tilTime.setError("Time cannot be empty");
            }
            if (date.isEmpty()) {
                tilDate.setError("Date cannot be empty");
            }
            if (note.isEmpty()) {
                etNote.setError("Note cannot be empty");
            }

        } else {
            todoTask.setName(name);
            todoTask.setTime(time);
            todoTask.setDate(date);
            todoTask.setNote(note);
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



