package model;

import java.beans.PropertyChangeListener;

public interface ObservableModel extends Model {
    public void addListener(PropertyChangeListener listener);
    public void removeListener(PropertyChangeListener listener);
}
