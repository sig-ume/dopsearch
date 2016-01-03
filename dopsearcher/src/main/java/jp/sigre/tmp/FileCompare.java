package jp.sigre.tmp;

import java.util.ArrayList;
import java.util.List;

public class FileCompare {

	List<FileDopBean> dopList = new ArrayList<FileDopBean>();

	public FileCompare() {
	}

	public List<FileDopBean> compareFile(List<FileBean> list) {

		for (int i = 0; i < list.size(); i++) {
			FileDopBean dopBean = makeDopBean(list.get(i));
			for (int j = i+1; j < list.size(); j++) {
				FileBean beanB = list.get(j);
				if (beanB.getMd5().equals(dopBean.getMd5())) {
					dopBean.setFullPath(beanB.getFullPath());
					dopBean.setName(beanB.getName());
					list.remove(j);
					j--;
				}
			}
			if (dopBean.getFullPathList().size() != 1) {
				dopList.add(dopBean);
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
