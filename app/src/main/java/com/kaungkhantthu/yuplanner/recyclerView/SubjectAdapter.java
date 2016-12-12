package com.kaungkhantthu.yuplanner.recyclerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.util.Util;
import com.kaungkhantthu.yuplanner.R;
import com.kaungkhantthu.yuplanner.data.entity.Subject;
import com.kaungkhantthu.yuplanner.utils.Utils;

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
        holder.bindData(dataList.get(position), context, dataList);

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
        private TextView tv_teacher_name;
        private TextView tv_subject;
        private TextView tv_period;
        private LinearLayout cardview_layout;

        public SubjectViewHolder(View itemView) {
            super(itemView);
            cardview_layout = (LinearLayout) itemView.findViewById(R.id.cardview_layout);
            txt_sbjname = (TextView) itemView.findViewById(R.id.txt_sbjname);
            tv_teacher_name = (TextView) itemView.findViewById(R.id.tv_teacher_name);
            tv_subject = (TextView) itemView.findViewById(R.id.tv_subject);
            tv_period = (TextView) itemView.findViewById(R.id.tv_period);
        }

        public void bindData(Subject subjects, Context context, ArrayList<Subject> SubjectList) {
            int color = 0;
            switch (subjects.getMtype()) {
                case "minor":
                    color = context.getResources().getColor(R.color.orange);
                    break;
                case "major":
                    color = context.getResources().getColor(R.color.primary);
                    break;
            }
            
            cardview_layout.setBackgroundColor(color);
            txt_sbjname.setText(subjects.getSubjectId());
            tv_teacher_name.setText(subjects.getTeachername());
            tv_subject.setText(subjects.getSubjectname());

            if (getPosition() == 0) {
                tv_period.setText(Utils.periodToTimeCoverter(null, SubjectList.get(getPosition())));
            } else if (getPosition() == 6) {
                tv_period.setText(Utils.periodToTimeCoverter(SubjectList.get(getPosition()), null));
            }else{
                tv_period.setText(Utils.periodToTimeCoverter(SubjectList.get(getPosition()-1),SubjectList.get(getPosition())));
            }
        }
    }
}
