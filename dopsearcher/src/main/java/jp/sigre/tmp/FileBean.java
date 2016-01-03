package jp.sigre.tmp;


public class FileBean {

	public FileBean() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	private String		fullPath;
	private String 		name;
	private long		size;
	private String		md5;
	private boolean		type;

	/**
	 * @return fullPath
	 */
	public String getFullPath() {
		return fullPath;
	}
	/**
	 * @param fullPath セットする fullPath
	 */
	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}
	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name セットする name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return size
	 */
	public long getSize() {
		return size;
	}
	/**
	 * @param size セットする size
	 */
	public void setSize(long size) {
		this.size = size;
	}
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
	 * @return type
	 */
	public boolean isType() {
		return type;
	}
	/**
	 * @param type セットする type
	 */
	public void setType(boolean type) {
		this.type = type;
	}
	/* (非 Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FileBean [fullPath=" + fullPath + ", name=" + name + ", size="
				+ size + ", md5=" + md5 + "]";
	}
	/* (非 Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((fullPath == null) ? 0 : fullPath.hashCode());
		result = prime * result + ((md5 == null) ? 0 : md5.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (int) (size ^ (size >>> 32));
		return result;
	}
	/* (非 Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FileBean other = (FileBean) obj;
		if (type) { //typeがTrue→ファイル
			if (md5 == null) {
				if (other.md5 != null)
					return false;
			} else if (md5.equals(other.md5))
				return true;
		} else { //typeがFalse→フォルダ
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (name.indexOf(other.name) != -1) {
				return true;
			} else if (other.name.indexOf(name) != -1) {
				return true;
			}
		}

		return true;
	}


}
