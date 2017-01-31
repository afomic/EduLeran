package com.example.afomic.edulearn;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.afomic.edulearn.data.PreferenceManager;

public class MainActivity extends AppCompatActivity {
    FragmentManager fm;
    PreferenceManager preference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fm=getSupportFragmentManager();
        Fragment fragment=fm.findFragmentById(R.id.main_container);

        if(fragment==null){
            LoginFragment fragment1=new LoginFragment();
            fm.beginTransaction().add(R.id.main_container,fragment1).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.menu_register){
            EditFragment dialogFragment=new EditFragment();
            dialogFragment.show(fm,null);
            return  true;
        }
        return super.onOptionsItemSelected(item);
    }

}
