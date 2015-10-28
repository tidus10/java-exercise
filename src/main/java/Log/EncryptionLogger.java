package Log;

import java.util.Observable;
import java.util.Observer;

import EventArgs.EncryptionLogEventArgs;
import JavaEx.FileEncryptor;
import Observables.DecryptionEndObserveble;
import Observables.DecryptionStartObserveble;
import Observables.EncryptionEndObserveble;
import Observables.EncryptionStartObserveble;

public class EncryptionLogger implements Observer {


	public synchronized void update(Observable o, Object arg) {
		EncryptionLogEventArgs args = (EncryptionLogEventArgs) arg;

		if(o instanceof EncryptionStartObserveble )
		{
			System.out.println("the encryptionfor file "
					+ args.getOriginalFilePath());
			System.out.println("with the algorithm " + args.getAlgorithm());
			System.out.println("started and will be located in file "
					+ args.getAfterEncOrDec());
		}
		if(o instanceof EncryptionEndObserveble )
		{
			System.out.println("the encryptionfor file "
					+ args.getOriginalFilePath());
			System.out.println("with the algorithm " + args.getAlgorithm());
			System.out.println("ended and located in file "
					+ args.getAfterEncOrDec());
		}
		if(o instanceof DecryptionStartObserveble )
		{
			System.out.println("the decryptionfor file "
					+ args.getOriginalFilePath());
			System.out.println("with the algorithm " + args.getAlgorithm());
			System.out.println("started and will be located in file "
					+ args.getAfterEncOrDec());
		}
		if(o instanceof DecryptionEndObserveble )
		{
			System.out.println("the decryptionfor file "
					+ args.getOriginalFilePath());
			System.out.println("with the algorithm " + args.getAlgorithm());
			System.out.println("ended and located in file "
					+ args.getAfterEncOrDec());
		}
		
	}

}
