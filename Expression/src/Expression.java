import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;


public class Expression {

	/**
	 * @param args
	 */
	//ָ����һ���ַ����α�
	WordNode wn;
	ArrayList<WordNode> list=new ArrayList<WordNode>();
	ArrayList<String> errorList=new ArrayList<String>();
	int i=0;
	String num="123456789";
	String num1="0123456789";
	
	public void scanStr(ArrayList<String> strl) {
		for(int i=0;i<strl.size();i++) {
			doInterpret(strl.get(i),i+1);
			this.i=0;
		}
	}
	
	public void doInterpret(String str,int row){
		StringBuffer bstr=new StringBuffer("");
		do{
			char c=this.getChar(str);
			if(c=='+'){
				wn=new WordNode("1",c+"","NULL","NULL");
				list.add(wn);
			}
			else if(c=='-'){
				wn=new WordNode("2",c+"","NULL","NULL");
				list.add(wn);
			}
			else if(c=='*'){
				wn=new WordNode("3",c+"","NULL","NULL");
				list.add(wn);
			}
			else if(c=='/'){
				wn=new WordNode("4",c+"","NULL","NULL");
				list.add(wn);
			}
			else if(c=='('){
				wn=new WordNode("6",c+"","NULL","NULL");
//				System.out.print("Adding...."+c);
				list.add(wn);
			}
			else if(c==')'){
				wn=new WordNode("7",c+"","NULL","NULL");
				list.add(wn);
			}
			
			//��0��ͷ
			else if(c=='0') 
			{
				bstr.append(c);
				char c1;
				//i<str.length()˵��0���滹���ַ�
				if(i<str.length())
				{
					//��ȡ0�����һ���ַ�
					c1=getChar(str);
					//����0����Ϊ��.��
					if(c1=='.')
					{
						bstr.append(c1);
						//i<str.length()˵����.�����滹���ַ�
						if(i<str.length()) 
						{
							c1=getChar(str);
							//���������ַ�Ϊ0~9
							int flag=0;//�����ж��Ƿ����0~9���ж�
							while(i<str.length()&&num1.contains(c1+""))
							{
								bstr.append(c1);
								c1=getChar(str);
								if(i==str.length()&&num1.contains(c1+"")) 
								{
									bstr.append(c1+"");
									i++;
								}
								flag=1;
							}
							//������0~9���жϣ�˵��С����������֣�����Ϊ��ȡ����һ����һλ��Ҫ����һλ
							if(flag==1) 
							{
								i--;
								addNumNode(bstr.toString(),"double");
							}
							//����С�������Ϊ������
							else 
							{
								i--;
								//��Ӵ�����ʾ
								addErrorNode(row,i-bstr.length(),bstr.toString(),str);
							}
						}
					}
					//����0����Ϊ0~9����
					else if(num1.contains(c1+"")) 
					{
						int flag=0;//�����ж��Ƿ����0~9�����ѭ���ж�
						//���������ַ�Ϊ0~9
						while(i<str.length()&&num1.contains(c1+"")||i<str.length()&&c1=='.')
						{
							bstr.append(c1+"");
//							System.out.println("105--adding::"+c1);
							c1=getChar(str);
							if(i==str.length()&&num1.contains(c1+"")) 
							{
								bstr.append(c1+"");
								i++;
							}
							flag=1;
						}
						if(flag==1)
							i--;
						//��Ӵ�����ʾ
						addErrorNode(row,i-bstr.length(),bstr.toString(),str);
					}
					//0���治�ǡ�.����������ʱ��i����һλ
					else 
					{
						i--;
						//����Ϊ����0
						addNumNode(bstr.toString(),"int");
					}
				}
				//0����û���ַ�
				else 
					addNumNode(bstr.toString(),"int");
				//����������ִ�
				bstr.delete(0, bstr.length());
			}
			
			//��1~9��ͷ
			else if(num.contains(c+"")) 
			{
				bstr.append(c);
				char c1;
				//i<str.length()˵��0���滹���ַ�
				if(i<str.length())
				{
					//��ȡ��0���ֺ����һ���ַ�
					c1=getChar(str);
					//������0����Ϊ��.��
					if(c1=='.')
					{
						bstr.append(c1);
						//i<str.length()˵����.�����滹���ַ�
						if(i<str.length()) 
						{
							c1=getChar(str);
							//���������ַ�Ϊ0~9
							int flag=0;//�����ж��Ƿ����0~9���ж�
							while(i<str.length()&&num1.contains(c1+""))
							{
								bstr.append(c1);
								c1=getChar(str);
								if(i==str.length()&&num1.contains(c1+"")) 
								{
									bstr.append(c1+"");
									i++;
								}
								flag=1;
							}
							//������0~9���жϣ�˵��С����������֣�����Ϊ��ȡ����һ����һλ��Ҫ����һλ
							if(flag==1) 
							{
								i--;
								addNumNode(bstr.toString(),"double");
							}
							//����С�������Ϊ������
							else 
							{
								i--;
								//��Ӵ�����ʾ
								addErrorNode(row,i-bstr.length(),bstr.toString(),str);
							}
						}
					}
					//�������ֺ���Ϊ0~9����
					else if(num1.contains(c1+"")) 
					{
						int flag=0;//�����ж��Ƿ����0~9�����ѭ���ж�
						//���������ַ�Ϊ0~9
						while(i<str.length()&&num1.contains(c1+""))
						{
							bstr.append(c1+"");
							c1=getChar(str);
							if(i==str.length()&&num1.contains(c1+"")) 
							{
								bstr.append(c1+"");
								i++;
							}
							flag=1;
						}
						if(flag==1)
							i--;
						//���Ϊ������
						
						if(i<str.length())
						{
							//��ȡ��0���ֺ����һ���ַ�
							c1=getChar(str);
							//����0����Ϊ��.��
							if(c1=='.')
							{
								bstr.append(c1);
								//i<str.length()˵����.�����滹���ַ�
								if(i<str.length()) 
								{
									c1=getChar(str);
									//���������ַ�Ϊ0~9
									int flag1=0;//�����ж��Ƿ����0~9���ж�
									while(i<str.length()&&num1.contains(c1+""))
									{
										bstr.append(c1);
										c1=getChar(str);
										if(i==str.length()&&num1.contains(c1+"")) 
										{
											bstr.append(c1+"");
											i++;
										}
										flag1=1;
									}
									//������0~9���жϣ�˵��С����������֣�����Ϊ��ȡ����һ����һλ��Ҫ����һλ
									if(flag1==1) 
									{
										i--;
										addNumNode(bstr.toString(),"double");
									}
									//����С�������Ϊ������
									else 
									{
										i--;
										//��Ӵ�����ʾ
										addErrorNode(row,i-bstr.length(),bstr.toString(),str);
									}
								}
							}
						}else
						
						addNumNode(bstr.toString(),"int");
					}
					//0���治�ǡ�.����������ʱ��i����һλ
					else 
					{
						i--;
					}
				}
				//���ֺ���û���ַ�
				else 
				{
					addNumNode(bstr.toString(),"int");
				}
				//����������ִ�
				bstr.delete(0, bstr.length());
			}
			else addErrorNode(row,i-1,c+"",str);
				
		}while(i<str.length());
	}
	
