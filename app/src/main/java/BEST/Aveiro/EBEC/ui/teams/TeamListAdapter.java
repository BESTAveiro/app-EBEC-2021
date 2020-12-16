package BEST.Aveiro.EBEC.ui.teams;

import  android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import java.util.ArrayList;

import BEST.Aveiro.EBEC.Objects.Event;
import BEST.Aveiro.EBEC.Objects.Team;
import BEST.Aveiro.EBEC.R;

public class TeamListAdapter extends RecyclerView.Adapter<TeamListAdapter.TeamViewHolder> {
    private ArrayList<Team> mTeams;
    private LayoutInflater mInflater;
    private Context mContext;
    private String mModality;

    public TeamListAdapter(Context context, ArrayList<Team> teams,String modality) {
        mInflater = LayoutInflater.from(context);
        Log.i("Teams", String.valueOf(teams));
        Log.i("MD", modality);
        this.mTeams = teams;
        this.mContext = context;
        this.mModality = modality;

    }


    @Override
    public TeamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView;
            mItemView = mInflater.inflate(R.layout.team_design, parent, false);


        return new TeamViewHolder(mItemView, this, mModality);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamViewHolder holder, int position) {
        Team currentTeam = this.mTeams.get(position);
        holder.mTeamName.setText(mTeams.get(position).getName());
        if (mModality.equalsIgnoreCase("TD")){
            holder.mTeamCredits.setText("Credits: "+ currentTeam.getCredits());
            holder.mExpand.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Gson converter = new Gson();
                    Bundle bundle = new Bundle();
                    bundle.putString("m", "TD");
                    bundle.putString("T", converter.toJson(currentTeam));
                    Navigation.findNavController(v).navigate(R.id.teamDetails, bundle);
                }
            });
        }else{
            holder.mExpand.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Gson converter = new Gson();
                    Bundle bundle = new Bundle();
                    bundle.putString("m", "CS");
                    bundle.putString("T", converter.toJson(currentTeam));
                    Navigation.findNavController(v).navigate(R.id.teamDetails, bundle);
                }
            });
        }


    }



    @Override
    public int getItemCount() {
        return this.mTeams.size();
    }

    public class TeamViewHolder extends RecyclerView.ViewHolder {
        public TeamListAdapter mAdapter;
        //For TD
        private ImageButton mExpand;
        private TextView mTeamCredits;
        private TabLayout tabs;
        //For both
        private TextView mTeamName;
        private CardView mExpandedCard;

        public TeamViewHolder(@NonNull View itemView, TeamListAdapter adapter, String modality) {
            super(itemView);
            this.mAdapter = adapter;
            mTeamName = itemView.findViewById(R.id.team_name);
            if (modality.equalsIgnoreCase("TD")){
                mExpand = itemView.findViewById(R.id.expand_team);
                mTeamCredits = itemView.findViewById(R.id.team_credits);

            }
        }
    }
}
