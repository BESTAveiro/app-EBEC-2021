package BEST.Aveiro.EBEC.ui.teams;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import BEST.Aveiro.EBEC.Objects.Prova;
import BEST.Aveiro.EBEC.Objects.Team;
import BEST.Aveiro.EBEC.Objects.User;
import BEST.Aveiro.EBEC.R;
import BEST.Aveiro.EBEC.ui.schedule.ScheduleListAdapter;

public class TeamsFragment extends Fragment {

    private TeamsViewModel teamsViewModel;
    private TabLayout mTabLayout;
    private RecyclerView teams_list;
    public View onCreateView(@NonNull LayoutInflater inflater,

                             ViewGroup container, Bundle savedInstanceState) {
        teamsViewModel =
                new ViewModelProvider(this).get(TeamsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_teams, container, false);
        teams_list = root.findViewById(R.id.teams_list);


        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        DatabaseReference teamsRef = db.child("teams");

        teamsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Team> td = new ArrayList<>();
                ArrayList<Team> cs = new ArrayList<>();
                for (DataSnapshot teamSnaphot : dataSnapshot.getChildren()) {
                    Team t = new Team();
                    t.setName(teamSnaphot.child("name").getValue().toString());
                    t.setModality(teamSnaphot.child("md").getValue().toString());
                    ArrayList<String> members = new ArrayList<>();
                    for (DataSnapshot membersSnapshot: teamSnaphot.child("members").getChildren()){
                        members.add(membersSnapshot.getValue().toString());
                    }
                    t.setMembers(members);
                    Log.i("Members", String.valueOf(t.getMembers()));
                    if (t.getModality().equalsIgnoreCase("TD")){
                        t.setCredits(teamSnaphot.child("credits").getValue(Integer.class));
                        ArrayList<Prova>  provas = new ArrayList<>();
                        for (DataSnapshot mini_comps: teamSnaphot.child("comps").getChildren()){
                            Prova p = new Prova();
                            String name = mini_comps.getKey().toString();
                            p.setName(name);
                            p.setScore(mini_comps.getValue().toString());
                            provas.add(p);
                        }
                        t.setProvas(provas);
                        td.add(t);

                    }else{
                        cs.add(t);
                    }
                }
                teamsViewModel.setTeamDesignTeams(td);
                teamsViewModel.setCaseStudyTeams(cs);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getActivity().getApplicationContext(), "Failed to access database, check your internet connection", Toast.LENGTH_LONG).show();
            }

        });

        teamsViewModel.getCaseStudyTeams().observe( getViewLifecycleOwner(), cs_teams->{
            if (mTabLayout.getSelectedTabPosition()==1){
                TeamListAdapter cs_adapter = new TeamListAdapter(getActivity().getApplicationContext(), teamsViewModel.getCaseStudyTeams().getValue(), "CS");
                teams_list.setAdapter(cs_adapter);
                teams_list.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
            }
        });

        teamsViewModel.getTeamDesignTeams().observe(getViewLifecycleOwner(), td_teams->{
            if (mTabLayout.getSelectedTabPosition()==0) {
                TeamListAdapter td_adapter = new TeamListAdapter(getActivity().getApplicationContext(), teamsViewModel.getTeamDesignTeams().getValue(), "TD");
                teams_list.setAdapter(td_adapter);
                teams_list.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
            }

        });

        mTabLayout = root.findViewById(R.id.choose_modality_tab);
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.i("Selected Tab", String.valueOf(tab.getPosition()));
                TeamListAdapter adapter;
                switch(tab.getPosition()){
                    case 0:
                        teams_list = root.findViewById(R.id.teams_list);

                        adapter = new TeamListAdapter(getActivity().getApplicationContext(), teamsViewModel.getTeamDesignTeams().getValue(), "TD");
                        teams_list.setAdapter(adapter);
                        teams_list.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
                        break;
                    case 1:
                        teams_list = root.findViewById(R.id.teams_list);
                        TeamListAdapter cs_adapter = new TeamListAdapter(getActivity().getApplicationContext(), teamsViewModel.getCaseStudyTeams().getValue(), "CS");
                        teams_list.setAdapter(cs_adapter);
                        teams_list.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
                        break;
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                switch(tab.getPosition()){
                    case 0:
                        teams_list = root.findViewById(R.id.teams_list);
                        TeamListAdapter td_adapter = new TeamListAdapter(getActivity().getApplicationContext(), teamsViewModel.getTeamDesignTeams().getValue(), "TD");
                        teams_list.setAdapter(td_adapter);
                        teams_list.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
                    case 1:
                        teams_list = root.findViewById(R.id.teams_list);
                        TeamListAdapter cs_adapter = new TeamListAdapter(getActivity().getApplicationContext(), teamsViewModel.getCaseStudyTeams().getValue(), "CS");
                        teams_list.setAdapter(cs_adapter);
                        teams_list.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
                }
            }
        });


        return root;
    }
}