package Log;

import java.util.Observable;
import java.util.Observer;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;

import EventArgs.EncryptionLogEventArgs;
import JavaEx.FileEncryptor;
import Observables.DecryptionEndObserveble;
import Observables.DecryptionStartObserveble;
import Observables.EncryptionEndObserveble;
import Observables.EncryptionStartObserveble;

public class EncryptionLog4JLogger implements Observer {
	public final static Logger logger = Logger.getLogger(EncryptionLog4JLogger.class);

	public synchronized void update(Observable o, Object arg) {
		EncryptionLogEventArgs args = (EncryptionLogEventArgs) arg;

		if(o instanceof EncryptionStartObserveble )
		{
			FileAppender f=new FileAppender();
			
			logger.info("the encryptionfor file "
					+ args.getOriginalFilePath());
			logger.info("with the algorithm " + args.getAlgorithm());
			logger.info("started and will be located in file "
					+ args.getAfterEncOrDec());
		}
		if(o instanceof EncryptionEndObserveble )
		{
			logger.info("the encryptionfor file "
					+ args.getOriginalFilePath());
			logger.info("with the algorithm " + args.getAlgorithm());
			logger.info("ended and located in file "
					+ args.getAfterEncOrDec());
		}
		if(o instanceof DecryptionStartObserveble )
		{
			logger.info("the decryptionfor file "
					+ args.getOriginalFilePath());
			logger.info("with the algorithm " + args.getAlgorithm());
			logger.info("started and will be located in file "
					+ args.getAfterEncOrDec());
		}
		if(o instanceof DecryptionEndObserveble )
		{
			logger.info("the decryptionfor file "
					+ args.getOriginalFilePath());
			logger.info("with the algorithm " + args.getAlgorithm());
			logger.info("ended and located in file "
					+ args.getAfterEncOrDec());
		}
		
	}
}
