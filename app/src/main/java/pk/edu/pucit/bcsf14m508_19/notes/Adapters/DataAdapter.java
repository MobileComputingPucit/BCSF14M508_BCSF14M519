package pk.edu.pucit.bcsf14m508_19.notes.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pk.edu.pucit.bcsf14m508_19.notes.Activities.DataActivity;
import pk.edu.pucit.bcsf14m508_19.notes.Activities.EditActivity;
import pk.edu.pucit.bcsf14m508_19.notes.Models.UserInfo;
import pk.edu.pucit.bcsf14m508_19.notes.R;

/**
 * Created by abc on 1/20/18.
 *
 * @package pk.edu.pucit.mobilecomputing.database.Adapters
 * @project Database
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    ArrayList<UserInfo> userInfos;
    Context context;

    public DataAdapter(Context context, ArrayList<UserInfo> userInfos) {
        this.userInfos = userInfos;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_info_layout,parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder holder, int position) {
        final UserInfo ui = userInfos.get(position);

        holder.id.setText(ui.getId()+"");
        holder.id.setTextColor(context.getResources().getColor(R.color.colorBlack));
        holder.title.setText(ui.getTitle()+"");
        holder.title.setTextColor(context.getResources().getColor(R.color.colorBlack));
        holder.date.setText(ui.getDate()+"");
        holder.date.setTextColor(context.getResources().getColor(R.color.colorBlack));
        holder.ll_ui.setBackgroundColor(position%2==1?Color.WHITE:Color.WHITE);

        holder.ll_ui.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Toast.makeText(context, ""+ui.getTitle(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, EditActivity.class);
                int i = ui.getId();
                intent.putExtra("id", i);
                intent.putExtra("title", ui.getTitle());
                intent.putExtra("body", ui.getBody());
                context.startActivity(intent);
            }


        });
    }




    @Override
    public int getItemCount() {
        return userInfos.size();
    }


    /**
     * DataAdapter.ViewHolder Class is below It will be used for designing
     * and setting the data entries in the adapter for recyclerview
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView id;
        public TextView title;
        public TextView date;
        public LinearLayout ll_ui;

        public ViewHolder(View view) {
            super(view);
            id = (TextView) view.findViewById(R.id.txt_id);
            title = (TextView) view.findViewById(R.id.txt_title);
            date = (TextView) view.findViewById(R.id.txt_date);
            ll_ui = (LinearLayout) view.findViewById(R.id.ll_ui_layout);
        }
    }





}
