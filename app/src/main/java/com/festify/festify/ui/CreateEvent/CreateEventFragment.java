package com.festify.festify.ui.CreateEvent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.festify.festify.databinding.FragmentCreateEventBinding;
import com.festify.festify.model.EventModel;

public class CreateEventFragment extends Fragment {

    private CreateEventViewModel createEventViewModel;
    private FragmentCreateEventBinding mFragmentCreateEventBinding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        createEventViewModel =
                new ViewModelProvider(this).get(CreateEventViewModel.class);

        mFragmentCreateEventBinding = FragmentCreateEventBinding.inflate(inflater, container, false);
        View root = mFragmentCreateEventBinding.getRoot();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText eventName = mFragmentCreateEventBinding.etEventName;
        EditText eventStartDate = mFragmentCreateEventBinding.etEventName;
        EditText eventEndDate = mFragmentCreateEventBinding.etEventName;
        EditText eventVenue = mFragmentCreateEventBinding.etEventName;
        EditText eventDescription = mFragmentCreateEventBinding.etEventName;
        EditText eventLocation = mFragmentCreateEventBinding.etEventLocation;
        EditText eventCreateButton = mFragmentCreateEventBinding.etEventName;

        mFragmentCreateEventBinding.btnCreateEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // validating if the text field is empty or not.
                if (eventName.getText().toString().isEmpty()
                        && eventStartDate.getText().toString().isEmpty()
                && eventEndDate.getText().toString().isEmpty()
                && eventVenue.getText().toString().isEmpty()
                && eventDescription.toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Please enter both the values", Toast.LENGTH_SHORT).show();
                    return;
                }
                // calling a method to post the data and passing our name and job.
                createEventViewModel.postEvent(eventName.getText().toString(),eventStartDate.getText().toString()
                        ,eventEndDate.getText().toString(), eventVenue.getText().toString()
                        ,eventDescription.getText().toString(),eventLocation.getText().toString())
                        .observe(getViewLifecycleOwner(), new Observer<EventModel>() {
                    @Override
                    public void onChanged(EventModel eventModel) {

                    }
                });
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mFragmentCreateEventBinding = null;
    }
}