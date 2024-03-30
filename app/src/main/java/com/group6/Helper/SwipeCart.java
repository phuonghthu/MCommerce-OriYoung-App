package com.group6.Helper;

import android.view.GestureDetector;
import android.view.MotionEvent;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.group6.Models.cartModel;

import java.util.List;
import java.util.Map;
import java.util.Queue;

public abstract class SwipeCart extends ItemTouchHelper.SimpleCallback {
    int buttonWidth;
    private RecyclerView recyclerView;
    private List<cartModel> carts;
    private GestureDetector gestureDetector;
    private int swipePosition = -1;
    private float swipeThreshold = 0.5f;
    private Map<Integer,List<cartModel>> buttonBuffer;
    private Queue<Integer> removeQueue;
    private  GestureDetector.SimpleOnGestureListener gestureListener = new GestureDetector.SimpleOnGestureListener(){};



    public SwipeCart(int dragDirs, int swipeDirs) {
        super(dragDirs, swipeDirs);
    }
}
