package com.example.dudu.studentsapp2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.dudu.studentsapp2.Model.Student;

import java.util.List;

/**
 * Created by Dudu on 13/04/2016.
 */
public class StudentsListAdapter extends ArrayAdapter<Student> {

    private final Context context;
    private final List<Student> students;

    public StudentsListAdapter(Context context, int textViewResourceId, List<Student> students) {
        super(context, R.layout.student_row, students);
        this.context = context;
        this.students = students;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.student_row, parent, false);

        TextView textViewId = (TextView) rowView.findViewById(R.id.student_list_row_id);
        TextView textViewStudentName = (TextView) rowView.findViewById(R.id.student_list_row_stud_name);
        CheckBox checkBoxChecked = (CheckBox) rowView.findViewById(R.id.student_list_row_checkbox);

        final Student currStudent = this.students.get(position);

        textViewId.setText(currStudent.getId());
        textViewStudentName.setText(currStudent.getName());
        checkBoxChecked.setChecked(currStudent.isChecked());
        checkBoxChecked.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                currStudent.setChecked(isChecked);
            }
        });

        return rowView;
    }
}
