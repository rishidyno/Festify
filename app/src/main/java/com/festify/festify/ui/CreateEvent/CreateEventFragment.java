package com.festify.festify.ui.CreateEvent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.festify.festify.databinding.FragmentCreateEventBinding;

public class CreateEventFragment extends Fragment {

    CreateEventViewModel createEventViewModel;
    FragmentCreateEventBinding mFragmentCreateEventBinding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        createEventViewModel = new ViewModelProvider(this).get(CreateEventViewModel.class);

        mFragmentCreateEventBinding = FragmentCreateEventBinding.inflate(inflater, container, false);
        View root = mFragmentCreateEventBinding.getRoot();
        EditText eventName = mFragmentCreateEventBinding.etEventName;
        EditText eventStartDate = mFragmentCreateEventBinding.etStartDate;
        EditText eventEndDate = mFragmentCreateEventBinding.etStartDate;
        EditText eventVenue = mFragmentCreateEventBinding.etVenue;
        EditText eventDescription = mFragmentCreateEventBinding.etEventDescription;
        EditText eventLocation = mFragmentCreateEventBinding.etEventLocation;

        mFragmentCreateEventBinding.btnCreateEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // validating if the text field is empty or not.
                if (eventName.getText().toString().isEmpty()
                        || eventStartDate.getText().toString().isEmpty()
                        || eventEndDate.getText().toString().isEmpty()
                        || eventVenue.getText().toString().isEmpty()
                        || eventDescription.toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Please enter all the values", Toast.LENGTH_SHORT).show();
                } else {
                    createEventViewModel.postEvent(eventName.getText().toString(), eventStartDate.getText().toString()
                            , eventEndDate.getText().toString(), eventVenue.getText().toString()
                            , eventDescription.getText().toString(), eventLocation.getText().toString());
                }
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mFragmentCreateEventBinding = null;
    }
}
