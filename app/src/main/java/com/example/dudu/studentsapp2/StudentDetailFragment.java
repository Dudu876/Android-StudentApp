package com.example.dudu.studentsapp2;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.method.KeyListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dudu.studentsapp2.Model.Model;
import com.example.dudu.studentsapp2.Model.Student;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StudentDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StudentDetailFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String TAG = "StudentDetailFragment";

    // TODO: Rename and change types of parameters
    private int mPosition;
    private EditText vName;
    private EditText vId;
    private EditText vPhone;
    private EditText vAddress;
    private CheckBox vChecked;
    private BirthDataView vBirthData;

    private Button btnDelete;
    private Button btnSave;
    private Button btnCancel;

    private Student  mStudent;

    public boolean addingMode = false;

    Button btnEdit;


    public StudentDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param position Student's position in the list
     * @return A new instance of fragment StudentDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StudentDetailFragment newInstance(int position) {
        StudentDetailFragment fragment = new StudentDetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            mPosition = getArguments().getInt(ARG_PARAM1,0);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Log.d(TAG, "onCreateOptionsMenu: ");
        MenuItem i = menu.getItem(0);
        if (mPosition != -1) {
            i.setTitle("Edit");
        }
        else {
            i.setVisible(false);
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Log.d(TAG, "onOptionsItemSelected: trying to edit");
        int id = item.getItemId();
        if (id == R.id.action_add) {
            goEditing();
            item.setVisible(false);
            Log.d(TAG, "onOptionsItemSelected: trying to edit");
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_student_detail, container, false);
        vName    = (EditText) rootView.findViewById(R.id.main_name);
        vId      = (EditText) rootView.findViewById(R.id.main_id);
        vPhone   = (EditText) rootView.findViewById(R.id.main_phone);
        vAddress = (EditText) rootView.findViewById(R.id.main_address);
        vChecked = (CheckBox) rootView.findViewById(R.id.main_checked);
        vBirthData = (BirthDataView) rootView.findViewById(R.id.birth_data);

        btnDelete = ((Button)rootView.findViewById(R.id.delete_button));
        btnSave   = ((Button)rootView.findViewById(R.id.save_button));
        btnCancel = ((Button)rootView.findViewById(R.id.cancel_button));

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Model.instance().delete(mPosition);
                showDialog("Student Deleted!");
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //go back without saving
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student s = Model.instance().getStudent(mPosition);
                s.setName(((EditText)getView().findViewById(R.id.main_name)).getText().toString());
                s.setId(((EditText)getView().findViewById(R.id.main_id)).getText().toString());
                s.setPhone(((EditText)getView().findViewById(R.id.main_phone)).getText().toString());
                s.setAddress(((EditText)getView().findViewById(R.id.main_address)).getText().toString());
                s.setBirthDate(((BirthDataView)getView().findViewById(R.id.birth_data)).getDate());
                s.setBirthTime(((BirthDataView)getView().findViewById(R.id.birth_data)).getTime());

                //Snackbar.make(getView(),"Student Saved!",Snackbar.LENGTH_SHORT).show();
                showDialog("Student Saved!");
            }
        });

        // if a student was passed means is an edit state
        if (mPosition != -1) {
            addingMode = true;
            mStudent = Model.instance().getStudent(mPosition);

            vName.setText(mStudent.getName());
            vId.setText(mStudent.getId());
            vPhone.setText(mStudent.getPhone());
            vAddress.setText(mStudent.getAddress());
            vBirthData.setDate(mStudent.getBirthDate());
            vBirthData.setTime(mStudent.getBirthTime());
            vChecked.setChecked(mStudent.isChecked());

            viewMode(vName);
            viewMode(vId);
            viewMode(vPhone);
            viewMode(vAddress);
            vBirthData.viewMode();

            rootView.findViewById(R.id.button_layout).setVisibility(View.INVISIBLE);
        }
        // If no student was passed, means its an new student action
        else {
            btnDelete.setVisibility(View.GONE);
            vBirthData.editMode();
        }

        return rootView;
    }

    public void goEditing() {
        editMode(vName);
        editMode(vId);
        editMode(vPhone);
        editMode(vAddress);
        vBirthData.editMode();
        getView().findViewById(R.id.button_layout).setVisibility(View.VISIBLE);
    }

    private void viewMode(EditText v){
        v.setTag(v.getKeyListener());
        v.setKeyListener(null);
        //v.setKeyListener((KeyListener) v.getTag());
    }
    private void editMode(EditText v){
        //v.setTag(v.getKeyListener());
        //v.setKeyListener(null);
        v.setKeyListener((KeyListener) v.getTag());
    }

    private void showDialog(String text) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Student");
        builder.setMessage(text);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d("TAG", "OK");
            }
        });
        builder.show();
    }
}
