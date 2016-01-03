package jp.sigre.tmp;

import java.util.ArrayList;
import java.util.List;

public class FileDopBean {

	public FileDopBean() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	String md5;
	List<String> fullPathList = new ArrayList<>();
	List<String> nameList = new ArrayList<>();
	/**
	 * @return md5
	 */
	public String getMd5() {
		return md5;
	}
	/**
	 * @param md5 セットする md5
	 */
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	/**
	 * @return fullPathList
	 */
	public List<String> getFullPathList() {
		return fullPathList;
	}
	/**
	 * @param fullPathList セットする fullPathList
	 */
	public void setFullPathList(List<String> fullPathList) {
		this.fullPathList = fullPathList;
	}

	/**
	 *
	 * @param fullPath
	 */
	public void setFullPath(String fullPath) {
		this.fullPathList.add(fullPath);
	}

	/**
	 * @return nameList
	 */
	public List<String> getNameList() {
		return nameList;
	}
	/**
	 * @param nameList セットする nameList
	 */
	public void setNameList(List<String> nameList) {
		this.nameList = nameList;
	}
	/**
	 *
	 * @param name
	 */
	public void setName(String name) {
		this.nameList.add(name);
	}
	/* (非 Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String result = "FileDopBean [md5=" + md5 + "\nfullPathList=\n";
		for (String fullPath : fullPathList) {
			result += fullPath + "\n";
		}
		result += "]";

		return result;
	}

}
