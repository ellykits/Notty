package com.nerdsoft.coder.notty.presenter;

import com.nerdsoft.coder.notty.R;
import com.nerdsoft.coder.notty.contract.INoteContract;
import com.nerdsoft.coder.notty.model.DaoSession;
import com.nerdsoft.coder.notty.model.Note;
import com.nerdsoft.coder.notty.model.NoteDao;
import com.nerdsoft.coder.notty.view.activity.NoteActivity;
import com.nerdsoft.coder.notty.view.fragment.DeleteNoteBottomSheet;
import com.nerdsoft.coder.notty.view.util.DateUtil;
import com.nerdsoft.coder.notty.view.util.InfoUtil;

import java.util.Date;

public class NoteActivityManager implements INoteContract.IPresenter {
    private NoteDao mNoteDao;
    private INoteContract.IView mView;
    private Note mModel;
    private DeleteNoteBottomSheet deleteNoteBottomSheet;

    public NoteActivityManager(INoteContract.IView view) {
        mView = view;
        //Get the session and DAO to allow interaction with the database;
        DaoSession daoSession = ((NoteActivity) mView).getApp().getDaoSession();
        mNoteDao = daoSession.getNoteDao();
        //Bottom sheet dialog
        deleteNoteBottomSheet = new DeleteNoteBottomSheet();
        initPresenter();
    }

    private void initPresenter() {
        mModel = new Note();
        mView.initViews();
        if (((NoteActivity) mView).isNewNote()) {
            mView.setCreatedAtDate(DateUtil.formatDateOnDisplay());
        } else {
            mView.displayNote(loadNote(((NoteActivity) mView).getId()));
        }
        mView.changeEditingState(((NoteActivity) mView).isNewNote());
    }



    @Override
    public void deleteNoteCallback() {
        //Handle the OnClick callback for the bottom sheet
        mNoteDao.deleteByKey(((NoteActivity) mView).getId());
        ((NoteActivity) mView).finish();
    }

    private Note loadNote(Long id) {
        return mNoteDao.load(id);
    }

    public void deleteNote() {
        deleteNoteBottomSheet.show(((NoteActivity) mView).getSupportFragmentManager(),
                deleteNoteBottomSheet.getTag());
    }

    public void setupEditingMode() {
        mView.changeEditingState(true);
    }


    public void saveNote() {
        Date today = new Date();
        mModel.setTitle(((NoteActivity) mView).getNoteTitle());
        mModel.setDetails(((NoteActivity) mView).getNoteContent());
        mModel.setCreatedAt(today);
        mModel.setUpdatedAt(today);

        long id = mNoteDao.insert(mModel);
        if (id > 0) {
            InfoUtil.toastMessage(((NoteActivity) mView).getApplicationContext(), "Saved successfully");
            ((NoteActivity) mView).finish();
        }
    }

    public void updateNote(Long id) {
        Note note = mNoteDao.loadByRowId(id);
        note.setTitle(((NoteActivity)mView).getNoteTitle());
        note.setDetails(((NoteActivity)mView).getNoteContent());
        note.setUpdatedAt(new Date());
        mNoteDao.update(note);
        mView.changeEditingState(false);
        ((NoteActivity) mView).getTitleEditText().clearFocus();
        ((NoteActivity) mView).getContentEditText().clearFocus();
        InfoUtil.snackbarMessage(((NoteActivity) mView).findViewById(R.id.note_coord_layout),"Updated successfully",null,null);
    }
}
