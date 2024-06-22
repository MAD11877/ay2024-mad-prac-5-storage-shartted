package sg.edu.np.mad.madpractical5;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {
    private ArrayList<User> list_objects;
    // private ListActivity activity;
    public UserAdapter(ArrayList<User> list_objects, ListActivity activity){
        this.list_objects = list_objects;
        // this.activity = activity;
    }
    //Method to create a view holder for a username.
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_activity_list, parent, false);
        UserViewHolder holder = new UserViewHolder(view);
        return holder;
    }
    //Method to bind username to a view holder.
    public void onBindViewHolder (UserViewHolder holder, int position) {
        //Get position of a username
        User list_items = list_objects.get(position);
        //Set username to the view holder based on custom_activity_list.xml
        holder.name.setText(list_items.getName());
        //Set description to the view holder based on custom_activity_list.xml
        holder.description.setText(list_items.getDescription());
        //Configure setOnClickListener() for the small image on the view holder based on custom_activity_list.xml
        holder.smallImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                // Create AlertDialog inside onClick
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Profile");
                builder.setMessage(list_items.getName());
                builder.setCancelable(true);
                builder.setPositiveButton("VIEW", (dialog, which) -> {
                    Intent MainActivity = new Intent(v.getContext(), MainActivity.class);

                    Bundle extras = new Bundle();
                    extras.putString("Name", list_items.getName());
                    extras.putString("Description" , list_items.getDescription());
                    extras.putBoolean("Followed" , list_items.getFollowed());

                    MainActivity.putExtras(extras);
                    v.getContext().startActivity(MainActivity);
                });
                builder.setNegativeButton("CLOSE", (dialog, which) -> {

                });

                AlertDialog alert = builder.create();
                alert.show();
                Log.i(TAG, "Alert created");
            }
        });
        //Check if the last digit of the name is 7
        String name = list_items.getName();
        if (name.charAt(name.length()-1) == '7'){
            holder.bigImage.setVisibility(View.VISIBLE);
        } else {
            holder.bigImage.setVisibility(View.GONE);
        }
    }

    public int getItemCount() {return list_objects.size();}
}
