package com.yoprogramo.earthquake;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yoprogramo.earthquake.earth.view.StarSignPrickerActivity;

import java.util.regex.Pattern;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PickerTesterActivity extends AppCompatActivity {

    public static final int PICK_STARSIGN = 1;

    private final String REG_EXP = "\\w*learn more";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picker_tester);

        TextView textView = findViewById(R.id.tester_textview);
        textView.setText("This is sample text <<thishyperlink>> the sample text continue. learn more");
        setHyperLynk(textView);


        Button button = findViewById(R.id.tester_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, Uri.parse("starsigns://"));

                startActivityForResult(intent, 1);
            }
        });
    }

    private void setHyperLynk(TextView textView) {
        int flags = Pattern.CASE_INSENSITIVE;
        Pattern p = Pattern.compile(REG_EXP, flags);

        Intent testIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("nudge://navigation_key"));
        startActivity(testIntent);
        //Linkify.addLinks(textView,p,"nudge://navigation_key");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        TextView textView = findViewById(R.id.tester_textview);
        textView.setText(data.getStringExtra(StarSignPrickerActivity.EXTRA_SIGN_NAME));
    }
}
