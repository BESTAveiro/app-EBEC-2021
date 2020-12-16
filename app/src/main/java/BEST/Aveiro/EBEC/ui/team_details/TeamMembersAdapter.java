package BEST.Aveiro.EBEC.ui.team_details;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import BEST.Aveiro.EBEC.R;

public class TeamMembersAdapter extends RecyclerView.Adapter<TeamMembersAdapter.MembersViewHolder> {
    private ArrayList<String> mMembers;
    private LayoutInflater mInflater;

    public TeamMembersAdapter(Context context, ArrayList<String> members) {
        mInflater = LayoutInflater.from(context);
        this.mMembers = members;

    }


    @Override
    public MembersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView;
        mItemView = mInflater.inflate(R.layout.name_item, parent, false);

        return new MembersViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull MembersViewHolder holder, int position) {
        String currentMembers = this.mMembers.get(position);
        holder.mMemberName.setText(currentMembers);


    }



    @Override
    public int getItemCount() {
        return this.mMembers.size();
    }

    public class MembersViewHolder extends RecyclerView.ViewHolder {
        public TeamMembersAdapter mAdapter;
        private TextView mMemberName;
        public MembersViewHolder(@NonNull View itemView, TeamMembersAdapter adapter) {
            super(itemView);
            this.mAdapter = adapter;
            mMemberName = itemView.findViewById(R.id.member_name);

        }
    }
}
