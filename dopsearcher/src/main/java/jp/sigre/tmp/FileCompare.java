package jp.sigre.tmp;

import java.util.ArrayList;
import java.util.List;

public class FileCompare {

	List<FileDopBean> dopList = new ArrayList<FileDopBean>();
	String md5 = "";
	Digest digest = new Digest();

	public FileCompare() {
	}

	//　TODO　再構築
	public List<FileDopBean> compareFile(List<FileBean> list) {
		for (int i = 0; i < list.size(); i++) {
			FileDopBean dopBean = makeDopBean(list.get(i));
			FileBean beanComparing = list.get(i);
			for (int j = i+1; j < list.size(); j++) {
				FileBean beanCompared = list.get(j);
				if (beanCompared.getSize() == beanComparing.getSize()) {
					//setMD5();
					dopBean.setFullPath(beanCompared.getFullPath());
					dopBean.setName(beanCompared.getName());

					//TODO ここで消してはだめ。md5比較後。
					list.remove(j);
					j--;
				}
			}
			if (dopBean.getFullPathList().size() != 1) {
				md5 = "";
				byte[] digest1 = digest.getDigest(beanComparing.getFullPath());
				for (int loop = 0;loop < digest1.length;loop++) {
					md5 += Integer.toHexString(0xff&(char)digest1[loop]).toString();
				}
				dopBean.setMd5(md5);
				for (int x = 0; x < dopBean.getFullPathList().size(); x++) {
					String path = dopBean.getFullPathList().get(x);
					String md5_2 = "";
					byte[] digest2 = digest.getDigest(path);
					for (int loop2 = 0;loop2 < digest2.length;loop2++) {
						md5_2 += Integer.toHexString(0xff&(char)digest2[loop2]).toString();
					}

					if (!md5_2.equals(md5)) {
						dopBean.getFullPathList().remove(x);
						x--;
					}
				}
				if (dopBean.getFullPathList().size() != 1) {
					dopList.add(dopBean);
				}
			}
		}
		return dopList;
	}

	public void printDopList() {
		for (FileDopBean dop : dopList) {
			System.out.println(dop.toString());
		}
	}

	private FileDopBean makeDopBean(FileBean bean) {
		FileDopBean dop = new FileDopBean();
		dop.setMd5(bean.getMd5());
		dop.setFullPath(bean.getFullPath());
		dop.setName(bean.getName());

		return dop;
	}

}
