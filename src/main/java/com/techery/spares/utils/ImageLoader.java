package com.techery.spares.utils;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ImageLoader {
    private final Context context;

    public ImageLoader(Context context) {
        this.context = context;
    }

    public void loadImage(String url, ImageView imageView) {
        Picasso.with(this.context).load(url).into(imageView);
    }
}
