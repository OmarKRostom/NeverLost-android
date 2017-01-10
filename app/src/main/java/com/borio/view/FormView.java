package com.borio.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.borio.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ahmed Emad on 4 May, 2015.
 */

public class FormView extends LinearLayout {

    @BindView(R.id.edit_name)
    EditText editName;
    @BindView(R.id.edit_name)
    EditText editPass;
    @BindView(R.id.tv_app_name2)
    TextView appText;

    public FormView(Context context) {
        super(context);
        loadView();
    }

    public FormView(Context context, AttributeSet attrs) {
        super(context, attrs);
        loadView();
    }

    public FormView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        loadView();
    }

    private void loadView() {
        setOrientation(VERTICAL);
        LayoutInflater.from(getContext()).inflate(R.layout.form_view, this);
        ButterKnife.bind(this);
    }

    @Override
    public void setFocusable(boolean focusable) {
        super.setFocusable(focusable);
        editName.setFocusable(focusable);
        editPass.setFocusable(focusable);
        appText.setFocusable(focusable);
    }
}
