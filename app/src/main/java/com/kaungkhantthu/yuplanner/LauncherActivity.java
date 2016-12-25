package com.kaungkhantthu.yuplanner;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.kaungkhantthu.yuplanner.data.entity.Subject;
import com.kaungkhantthu.yuplanner.data.models.UserInfoModel;
import com.kaungkhantthu.yuplanner.utils.Constants;
import com.kaungkhantthu.yuplanner.utils.SPrefHelper;
import com.kaungkhantthu.yuplanner.utils.Utils;

import io.realm.Realm;

/**
 * Created by Administrator's user on 14-Dec-16.
 */

public class LauncherActivity extends AppCompatActivity {
    private Spinner sp_major;
    private Spinner sp_class;
    private Spinner sp_year;
    private TextView txt_btnSubmit;

    @Override
    protected void onStart() {
        super.onStart();
        final boolean firstTime = SPrefHelper.getBoolean(this, Constants.FIRSTTIME, true);
        if(!firstTime){
            Intent intent = new Intent(LauncherActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(intent);
            finish();

        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_start_screen);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.primary));
        }

        sp_major = (Spinner) findViewById(R.id.sp_major);
        sp_class = (Spinner) findViewById(R.id.sp_class);
        sp_year = (Spinner) findViewById(R.id.sp_year);
        txt_btnSubmit = (TextView) findViewById(R.id.btn_submit);

        setupSpinnerMajor();
        setupSpinnerClass();
        setupSpinnerYear();

        txt_btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedmajor = sp_major.getSelectedItemPosition();
                int selectedyear = sp_year.getSelectedItemPosition();
                int selectedclass = sp_class.getSelectedItemPosition();

                if (selectedmajor == 0 ||
                        selectedyear == 0) {
                    Toast.makeText(LauncherActivity.this, "Please complete the selection to Continue", Toast.LENGTH_SHORT).show();
                } else {

                    UserInfoModel.getObjInstance().asssingToSharePref(selectedmajor, selectedyear, selectedclass);
                    UserInfoModel.deleteObj();

                    SPrefHelper.putBoolean(LauncherActivity.this, Constants.FIRSTTIME, false);

                    Realm realm = Realm.getDefaultInstance();
                    realm.beginTransaction();
                    realm.where(Subject.class).findAll().deleteAllFromRealm();
                    realm.commitTransaction();
                    Intent intent = new Intent(LauncherActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                    startActivity(intent);
                }
            }
        });
    }

    private void setupSpinnerMajor() {
        ArrayAdapter<CharSequence> majorAdapter = ArrayAdapter.createFromResource(this,
                R.array.major_list, android.R.layout.simple_spinner_item);
        majorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_major.setAdapter(majorAdapter);
    }

    private void setupSpinnerYear() {
        ArrayAdapter<CharSequence> yearAdapter = ArrayAdapter.createFromResource(this,
                R.array.year_list, android.R.layout.simple_spinner_item);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_year.setAdapter(yearAdapter);
        sp_year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 1){
                    sp_class.setVisibility(View.VISIBLE);
                }else{
                    sp_class.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        sp_class.setVisibility(View.INVISIBLE);
    }

    private void setupSpinnerClass() {
        ArrayAdapter<CharSequence> classAdapter = ArrayAdapter.createFromResource(this,
                R.array.class_list, android.R.layout.simple_spinner_item);
        classAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_class.setAdapter(classAdapter);
    }

}
