package com.example.dudu.studentsapp2;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.InputType;
import android.text.method.KeyListener;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TimePicker;

import com.example.dudu.studentsapp2.R;

/**
 * Created by Dudu on 14/04/2016.
 */
public class BirthDataView extends RelativeLayout {

    private static final String TAG = "BirthDataView";

    private EditText m_Date;
    private EditText m_Time;

    private boolean edit = false;

    public String getDate() {
        return m_Date.getText().toString();
    }

    public String getTime() {
        return m_Time.getText().toString();
    }

    public void setDate(String date) {
        m_Date.setText(date);
    }

    public void setTime(String time) {
        m_Time.setText(time);
    }

    public BirthDataView(Context context) {
        super(context);
        init();
    }

    public BirthDataView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BirthDataView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BirthDataView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.view_birth_data, this);
        if (!isInEditMode())
        {
            m_Date = (EditText) findViewById(R.id.main_date);
            m_Time = (EditText) findViewById(R.id.main_time);

            m_Date.setInputType(InputType.TYPE_NULL);
            m_Time.setInputType(InputType.TYPE_NULL);

            m_Date.setOnTouchListener(new OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (edit && motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                        DatePickerDialog dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                m_Date.setText(Integer.toString(i) + '/' + Integer.toString(i1+1) + '/' + Integer.toString(i2));
                            }
                        }, 1992, 6, 16);
                        dialog.show();
                    }
                    return false;
                }
            });

            m_Time.setOnTouchListener(new OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (edit && motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                        TimePickerDialog dialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                                m_Time.setText(Integer.toString(i) + ':' + Integer.toString(i1));
                            }
                        },14,35,true);
                        dialog.show();
                    }
                    return false;
                }
            });
        }
    }

    public void viewMode() {
        m_Date.setTag(m_Date.getKeyListener());
        m_Date.setKeyListener(null);
        m_Time.setTag(m_Time.getKeyListener());
        m_Time.setKeyListener(null);
    }

    public void editMode(){
        //v.setTag(v.getKeyListener());
        //v.setKeyListener(null);
        //m_Date.setKeyListener((KeyListener) m_Date.getTag());
        //m_Time.setKeyListener((KeyListener) m_Time.getTag());
        edit = true;
    }
}
