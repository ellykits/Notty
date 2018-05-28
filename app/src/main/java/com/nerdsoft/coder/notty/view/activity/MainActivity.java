package com.nerdsoft.coder.notty.view.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.context.IconicsContextWrapper;
import com.mikepenz.iconics.context.IconicsLayoutInflater2;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;
import com.nerdsoft.coder.notty.NottyApplication;
import com.nerdsoft.coder.notty.R;
import com.nerdsoft.coder.notty.contract.IMainContract;
import com.nerdsoft.coder.notty.model.Note;
import com.nerdsoft.coder.notty.presenter.MainActivityManager;
import com.nerdsoft.coder.notty.view.adapter.NoteAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements IMainContract.IView {
    //Presenter for this activity. It handles all the business logic separately
    private MainActivityManager mMainActivityManager;
    private FloatingActionButton fb;
    private Toolbar mToolbar;
    private RecyclerView mNotesRecyclerView;
//    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Make the icons identifiable on the xml; check using Iconics icons library for more
        LayoutInflaterCompat.setFactory2(getLayoutInflater(), new IconicsLayoutInflater2(getDelegate()));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMainActivityManager = new MainActivityManager(MainActivity.this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
         mNotesRecyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(IconicsContextWrapper.wrap(newBase));
    }

    @Override
    public void initViews() {
        mToolbar = findViewById(R.id.mainToolbar);
        setSupportActionBar(mToolbar);
        fb = findViewById(R.id.new_note);
        fb.setImageDrawable(
                new IconicsDrawable(this).icon(MaterialDesignIconic.Icon.gmi_plus).color(Color.WHITE));
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainActivityManager.onClick(v);
            }
        });

        mNotesRecyclerView = findViewById(R.id.notesRecyclerView);
        //Set layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mNotesRecyclerView.setLayoutManager(layoutManager);
        //set adapter

    }


    public RecyclerView getNotesRecyclerView() {
        return mNotesRecyclerView;
    }

    public NottyApplication getApp() {
        return (NottyApplication) getApplication();
    }
}
