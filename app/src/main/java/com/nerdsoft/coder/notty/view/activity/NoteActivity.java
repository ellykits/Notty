package com.nerdsoft.coder.notty.view.activity;

import android.content.Context;
import android.os.Build;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mikepenz.iconics.context.IconicsContextWrapper;
import com.mikepenz.iconics.context.IconicsLayoutInflater2;
import com.nerdsoft.coder.notty.NottyApplication;
import com.nerdsoft.coder.notty.R;
import com.nerdsoft.coder.notty.contract.INoteContract;
import com.nerdsoft.coder.notty.model.Note;
import com.nerdsoft.coder.notty.presenter.NoteActivityManager;
import com.nerdsoft.coder.notty.view.util.DateUtil;

import java.util.Objects;

public class NoteActivity extends AppCompatActivity implements INoteContract.IView {

    private Long id;
    private TextView createdAtTextView;
    private EditText titleEditText;
    private EditText contentEditText;
    private TextInputLayout titleTIL;
    private TextInputLayout contentTIL;
    private Toolbar noteToolbar;
    private NoteActivityManager mNoteActivityManager;
    private boolean isNewNote = true;
    private boolean isInEditMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Make the icons identifiable on the xml; check using Iconics icons library for more
        LayoutInflaterCompat.setFactory2(getLayoutInflater(), new IconicsLayoutInflater2(getDelegate()));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            if (bundle.containsKey("isNew") && bundle.getInt("isNew") == 1) {
                isNewNote = true;
            } else {
                //Grab the id for this note that is also sent with the bundle;
                id = bundle.getLong("id");
                isNewNote = false;
            }
        }
        mNoteActivityManager = new NoteActivityManager(this);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(IconicsContextWrapper.wrap(newBase));
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        if (!isNewNote) {
            menu.findItem(R.id.delete_note).setVisible(true);
            menu.findItem(R.id.update_note).setVisible(false);
        }

        //Change menu for editing
        if (isInEditMode) {
            menu.findItem(R.id.delete_note).setVisible(false);
            menu.findItem(R.id.update_note).setVisible(true);
        }

        return super.onPrepareOptionsMenu(menu);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Show the appropriate menu on the toolbar when adding or viewing a note
        if (isNewNote) {
            getMenuInflater().inflate(R.menu.new_note_menu, menu);
        } else {
            //set onclick listeners on edit texts to enable updates
            getMenuInflater().inflate(R.menu.view_note_menu, menu);

        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        if (!isNewNote) {
            switch (item.getItemId()) {
                case R.id.delete_note:
                    mNoteActivityManager.deleteNote();
                    break;
                case R.id.update_note:
                    mNoteActivityManager.updateNote(id);
                    break;
            }
        } else {
            switch (item.getItemId()) {
                case R.id.save_note:
                    mNoteActivityManager.saveNote();
                    break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void initViews() {
        createdAtTextView = findViewById(R.id.createdAtTextView);
        titleEditText = findViewById(R.id.titleEditText);
        contentEditText = findViewById(R.id.contentEditText);
        titleTIL = findViewById(R.id.titleTIL);
        contentTIL = findViewById(R.id.contentTIL);
        noteToolbar = findViewById(R.id.noteToolbar);
        setSupportActionBar(noteToolbar);
        if (!isNewNote) {
            titleEditText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isInEditMode = true;
                    mNoteActivityManager.setupEditingMode();
                    titleEditText.requestFocus();
                    supportInvalidateOptionsMenu();
                }
            });
            contentEditText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isInEditMode = true;
                    mNoteActivityManager.setupEditingMode();
                    contentEditText.requestFocus();
                    supportInvalidateOptionsMenu();
                }
            });
        }
        //Allow back navigation
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

    }

    @Override
    public void changeEditingState(boolean flag) {
        titleTIL.setHintEnabled(flag);
        titleEditText.setFocusableInTouchMode(flag);
        contentTIL.setHintEnabled(flag);
        contentEditText.setFocusableInTouchMode(flag);
    }

    @Override
    public void setCreatedAtDate(String date) {
        createdAtTextView.setText(date);
    }

    @Override
    public void displayNote(Note note) {
        titleEditText.setText(note.getTitle());
        contentEditText.setText(note.getDetails());
        createdAtTextView.setText(DateUtil.formatDateOnDisplay(note.getUpdatedAt()));
    }

    public NottyApplication getApp() {
        return (NottyApplication) getApplication();
    }

    public String getNoteTitle() {
        return titleEditText.getText().toString();
    }

    public String getNoteContent() {
        return contentEditText.getText().toString();
    }

    public boolean isNewNote() {
        return isNewNote;
    }

    public EditText getTitleEditText() {
        return titleEditText;
    }

    public EditText getContentEditText() {
        return contentEditText;
    }

    public Long getId() {
        return id;
    }

    public Toolbar getNoteToolbar() {
        return noteToolbar;
    }

    @Override
    public NoteActivityManager getNoteActivityManager() {
        return mNoteActivityManager;
    }
}
