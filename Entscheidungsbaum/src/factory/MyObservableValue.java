package factory;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 * 
 * @author Julius
 * 
 */
public class MyObservableValue implements ObservableValue<String> {

	String s;

	public MyObservableValue(String pS) {
		s = pS;
	}

	@Override
	public void removeListener(InvalidationListener arg0) {
	}

	@Override
	public void addListener(ChangeListener<? super String> arg0) {
	}

	@Override
	public String getValue() {
		return s;
	}

	@Override
	public void removeListener(ChangeListener<? super String> arg0) {
	}

	@Override
	public void addListener(InvalidationListener arg0) {
	}
}
