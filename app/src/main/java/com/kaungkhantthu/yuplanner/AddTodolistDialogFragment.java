package com.kaungkhantthu.yuplanner;

import android.app.TimePickerDialog;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.kaungkhantthu.yuplanner.data.entity.TodoTask;
import com.kaungkhantthu.yuplanner.mvp.todolistmvp.TodolistModel;
import com.kaungkhantthu.yuplanner.mvp.todolistmvp.TodolistModelImpl;
import com.kaungkhantthu.yuplanner.recyclerView.ToDoAdapter;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Administrator's user on 13-Dec-16.
 */

public class AddTodolistDialogFragment extends android.support.v4.app.DialogFragment implements View.OnClickListener {

    TextView btnSubmit;
    EditText etName;
    EditText etTime;
    EditText etDate;
    EditText etNote;
    CheckBox cbAlarm;

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

        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity().getApplicationContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                if (i < 10 || i1 < 10) {
                    etTime.setText("0" + i + ":" + "0" + i1);
                } else {
                    etTime.setText(i + ":" + i1);
                }
            }
        }, hour, minute, true);

        timePickerDialog.show();

    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity().getApplicationContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                etDate.setText(i + "-" + i1 + "-" + i2);
            }
        }, year, month, day);

        datePickerDialog.show();
    }

    private void saveToDoTask() {
        todoTask.setName(etName.getText().toString());
        todoTask.setTime(etTime.getText().toString());
        todoTask.setDate(etDate.getText().toString());
        todoTask.setNote(etNote.getText().toString());
        todolistModel.saveTask(todoTask);
    }
}
