package com.example.whatsapp2;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class SquareItemDecoration extends RecyclerView.ItemDecoration {
    private int spacing;

    public SquareItemDecoration(int spacing) {
        this.spacing = spacing;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        int column = position % 3; // Assuming 3 columns
        outRect.left = spacing - column * spacing / 3;
        outRect.right = (column + 1) * spacing / 3;
        if (position < 3) {
            outRect.top = spacing;
        }
        outRect.bottom = spacing;
    }
}
