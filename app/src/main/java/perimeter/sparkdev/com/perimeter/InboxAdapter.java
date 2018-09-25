package perimeter.sparkdev.com.perimeter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class InboxAdapter extends RecyclerView.Adapter<InboxAdapter.InboxViewHolder> {

    private ArrayList<String> list;
    private ArrayList<String> list2;



    public InboxAdapter(ArrayList list, ArrayList list2)
    {
        this.list = list;
        this.list2 = list2;

    }


    public class InboxViewHolder extends RecyclerView.ViewHolder
    {
//        ImageView ImageView;
        public TextView title;
        public TextView descrip;
        public InboxViewHolder(View view)
        {
            super(view);


            //ImageView imageView = (ImageView) view.findViewById(R.id.chatImage);
             title =  (TextView) view.findViewById(R.id.chatName);
             descrip = (TextView) view.findViewById(R.id.description);


        }
    }
    @NonNull
    @Override
    public InboxAdapter.InboxViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inboxView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listview_row, viewGroup, false);

        return new InboxViewHolder(inboxView);
    }

    @Override
    public void onBindViewHolder(@NonNull InboxAdapter.InboxViewHolder inboxViewHolder, int i) {
        String a = list.get(i);
        String b = list2.get(i);
        inboxViewHolder.title.setText(a);
        inboxViewHolder.descrip.setText(b);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
