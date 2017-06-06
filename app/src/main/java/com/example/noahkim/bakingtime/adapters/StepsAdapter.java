package com.example.noahkim.bakingtime.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.noahkim.bakingtime.R;
import com.example.noahkim.bakingtime.model.Step;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Noah on 5/17/2017.
 */

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.StepsItemViewHolder> {
    private List<Step> mSteps;
    private ListItemClickListener mOnClickListener;
    private Context mContext;

    public StepsAdapter(ListItemClickListener onClickListener, List<Step> steps) {
        mOnClickListener = onClickListener;
        mSteps = steps;
    }

    public interface ListItemClickListener {
        void onItemClick(int itemIndex);
    }

    @Override
    public StepsAdapter.StepsItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.steps_item_layout, parent, false);
        return new StepsItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StepsAdapter.StepsItemViewHolder holder, int position) {
        final Step currentStep = mSteps.get(position);
        holder.mStepsShortDescView.setText(currentStep.getStepShortDescription());
        if (currentStep.getThumbnailUrl().length() > 0) {
            Picasso.with(mContext)
                    .load(currentStep.getThumbnailUrl())
                    .placeholder(R.drawable.video_thumbnail)
                    .error(R.drawable.video_thumbnail)
                    .into(holder.mThumbnailView);
        } else {
            Picasso.with(mContext)
                    .load(R.drawable.video_thumbnail)
                    .into(holder.mThumbnailView);
        }
    }

    @Override
    public int getItemCount() {
        if (mSteps == null) return 0;
        return mSteps.size();
    }

    public class StepsItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.steps_short_description)
        TextView mStepsShortDescView;
        @BindView(R.id.video_thumbnail)
        ImageView mThumbnailView;

        public StepsItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mOnClickListener.onItemClick(getAdapterPosition());
        }
    }
}
