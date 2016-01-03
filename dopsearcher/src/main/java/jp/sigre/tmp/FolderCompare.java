package jp.sigre.tmp;

import java.util.ArrayList;
import java.util.List;

public class FolderCompare {

	public FolderCompare() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public void printNearlyFolders(List<FileBean> list) {

		List<FileBean> result = new ArrayList<FileBean>();

		for (int i = 0; i < list.size(); i++) {
			FileBean beanA = list.get(i);
			String nameA = getFirstHalf(beanA.getName());
			result.add(beanA);
			for (int j = i+1; j < list.size(); j++) {
				FileBean beanB = list.get(j);
				String nameB = getFirstHalf(beanB.getName());
				if (nameA.equals(nameB)) {
					result.add(beanB);
					list.remove(j);
					j--;
				}
			}
			if (result.size() != 1) {
				printResult(result);
			}
			result = new ArrayList<FileBean>();
		}
	}


	private String getFirstHalf(String fileName) {
		if (fileName == null)
	        return null;
	    int point = fileName.length() / 2;
	    if (point != -1) {
	        return fileName.substring(0, point);
	    }
	    return fileName;
	}

	private void printResult(List<FileBean> result) {
		System.out.println("----------------------");
		for (int i = 0; i < result.size(); i++) {
			System.out.println(i+1 + "\t:" + result.get(i).getFullPath());
		}

	}

}
