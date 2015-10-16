package core;


import java.math.BigInteger;

public class HECiphertext {

	private BigInteger u;
	
	private BigInteger v;
	
	public HECiphertext(BigInteger u, BigInteger v) {
		
		this.u = u;
		this.v = v;
	}

	public BigInteger getU() {
		return u;
	}

	public void setU(BigInteger u) {
		this.u = u;
	}

	public BigInteger getV() {
		return v;
	}

	public void setV(BigInteger v) {
		this.v = v;
	}
}
