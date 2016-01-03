package jp.sigre.tmp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FullSearch extends Digest{

	Digest digSample = new Digest();

	List<FileBean> fileList = new ArrayList<>();
	List<FileBean> dirList  = new ArrayList<>();


	public FullSearch() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public static void main(String[] args) {
		long start = System.currentTimeMillis();

		System.out.println("@Start");
		FullSearch fullSearch = new FullSearch();
		for (int i = 0; i < args.length; i++) {
			fullSearch.readFolder(new File(args[i]));
		}
		System.out.println("@FullSearch End");

//		System.out.println("-----------------------");
//		fullSearch.printDirList();
//		System.out.println("-----------------------");
//		fullSearch.printFileList();

		System.out.println("@CompareFile");
		fullSearch.compareFile();

		System.out.println("@CompareFolder");

		fullSearch.compareFolder();

		long end = System.currentTimeMillis();

		System.out.println((end - start) / 1000  + "s");
	}

	public void readFolder(File dir) {
		File[] files = dir.listFiles();

		if (files == null) {
			return;
		}

		for (File file : files) {
			if (!file.exists()) {
				continue;
			} else if (file.isDirectory()) {
				System.out.println("fold:" + file.getPath());

				executeFolder(dir);
				readFolder(file);
			} else if (file.isFile()) {
				System.out.println("file:" + file.getPath());

				executeFile(file);
			}
		}
	}

	private void executeFile(File file) {
		//System.out.print("file\t:");
		digSample.getDigest(file.getPath());
		System.out.println("exeFile:" + file.length());

		fileList.add(getBean(file));

	}

	private void executeFolder(File folder) {
		//System.out.print("folder\t:");
		//System.out.println(folder.length());

		dirList.add(getBean(folder));
	}

	private FileBean getBean(File file) {
		FileBean result = new FileBean();

		result.setFullPath(file.getAbsolutePath());
		result.setName(file.getName());
		result.setSize(file.length());
		String md5 = "";
		if (file.isFile()) {
			byte[] digest = digSample.getDigest(file.getAbsolutePath());
			for (int loop = 0;loop < digest.length;loop++) {
	            md5 += Integer.toHexString(0xff&(char)digest[loop]).toString();
	        }

			result.setType(true);
		} else if(file.isDirectory()) {
			result.setType(false);
		}
		result.setMd5(md5);
		return result;
	}

	private void printList(List<FileBean> list) {
		for (FileBean bean : list) {
			System.out.println(bean.toString());
		}
	}

	private void printFileList() {
		printList(fileList);
	}

	private void printDirList() {
		printList(dirList);
	}

	private void compareFile() {
		FileCompare comp = new FileCompare();

		comp.compareFile(fileList);
		comp.printDopList();
	}

	private void compareFolder() {
		fileList.addAll(dirList);
		new FolderCompare().printNearlyFolders(fileList);

	}
}
