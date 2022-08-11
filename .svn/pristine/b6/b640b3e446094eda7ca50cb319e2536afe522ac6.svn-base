package yunkeiot.com.carapp.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.annotation.Nullable;
import yunkeiot.com.carapp.R;
import yunkeiot.com.carapp.common.CAShareData;
import yunkeiot.com.carapp.common.CATools;
import yunkeiot.com.carapp.entity.CALoginEntity;
import yunkeiot.com.carapp.ui.base.CABaseActivity;

public class CALaunchActivity extends CABaseActivity{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_layout);
    }
    @Override
    public void setUpViews() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                checkData();
            }
        }, 1000);
    }
    private void checkData() {
//        if (EBShareData.getHasGuide()) {
//        CALoginEntity.UserLoginData userLoginData = CAShareData.getUserLoginData();
//        if (userLoginData != null && !CATools.isEmpty(userLoginData.getToken())) {
//          if (userLoginData != null && !CATools.isEmpty(userLoginData.getToken())) {
//            autoLogin(userLoginData.getToken());
//        } else {
            toLoginActivity();
//        }
//        } else {
//            startActivity(EBGuideActivity.class);
//            finish();
//        }
    }
    private void autoLogin(String token) {
//        String mobile = EBShareData.getLoginMobile();
//        String pwd = EBShareData.getLoginPwd();
//        if (!EBTools.isEmpty(mobile) && !EBTools.isEmpty(pwd)) {
//            postRequest(EBHttpParams.getLoginByPwdEncrypt(mobile, pwd), EBLoginEntity.class, new RequestCallback() {
//                @Override
//                public void onRequestSuccess(BaseEntity result, String jsonData) {
//                    if (result.isSuccesses()) {
//                        EBLoginEntity loginEntity = (EBLoginEntity) result;
//                        if (loginEntity != null && loginEntity.getData() != null) {
//                            if (EBShareData.isLoginWidthDefault()) {
//                                loginEntity.getData().setType(1);
//                            }
//                            EBShareData.saveUserLoginData(loginEntity.getData());
//
//                            /**
//                             * 消息
//                             */
//                            Intent intent = createMainIntent();
//                            if (getIntent() != null && getIntent().getExtras() != null) {
//                                intent.putExtra("message", getIntent().getExtras().getSerializable("message"));
//                            }
//                            toMainActivity(intent);
//
//                            finish();
//                        } else {
//                            toLoginActivity();
//                        }
//                    } else {
//                        toLoginActivity();
//                    }
//                }
//
//                @Override
//                public void onRequestFailed(String msg) {
//                    toLoginActivity();
//                }
//            });
//        } else {
//            toLoginActivity();
//        }
    }
    private void toLoginActivity() {
        startActivity(CALoginActivity.class);
        finish();
    }
    @Override
    public void setUpData() {

    }
}
