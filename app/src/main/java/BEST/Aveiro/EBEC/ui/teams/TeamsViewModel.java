package BEST.Aveiro.EBEC.ui.teams;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import javax.net.ssl.SSLSession;

import BEST.Aveiro.EBEC.Objects.Team;

public class TeamsViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Team>> mTeamDesignTeams;
    private MutableLiveData<ArrayList<Team>> mCaseStudyTeams;

    public TeamsViewModel() {
        mTeamDesignTeams = new MutableLiveData<ArrayList<Team>>(new ArrayList<Team>());
        mCaseStudyTeams = new MutableLiveData<ArrayList<Team>>(new ArrayList<Team>());

    }


    public void setTeamDesignTeams(ArrayList<Team> td) {
        mTeamDesignTeams.setValue(td);
    }

    public void setCaseStudyTeams(ArrayList<Team> cs){
        mCaseStudyTeams.setValue(cs);
    }

    public MutableLiveData<ArrayList<Team>> getTeamDesignTeams() {
        return mTeamDesignTeams;
    }

    public MutableLiveData<ArrayList<Team>> getCaseStudyTeams() {
        return mCaseStudyTeams;
    }
}