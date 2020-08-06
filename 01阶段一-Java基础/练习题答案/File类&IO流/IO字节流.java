��ϰһ:�ֽ������д���ֽ�����
����:�����ֽ������һ��дһ���ֽڵķ�ʽ����D�̵�a.txt�ļ�����ַ���a����
��
��������:
1.	�����ֽ������FileOutputStream����ָ���ļ�·����
2.	�����ֽ��������write(int byte)����д������
����:
public class Test01_01 {
public static void main(String[] args) throws IOException {
		// 1.�����ֽ������FileOutputStream����ָ���ļ�·����
		FileOutputStream fos = new FileOutputStream("d:/a.txt");
		// 2.�����ֽ��������write(int byte)����д������
		fos.write(97);
		// 3.�ر���
		fos.close();
	}
}
��ϰ��:�ֽ������д���ֽ���������
����:�����ֽ������һ��дһ���ֽ�����ķ�ʽ��D�̵�b.txt�ļ��������:"i love java"��
��
��������:
1.	�����ֽ������FileOutputStream����ָ���ļ�·����
2.	�����ֽ��������write(byte[] buf)����д�����ݡ�
����:
public class Test01_02 {
		public static void main(String[] args) throws IOException {
		// 1.�����ֽ������FileOutputStream����ָ���ļ�·����
		FileOutputStream fos = new FileOutputStream("d:/b.txt");
		// 2.�����ֽ��������write(byte[] buf)����д�����ݡ�
		byte[] buf = "i love java".getBytes();
		fos.write(buf);
		// 3.�ر���Դ
		fos.close();
	}
}
��ϰ��:�ļ�����д�ͻ������
����:��D���£���һc.txt �ļ�������Ϊ��HelloWorld 
��c.txt�ļ�ԭ���ݻ����ϣ������� I love java������Ҫʵ��һ��һ�в���(ע��ԭ�Ĳ��ɸ���)��
�����ֽ������������C����c.txt�ļ����5�䣺��i love java��

��
��������:
1.	�������������Ĺ��췽�������ֽ���������󣬲���һָ���ļ�·����������ָ��Ϊtrue
2.	�����ֽ��������write()����д�����ݣ���ÿһ�к�����ϻ��з�:��\r\n��
����:
public class Test01_03 {
	public static void main(String[] args) throws IOException{
		// 1.�����ֽ������FileOutputStream����ָ���ļ�·��,��׷�ӷ�ʽ
		FileOutputStream fos = new FileOutputStream("c:/c.txt",true);
		// 2.�����ֽ��������write����д������
		// 2.1 Ҫ������ַ���
		String content = "i love java \r\n";
		for (int i = 0; i< 5; i++) {
			fos.write(content.getBytes());
		}
		// 3.�ر���
		fos.close();
	}
}
��ϰ��:�ֽ�������һ�ζ�ȡһ���ֽ�����
����:�����ֽ���������ȡD���ļ�a.txt�����ݣ��ļ�����ȷ����Ϊ��ASCII�ַ�
,ʹ��ѭ����ȡ��һ�ζ�ȡһ���ֽڣ�ֱ����ȡ���ļ�ĩβ������ȡ���ֽ����������̨
��
��������:
1.	�����ֽ�����������ָ���ļ�·����
2.	����read(byte b)����ѭ����ȡ�ļ��е�����
3.	ֱ����ȡ��-1ʱ������ȡ
����:
public class Test01_04 {
	public static void main(String[] args) throws IOException{
		// �����ֽ����������󲢹����ļ�
		FileInputStream fis = new FileInputStream("d:/a.txt");
		// ����������ն�ȡ���ֽ�
		int len = -1;
		// ѭ�������ж�ȡ����
		while((len = fis.read()) != -1) {
			System.out.print((char)len);
		}
		// �ر���
		fis.close();
	}
}
��ϰ��:�ֽ�������һ�ζ�ȡһ���ֽ���������
����:�����ֽ���������ȡD���ļ�b.txt�����ݣ��ļ�����ȷ����Ϊ��ASCII�ַ�
,ʹ��ѭ����ȡ��һ�ζ�ȡһ���ֽ����飬ֱ����ȡ���ļ�ĩβ������ȡ�����ֽ�����ת�����ַ������������̨��
��
��������:
1.	�����ֽ�����������ָ���ļ�·����
2.	����һ���ֽ������飬������Ŷ�ȡ���ֽ���
3.	����read(byte[] buf)���������ֽ����飬ѭ����ȡ�ļ��е�����
4.	ֱ����ȡ��-1ʱ������ȡ
����:
public class Test01_05 {
	public static void main(String[] args) throws IOException{
		// �����ֽ����������󲢹����ļ�
		FileInputStream fis = new FileInputStream("d:/b.txt");
		// �����ֽ������Ŷ�ȡ���ֽ���
		byte[] buffer = new byte[1024];
		// ����������ն�ȡ���ֽ�
		int len = -1;
		// ѭ�������ж�ȡ����
		while((len = fis.read(buffer)) != -1) {
			System.out.print(new String(buffer,0,len));
		}
		// �ر���
		fis.close();
	}
}
��ϰ��:�ֽ��������ļ�
����:�����ֽ�����E���µ�a.pngͼƬ���Ƶ�D����(�ļ�������һ��)
Ҫ��
һ�ζ�дһ���ֽڵķ�ʽ
��
��������:
1.	�����ֽ���������������ļ�·����E���µ�a.png
2.	�����ֽ��������������ļ�·����D���µ�a.png
3.	ʹ��ѭ�����ϴ��ֽ���������ȡһ���ֽڣ�ÿ��ȡһ���ֽھ����������д��һ���ֽڡ�
4.	�ر������ͷ���Դ
����:
public class Test01_06 {
	public static void main(String[] args) throws IOException {
		// �����ֽ����������󲢹����ļ�
		FileInputStream fis = new FileInputStream("e:/a.png");
		// �����ֽ���������󲢹����ļ�
		FileOutputStream fos = new FileOutputStream("d:/a.png");
		// ����������ն�ȡ���ֽ���
		int len = -1;
		// ѭ����ȡͼƬ����
		while((len = fis.read()) != -1) {
			// ÿ��ȡһ���ֽڵ����ݾ�д����Ŀ���ļ���
			fos.write(len);
		}
		// �ر���
		fis.close();
		fos.close();
	}
}
