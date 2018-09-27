package perimeter.sparkdev.com.perimeter;

import android.media.Image;
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

    private ArrayList<String> list;  //title
    private ArrayList<String> list2; //description
    private ArrayList<Integer>   list3; //image



    public InboxAdapter(ArrayList list, ArrayList list2, ArrayList list3)
    {
        this.list = list;
        this.list2 = list2;
        this.list3 = list3;

    }


    @NonNull
    @Override
    public InboxAdapter.InboxViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View inboxView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listview_row,
                                                                    viewGroup, false);

        //creates InboxViewHolder with the custom layout for the row
        return new InboxViewHolder(inboxView);
    }

    @Override
    public void onBindViewHolder(@NonNull InboxAdapter.InboxViewHolder inboxViewHolder, int i)
    {
        String a = list.get(i);
        String b = list2.get(i);
        Integer c = list3.get(i) ;

        inboxViewHolder.title.setText(a);
        inboxViewHolder.descrip.setText(b);
        inboxViewHolder.imageView.setImageResource(c);
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }

    //inner class too retrieve the views
    public class InboxViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView imageView;
        public TextView title;
        public TextView descrip;

        public InboxViewHolder(View view)
        {
            super(view);


            imageView = (ImageView) view.findViewById(R.id.chatImage);
            title =  (TextView) view.findViewById(R.id.chatName);
            descrip = (TextView) view.findViewById(R.id.description);


        }
    }
}
