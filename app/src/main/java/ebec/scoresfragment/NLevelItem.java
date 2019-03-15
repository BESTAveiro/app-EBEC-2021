package ebec.scoresfragment;

import android.view.View;

import com.example.jmfs1.ebec.scoresfragment.NLevelListItem;
import com.example.jmfs1.ebec.scoresfragment.NLevelView;

/**
 * Created by miguel on 21-12-2016.
 */

public class NLevelItem implements NLevelListItem {

    private Object wrappedObject;
    private com.example.jmfs1.ebec.scoresfragment.NLevelItem parent;
    private NLevelView nLevelView;
    private boolean isExpanded = false;

    public NLevelItem(Object wrappedObject, com.example.jmfs1.ebec.scoresfragment.NLevelItem parent, NLevelView nLevelView){
        this.wrappedObject = wrappedObject;
        this.parent = parent;
        this.nLevelView = nLevelView;
    }

    public Object getWrappedObject(){
        return wrappedObject;
    }

    @Override
    public boolean isExpanded() {
        return isExpanded;
    }

    @Override
    public void toggle() {
        isExpanded = !isExpanded;
    }

    @Override
    public NLevelListItem getParent() {
        return parent;
    }

    @Override
    public View getView() {
        return nLevelView.getView(this);
    }
}
