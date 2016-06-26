package jp.sigre.tmp;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.util.Arrays;

public class Digest {

	final static String DIG_ALGORITHM = "MD5";

	static String target1 = "target\\TestFile\\target1.txt";
	static String target2 = "target\\TestFile\\target2.txt";
	static String target1_copy = "target\\TestFile\\target1_copy.txt";

	public Digest() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public static void main(String[] args) throws Exception {

		Digest digSample = new Digest();

		digSample.getDigest(target1);

		digSample.getDigest(target2);

		digSample.getDigest(target1_copy);

	}

	public String getDigestStr(String path) {
		byte[] digest = getDigest(path);
		String md5 = "";
		for (int loop = 0;loop < digest.length;loop++) {
			md5 += Integer.toHexString(0xff&(char)digest[loop]).toString();
		}

		return md5;
	}

	public byte[] getDigest(String path) {
		MessageDigest msgDig = null;
		byte[] digest = new byte[4];
		DigestInputStream digInput = null;
		try {
			msgDig = MessageDigest.getInstance(DIG_ALGORITHM);
			InputStream stream = new FileInputStream(path);
			digInput = new DigestInputStream(stream, msgDig);

			while(digInput.read() != -1) {	}
		} catch (Exception e) {
			//e.printStackTrace();
			Arrays.fill(digest,(byte)0);
			return digest;
		} finally {
			try {
				digInput.close();
			} catch (Exception e) {
				//e.printStackTrace();
				Arrays.fill(digest,(byte)0);
				return digest;
			}
		}



		digest  = msgDig.digest();

		String fileNames[] = path.split("\\\\");
		String fileName = fileNames[fileNames.length - 1];

		//System.out.println(fileName + "\t:" + digest);
		return digest;
	}

}
