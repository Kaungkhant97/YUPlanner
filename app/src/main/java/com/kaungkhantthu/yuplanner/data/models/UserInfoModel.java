package com.kaungkhantthu.yuplanner.data.models;

import android.util.Log;

import com.kaungkhantthu.yuplanner.R;
import com.kaungkhantthu.yuplanner.YuPlannerApp;
import com.kaungkhantthu.yuplanner.utils.Constants;
import com.kaungkhantthu.yuplanner.utils.SPrefHelper;

import static com.kaungkhantthu.yuplanner.YuPlannerApp.getContext;

/**
 * Created by IN-3442 on 23-Dec-16.
 */

public class UserInfoModel {

    private static UserInfoModel objInstance;

    private String apiMajor;
    private String apiYear;
    private String apiClass;

    private String textMajor;
    private String textYear;
    private String textClass;

    private int userMajor;
    private int userYear;
    private int userClass;

    private UserInfoModel(){
        userMajor = SPrefHelper.getInteger(getContext(), Constants.MAJOR_POSITION, 0);
        userYear = SPrefHelper.getInteger(getContext(), Constants.YEAR_POSITION, 0);
        userClass = SPrefHelper.getInteger(getContext(), Constants.CLASS_POSITION, 0);

        getSelectedInfoForAPI(userMajor, userYear, userClass);
        getSelectedInfoForDisplay(userMajor, userYear, userClass);
    }

    public static UserInfoModel getObjInstance(){
        if(objInstance == null) objInstance = new UserInfoModel();
        return objInstance;
    }

    public static void deleteObj(){
        if(objInstance != null) objInstance = null;
    }

    private void getSelectedInfoForAPI(int selectedMajor, int selectedYear, int selectedClass){
        String[] majorarray = YuPlannerApp.getContext().getResources().getStringArray(R.array.mmajor_list);
        String[] yeararray = YuPlannerApp.getContext().getResources().getStringArray(R.array.myear_list);
        String[] classarray = YuPlannerApp.getContext().getResources().getStringArray(R.array.mclass_list);

        apiMajor = majorarray[selectedMajor];

        if(selectedYear == 1){
            apiYear = yeararray[selectedYear];
            apiClass = classarray[selectedClass + 1];
        }
        else if(selectedYear == 5){
            apiYear = yeararray[3];
            apiClass = classarray[1];
        }else if(selectedYear == 6){
            apiYear = yeararray[4];
            apiClass = classarray[1];
        }else{
            apiYear = yeararray[selectedYear];
            apiClass = classarray[2];
        }

        Log.d("YUPlanner", "UserInfoModel API " + apiMajor + " " + apiYear + " " + apiClass);
    }

    private void getSelectedInfoForDisplay(int selectedMajor, int selectedYear, int selectedClass){
        String[] majorTextArray = YuPlannerApp.getContext().getResources().getStringArray(R.array.mmajor_list);
        String[] yearTextArray = YuPlannerApp.getContext().getResources().getStringArray(R.array.year_list);
        String[] classTextArray = YuPlannerApp.getContext().getResources().getStringArray(R.array.class_list);

        textMajor = majorTextArray[selectedMajor];
        textYear = yearTextArray[selectedYear];
        textClass = classTextArray[selectedClass];

        Log.d("YUPlanner", "UserInfoModel Display " + textMajor + " " + textYear + " " + textClass);
    }

    public String getApiMajor() {
        return apiMajor;
    }

    public String getApiYear() {
        return apiYear;
    }

    public String getApiClass() {
        return apiClass;
    }

    public String getTextMajor() {
        return textMajor;
    }

    public String getTextYear() {
        return textYear;
    }

    public String getTextClass() {
        if(textClass.compareTo("class") == 0){
            return "";
        }
        return textClass;
    }
}
