package com.example.petagram.view;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.petagram.CircleTransform;
import com.example.petagram.R;
import com.squareup.picasso.Picasso;

public class BioActivity extends BaseActivity {

    private ImageView imageBio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bio);

        imageBio = findViewById(R.id.imageBio);
        configActionBar(this);
        loadImageBio();
    }

    private void loadImageBio() {
        try {
            Picasso.get().load("https://media-exp1.licdn.com/dms/image/C4E03AQH9o32ooRkcDA/profile-displayphoto-shrink_200_200/0/1568994050534?e=1619049600&v=beta&t=slw1W3PtPCV2CCUIgvDyf3U8FhKrf39dbxtZsgePsQc").transform(new CircleTransform())
                    .into(imageBio);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}