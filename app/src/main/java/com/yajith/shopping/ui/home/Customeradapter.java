package com.yajith.shopping.ui.home;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.yajith.shopping.R;

import java.util.ArrayList;
import java.util.List;

public class Customeradapter extends ArrayAdapter<String> {
    Activity activity;
    String[] name;
    String[] color;
    String[] price;
    String[] size;
    Integer[] imag;
    public Customeradapter(Activity context, String[] name,String[] color,String[] price,String[] size,Integer[] imag) {
        super(context, R.layout.list_shopping,name);
        this.activity=context;
        this.name=name;
        this.color=color;
        this.price=price;
        this.size=size;
        this.imag=imag;
    }
    private int lastPosition = -1;

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=activity.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.list_shopping, null,true);
        Animation animation = AnimationUtils.loadAnimation(getContext(), (position > lastPosition) ? R.anim.up : R.anim.down);
        rowView.startAnimation(animation);
        lastPosition = position;
        TextView Name=rowView.findViewById(R.id.name);
        TextView Size=rowView.findViewById(R.id.size);
        TextView Color=rowView.findViewById(R.id.color);
        TextView Price=rowView.findViewById(R.id.price);
        ImageView Imag=rowView.findViewById(R.id.image);
        Name.setText(name[position]);
        Size.setText(size[position]);
        Color.setText(color[position]);
        Price.setText(price[position]);
        Imag.setImageResource(imag[position]);
        return rowView;

    }
}