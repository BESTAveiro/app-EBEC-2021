package BEST.Aveiro.EBEC.ui.schedule;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import BEST.Aveiro.EBEC.Objects.Day;
import BEST.Aveiro.EBEC.Objects.Event;
import BEST.Aveiro.EBEC.R;

public class ScheduleFragment extends Fragment {

    private ScheduleViewModel mViewModel;
    private RecyclerView events_recycler_view;
    private ScheduleListAdapter mAdapter;
    private TextView selected_day;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(ScheduleViewModel.class);
        View v = inflater.inflate(R.layout.schedule_fragment, container, false);
        TabLayout day_picker = v.findViewById(R.id.schedule_day_picker);

        selected_day = v.findViewById(R.id.selected_day);
        events_recycler_view = (RecyclerView)v.findViewById(R.id.events_recycler_view);

        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        DatabaseReference schedule = db.child("schedule");
        mViewModel.getDays().observe(getViewLifecycleOwner(), days->{
            day_picker.removeAllTabs();
            for (Day d: days){
                //Log.i("Tab Designation", String.valueOf(d.getDayDesignation()));
                day_picker.addTab(day_picker.newTab().setText(d.getDayDesignation()));
            }

        });
        
        schedule.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot day_snapshot: snapshot.getChildren()){
                            Day d = new Day();
                            d.setDayDesignation(day_snapshot.getKey());
                            d.setDate(day_snapshot.child("date").getValue(String.class));
                            ArrayList<Event> events_list = new ArrayList<>();
                            for (DataSnapshot event: day_snapshot.child("events").getChildren()){
                                Event e = new Event();
                                e.setDesignation(event.getKey());
                                e.setTime(event.child("time").getValue(String.class));
                                e.setDescription(event.child("description").getValue(String.class));
                                events_list.add(e);
                            }
                            d.setEvents(events_list);
                            //Log.i("Added day", d.getDayDesignation());
                            mViewModel.addDay(d);
                            Log.i("New Day", String.valueOf(mViewModel.getDays().getValue()));
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                }
        );


       

        day_picker.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                selected_day.setText(mViewModel.getDays().getValue().get(tab.getPosition()).getDate());
                ScheduleListAdapter new_adapter = new ScheduleListAdapter(getActivity().getApplicationContext(), mViewModel.getDays().getValue().get(tab.getPosition()).getEvents());
                events_recycler_view.setAdapter(new_adapter);
                events_recycler_view.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }


        });

        return v;
    }



}