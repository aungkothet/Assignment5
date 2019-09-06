package io.github.aungkothet.padc.assignment5.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import io.github.aungkothet.padc.assignment5.data.models.HouseModel;
import io.github.aungkothet.padc.assignment5.data.models.HouseModelImpl;


public class BaseActivity extends AppCompatActivity {

    HouseModel mHouseModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHouseModel = HouseModelImpl.getObjInstance();
    }

    protected void showSnack(String message){
        final Snackbar snackbar = Snackbar.make(getWindow().getDecorView(),message,Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction(getResources().getString(android.R.string.ok), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });
        snackbar.show();

    }
}
