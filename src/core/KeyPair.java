package core;


import java.math.BigInteger;

public class KeyPair {

	private BigInteger sk;
	
	private BigInteger pk;
	
	public KeyPair(BigInteger sk, BigInteger pk) {
		
		this.sk = sk;
		this.pk = pk;
	}

	public BigInteger getSk() {
		return sk;
	}

	public void setSk(BigInteger sk) {
		this.sk = sk;
	}

	public BigInteger getPk() {
		return pk;
	}

	public void setPk(BigInteger pk) {
		this.pk = pk;
	}
}
