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
import androidx.navigation.fragment.NavHostFragment;

import com.festify.festify.R;
import com.festify.festify.databinding.FragmentCreateEventBinding;
import com.google.android.material.snackbar.Snackbar;

// inheritance Fragment
public class CreateEventFragment extends Fragment {

    CreateEventViewModel createEventViewModel;
    FragmentCreateEventBinding mFragmentCreateEventBinding;

    // override onCreateView from Fragment
    //annotation so that LayoutInflater is not null
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
                        || eventDescription.toString().isEmpty()
                        || eventLocation.toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Please enter all the values", Toast.LENGTH_SHORT).show();
                } else {
                    createEventViewModel.postEvent(eventName.getText().toString(), eventStartDate.getText().toString()
                            , eventEndDate.getText().toString(), eventVenue.getText().toString()
                            , eventDescription.getText().toString(), eventLocation.getText().toString());

                    Snackbar.make(container, "New Event Added", Snackbar.LENGTH_LONG)
                            .show();

                    NavHostFragment.findNavController(CreateEventFragment.this)
                            .navigate(R.id.action_createEventFragment_to_navigation_home);
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
