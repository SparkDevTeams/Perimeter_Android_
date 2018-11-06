package com.sparkdev.perimeter.activities.Onboarding.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sparkdev.perimeter.R;

public class SliderAdapter extends PagerAdapter {
  private Context context;
  private LayoutInflater mLayoutInflater;


  public SliderAdapter(Context context){
    this.context= context;
  }

  public int[] slide_images ={
      R.drawable.onboarding1,R.drawable.onboarding2,R.drawable.logo
  };


  public String [] slide_descs={
      "Perimeter is a location based messaging application that allows users to communicate in realtime.",
      "Perimeter uses beacons to detect when a user is near in order to connect them to the correct chat room. (This feature is still in progress)",
      "Enjoy using Perimeter!"
  };

  @Override
  public int getCount(){
    return slide_images.length;
  }

  @Override
  public boolean isViewFromObject(View view, Object o){
    return view ==(RelativeLayout) o;
  }

  @NonNull
  @Override
  public Object instantiateItem(@NonNull ViewGroup container, int position) {
    mLayoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    View view= mLayoutInflater.inflate(R.layout.slide_layout,container,false);

    ImageView slideImageView= (ImageView) view.findViewById(R.id.slide_image);// the slide image that will have to be used
    TextView slideDescription = (TextView) view.findViewById(R.id.slide_desc);

    slideImageView.setImageResource(slide_images[position]);
    slideDescription.setText(slide_descs[position]);

    if (position==2){

    }
    container.addView(view);
    return view;
  }
  public void destroyItem( ViewGroup container, int position ,Object object){
    container.removeView((RelativeLayout)object);
  }
}