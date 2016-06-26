package jp.sigre.tmp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FileCompare {

	List<FileDopBean> dopList = new ArrayList<FileDopBean>();
	String md5 = "";
	Digest digest = new Digest();

	public FileCompare() {
	}

	public List<FileDopBean> compareFile(List<FileBean> list) {
		List<List<FileBean>> sameSizeList = getSameFileList(list);

		dopList = getSameMd5List(sameSizeList);

		return dopList;
	}

	private List<List<FileBean>> getSameFileList(List<FileBean> list) {
		List<List<FileBean>> result = new ArrayList<List<FileBean>>();

		for (int i = 0; i < list.size(); i++) {
			List<FileBean> beanList = new ArrayList<FileBean>();
			FileBean bean = list.get(i);
			long fileSize = bean.getSize();
			beanList.add(bean);
			list.remove(i--);

			Iterator<FileBean> innerItr = list.iterator();
			while (innerItr.hasNext()) {
				FileBean sizeComparedTarget = innerItr.next();
				if (sizeComparedTarget.getSize() == fileSize) {
					beanList.add(sizeComparedTarget);
					innerItr.remove();
				}
			}

			if (beanList.size() >= 2) result.add(beanList);
		}

		return result;
	}

	private List<FileDopBean> getSameMd5List(List<List<FileBean>> list) {
		List<FileDopBean> result = new ArrayList<FileDopBean>();

		for (List<FileBean> beanList : list) {

			for (int i = 0; i < beanList.size(); i++) {
				FileDopBean dopBean = makeDopBean(beanList.get(i));
				beanList.remove(i--);

				Iterator<FileBean> innerItr = beanList.iterator();
				while (innerItr.hasNext()) {
					FileBean dopComparedTarget = innerItr.next();
					String md5 = digest.getDigestStr(dopComparedTarget.getFullPath());

					if (dopBean.getMd5().equals(md5)) {
						dopBean.setFullPath(dopComparedTarget.getFullPath());
						innerItr.remove();
					}
				}

				if (dopBean.getFullPathList().size() >= 2) result.add(dopBean);
			}
		}


		return result;
	}


	public void printDopList() {
		for (FileDopBean dop : dopList) {
			System.out.println(dop.toString());
		}
	}

	private FileDopBean makeDopBean(FileBean bean) {
		FileDopBean dop = new FileDopBean();
		String md5 = digest.getDigestStr(bean.getFullPath());
		dop.setMd5(md5);
		dop.setFullPath(bean.getFullPath());
		dop.setName(bean.getName());

		return dop;
	}

	private FileDopBean makeUnDopBean(List<FileBean> list) {
		FileDopBean dop = new FileDopBean();
		dop.setMd5("Unculclate MD5");
		for (FileBean bean :list) {
			dop.setFullPath(bean.getFullPath());
		dop.setName(bean.getName());
		}

		return dop;
	}

}