	//��ȡ��һ���ַ�
	public char getChar(String str){
		char nextChar=str.charAt(i); 
//		System.out.println("��ǰ��õ�nextChar="+nextChar+"   ,i="+i);
		i++;
		return nextChar;
	}
	
	public void addNumNode(String bstr,String type) {
//		System.out.println("adding.."+bstr);
		if(type.equals("Double"))
			wn=new WordNode("8",bstr,bstr,type);
		else wn=new WordNode("5",bstr,bstr,type);
		list.add(wn);
			
	}
	
	public void addErrorNode(int row,int column,String error,String str) {
		if(column==0)
			errorList.add("error"+errorList.size()+":��"+row+"���ַ�"+"��ʼλ�ô��ڷǷ��ַ�\""+error+"\"");
		else 
			errorList.add("error"+errorList.size()+":��"+row+"��"+str.charAt(column-1)+"������ڷǷ��ַ�\""+error+"\"");
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Expression e1=new Expression();
		ArrayList<String> strl=new ArrayList<String>();
		//�����ַ���
//		System.out.println("������,��Enter������,��Ctrl+Z������");
//		Scanner scanner=new Scanner(System.in);
//		String str;
//		while(scanner.hasNext()) {
//			str=scanner.nextLine();
//			strl.add(str);
//			str=null;
//		}
//		scanner.close();
		
//		//ֱ�Ӷ�txt�ļ�
		try {
//			//����txt�ļ�
		//�̶�Ŀ¼
//			String pathname="H:\\��ѧ����\\����\\������1��\\����ԭ��\\aaa.txt";
		//���м���Ŀ¼
			System.out.println("�������������");
			Scanner scanner1=new Scanner(System.in);
			String pathname=scanner1.nextLine();
			File filename=new File("h:\\"+pathname);
			InputStreamReader reader=new InputStreamReader(new FileInputStream(filename));
			BufferedReader br=new BufferedReader(reader);
			String line="";
			line=br.readLine();
			while(line!=null) {
				strl.add(line);
				line=br.readLine();
			}
		}catch(Exception e) {	
		}
		
		
		e1.scanStr(strl);
		System.out.println("���ߣ�Sakalaka");
		for(int j=0;j<e1.list.size();j++){
			e1.list.get(j).print();
		}
		for(int j=0;j<e1.errorList.size();j++){
			System.out.println(e1.errorList.get(j));
		}
	}

}
