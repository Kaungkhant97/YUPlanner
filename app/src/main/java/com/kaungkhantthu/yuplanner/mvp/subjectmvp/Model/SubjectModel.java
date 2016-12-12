package com.kaungkhantthu.yuplanner.mvp.subjectmvp.Model;

import com.kaungkhantthu.yuplanner.data.entity.Subject;

import java.util.List;

import io.realm.RealmList;

/**
 * Created by kaungkhantthu on 12/11/16.
 */

public interface SubjectModel {
    void saveSubject(RealmList<Subject> Subject);
    void getAllSubjectFromServer(SubjectModelImpl.Callback c);
    void getSubjectList(String major,String year,String mclass,SubjectModelImpl.Callback c);
    void getSubjectList(String major,String year,String mclass,String day,SubjectModelImpl.Callback c);
    List<Subject> getSubjectListFromCache(int day);


}
