package com.nerdsoft.coder.notty.view.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.View;
import android.widget.Button;

import com.nerdsoft.coder.notty.R;
import com.nerdsoft.coder.notty.contract.INoteContract;

public class DeleteNoteBottomSheet extends BottomSheetDialogFragment {

    public DeleteNoteBottomSheet() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setupDialog(Dialog dialog, int style) {
        View view = View.inflate(getContext(), R.layout.del_note_bottom_sheet_frag, null);
        Button okAction = view.findViewById(R.id.ok_action);
        Button cancelAction = view.findViewById(R.id.cancel_action);

        cancelAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        okAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getActivity()!= null){
                    INoteContract.IView activity = (INoteContract.IView) getActivity();
                    activity.getNoteActivityManager().deleteNoteCallback();
                }
            }
        });

        dialog.setContentView(view);
        dialog.setCancelable(true);
    }

}
