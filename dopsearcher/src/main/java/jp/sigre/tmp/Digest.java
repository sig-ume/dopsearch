package jp.sigre.tmp;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;

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

	public byte[] getDigest(String path) {
		MessageDigest msgDig = null;
		DigestInputStream digInput = null;
		try {
			msgDig = MessageDigest.getInstance(DIG_ALGORITHM);
			InputStream stream = new FileInputStream(path);
			digInput = new DigestInputStream(stream, msgDig);

			while(digInput.read() != -1) {	}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				digInput.close();
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}



		byte[] digest = msgDig.digest();

		String fileNames[] = path.split("\\\\");
		String fileName = fileNames[fileNames.length - 1];

		//System.out.println(fileName + "\t:" + digest);
		return digest;
	}

}
