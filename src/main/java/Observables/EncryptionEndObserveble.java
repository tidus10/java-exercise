package Observables;

import java.util.Observable;

public class EncryptionEndObserveble extends Observable {

	@Override
	public void notifyObservers(Object arg) {
		// TODO Auto-generated method stub
		setChanged();
		super.notifyObservers(arg);
	}
}
