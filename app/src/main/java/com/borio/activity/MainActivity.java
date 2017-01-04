package com.borio.activity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.borio.R;
import com.borio.data.ProviderInfo;
import com.borio.view.ProviderCardView;
import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IAdapter;
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Ahmed Emad on 4 May, 2015.
 */

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;

    private List<ProviderInfo> providerInfos;
    private List<ProviderCardView> providerPasswordsViews;
    private FastItemAdapter<ProviderCardView> mFastItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Panton-SemiBoldItalic.otf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_providers);

        System.out.println(mRecyclerView == null);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        providerInfos = getIntent().getParcelableArrayListExtra("provider_passwords");
        System.out.println(providerInfos.size());
        setupProvidersCards();
    }

    private void setupProvidersCards() {
        mFastItemAdapter = new FastItemAdapter<>();
        mFastItemAdapter.withSelectable(false);
        mFastItemAdapter.withMultiSelect(false);
        mFastItemAdapter.withSelectOnLongClick(false);
        mFastItemAdapter.withOnClickListener(new FastAdapter.OnClickListener<ProviderCardView>() {
            @Override
            public boolean onClick(View v, IAdapter<ProviderCardView> adapter, ProviderCardView item, int position) {
                System.out.println(item.providerInfo.getProvider());
//                Intent intent = new Intent(MainActivity.this, MainActivity.class);
//                intent.putExtra("provider_password", item.providerPassword);
//                startActivity(intent);
                return false;
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setAdapter(mFastItemAdapter);

        providerPasswordsViews = new LinkedList<>();
        for (ProviderInfo providerInfo : providerInfos) {
            providerPasswordsViews.add(new ProviderCardView(providerInfo, this));
        }
        mFastItemAdapter.add(providerPasswordsViews);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
