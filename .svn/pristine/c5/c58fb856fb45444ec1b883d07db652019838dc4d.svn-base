package yunkeiot.com.carapp.ui.main.fragment;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.gson.Gson;

import androidx.annotation.Nullable;
import yunkeiot.com.carapp.R;
import yunkeiot.com.carapp.common.CAShareData;
import yunkeiot.com.carapp.common.Logger;
import yunkeiot.com.carapp.entity.CAMyCarsEntity;
import yunkeiot.com.carapp.ui.base.CABaseActivity;
import yunkeiot.com.carapp.ui.base.CABaseFragment;

public class CACarDetailFragment extends CABaseFragment {
    private View rootView;
    private CAMyCarsEntity.CarBean item;
    private TextView defaultButton;
    private EditText carNickName, carCode, carPlat, carBrand, carModel, carDate, carDeciveID, carW;
    private TextView tvColor;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return rootView = inflater.inflate(R.layout.car_detail_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        parent = (CABaseActivity) getActivity();
        item = (CAMyCarsEntity.CarBean) getArguments().getSerializable("item");
        setUpViews();
        setUpData();
        update();
    }

    @Override
    public void refreshData() {
        super.refreshData();
        update();
        //setShareStatus();
    }

    public void setUpData() {
        if (item!=null){
            carPlat.setText(item.getPlateNumber());
            carDeciveID.setText(item.getDeviceId());
            carCode.setText(item.getVehicleIdentificationNumber());
            carBrand.setText(item.getVehicleManufacturers());
            carModel.setText(item.getVehicleMode());
        }
    }

    public void setUpViews() {
        carNickName = (EditText) rootView.findViewById(R.id.carNickName);  //爱车昵称
        carPlat = (EditText) rootView.findViewById(R.id.carPlat);    //车牌号
        carDeciveID = (EditText) rootView.findViewById(R.id.carDeviceID);  //设备号
        carCode = (EditText) rootView.findViewById(R.id.carCode);  //车架号
        carBrand = (EditText) rootView.findViewById(R.id.carBrand);  //品牌
        carModel = (EditText) rootView.findViewById(R.id.carModel);  //型号
        carDate = (EditText) rootView.findViewById(R.id.carDate);  //绑定日期
        tvColor = (TextView) rootView.findViewById(R.id.tvColor);  //车辆颜色
        defaultButton = (TextView) rootView.findViewById(R.id.defaultCar);
//        rootView.findViewById(R.id.unBindBiKe).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                unBindBike();
//            }
//        });
//
//        carName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
//                if (actionId == EditorInfo.IME_ACTION_DONE) {
//                    EBTools.hideKeyBoard(parent, carName);
//                    commitNewName();
//                    return true;
//                }
//                return false;
//            }
//        });
//        carName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View view, boolean b) {
//                if (!b) {
//                    commitNewName();
//                }
//            }
//        });
//
//        share = (Button) rootView.findViewById(R.id.shareCar);
//        rootView.findViewById(R.id.shareCar).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (item.getShareState() == 0) {
//                    doShareVehicle();
//                } else {
//                    doEndShareVehicle();
//                }
//            }
//        });
//
        rootView.findViewById(R.id.defaultCar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CAShareData.saveDefaultCar(item.getPlateNumber());
                parent.showSuccessDialog("设置为默认成功");
                rootView.findViewById(R.id.defaultCar).setEnabled(false);
                update();
            }
        });
//
//        setShareStatus();
    }
    public void update() {
        if (item != null) {
            if (item.getPlateNumber().equals(CAShareData.getDefaultCar())) {
                rootView.findViewById(R.id.defaultCar).setEnabled(false);
                defaultButton.setText("已为默认");
            } else {
                rootView.findViewById(R.id.defaultCar).setEnabled(true);
                defaultButton.setText("设为默认");
            }
        }
    }
}
