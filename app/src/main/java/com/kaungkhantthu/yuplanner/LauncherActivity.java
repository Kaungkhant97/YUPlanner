package com.kaungkhantthu.yuplanner;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.kaungkhantthu.yuplanner.utils.Constants;
import com.kaungkhantthu.yuplanner.utils.SPrefHelper;

/**
 * Created by Administrator's user on 14-Dec-16.
 */

public class LauncherActivity extends AppCompatActivity {
    private Spinner sp_major;
    private Spinner sp_class;
    private Spinner sp_year;
    private TextView txt_btnSubmit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.primary));
        }

        boolean firstTime = SPrefHelper.getBoolean(this, Constants.FIRSTTIME, false);


        if (!firstTime ) {
            SPrefHelper.putBoolean(this, Constants.FIRSTTIME, true);
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
                if (sp_major.getSelectedItemPosition() == 0 ||
                        sp_year.getSelectedItemPosition() == 0 ||
                        sp_class.getSelectedItemPosition() == 0) {
                    Toast.makeText(LauncherActivity.this, "Please complete the selection to Continue", Toast.LENGTH_SHORT).show();
                } else {
                    String[] majorarray = getResources().getStringArray(R.array.mmajor_list);
                    String[] yeararray = getResources().getStringArray(R.array.myear_list);
                    String[] classarray = getResources().getStringArray(R.array.mclass_list);

                    int selectedmajor = sp_major.getSelectedItemPosition();
                    int selectedyear = sp_year.getSelectedItemPosition();
                    int selectedclass = sp_class.getSelectedItemPosition();

                    SPrefHelper.putString(LauncherActivity.this, Constants.MAJOR, majorarray[selectedmajor]);
                    SPrefHelper.putString(LauncherActivity.this, Constants.CLASS, classarray[selectedclass]);
                    SPrefHelper.putString(LauncherActivity.this, Constants.YEAR, yeararray[selectedyear]);
                    Intent intent = new Intent(LauncherActivity.this, MainActivity.class);
                    startActivity(intent);

                }
            }
        });
    }

    private void setupSpinnerClass() {
        ArrayAdapter<CharSequence> classAdapter = ArrayAdapter.createFromResource(this,
                R.array.class_list, android.R.layout.simple_spinner_item);
        classAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_class.setAdapter(classAdapter);
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
    }

}
