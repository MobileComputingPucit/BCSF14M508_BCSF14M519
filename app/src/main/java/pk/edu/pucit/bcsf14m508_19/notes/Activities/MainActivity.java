package pk.edu.pucit.bcsf14m508_19.notes.Activities;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

import pk.edu.pucit.bcsf14m508_19.notes.Adapters.DataAdapter;
import pk.edu.pucit.bcsf14m508_19.notes.Database.DBHelper;
import pk.edu.pucit.bcsf14m508_19.notes.Globals;
import pk.edu.pucit.bcsf14m508_19.notes.Models.UserInfo;
import pk.edu.pucit.bcsf14m508_19.notes.R;

public class MainActivity extends AppCompatActivity {


    DataAdapter dataAdapter;
    RecyclerView recyclerView;
    DBHelper DB_Helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        DB_Helper = new DBHelper(this);


        //Code to change the title bar of any activity that extends AppCompatActivity
        ActionBar ab = getSupportActionBar();
        ab.setTitle(Html.fromHtml("<font color='white'>Welcome To</font> <font color='red'><b>My Notes</b></font>"));
        ab.setSubtitle("A small notes app");


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        ArrayList<UserInfo> ui = DB_Helper.getAll();
        if (ui.size() <= 0) {
            final Snackbar sb = Snackbar.make(findViewById(R.id.clayout), "No Records Found.", Snackbar.LENGTH_INDEFINITE);
            sb.setAction("Dismiss", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sb.dismiss();
                }
            });
            sb.show();
        } else {
            dataAdapter = new DataAdapter(this, ui);

            LinearLayoutManager llm = new LinearLayoutManager(this);
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(llm);

            recyclerView.setAdapter(dataAdapter);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
                case R.id.list_all:
                    startActivityForResult(new Intent(this, DataActivity.class),123);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (requestCode == 123) {
            doRefresh(); // your "refresh" code
        }
        if (requestCode == 124) {
            doRefresh(); // your "refresh" code
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        doRefresh();
    }

    private void doRefresh() {
        Intent refresh = new Intent(this, MainActivity.class);
        startActivity(refresh);
        this.finish();
    }




}

