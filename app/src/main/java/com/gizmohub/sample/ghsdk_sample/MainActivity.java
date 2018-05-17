package com.gizmohub.sample.ghsdk_sample;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.gizmohub.sdk.parameter.car.CarParameter;
import com.gizmohub.sdk.parameter.car.CarStateInfo;
import com.gizmohub.sdk.parameter.car.CarStateModel;
import com.gizmohub.sdk.view.GHView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String PARAMETER = "parameter";
    private com.gizmohub.sdk.view.GHView GHView;
    private boolean carLight = false;
    private boolean car3DButton = false;
    private boolean carDoor = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(com.gizmohub.sdk.R.layout.gactivity);

        GHView = (GHView) findViewById(com.gizmohub.sdk.R.id.gview);
        findViewById(com.gizmohub.sdk.R.id.white).setOnClickListener(this);
        findViewById(com.gizmohub.sdk.R.id.blue).setOnClickListener(this);
        findViewById(com.gizmohub.sdk.R.id.black).setOnClickListener(this);
        findViewById(com.gizmohub.sdk.R.id.gray).setOnClickListener(this);
        findViewById(com.gizmohub.sdk.R.id.door).setOnClickListener(this);
        findViewById(com.gizmohub.sdk.R.id.light).setOnClickListener(this);
        findViewById(com.gizmohub.sdk.R.id.dButton).setOnClickListener(this);
        findViewById(com.gizmohub.sdk.R.id.outview).setOnClickListener(this);
        findViewById(com.gizmohub.sdk.R.id.inview).setOnClickListener(this);

        load3D();
    }

    private void load3D(){
        Intent intent = getIntent();
        if(intent == null || GHView == null) return;
        //BaseParameter baseParameter = intent.get(PARAMETER);
        CarStateInfo stateInfo = new CarStateInfo();
        stateInfo.CarLight = carLight;
        stateInfo.CarExterior = CarStateModel.build().setName("F00002-6");
        //stateInfo.CarView = CarStateModel.build().setName("look");
        stateInfo.CarDoors = new Boolean[]{false,false};
        stateInfo.CarWheel = CarStateModel.build().setName("F00003-5");
        stateInfo.Car3DButtons =car3DButton;
        CarParameter carParameter = new CarParameter("",stateInfo,false);
        GHView.loadModel(carParameter, null);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if ((keyCode == KeyEvent.KEYCODE_BACK) && gView.goBack() ) {
//            return true;
//        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == com.gizmohub.sdk.R.id.white){
            GHView.modifyCarExterior(CarStateModel.build().setName("F00002-7"));
        }else if(id == com.gizmohub.sdk.R.id.blue){
            GHView.modifyCarExterior(CarStateModel.build().setName("F00002-8"));
        }else if(id == com.gizmohub.sdk.R.id.black){
            GHView.modifyCarExterior(CarStateModel.build().setName("F00002-6"));
        }else if(id == com.gizmohub.sdk.R.id.gray){
            GHView.modifyCarExterior(CarStateModel.build().setName("F00002-9"));
        }else if(id == com.gizmohub.sdk.R.id.door){
            carDoor = !carDoor;
            if(carDoor){
                GHView.modifyCarDoor(new Boolean[]{true,true});
            }else {
                GHView.modifyCarDoor(new Boolean[]{false,false});
            }
        }else if(id == com.gizmohub.sdk.R.id.light){
            carLight = !carLight;
            GHView.modifyCarLight(carLight);
        }else if(id == com.gizmohub.sdk.R.id.dButton){
            car3DButton = !car3DButton;
            GHView.modifyCar3DButtons(car3DButton);
        }else if(id == com.gizmohub.sdk.R.id.outview){
            GHView.modifyCarView(CarStateModel.build().setName("orbit"));
        }else if(id == com.gizmohub.sdk.R.id.inview){
            GHView.modifyCarView(CarStateModel.build().setName("look"));
        }
    }
}
