package BEST.Aveiro.EBEC.ui.schedule;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import BEST.Aveiro.EBEC.Objects.Day;

public class ScheduleViewModel extends ViewModel {
    private MutableLiveData<List<Day>>  days = new MutableLiveData<List<Day>>(new ArrayList<Day>());
    private MutableLiveData<Integer>  selectedTab = new MutableLiveData<Integer>(0);


    public void addDay(Day d){
        if (days.getValue()==null)
            days.setValue(new ArrayList<Day>());
        ArrayList<Day> temp_days = (ArrayList<Day>) days.getValue();
        temp_days.add(d);
        days.setValue(temp_days);
    }

    public MutableLiveData<List<Day>> getDays(){
        return days;
    }

    public void setSelectedTab(int i) {
        this.selectedTab = new MutableLiveData<Integer>(i);
    }
    public MutableLiveData<Integer> getSelectedTab(){
        return this.selectedTab;
    }
}