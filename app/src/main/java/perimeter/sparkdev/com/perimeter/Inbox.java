package perimeter.sparkdev.com.perimeter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Inbox extends  AppCompatActivity {

    int[] IMAGES = {R.drawable.person1, R.drawable.ecs,R.drawable.lab,
                    R.drawable.sun, R.drawable.panther_hall,R.drawable.person1,
                    R.drawable.ecs,R.drawable.lab,R.drawable.sun, R.drawable.panther_hall};
    String[] NAMES = {"GC", "ECS", "CP", "Astronomy",
                        "Panther Hall","GC", "ECS", "CP", "Astronomy",
                         "Panther Hall"};
    String [] DESCRIPTIONS = {"Whats for lunch?", "Im going to print now.", "Good luck on the test!"
                            ,"The starts look beautiful.", "Anyone found an ID?",
                            "Whats for lunch?", "Im going to print now.", "Good luck on the test!"
                             ,"The starts look beautiful.", "Anyone found an ID?"} ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);

        ListView listView = (ListView)findViewById(R.id.messagesID);

        CustomAdapter customAdapter = new CustomAdapter();

        listView.setAdapter(customAdapter);

    }


    class CustomAdapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            return IMAGES.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.listview_row,null);

            ImageView imageView = (ImageView) convertView.findViewById(R.id.chatImage);
            TextView  title =  (TextView) convertView.findViewById(R.id.chatName);
            TextView descrip = (TextView) convertView.findViewById(R.id.description);

            imageView.setImageResource(IMAGES[position]);
            title.setText(NAMES[position]);
            descrip.setText(DESCRIPTIONS[position]);
            return convertView;
        }
    }

}
