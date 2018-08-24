package com.example.haniumvra.user;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.haniumvra.R;


public class JoinSuccessActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join_success);
    }

    public void onClick(View v) {

        Intent intent = null;

        switch (v.getId()) {

            case R.id.login_activity:

                intent = new Intent(JoinSuccessActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();

                break;

        }
    }
}
