package com.festify.festify.ui.CreateEvent;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.bumptech.glide.Glide;
import com.festify.festify.R;
import com.festify.festify.databinding.DialogCustomImageSelectionBinding;
import com.festify.festify.databinding.FragmentCreateEventBinding;
import com.google.android.material.snackbar.Snackbar;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.List;

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

        mFragmentCreateEventBinding.ivAddEventImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customImageSelectionDialog();
            }
        });


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

    void customImageSelectionDialog() {
        Dialog dialog = new Dialog(getActivity());
        DialogCustomImageSelectionBinding mDialogCustomImageSelectionBinding =
                DialogCustomImageSelectionBinding.inflate(getLayoutInflater());
        dialog.setCancelable(true);
        dialog.setContentView(mDialogCustomImageSelectionBinding.getRoot());

        mDialogCustomImageSelectionBinding.tvCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dexter.withContext(getActivity())
                        .withPermissions(
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.CAMERA
                        )
                        .withListener(new MultiplePermissionsListener() {
                                          @Override
                                          public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                                              if (multiplePermissionsReport.areAllPermissionsGranted()) {
                                                  Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                                  startActivityForResult(intent, 1);
                                              }
                                          }
                                          @Override
                                          public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions,
                                                                                         PermissionToken token) {
                                              showRationalDialogForPermissions();
                                          }
                                      }

                        ).onSameThread()
                        .check();
                dialog.dismiss();
            }
        });

        mDialogCustomImageSelectionBinding.tvGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dexter.withContext(getActivity())
                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE
                        )
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                                Intent gallEryIntent =
                                        new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                startActivityForResult(gallEryIntent, 2);
                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                                Toast.makeText(getActivity(), "You have denied the storage permission to select image.", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                                showRationalDialogForPermissions();
                            }
                        }).onSameThread()
                        .check();
                ;
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void showRationalDialogForPermissions() {
        new AlertDialog.Builder(getActivity())
                .setMessage("It Looks like you have turned off permissions required for this feature." +
                        " It can be enabled under Application Settings")
                .setPositiveButton("GO TO SETTINGS", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        try {
                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            Uri uri = Uri.fromParts("package", getActivity().getPackageName(), null);
                            intent.setData(uri);
                            startActivity(intent);
                        } catch (ActivityNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 1) {
                Bitmap eventImage = (Bitmap) data.getExtras().get("data");
                Glide.with(requireActivity())
                        .load(eventImage)
                        .centerCrop()
                        .into(mFragmentCreateEventBinding.ivEventImage);

                mFragmentCreateEventBinding.ivAddEventImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_vector_edit));
            } else if (requestCode == 2) {
                Uri selectedPhotoUri = data.getData();
                Glide.with(requireActivity())
                        .load(selectedPhotoUri)
                        .centerCrop()
                        .into(mFragmentCreateEventBinding.ivEventImage);

                mFragmentCreateEventBinding.ivAddEventImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_vector_edit));
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mFragmentCreateEventBinding = null;
    }
}
