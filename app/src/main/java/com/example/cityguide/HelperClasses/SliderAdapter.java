package com.example.cityguide.HelperClasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.cityguide.R;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context){
        this.context = context;
    }

    int[] images = {
            R.drawable.ic_launcher_background,
            R.drawable.next_icon,
            R.drawable.splash_screen_background,
            R.drawable.ic_launcher_foreground
    };

    int[] headings = {
            R.string.first_slide_title,
            R.string.second_slide_title,
            R.string.third_slide_title,
            R.string.fourth_slide_title
    };

    int[] description = {
            R.string.first_slide_desctiption,
            R.string.second_slide_desctiption,
            R.string.third_slide_desctiption,
            R.string.fourth_slide_desctiption
    };

    @Override
    public int getCount() {
        return headings.length;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slides_layout, container, false);

        ImageView sliderImage = view.findViewById(R.id.iv_sliderImage);
        TextView descriptionText= view.findViewById(R.id.tv_sliderDescription);
        TextView headingsText = view.findViewById(R.id.tv_sliderHeading);

        sliderImage.setImageResource(images[position]);
        descriptionText.setText(description[position]);
        headingsText.setText(headings[position]);

        container.addView(view);

        return view;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}