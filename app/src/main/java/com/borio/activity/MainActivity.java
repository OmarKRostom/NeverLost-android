package com.borio.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.borio.R;
import com.borio.Utils;
import com.borio.adapter.ProvidersAdapter;
import com.borio.adapter.SimpleItemTouchHelperCallback;
import com.borio.data.ProviderInfo;
import com.borio.view.ProviderCardView;
import com.melnykov.fab.FloatingActionButton;
import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IAdapter;
import com.tiancaicc.springfloatingactionmenu.MenuItemView;
import com.tiancaicc.springfloatingactionmenu.OnMenuActionListener;
import com.tiancaicc.springfloatingactionmenu.SpringFloatingActionMenu;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Ahmed Emad on 4 May, 2015.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static int[] frameAnimRes = new int[]{
            R.mipmap.compose_anim_1,
            R.mipmap.compose_anim_2,
            R.mipmap.compose_anim_3,
            R.mipmap.compose_anim_4,
            R.mipmap.compose_anim_5,
            R.mipmap.compose_anim_6,
            R.mipmap.compose_anim_7,
            R.mipmap.compose_anim_8,
            R.mipmap.compose_anim_9,
            R.mipmap.compose_anim_10,
            R.mipmap.compose_anim_11,
            R.mipmap.compose_anim_12,
            R.mipmap.compose_anim_13,
            R.mipmap.compose_anim_14,
            R.mipmap.compose_anim_15,
            R.mipmap.compose_anim_15,
            R.mipmap.compose_anim_16,
            R.mipmap.compose_anim_17,
            R.mipmap.compose_anim_18,
            R.mipmap.compose_anim_19
    };

    private SpringFloatingActionMenu springFloatingActionMenu;
    private int frameDuration = 20;
    private AnimationDrawable frameAnim;
    private AnimationDrawable frameReverseAnim;

    private RecyclerView mRecyclerView;
    private CoordinatorLayout coordinatorLayout;

    private List<ProviderInfo> providerInfos;
    private List<ProviderCardView> providerPasswordsViews;
    private ProvidersAdapter<ProviderCardView> mFastItemAdapter;
    private ItemTouchHelper mItemTouchHelper;

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

        initViews();

        createFabFrameAnim();
        createFabReverseFrameAnim();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        providerInfos = getIntent().getParcelableArrayListExtra("providers_infos");
        System.out.println(providerInfos.size());
        setupProvidersCards();

        initFAB();
    }

    private void initViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_providers);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.activity_main);
    }

    private void setupProvidersCards() {
        mFastItemAdapter = new ProvidersAdapter<>(this, coordinatorLayout);
        mFastItemAdapter.withSelectable(false);
        mFastItemAdapter.withMultiSelect(false);
        mFastItemAdapter.withSelectOnLongClick(false);
        mFastItemAdapter.withOnClickListener(new FastAdapter.OnClickListener<ProviderCardView>() {
            @Override
            public boolean onClick(View v, IAdapter<ProviderCardView> adapter, ProviderCardView item, int position) {
                System.out.println(item.providerInfo.getProvider());
                Intent intent = new Intent(MainActivity.this, ProviderInfoActivity.class);
                intent.putExtra("provider_info", item.providerInfo);
                startActivity(intent);
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

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mFastItemAdapter, this);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    private void addNewProviderInfo() {
        Intent intent = new Intent(MainActivity.this, NewProviderInfoActivity.class);
        intent.putParcelableArrayListExtra("providers_infos", new ArrayList(providerInfos));
        startActivity(intent);
    }

    private void initFAB() {
        final FloatingActionButton fab = new FloatingActionButton(this);
        fab.setType(FloatingActionButton.TYPE_NORMAL);
        fab.setImageDrawable(frameAnim);
        fab.setColorPressedResId(R.color.fabPrimary);
        fab.setColorNormalResId(R.color.fab);
        fab.setColorRippleResId(R.color.text_color);
        fab.setShadow(true);
        springFloatingActionMenu = new SpringFloatingActionMenu.Builder(this)
                .fab(fab)
                .addMenuItem(R.color.photo, R.mipmap.out, Utils.logOut,
                        R.color.text_color, this)
                .addMenuItem(R.color.link, R.mipmap.key, Utils.newProviderInfo,
                        R.color.text_color, this)
                .addMenuItem(R.color.quote, R.mipmap.aboutapp, Utils.aboutApp,
                        R.color.text_color, this)
                .addMenuItem(R.color.audio, R.mipmap.aboutus, Utils.aboutUS,
                        R.color.text_color, this)
                .animationType(SpringFloatingActionMenu.ANIMATION_TYPE_TUMBLR)
                .revealColor(R.color.fabPrimary)
                .gravity(Gravity.CENTER | Gravity.BOTTOM)
                .onMenuActionListner(new OnMenuActionListener() {
                    @Override
                    public void onMenuOpen() {
                        fab.setImageDrawable(frameAnim);
                        frameReverseAnim.stop();
                        frameAnim.start();
                    }

                    @Override
                    public void onMenuClose() {
                        fab.setImageDrawable(frameReverseAnim);
                        frameAnim.stop();
                        frameReverseAnim.start();
                    }
                })
                .build();
    }

    private void createFabFrameAnim() {
        frameAnim = new AnimationDrawable();
        frameAnim.setOneShot(true);
        Resources resources = getResources();
        for (int res : frameAnimRes) {
            frameAnim.addFrame(resources.getDrawable(res), frameDuration);
        }
    }

    private void createFabReverseFrameAnim() {
        frameReverseAnim = new AnimationDrawable();
        frameReverseAnim.setOneShot(true);
        Resources resources = getResources();
        for (int i = frameAnimRes.length - 1; i >= 0; i--) {
            frameReverseAnim.addFrame(resources.getDrawable(frameAnimRes[i]), frameDuration);
        }
    }

    @Override
    public void onClick(View v) {
        MenuItemView menuItemView = (MenuItemView) v;
        String text = menuItemView.getLabelTextView().getText().toString();
        if (text.equals(Utils.newProviderInfo)) {
            if (springFloatingActionMenu.isMenuOpen()) {
                springFloatingActionMenu.hideMenu();
            }
            addNewProviderInfo();
        } else if (text.equals(Utils.logOut)) {
            Toast.makeText(this, menuItemView.getLabelTextView().getText(), Toast.LENGTH_SHORT).show();
        } else if (text.equals(Utils.aboutApp)) {
            Toast.makeText(this, "This is the best !", Toast.LENGTH_SHORT).show();
        } else if (text.equals(Utils.aboutUS)) {
            Toast.makeText(this, "We are the best !", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onBackPressed() {
        if (springFloatingActionMenu.isMenuOpen()) {
            springFloatingActionMenu.hideMenu();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
