package yunkeiot.com.carapp.ui.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qmuiteam.qmui.widget.QMUIRadiusImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import yunkeiot.com.carapp.R;
import yunkeiot.com.carapp.ui.activities.CAMyCarsActivity;
import yunkeiot.com.carapp.ui.base.CABaseFragment;

public class CAUserInfoFragment extends CABaseFragment implements View.OnClickListener {
    private QMUIRadiusImageView headImage;
    private TextView userName;
    private TextView hasAuthor;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState, R.layout.fragment_userinfo_layout);
    }

    @Override
    public void setUpViews() {
        super.setUpViews();
        headImage = (QMUIRadiusImageView) findViewById(R.id.headPhoto);
        userName = (TextView) findViewById(R.id.mineUserName);
        hasAuthor = (TextView) findViewById(R.id.mineHasAuthor);

        findViewById(R.id.mineCars).setOnClickListener(this);
        findViewById(R.id.mineMessage).setOnClickListener(this);
        findViewById(R.id.mineNews).setOnClickListener(this);
        findViewById(R.id.mineSettings).setOnClickListener(this);
        findViewById(R.id.mineReflect).setOnClickListener(this);
        findViewById(R.id.mineAbout).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.mineCars:
                parent.startActivity(CAMyCarsActivity.class);
        }
    }
}
