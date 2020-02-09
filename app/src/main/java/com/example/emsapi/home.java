package com.example.emsapi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class home extends AppCompatActivity {
    private static final int REQUEST_CALL = 1;
    TextView name,email,phone,gender,birthdate;
    String getName,getEmail,getPhone,getGender,getBirthdate;
    CircleImageView mImage;
    String getImage;
    CardView mcall,memail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mImage = findViewById(R.id.image);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        gender = findViewById(R.id.gender);
        birthdate = findViewById(R.id.birthdate);
        mcall = findViewById(R.id.mcall);
        memail = findViewById(R.id.memail);
        getName = getIntent().getStringExtra("name");
        name.setText(getName);

        getEmail = getIntent().getStringExtra("email");
        email.setText(getEmail);

        getPhone = getIntent().getStringExtra("mobile");
        phone.setText(getPhone);

        getGender = getIntent().getStringExtra("gender");
        gender.setText(getGender);

        String imageur=getIntent().getStringExtra("image");
        Picasso.get().load(imageur).into(mImage);

        getBirthdate = getIntent().getStringExtra("birthday");
        birthdate.setText(getBirthdate);

        mcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall();
            }
        });
        memail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto",getEmail, null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
            }
        });
    }
    void makePhoneCall() {

        if(getPhone.trim().length() > 0) {
            if(ContextCompat.checkSelfPermission(getApplicationContext(),
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(home.this,
                        new String[] {Manifest.permission.CALL_PHONE},REQUEST_CALL);
            } else {
                String dial = "tel: " + getPhone;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        } else {
            Toast.makeText(getApplicationContext(),"Please Enter phone Number", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_CALL) {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall();
            } else {
                Toast.makeText(this, "permission Denided", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
