package com.nerdsoft.coder.notty.presenter;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.nerdsoft.coder.notty.R;
import com.nerdsoft.coder.notty.contract.IMainContract;
import com.nerdsoft.coder.notty.model.DaoSession;
import com.nerdsoft.coder.notty.model.Note;
import com.nerdsoft.coder.notty.model.NoteDao;
import com.nerdsoft.coder.notty.view.activity.MainActivity;
import com.nerdsoft.coder.notty.view.activity.NoteActivity;
import com.nerdsoft.coder.notty.view.adapter.NoteAdapter;

import java.util.List;

public class MainActivityManager implements IMainContract.IPresenter {
    private IMainContract.IView mView;
    private NoteDao mNoteDao;

    public MainActivityManager(IMainContract.IView view) {
        mView = view;
        DaoSession daoSession = ((MainActivity) mView).getApp().getDaoSession();
        mNoteDao = daoSession.getNoteDao();
        initPresenter();
    }

    private void initPresenter() {
        mView.initViews();
        NoteAdapter adapter = new NoteAdapter(this);
        ((MainActivity)mView).getNotesRecyclerView().setAdapter(adapter);
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.new_note:
                Intent intent = new Intent((MainActivity) mView, NoteActivity.class);
                intent.putExtra("isNew", 1);
                ((MainActivity) mView).startActivity(intent);
                break;
        }
    }
    public List<Note> loadNotes() {
        Log.d("NOTES", String.valueOf(mNoteDao.loadAll().size()));
        return mNoteDao.queryBuilder().orderDesc(NoteDao.Properties.UpdatedAt).list();
    }

    public IMainContract.IView getView() {
        return mView;
    }
}
