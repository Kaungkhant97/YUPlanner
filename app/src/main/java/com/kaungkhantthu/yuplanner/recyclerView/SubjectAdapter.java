package com.kaungkhantthu.yuplanner.recyclerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
    private String[] order = {"1st","2nd","3rd","4th","5th","6th","7th","8th","9th","10th"};

    public SubjectAdapter(ArrayList<Subject> subjectArraylist) {
        this.dataList = subjectArraylist;
    }

    public void addallSubjects(List<Subject> eventArrayList) {
        dataList.addAll(eventArrayList);
        notifyDataSetChanged();
    }

    @Override
    public SubjectAdapter.SubjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_subject, parent, false);
        this.context = parent.getContext();
        return new SubjectViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SubjectAdapter.SubjectViewHolder holder, int position) {
        holder.bindData(dataList.get(position), context, dataList,position);

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
        private TextView tv_order;
        private TextView tv_moduleno;
        private TextView tv_teacher;
        private TextView tv_subject;
        private TextView tv_period;
        private LinearLayout cardview_layout;

        private ImageView iv_teacher;
        private ImageView iv_subject;
        private ImageView iv_period;

        public SubjectViewHolder(View itemView) {
            super(itemView);
            cardview_layout = (LinearLayout) itemView.findViewById(R.id.cardview_layout);
            tv_order = (TextView) itemView.findViewById(R.id.tv_order);
            tv_moduleno = (TextView) itemView.findViewById(R.id.tv_moduleno);
            tv_teacher = (TextView) itemView.findViewById(R.id.tv_teacher);
            tv_subject = (TextView) itemView.findViewById(R.id.tv_subject);
            tv_period = (TextView) itemView.findViewById(R.id.tv_period);

            iv_teacher = (ImageView) itemView.findViewById(R.id.iv_teacher);
            iv_subject = (ImageView) itemView.findViewById(R.id.iv_subject);
            iv_period = (ImageView) itemView.findViewById(R.id.iv_period);
        }

        public void bindData(Subject subjects, Context context, ArrayList<Subject> SubjectList,int position) {
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
            tv_order.setText(order[position]);
            tv_moduleno.setText(subjects.getSubjectId());
            tv_teacher.setText(subjects.getTeachername());
            tv_subject.setText(subjects.getSubjectname());
            //TODO tutorial
            if (position == 0) {
                tv_period.setText(Utils.periodToTimeCoverter(null, SubjectList.get(position)));
            }else{
                tv_period.setText(Utils.periodToTimeCoverter(SubjectList.get(position-1),SubjectList.get(position)));
            }

            //Hide layout with null data
            if(tv_moduleno.getText().toString().trim().isEmpty()){
                tv_moduleno.setVisibility(View.GONE);
            }
            if(tv_period.getText().toString().trim().isEmpty()){
                tv_period.setVisibility(View.GONE);
                iv_period.setVisibility(View.GONE);
            }
            if(tv_teacher.getText().toString().trim().isEmpty()){
                tv_teacher.setVisibility(View.GONE);
                iv_teacher.setVisibility(View.GONE);
            }
            if(tv_subject.getText().toString().trim().isEmpty()){
                tv_subject.setVisibility(View.GONE);
                iv_subject.setVisibility(View.GONE);
            }
        }
    }
}
