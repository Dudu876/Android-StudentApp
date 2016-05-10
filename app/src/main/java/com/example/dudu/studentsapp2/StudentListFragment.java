package com.example.dudu.studentsapp2;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.dudu.studentsapp2.Model.Model;
import com.example.dudu.studentsapp2.Model.Student;

import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class StudentListFragment extends Fragment {

    private static final String TAG = "StudentListFragment";

    ListView list;
    List<Student> data;
    StudentsListAdapter adapter;
    private onStudentListSelected mListener;


    public static StudentListFragment newInstance() {
        return new StudentListFragment();
    }

    public StudentListFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof onStudentListSelected) {
            mListener = (onStudentListSelected) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement OnRageComicSelected.");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView =  inflater.inflate(R.layout.fragment_main, container, false);
        list = (ListView) rootView.findViewById(R.id.student_listview);
        data = Model.instance().getStudents();

        adapter = new StudentsListAdapter(getActivity(), R.layout.student_row, data);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Log.d(TAG, "onItemClick: position: " + position);
                mListener.onStudentListSelected(position);
            }
        });
        return rootView;
    }

    public interface onStudentListSelected {
        void onStudentListSelected(int position);
    }
}
