package com.kaungkhantthu.yuplanner.recyclerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kaungkhantthu.yuplanner.R;
import com.kaungkhantthu.yuplanner.data.entity.TodoTask;
import com.kaungkhantthu.yuplanner.mvp.todolistmvp.TodolistModelImpl;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Administrator's user on 13-Dec-16.
 */

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder> {

    ArrayList<TodoTask> dataList;
    private Context context;

    public ToDoAdapter(ArrayList<TodoTask> todoTaskArrayList) {
        this.dataList = todoTaskArrayList;
    }

    public void addAllToDoTasks(List<TodoTask> todoTaskArrayList) {
        dataList.addAll(todoTaskArrayList);
        notifyDataSetChanged();
    }

    @Override
    public ToDoAdapter.ToDoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_to_do, parent, false);
        this.context = parent.getContext();
        return new ToDoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ToDoViewHolder holder, final int position) {
        holder.bindData(dataList.get(position), context);
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TodolistModelImpl.getInstance().deleteToDoTask(dataList.get(position));
                dataList.remove(position);
                notifyItemRemoved(position);
            }
        });
    }



    public void clearToDoTasks() {
        dataList.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ToDoViewHolder extends RecyclerView.ViewHolder {

        TextView tv_title;
        TextView tv_time;
        TextView tv_date;
        TextView tv_note;

        ImageView btnEdit;
        ImageView btnDelete;
        ImageView btnFinished;
        ImageView btnSetAlarm;

        public ToDoViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
            tv_date = (TextView) itemView.findViewById(R.id.tv_date);
            tv_note = (TextView) itemView.findViewById(R.id.tv_note);

            btnDelete = (ImageView) itemView.findViewById(R.id.btn_delete);
        }


        public void bindData(TodoTask todoTask, Context context) {
            tv_title.setText(todoTask.getName());
            tv_time.setText("Time : " + todoTask.getTime());
            tv_date.setText("Date : " + todoTask.getDate());
            tv_note.setText("Note : " + todoTask.getNote());
        }
    }
}
