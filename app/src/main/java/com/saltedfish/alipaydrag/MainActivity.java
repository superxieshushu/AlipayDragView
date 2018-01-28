package com.saltedfish.alipaydrag;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DragAdapter.OnDragItemListener {
    private RecyclerView mRvDrag;

    private int mDragPosition = -1;
    private int mNewPosition = -1;
    private DragHelp mDragHelp;
    private DragAdapter mDragAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRvDrag = (RecyclerView) findViewById(R.id.rv_drag);
        initDragRv();
        initDragListener();
    }

    private void initDragRv() {
        mDragHelp = new DragHelp(this);
        mRvDrag.setLayoutManager(new GridLayoutManager(this, 4));
        mDragAdapter = new DragAdapter(this);
        mRvDrag.setAdapter(mDragAdapter);
        mDragAdapter.setOnDragItemListener(this);
    }

    private void initDragListener() {
        mRvDrag.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) {
                View v = (View) dragEvent.getLocalState();
                switch (dragEvent.getAction()) {
                    case DragEvent.ACTION_DRAG_LOCATION:
                        mNewPosition = mDragHelp.getPosition(dragEvent.getX(), dragEvent.getY());
                        if (mNewPosition != -1) {
                            mRvDrag.getAdapter().notifyItemMoved(mDragPosition, mNewPosition);
                            mDragPosition = mNewPosition;
                        }
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        v.setVisibility(View.VISIBLE);
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void onDragItem(int position) {
        mDragPosition = position;
    }
}
