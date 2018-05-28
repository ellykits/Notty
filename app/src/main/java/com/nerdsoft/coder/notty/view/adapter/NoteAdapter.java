package com.nerdsoft.coder.notty.view.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nerdsoft.coder.notty.R;
import com.nerdsoft.coder.notty.model.Note;
import com.nerdsoft.coder.notty.presenter.MainActivityManager;
import com.nerdsoft.coder.notty.presenter.NoteActivityManager;
import com.nerdsoft.coder.notty.view.activity.NoteActivity;
import com.nerdsoft.coder.notty.view.util.DateUtil;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    MainActivityManager mManager;

    public NoteAdapter(MainActivityManager manager) {
        mManager = manager;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_note, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Note note = mManager.loadNotes().get(position);
        holder.mTitle.setText(note.getTitle());
        holder.mDate.setText(DateUtil.formatDateOnDisplay(note.getUpdatedAt()));
        holder.id = note.getId();

    }

    @Override
    public int getItemCount() {
        return mManager.loadNotes().size();
    }


    //Inner view holder class
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mTitle;
        TextView mDate;
        Long id;
        private Intent mIntent;

        public ViewHolder(View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.titleTextView);
            mDate = itemView.findViewById(R.id.dateModifiedTextView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.d("NOTES", "Current position is: " + getAdapterPosition() + " and id" + id);
            mIntent = new Intent(v.getContext(), NoteActivity.class);
            mIntent.putExtra("isNew", 0);
            mIntent.putExtra("id", id);
            (v.getContext()).startActivity(mIntent);
        }
    }
}
