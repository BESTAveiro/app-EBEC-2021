package BEST.Aveiro.EBEC.ui.team_details;

import androidx.cardview.widget.CardView;
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
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import BEST.Aveiro.EBEC.Objects.Team;
import BEST.Aveiro.EBEC.R;
import BEST.Aveiro.EBEC.ui.teams.TeamListAdapter;
import BEST.Aveiro.EBEC.ui.teams.TeamsViewModel;

public class TeamDetails extends Fragment {

    private TeamDetailsViewModel mViewModel;
    private String mModality;
    private Team currentTeam;
    private RecyclerView teamMembersList;
    private RecyclerView teamProvasList;
    private TextView mTeamName;
    private CardView mProvasCard;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(TeamDetailsViewModel.class);
        View root = inflater.inflate(R.layout.team_details_fragment, container, false);
        mModality = getArguments().getString("m");
        Gson converter = new Gson();
        currentTeam = converter.fromJson(getArguments().getString("T"), Team.class);

        mTeamName = root.findViewById(R.id.team_details_name);
        mTeamName.setText(currentTeam.getName());
        mProvasCard = root.findViewById(R.id.provas_card);
        teamMembersList = root.findViewById(R.id.team_members_recyclerView);
        TeamMembersAdapter members_adapter = new TeamMembersAdapter(getActivity().getApplicationContext(), currentTeam.getMembers());
        teamMembersList.setAdapter(members_adapter);

        teamMembersList.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        if (mModality.equalsIgnoreCase("TD")){
            if (mProvasCard.getVisibility()==View.GONE)
                mProvasCard.setVisibility(View.VISIBLE);
            teamProvasList = root.findViewById(R.id.team_provas_recyclerView);
            TeamProvasAdapter provas_adapter = new TeamProvasAdapter(getActivity().getApplicationContext(), currentTeam.getProvas());
            teamProvasList.setAdapter(provas_adapter);
            teamProvasList.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        }else{
            mProvasCard.setVisibility(View.GONE);
        }
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(TeamDetailsViewModel.class);
        // TODO: Use the ViewModel
    }

}