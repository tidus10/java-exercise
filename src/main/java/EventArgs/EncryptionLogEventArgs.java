package EventArgs;

import Encryptions.EncryptionAlgorithm;

public class EncryptionLogEventArgs {

	String originalFilePath;
	String afterEncOrDec;
	EncryptionAlgorithm algorithm;
	float milis;

	public EncryptionLogEventArgs(String originalFilePath,
			String afterEncOrDec, EncryptionAlgorithm algorithm, float milis) {
		super();
		this.originalFilePath = originalFilePath;
		this.afterEncOrDec = afterEncOrDec;
		this.algorithm = algorithm;
		this.milis = milis;
	}

	public String getOriginalFilePath() {
		return originalFilePath;
	}

	public void setOriginalFilePath(String originalFilePath) {
		this.originalFilePath = originalFilePath;
	}

	public String getAfterEncOrDec() {
		return afterEncOrDec;
	}

	public void setAfterEncOrDec(String afterEncOrDec) {
		this.afterEncOrDec = afterEncOrDec;
	}

	public EncryptionAlgorithm getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(EncryptionAlgorithm algorithm) {
		this.algorithm = algorithm;
	}

	public float getMilis() {
		return milis;
	}

	public void setMilis(float milis) {
		this.milis = milis;
	}

	@Override
	public boolean equals(Object obj) {
		EncryptionLogEventArgs e = (EncryptionLogEventArgs) obj;
		return this.afterEncOrDec.equals(e.getAfterEncOrDec())
				&& this.algorithm.toString()
						.equals(e.getAlgorithm().toString())
				&& this.milis == e.getMilis()
				&& this.originalFilePath.equals(e.getOriginalFilePath());
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return this.afterEncOrDec.hashCode() * this.algorithm.hashCode()
				* (int)this.milis * this.originalFilePath.hashCode();
	}

}
