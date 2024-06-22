package sg.edu.np.mad.madpractical5;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    private static final String TAG = "ListActivity";
    public static ArrayList<User> myUser_List;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        myUser_List = new ArrayList<>();

        //Create a list of 10 Random Users
//        for (int i = 0; i < 20; i++) {
//            int name = new Random().nextInt(999999999);
//            int description = new Random().nextInt(999999999);
//            boolean followed = new Random().nextBoolean();
//
//            User user = new User("John Doe", "MAD Developer", 1, false);
//            user.setName("Name" + String.valueOf(name));
//            user.setDescription("Description " + String.valueOf(description));
//            user.setFollowed(followed);
//            myUser_List.add(user);
//        }

        DatabaseHandler db = new DatabaseHandler(this);
        myUser_List = db.getUsers();



        //Add This (RecyclerView)
        UserAdapter userAdapter = new UserAdapter(myUser_List, this);
        RecyclerView recyclerView = findViewById(R.id.recyclerView1);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager (layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(userAdapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}


