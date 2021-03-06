package com.festify.festify.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.festify.festify.Adapter.EventsAdapter;
import com.festify.festify.R;
import com.festify.festify.databinding.FragmentHomeBinding;
import com.festify.festify.model.EventModel;

import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.tvNoEventsToShow;
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        binding.swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                homeViewModel.refreshEvents();
                binding.swiperefreshlayout.setRefreshing(false);
            }
        });
        homeViewModel.eventList.observe(getViewLifecycleOwner(), new Observer<List<EventModel>>() {
            @Override
            public void onChanged(List<EventModel> eventModels) {
                if (eventModels == null) {
                    homeViewModel.refreshEvents();
                    textView.setVisibility(View.VISIBLE);
                } else {
                    textView.setVisibility(View.GONE);
                    EventsAdapter adapter = new EventsAdapter(homeViewModel.eventList.getValue(), getContext(), new EventsAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(int position) {
                            Log.d("HomeFragment", "onItemClick: " + position);
                            Navigation.findNavController(root).navigate(R.id.action_navigation_home_to_eventDetailFragment);
                        }
                    });
                    binding.eventListRecyclerView.setAdapter(adapter);
                    binding.eventListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                }
            }
        });
        homeViewModel.loading.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    binding.progressBar.setVisibility(View.VISIBLE);
                } else {
                    binding.progressBar.setVisibility(View.GONE);
                }
            }
        });

        binding.btnCreateEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(HomeFragment.this)
                        .navigate(R.id.action_navigation_home_to_createEventFragment);
            }
        });
        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
