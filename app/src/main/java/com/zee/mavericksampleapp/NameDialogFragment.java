package com.zee.mavericksampleapp;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

public class NameDialogFragment extends DialogFragment {

    private EditText mEditText;
    private AddNameListener mNameListener;

    public NameDialogFragment() {
        // Empty constructor required for DialogFragment
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof AddNameListener){
            mNameListener = (AddNameListener) getActivity();
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Add Name");
        View viewInflated = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_add_name, (ViewGroup) getView(), false);
        // Set up the edit text
        mEditText = (EditText) viewInflated.findViewById(R.id.name_edit_text);
        builder.setView(viewInflated);

        builder.setPositiveButton(R.string.label_save, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(mEditText.getText().toString().length() > 0){
                    if(mNameListener != null){
                        mNameListener.onNameEntered(mEditText.getText().toString());
                    }        
                }else{
                    Toast.makeText(getActivity(), "No Name Entered", Toast.LENGTH_SHORT).show();
                }
            
            }
        });
        builder.setNegativeButton(R.string.label_cancel, null);
        return builder.create();
    }

    public interface AddNameListener{
        void onNameEntered(String name);
    }
}