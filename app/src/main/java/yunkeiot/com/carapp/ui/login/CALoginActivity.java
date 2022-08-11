package yunkeiot.com.carapp.ui.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Timer;
import androidx.annotation.Nullable;

import yunkeiot.com.carapp.R;
import yunkeiot.com.carapp.common.CAShareData;
import yunkeiot.com.carapp.common.CATools;
import yunkeiot.com.carapp.common.Logger;
import yunkeiot.com.carapp.common.PwdCheckUtil;
import yunkeiot.com.carapp.entity.CALoginEntity;
import yunkeiot.com.carapp.entity.Data.BaseEntity;
import yunkeiot.com.carapp.entity.Data.BaseFaultEntity;
import yunkeiot.com.carapp.http.engine.CAHttpParams;
import yunkeiot.com.carapp.http.engine.MD5Util;
import yunkeiot.com.carapp.http.engine.RequestCallback;
import yunkeiot.com.carapp.ui.base.CABaseActivity;

public class CALoginActivity extends CABaseActivity {
    private EditText mobile, code;
    private Timer timer;
    private int second = 60;
    private Button sendCode;


    private EditText userName, userPwd;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_layout);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void setUpViews() {
//        mobile = (EditText) findViewById(R.id.et_mobile);
//        code = (EditText) findViewById(R.id.et_code);
//        sendCode = (Button) findViewById(R.id.sendCode);


        userName = (EditText) findViewById(R.id.userName);
        userPwd = (EditText) findViewById(R.id.userPwd);

    }

    @Override
    public void setUpData() {

    }

    public void noLogin(View view) {
        /**
         * 游客登录
         */
        final String _mobile = "13900000000";
        final String _pwd = "123456";
        toMainActivity();
    }

    public void doLogin(View view) {

        final String _account = userName.getText().toString().trim();
        final String _pwd = userPwd.getText().toString().trim();
        if (CATools.isEmpty(_account)) {
            showToast(R.string.login_mobile_hide);
            return;
        }
        if (CATools.isEmpty(_pwd)) {
            showToast(R.string.login_pwd_hide);
            return;
        }

        if (!PwdCheckUtil.checkPasswordChars(_pwd)) {
            showToast("密码错误");
            return;
        }

        if (!PwdCheckUtil.checkPasswordLength(_pwd, 6, 20)) {
            showToast("密码错误");
            return;
        }
        showLoadingDialog(getString(R.string.dialog_login));
        postRequest(CAHttpParams.getLoginByPwd(_account, _pwd), CALoginEntity.class, new RequestCallback() {
            @Override
            public void onRequestSuccess(BaseEntity result, String jsonData) {
                if (result.isSuccesses()) {
                 //   closeTimer();
                    CALoginEntity loginEntity = (CALoginEntity) result;
                    if (loginEntity != null && loginEntity.getData() != null) {
                        Logger.i("loginResponse",loginEntity.getData().getAccessToken());
                        CAShareData.saveUserLoginData(loginEntity.getData());
                        CAShareData.saveLoginAccount(_account);
                        CAShareData.saveLoginPwd(_account,MD5Util.encrypt(_pwd));
                        CAShareData.saveCurrentLoginAccount(_account);
                        toMainActivity();
                        closeTipDialog();
                        finish();
                    } else {
                        closeTipDialog();
                        showFailedDialog(result.getResMsg());
                    }
                } else {
                    closeTipDialog();
                    showFailedDialog(((BaseFaultEntity)result).getData());
                }
            }

            @Override
            public void onRequestFailed(String msg) {
//                showToast(msg);
                closeTipDialog();
                showFailedDialog("登录失败,网络异常请重试");
            }
        });
    }

//    private void startTimer() {
//        if (timer == null) {
//            sendCode.setEnabled(false);
//            timer = new Timer();
//            timer.schedule(new TimerTask() {
//                @Override
//                public void run() {
//                    handler.sendEmptyMessage(second);
//                    second--;
//                }
//            }, 0, 1000);
//        }
//    }
//
//    private void closeTimer() {
//        if (timer != null) {
//            timer.cancel();
//            timer = null;
//        }
//    }
}
