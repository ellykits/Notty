package com.nerdsoft.coder.notty.contract;

import com.nerdsoft.coder.notty.model.Note;
import com.nerdsoft.coder.notty.presenter.NoteActivityManager;

public interface INoteContract {

    interface IView{
        void initViews();
        void setCreatedAtDate(String s);
        void changeEditingState(boolean flag);
        void displayNote(Note note);
        NoteActivityManager getNoteActivityManager();
    }
    interface IPresenter{
        void deleteNoteCallback();


    }
}
