package core;


import java.math.BigInteger;
import java.util.Random;

public class Parameters {
	
	public Random rand;
	
	public BigInteger biG;
	
	public BigInteger biP;

	public Parameters() {
		
		this.rand = new Random();
		
		this.biG = new BigInteger("2");
		
		this.biP = new BigInteger(512, 64, rand);
	}
}
