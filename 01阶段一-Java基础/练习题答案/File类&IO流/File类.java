��ϰһ:���·���;���·����ʹ��
����:���������ļ����󣬷ֱ�ʹ�����·���;���·��������
��
��������:
����·�������ļ�����ʹ��File��һ�������Ĺ��췽����
���·�������ļ�����ʹ��File�����������Ĺ��췽����
����:
public class Test01_01 {
public static void main(String[] args) {
// �����ļ����󣺾���·��
		File f1 = new File("d:/aaa/a.txt");
		// �����ļ��������·��
		File f2 = new File("a.txt");
	}
}
��ϰ��:����ļ��Ƿ����,�ļ��Ĵ���
����:���D�����Ƿ�����ļ�a.txt,����������򴴽����ļ���
��
��������:
1.	ʹ�þ���·���������������D�̵�a.txt��
2.	ͨ���ļ����󷽷��ж��ļ��Ƿ���ڡ�
3.	����������ô����ļ��ķ��������ļ���
����:
public class Test01_02 {
	public static void main(String[] args) throws IOException{
		// �����ļ����󣺾���·��
		File f = new File("d:/a.txt");
		// ����ļ������ڣ��򴴽��ļ�
		if(!f.exists()) {
			f.createNewFile();
		}
	}
}

��ϰ��:�����ļ��еĴ���
����:��D���´���һ����Ϊbbb���ļ��С�
��
��������:
1.	�����ļ�����ָ��·��Ϊd:/bbb
2.	�����ļ����󴴽��ļ��еķ���
����:
public class Test01_03 {
	public static void main(String[] args) {
		// �����ļ�����
		File f = new File("d:/bbb");
		// ���������ļ���
		f.mkdir();
	}
}

��ϰ��:�༶�ļ��еĴ���
����:��D���´���һ����Ϊccc���ļ��У�Ҫ�����£�
1.ccc�ļ�����Ҫ�����bbb���ļ���
2.bbb���ļ���Ҫ�����aaa�ļ���
��:
��������:
1.	�����ļ��������·����d:/ccc/bbb/aaa
2.	�����ļ����󴴽��༶�ļ��еķ���
����:
public class Test01_04 {
	public static void main(String[] args) {
		// �����ļ�����
		File f = new File("d:/ccc/bbb/aaa");
		// �����༶�ļ���
		f.mkdirs();
	}
}
��ϰ��:ɾ���ļ����ļ���
����:
��D����a.txt�ļ�ɾ��
��D����aaa�ļ���ɾ��,Ҫ���ļ���aaa��һ�����ļ��С�
��:
��������:
1.	�����ļ��������·����d:/a.txt
2.	�����ļ�����ɾ���ļ��ķ���
3.	�����ļ��������·����d:/aaa
4.	�����ļ�����ɾ���ļ��еķ���
����:
public class Test01_05 {
	public static void main(String[] args) {
		// �����ļ�����
		File f = new File("d:/a.txt");
		// ɾ���ļ�
		f.delete();
		
		// �����ļ��ж���
		File dir = new File("d:/aaa");
		// ɾ���ļ���
		dir.delete();
	}
}
��ϰ��:��ȡ�ļ���Ϣ:�ļ���,�ļ���С,�ļ��ľ���·��,�ļ��ĸ�·��
����:
��ȡD��aaa�ļ�����b.txt�ļ����ļ������ļ���С���ļ��ľ���·���͸�·������Ϣ��������Ϣ����ڿ���̨��
��:
��������:
1.	��D��aaa�ļ����д���һ��b.txt�ļ�����������
2.	�����ļ��������·����d:/aaa/b.txt
3.	�����ļ��������ط��������Ϣ�����������ͨ��API�����ĵ���ѯ������
����:
public class Test01_06 {
	public static void main(String[] args) {
		// �����ļ�����
		File f = new File("d:/aaa/b.txt");
		// ����ļ���
		String filename = f.getName();
		// ����ļ���С
		longfilesize = f.length();
		// ����ļ��ľ���·��
		String path = f.getAbsolutePath();
		// ��ø��ļ���·���������ַ���
		String parentPath = f.getParent();
		// ��ø��ļ���·���������ļ�����
		File parentFile = f.getParentFile();
		// �����Ϣ
		System.out.println("�ļ�����" + filename);
		System.out.println("�ļ���С��" + filesize);
		System.out.println("�ļ�·����" + path);
		System.out.println("�ļ���·����" + parentPath);
		System.out.println("�ļ���·����" + parentFile);
	}
}

��ϰ��:�ļ��л��ļ����ж�
����:
1.�ж�File�����Ƿ����ļ�,���ļ��������xxx��һ���ļ������������xxx����һ���ļ���
2.�ж�File�����Ƿ����ļ���,���ļ����������xxx��һ���ļ��У����������xxx����һ���ļ��С�(xxx���ļ������ļ�����)
��:
��������:
1.	���������ļ�����ֱ��������ͬ���ļ������磺d:/a.txt��d:/aaa
2.	�����ļ�������ж��Ƿ����ļ����Ƿ����ļ��еķ���
3.	����ļ����������жϽ�������Ϣ��
����:
public class Test01_07 {
	public static void main(String[] args) {
		// �����ļ�����
		File f1 = new File("d:/b.txt");
		// �ж��Ƿ���һ���ļ�
		if(f1.isFile()) {
			System.out.println(f1.getName()+"��һ���ļ�");
		}  else {
			System.out.println(f1.getName()+"����һ���ļ�");
		}
		// �����ļ�����
		File f2 = new File("d:/aaaa");
		// �ж��Ƿ���һ���ļ���
		if(f2.isDirectory()) {
			System.out.println(f2.getName()+"��һ���ļ���");
		}  else {
			System.out.println(f2.getName()+"����һ���ļ���");
		}
	}
}
