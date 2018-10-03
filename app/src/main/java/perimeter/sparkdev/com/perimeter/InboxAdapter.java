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

    private ArrayList<String> mNames;  //title
    private ArrayList<String> mDescp; //description
    private ArrayList<Integer>   mImgs; //image



    public InboxAdapter(ArrayList list, ArrayList list2, ArrayList list3) {
        this.mNames = list;
        this.mDescp = list2;
        this.mImgs = list3;

    }


    @NonNull
    @Override
    public InboxAdapter.InboxViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inboxView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listview_row,
                                                                    viewGroup, false);

        //creates InboxViewHolder with the custom layout for the row
        return new InboxViewHolder(inboxView);
    }

    @Override
    public void onBindViewHolder(@NonNull InboxAdapter.InboxViewHolder inboxViewHolder, int i) {
        String a = mNames.get(i);
        String b = mDescp.get(i);
        Integer c = mImgs.get(i) ;

        inboxViewHolder.title.setText(a);
        inboxViewHolder.descrip.setText(b);
        inboxViewHolder.imageView.setImageResource(c);
    }

    @Override
    public int getItemCount() {
        return mNames.size();
    }

    //inner class too retrieve the views
    public class InboxViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView title;
        public TextView descrip;

        public InboxViewHolder(View view) {
            super(view);


            imageView = (ImageView) view.findViewById(R.id.chatImage);
            title =  (TextView) view.findViewById(R.id.chatName);
            descrip = (TextView) view.findViewById(R.id.description);


        }
    }
}
