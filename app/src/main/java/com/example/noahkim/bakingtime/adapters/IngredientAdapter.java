package com.example.noahkim.bakingtime.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.noahkim.bakingtime.R;
import com.example.noahkim.bakingtime.model.Ingredient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Noah on 5/17/2017.
 */

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientsItemViewHolder> {

    private Context mContext;
    private List<Ingredient> mIngredients;

    public IngredientAdapter(Context context, List<Ingredient> ingredients) {
        mContext = context;
        mIngredients = ingredients;
    }

    @Override
    public IngredientsItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredient_item_layout, parent, false);
        return new IngredientsItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(IngredientsItemViewHolder holder, int position) {
        final Ingredient currentIngredient = mIngredients.get(position);
        holder.mIngredientNameView.setText(currentIngredient.getIngredientName());
        holder.mIngredientQuantityView.setText(currentIngredient.getIngredientQuantity().toString());
        holder.mIngredientMeasureView.setText(currentIngredient.getIngredientMeasure());
    }

    @Override
    public int getItemCount() {
        if (mIngredients == null) return 0;
        return mIngredients.size();
    }

    public class IngredientsItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ingredient_name)
        TextView mIngredientNameView;
        @BindView(R.id.ingredient_quantity)
        TextView mIngredientQuantityView;
        @BindView(R.id.ingredient_measure)
        TextView mIngredientMeasureView;


        public IngredientsItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
