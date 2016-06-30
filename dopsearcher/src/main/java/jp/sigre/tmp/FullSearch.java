package jp.sigre.tmp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FullSearch extends Digest{

	Digest digSample = new Digest();

	List<FileBean> fileList = new ArrayList<>();
	List<FileBean> dirList  = new ArrayList<>();


	public FullSearch() {
	}

	public static void main(String[] args) throws IOException {
		/*
         * 現在の標準出力先を保持する
         */
        PrintStream sysOut = System.out;


		FullSearch fullSearch = new FullSearch();

        /*
         * 標準出力の出力先をファイルに切り変える
         */
        FileOutputStream fos = new FileOutputStream("target\\out"+fullSearch.getDateStr()+".txt");
        PrintStream ps = new PrintStream(fos);

        System.setOut(ps); // 実際に切り替えているのはここ

		long start = System.currentTimeMillis();

		System.out.println("@Start");
		for (int i = 0; i < args.length; i++) {
			fullSearch.readFolder(new File(args[i]));
		}
		System.out.println("@FullSearch End");


		System.out.println("@CompareFile");
		fullSearch.compareFile();

		System.out.println("@CompareFolder");

		fullSearch.compareFolder();

		long end = System.currentTimeMillis();

		System.out.println((end - start) / 1000  + "s");

		// ファイルをクローズ
        ps.close();
        fos.close();

        /*
         * 標準出力をデフォルトに戻す
         */
        System.setOut(sysOut);

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
				//System.out.println("fold:" + file.getPath());

				executeFolder(dir);
				readFolder(file);
			} else if (file.isFile()) {
				//System.out.println("file:" + file.getPath());

				executeFile(file);
			}
		}
	}

	private void executeFile(File file) {
		//System.out.println("exeFile:" + file.length());
		//TODO; 特定の条件のファイルを追加しない

		if (!file.getName().endsWith(".svn-base")) fileList.add(getBean(file));

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

	private String getDateStr() {
		Date date = new Date();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf1.format(date);
	}
}
