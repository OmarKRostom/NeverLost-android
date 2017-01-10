package com.borio.adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.borio.R;
import com.borio.data.ProviderInfo;
import com.borio.view.ProviderCardView;
import com.mikepenz.fastadapter.IItem;
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter;

/**
 * Created by Ahmed Emad on 4 May, 2015.
 */

public class ProvidersAdapter<Item extends IItem> extends FastItemAdapter<Item> implements ItemTouchHelperAdapter {

    private Context context;
    private CoordinatorLayout coordinatorLayout;

    public ProvidersAdapter(Context context, CoordinatorLayout coordinatorLayout) {
        super();
        this.context = context;
        this.coordinatorLayout = coordinatorLayout;
    }

    @Override
    public void onItemDismiss(final int position) {
        final ProviderInfo providerInfo = ((ProviderCardView) getAdapterItem(position)).providerInfo;

        notifyItemRemoved(position);
        remove(position);
        notifyItemRangeChanged(0, getItemCount());
        notifyDataSetChanged();

        final Snackbar snackbar = Snackbar
                .make(coordinatorLayout, providerInfo.getProvider() + " Deleted", Snackbar.LENGTH_LONG)
                .setActionTextColor(ContextCompat.getColor(context, R.color.white))
                .setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        add(position, (Item) new ProviderCardView(providerInfo, context));
                        notifyItemInserted(position);
                        notifyDataSetChanged();
                    }
                });

        View snackBarView = snackbar.getView();
        snackBarView.setBackgroundColor(ContextCompat.getColor(context, R.color.fab));
        TextView tvSnack = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
        tvSnack.setTextColor(Color.WHITE);
        snackbar.show();

        Runnable runnableUndo = new Runnable() {
            @Override
            public void run() {
                snackbar.dismiss();
                System.out.println("onItemDismiss: " + position);
            }
        };
        Handler handlerUndo = new Handler();
        handlerUndo.postDelayed(runnableUndo, 2500);
    }

}
