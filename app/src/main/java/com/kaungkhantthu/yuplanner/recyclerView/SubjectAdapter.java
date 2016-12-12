package com.kaungkhantthu.yuplanner.recyclerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kaungkhantthu.yuplanner.R;
import com.kaungkhantthu.yuplanner.data.entity.Event;
import com.kaungkhantthu.yuplanner.data.entity.Subject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kaungkhantthu on 12/12/16.
 */

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder> {

    private ArrayList<Subject> dataList;
    private Context context;

    public SubjectAdapter(ArrayList<Subject> subjectArraylist) {
        this.dataList = subjectArraylist;
    }

    public void addallSubjects(List<Subject> eventArrayList) {
        dataList.addAll(eventArrayList);
        notifyDataSetChanged();
    }

    ;

    @Override
    public SubjectAdapter.SubjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_subject, parent, false);
        this.context = parent.getContext();
        return new SubjectViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SubjectAdapter.SubjectViewHolder holder, int position) {
        holder.bindData(dataList.get(position), context);

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void clearSubjects() {
        dataList.clear();
        notifyDataSetChanged();
    }


    public class SubjectViewHolder extends RecyclerView.ViewHolder {
        private TextView txt_sbjname;
        public SubjectViewHolder(View itemView) {
            super(itemView);
            txt_sbjname = (TextView) itemView.findViewById(R.id.txt_sbjname);
        }

        public void bindData(Subject subjects, Context context) {
            txt_sbjname.setText(subjects.getSubjectId());
        }
    }
}