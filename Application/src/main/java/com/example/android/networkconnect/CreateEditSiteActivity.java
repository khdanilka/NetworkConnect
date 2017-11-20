package com.example.android.networkconnect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.networkconnect.com.server.data.datas.Site;

public class CreateEditSiteActivity extends AppCompatActivity implements View.OnClickListener {

    Site site;
    Button b;
    EditText name;
    EditText url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_edit_site);


        Intent intent = getIntent();
        site = intent.getParcelableExtra("test site");
//
//        site = new Site();
//        site.setName("koko");
//        site.setUrl("koko.ru");


        name = (EditText) findViewById(R.id.name_site);
        if (site.getName()!=null) name.setText(site.getName());
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //name.setText(charSequence.toString());
                site.setName(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //name.setText(editable.toString());
                //System.out.println(name.getText());
            }
        });

        url = (EditText) findViewById(R.id.url);
        if (site.getUrl()!=null) url.setText(site.getUrl());
        url.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //name.setText(charSequence.toString());
                site.setUrl(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //name.setText(editable.toString());
                //System.out.println(name.getText());
            }
        });


        b = (Button) findViewById(R.id.add_button_site);
        b.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if (view == b) {

            if (TextUtils.isEmpty(name.getText().toString())){
                name.setError(getString(R.string.error_field_required));
                return;
            }

            if (TextUtils.isEmpty(url.getText().toString())){
                url.setError(getString(R.string.error_field_required));
                return;
            }

            //dataManager.addOrEditSite(site);
            //close activity
            System.out.println(site);

        }


    }
}
