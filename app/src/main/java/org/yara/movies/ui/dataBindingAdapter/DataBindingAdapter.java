package org.yara.movies.ui.dataBindingAdapter;

import android.view.View;
import android.widget.ImageView;
import androidx.databinding.BindingAdapter;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import org.yara.movies.R;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class DataBindingAdapter {

    @BindingAdapter(value = "disableClickEvent")
    public static void setDisable(View view, boolean isDisable) {
        view.setOnTouchListener((v, event) -> isDisable);
    }

    @BindingAdapter(value = {"imageUrl"})
    public static void setImageWithSize(ImageView imageView, String url) {
        if (url != null && !url.equals("null")) {
            final int radius = 20;
            final int margin = 4;
            final Transformation transformation = new RoundedCornersTransformation(radius, margin);
            Picasso.get().load(url)
                    .placeholder(R.drawable.ic_place_holder_squar)
                    .transform(transformation)
                    .into(imageView);
        } else {
            imageView.setImageResource(R.drawable.ic_place_holder_squar);
        }
    }
}
