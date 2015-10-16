package core;


import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashFunction {

	public static String hashType = "SHA-256";

	public static String keyedHashType = "HmacSHA256";

	public static char[] hexChar = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
	
	public static byte[] SHA256(byte[] msg) {

        try {

            MessageDigest md = MessageDigest.getInstance(hashType);

            md.update(msg);

            return md.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String SHA256(String msg) {

        return SHA256(msg, 256);
    }
    
    /**
     * @param msg   input
     * @param limit limit the output size, should be bigger than 8
     * @return a limited digest
     */
    public static String SHA256(String msg, int limit) {

        String digest;

        byte[] bt;

        if (limit < 8) {
            System.err.println("The limit size should be bigger than 8!");
            return null;
        } else {
            limit = limit / 8;
        }

        try {

            MessageDigest md = MessageDigest.getInstance(hashType);

            md.update(msg.getBytes());

            bt = md.digest();

            String oriDigest = toHexString(bt);
            if (limit < 32) {
                digest = oriDigest.substring(0, 2 * limit);
            } else if (limit > 32) {

                StringBuilder buf = new StringBuilder(2 * limit);

                while (limit > 32) {
                    buf.append(oriDigest);
                    limit -= 32;
                }
                buf.append(oriDigest, 0, 2 * limit);

                digest = buf.toString();
            } else {
                digest = oriDigest;
            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        return digest;
    }
    
    public static long SHA256ToUnsignedInt(String msg) {

        long digest;

        try {

            MessageDigest md = MessageDigest.getInstance(hashType);

            md.update(msg.getBytes());

            digest = bytesToUnsignedInt(md.digest());

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return -1;
        }

        return digest;
    }


	public static String toHexString(byte[] b) {
		StringBuilder sb = new StringBuilder(b.length * 2);
		for (int i = 0; i < b.length; i++) {
			sb.append(hexChar[(b[i] & 0xf0) >>> 4]);
			sb.append(hexChar[b[i] & 0x0f]);
		}
		return sb.toString();
	}
	
	public static long bytesToUnsignedInt(byte[] bytes) {

    	ByteBuffer buffer = ByteBuffer.allocate(8);
    	
        buffer.clear();
        buffer.put(bytes, 0, 4);
        buffer.flip();
        return buffer.getInt() & 0x0FFFFFFFFl;
    }
}
