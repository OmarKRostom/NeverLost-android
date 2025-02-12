package com.borio.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.borio.R;
import com.borio.Utils;
import com.borio.adapter.ItemTouchHelperViewHolder;
import com.borio.data.ProviderInfo;
import com.mikepenz.fastadapter.items.AbstractItem;
import com.mikepenz.fastadapter.utils.ViewHolderFactory;

import java.util.List;

/**
 * Created by Ahmed Emad.
 */

public class ProviderCardView extends AbstractItem<ProviderCardView, ProviderCardView.FeedNewViewHolder> {

    private static final ViewHolderFactory<? extends FeedNewViewHolder> FACTORY = new ItemFactory();
    private static String TAG = ProviderCardView.class.getSimpleName();

    public ProviderInfo providerInfo;
    private Context context;

    public ProviderCardView(ProviderInfo providerInfo, Context context) {
        this.providerInfo = providerInfo;
        this.context = context;
    }

    @Override
    public int getType() {
        return R.id.fastadapter_card_item_id;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.provider_card;
    }

    @Override
    public void bindView(FeedNewViewHolder feedNewViewHolder, List<Object> payloads) {
        super.bindView(feedNewViewHolder, payloads);

        feedNewViewHolder.mProvider.setText(providerInfo.getProvider());
        feedNewViewHolder.mUsername.setText(providerInfo.getUsername());

        Drawable drawable;
        String providerName = providerInfo.getProvider().trim().toLowerCase();
        if (Utils.providersImages.containsKey(providerName)) {
            drawable = context.getResources().getDrawable(Utils.providersImages.get(providerName));
        } else {
            drawable = context.getResources().getDrawable(Utils.providersImages.get("provider"));
        }
        feedNewViewHolder.mImage.setImageDrawable(drawable);
    }

    @Override
    public void unbindView(FeedNewViewHolder holder) {
        super.unbindView(holder);
    }

    @Override
    public ViewHolderFactory<? extends FeedNewViewHolder> getFactory() {
        return FACTORY;
    }

    private static class ItemFactory implements ViewHolderFactory<FeedNewViewHolder> {
        public FeedNewViewHolder create(View v) {
            return new FeedNewViewHolder(v);
        }
    }


    static class FeedNewViewHolder extends RecyclerView.ViewHolder implements
            ItemTouchHelperViewHolder {
        CardView cardView;
        TextView mProvider;
        TextView mUsername;
        ImageView mImage;

        public FeedNewViewHolder(View view) {
            super(view);
            cardView = (CardView) view.findViewById(R.id.card_provider);
            mProvider = (TextView) view.findViewById(R.id.card_tv_provider);
            mUsername = (TextView) view.findViewById(R.id.card_tv_username);
            mImage = (ImageView) view.findViewById(R.id.card_image);
        }

        @Override
        public void onItemSelected(Context context) {
            cardView.setBackgroundColor(ContextCompat.getColor(context, R.color.dismisscolor2));
            mUsername.setTextColor(ContextCompat.getColor(context, R.color.white));
//            cardView.setBackgroundColor(ContextCompat.getColor(context, R.color.dismisscolor));
        }

        @Override
        public void onItemClear(Context context) {
            cardView.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
            mUsername.setTextColor(ContextCompat.getColor(context, R.color.darkteal));
//            container.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
        }
    }

}
