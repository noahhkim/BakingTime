package com.example.noahkim.bakingtime.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.noahkim.bakingtime.R;
import com.example.noahkim.bakingtime.model.Step;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Noah on 5/17/2017.
 */

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.StepsItemViewHolder> {
    public static final String STEP_DETAILS = "step_details";
    private Context mContext;
    private List<Step> mSteps;
    private ListItemClickListener mOnClickListener;

    public StepsAdapter(ListItemClickListener onClickListener, List<Step> steps) {
        mOnClickListener = onClickListener;
        mSteps = steps;
    }

    public interface ListItemClickListener {
        void onItemClick(int clickedItem);
    }

    @Override
    public StepsAdapter.StepsItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.steps_item_layout, parent, false);
        return new StepsItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StepsAdapter.StepsItemViewHolder holder, int position) {
        final Step currentStep = mSteps.get(position);
        holder.mStepsIdView.setText(String.valueOf(currentStep.getStepId()) + ".");
        holder.mStepsShortDescView.setText(currentStep.getStepShortDescription());
    }

    @Override
    public int getItemCount() {
        if (mSteps == null) return 0;
        return mSteps.size();
    }

    public class StepsItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.steps_id)
        TextView mStepsIdView;
        @BindView(R.id.steps_short_description)
        TextView mStepsShortDescView;

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
