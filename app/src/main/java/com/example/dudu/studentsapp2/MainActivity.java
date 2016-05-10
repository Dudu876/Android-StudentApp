package com.example.dudu.studentsapp2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentContainer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements StudentListFragment.onStudentListSelected{

    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, StudentListFragment.newInstance(), "studentlist")
                    .commit();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG, "onCreateOptionsMenu: ");
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Log.d(TAG, "onOptionsItemSelected: trying to edit");

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            Fragment f = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
            if (f instanceof StudentListFragment) {
                Log.d(TAG, "onOptionsItemSelected: instanceof StudentListFragment");
                item.setVisible(false);
                getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(android.R.anim.slide_in_left ,android.R.anim.slide_out_right)
                        .replace(R.id.fragment_container, StudentDetailFragment.newInstance(-1), "studentlist")
                        .addToBackStack(null)
                        .commit();
            }
//            if (f instanceof StudentDetailFragment) {
//                Log.d(TAG, "onOptionsItemSelected: instanceof StudentDetailFragment");
//                if (((StudentDetailFragment)f).addingMode) {
//
//                }
//            }

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStudentListSelected(int position) {
        Log.d(TAG, "onStudentListSelected: position " + position);
        ((MenuView.ItemView)findViewById(R.id.action_add)).setTitle("Edit");
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(android.R.anim.slide_in_left ,android.R.anim.slide_out_right)
                .replace(R.id.fragment_container, StudentDetailFragment.newInstance(position), "studentlist")
                .addToBackStack(null)
                .commit();
    }
}
