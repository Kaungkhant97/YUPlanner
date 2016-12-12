package com.kaungkhantthu.yuplanner.mvp.subjectmvp.Model;

import android.util.Log;

import com.kaungkhantthu.yuplanner.data.entity.Subject;
import com.kaungkhantthu.yuplanner.data.entity.mSubjectResponse;
import com.kaungkhantthu.yuplanner.network.api.RetrofitClient;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by kaungkhantthu on 12/11/16.
 */

public class SubjectModelImpl implements SubjectModel {

    private static final String TAG = SubjectModel.class.getName();
    private static SubjectModelImpl subjectModel;
    private static Realm realm;


    private SubjectModelImpl() {

    }

    public static SubjectModelImpl getInstance() {
        if (subjectModel == null) {
            subjectModel = new SubjectModelImpl();
            realm = Realm.getDefaultInstance();
        }
        return subjectModel;
    }

    @Override
    public void saveSubject(RealmList<Subject> Subject) {
        realm.beginTransaction();
        realm.copyToRealm(Subject);
        realm.commitTransaction();

    }

    @Override
    public void getAllSubjectFromServer(final Callback c) {
        RetrofitClient.getInstance().getService().getSubjectList().enqueue(new retrofit2.Callback<mSubjectResponse>() {
            @Override
            public void onResponse(Call<mSubjectResponse> call, Response<mSubjectResponse> response) {
                if (response.body() != null) {
                    //TODO /**subject list could be empty**/
                    c.onSuccess(response.body().getSubjects());

                }
            }

            @Override
            public void onFailure(Call<mSubjectResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getLocalizedMessage());
                c.onError();
            }
        });
    }

    @Override
    public void getSubjectList(String major, String year, String mclass, final Callback c) {
        RetrofitClient.getInstance().getService().getSubjectList(major, year, mclass).enqueue(new retrofit2.Callback<mSubjectResponse>() {
            @Override
            public void onResponse(Call<mSubjectResponse> call, Response<mSubjectResponse> response) {
                if (response.body() != null) {
                    //TODO /**subject list could be empty**/
                    c.onSuccess(response.body().getSubjects());

                }
            }

            @Override
            public void onFailure(Call<mSubjectResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getLocalizedMessage());
                c.onError();
            }
        });
    }

    @Override
    public void getSubjectList(String major, String year, String mclass, String day, final Callback c) {
        RetrofitClient.getInstance().getService().getSubjectList(major, year, mclass, day).enqueue(new retrofit2.Callback<mSubjectResponse>() {
            @Override
            public void onResponse(Call<mSubjectResponse> call, Response<mSubjectResponse> response) {
                if (response.body() != null) {
                    //TODO /**subject list could be empty**/
                    c.onSuccess(response.body().getSubjects());

                }
            }

            @Override
            public void onFailure(Call<mSubjectResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getLocalizedMessage());
                c.onError();
            }
        });
    }

    @Override
    public List<Subject> getSubjectListFromCache(int day) {
        Log.d(TAG, "getSubjectListFromCache: " + day);
        List<Subject> subjectList = new RealmList<>();
        realm.beginTransaction();

        RealmResults<Subject> realmSubjectlist = realm.where(Subject.class).equalTo("timetable.day", day).findAll();
        subjectList = realm.copyFromRealm(realmSubjectlist);
        realm.commitTransaction();
        return subjectList;
    }

    private Subject getsubjectForperiod(int day, int period) {
        Subject subject = realm.where(Subject.class)
                .equalTo("timetable.day", day)
                .equalTo("timetable.period.p", period)
                .findFirst();
        return subject;
    }


    public interface Callback {
        void onSuccess(RealmList<Subject> sbjs);

        void onError();
    }
}
