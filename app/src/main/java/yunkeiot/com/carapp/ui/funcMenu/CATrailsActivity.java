package yunkeiot.com.carapp.ui.funcMenu;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import yunkeiot.com.carapp.R;
import yunkeiot.com.carapp.common.CAShareData;
import yunkeiot.com.carapp.common.CATools;
import yunkeiot.com.carapp.common.Logger;
import yunkeiot.com.carapp.entity.CAMyCarsEntity;
import yunkeiot.com.carapp.entity.CATravelListEntity;
import yunkeiot.com.carapp.entity.Data.BaseEntity;
import yunkeiot.com.carapp.http.engine.CAHttpParams;
import yunkeiot.com.carapp.http.engine.RequestCallback;
import yunkeiot.com.carapp.ui.adapter.CATrailsListAdapter;
import yunkeiot.com.carapp.ui.base.CABaseActivity;
import yunkeiot.com.carapp.ui.view.XList.XListView;

public class CATrailsActivity extends CABaseActivity implements XListView.IXListViewListener{
    private XListView listView;
    private CATrailsListAdapter listAdapter;
    private String date = null;
    private CATravelListEntity dataBean;
    private List<CATravelListEntity.DataBean> dataList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trails_layout);
    }
    @Override
    public void setUpViews() {
        setNavTitle(getString(R.string.nav_fuc_trails));
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            date = bundle.getString("date", null);
        }
        listView = (XListView) findViewById(R.id.listView);
        listView.setAdapter(listAdapter = new CATrailsListAdapter(this));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CATravelListEntity.DataBean item = dataList.get(i - 1);
            }
        });
        listView.setPullLoadEnable(false);
        listView.setPullRefreshEnable(true);
        listView.setXListViewListener(this);
    }

    @Override
    public void setUpData() {
        getData(PAGE_REFRESH, 1);
    }

    private void getData(final int type,int page){
        String defaultCar = CAShareData.getDefaultCar();
        if (!CATools.isEmpty(defaultCar)) {
            postRequest(CAHttpParams.getTravelList(defaultCar, page), CATravelListEntity.class, new RequestCallback() {
                @Override
                public void onRequestSuccess(BaseEntity result, String jsonData) {
                    if (result.isSuccesses()) {
                        CATravelListEntity travelListEntity = (CATravelListEntity) result;
                        if (travelListEntity != null) {
                            dataBean = travelListEntity;
                            if (dataBean.getData() != null) {
                                if (type == PAGE_REFRESH) {
//                            listAdapter.resetDate();
                                    dataList = dataBean.getData();
                                } else {
                                    if (dataList == null) {
                                        dataList = new ArrayList<>();
                                    }
                                    if (dataBean.getData() != null) {
                                        dataList.addAll(dataBean.getData());
                                    }
                                }
                            }
                            listAdapter.setData(dataList);
                            if (dataBean.getPageNum() == 0 || dataBean.getPage() == dataBean.getPageNum()) {
                                listView.setPullLoadEnable(false);
                            } else {
                                listView.setPullLoadEnable(true);
                            }
                        }
                    }
                    stopRefresh();
                }

                @Override
                public void onRequestFailed(String msg) {
                    stopRefresh();
                }
            });
        }
    }


    @Override
    public void onRefresh() {
        getData(PAGE_REFRESH, 1);
    }

    @Override
    public void onLoadMore() {
        if (dataBean != null) {
            getData(PAGE_LOADMORE, dataBean.getPage() + 1);
        } else {
            getData(PAGE_REFRESH, 1);
        }
    }

    private void stopRefresh() {
        listView.stopRefresh();
        listView.stopLoadMore();

        if (dataList == null || dataList.size() == 0) {
            setCommonEmptyViewVisible(false);
        } else {
            setCommonEmptyViewVisible(true);
        }
    }

}
