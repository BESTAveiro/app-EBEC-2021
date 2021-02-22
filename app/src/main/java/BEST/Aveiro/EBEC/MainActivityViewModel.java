package BEST.Aveiro.EBEC;

import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import BEST.Aveiro.EBEC.Objects.Team;
import BEST.Aveiro.EBEC.Objects.User;

public class MainActivityViewModel extends ViewModel {

    private User current_user;
    private Team current_team;
    private MutableLiveData<Integer> fab_view = new MutableLiveData<Integer>();


    public User getCurrentUser() {
        return current_user;
    }

    public void setCurrentUser(User current_user) {
        this.current_user = current_user;
    }


    public void setIsFABVisible(int newView){
        this.fab_view.setValue(newView);
    };
    public MutableLiveData<Integer> getFabView(){
        return this.fab_view;
    }
    public Team getCurrentTeam() {

        return current_team;
    }

    public void setCurrentTeam(Team current_team) {
        this.current_team = current_team;
    }
}