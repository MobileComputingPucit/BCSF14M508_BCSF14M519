package pk.edu.pucit.bcsf14m508_19.notes.Activities;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.database.Cursor;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.ArrayList;

import pk.edu.pucit.bcsf14m508_19.notes.Adapters.DataAdapter;
import pk.edu.pucit.bcsf14m508_19.notes.Database.DBHelper;
import pk.edu.pucit.bcsf14m508_19.notes.Models.UserInfo;
import pk.edu.pucit.bcsf14m508_19.notes.R;

public class DataActivity extends AppCompatActivity implements View.OnClickListener{
    DBHelper DB_Helper;
    EditText et_title, et_body, et_date;
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DB_Helper = new DBHelper(this);
        et_title = (EditText) findViewById(R.id.et_title);
        et_body = (EditText) findViewById(R.id.et_body);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date1 = new Date();



        date = dateFormat.format(date1);






    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_save:
                long rid = DB_Helper.insert(et_title.getText().toString(), et_body.getText().toString(), date);
                finish();
                break;
        }
    }
}
