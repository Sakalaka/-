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
	//指向下一个字符的游标
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
			
			//以0开头
			else if(c=='0') 
			{
				bstr.append(c);
				char c1;
				//i<str.length()说明0后面还有字符
				if(i<str.length())
				{
					//获取0后面的一个字符
					c1=getChar(str);
					//倘若0后面为‘.’
					if(c1=='.')
					{
						bstr.append(c1);
						//i<str.length()说明‘.’后面还有字符
						if(i<str.length()) 
						{
							c1=getChar(str);
							//倘若后面字符为0~9
							int flag=0;//用于判断是否进入0~9的判断
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
							//进入了0~9的判断，说明小数点后有数字，但因为获取多了一次下一位需要回退一位
							if(flag==1) 
							{
								i--;
								addNumNode(bstr.toString(),"double");
							}
							//否则，小数点后面为非数字
							else 
							{
								i--;
								//添加错误提示
								addErrorNode(row,i-bstr.length(),bstr.toString(),str);
							}
						}
					}
					//倘若0后面为0~9数字
					else if(num1.contains(c1+"")) 
					{
						int flag=0;//用于判断是否进入0~9的添加循环判断
						//倘若后面字符为0~9
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
						//添加错误提示
						addErrorNode(row,i-bstr.length(),bstr.toString(),str);
					}
					//0后面不是‘.’或不是数字时，i回退一位
					else 
					{
						i--;
						//则它为整数0
						addNumNode(bstr.toString(),"int");
					}
				}
				//0后面没有字符
				else 
					addNumNode(bstr.toString(),"int");
				//清除缓存数字串
				bstr.delete(0, bstr.length());
			}
			
			//以1~9开头
			else if(num.contains(c+"")) 
			{
				bstr.append(c);
				char c1;
				//i<str.length()说明0后面还有字符
				if(i<str.length())
				{
					//获取非0数字后面的一个字符
					c1=getChar(str);
					//倘若非0后面为‘.’
					if(c1=='.')
					{
						bstr.append(c1);
						//i<str.length()说明‘.’后面还有字符
						if(i<str.length()) 
						{
							c1=getChar(str);
							//倘若后面字符为0~9
							int flag=0;//用于判断是否进入0~9的判断
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
							//进入了0~9的判断，说明小数点后有数字，但因为获取多了一次下一位需要回退一位
							if(flag==1) 
							{
								i--;
								addNumNode(bstr.toString(),"double");
							}
							//否则，小数点后面为非数字
							else 
							{
								i--;
								//添加错误提示
								addErrorNode(row,i-bstr.length(),bstr.toString(),str);
							}
						}
					}
					//倘若数字后面为0~9数字
					else if(num1.contains(c1+"")) 
					{
						int flag=0;//用于判断是否进入0~9的添加循环判断
						//倘若后面字符为0~9
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
						//添加为纯整数
						
						if(i<str.length())
						{
							//获取非0数字后面的一个字符
							c1=getChar(str);
							//倘若0后面为‘.’
							if(c1=='.')
							{
								bstr.append(c1);
								//i<str.length()说明‘.’后面还有字符
								if(i<str.length()) 
								{
									c1=getChar(str);
									//倘若后面字符为0~9
									int flag1=0;//用于判断是否进入0~9的判断
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
									//进入了0~9的判断，说明小数点后有数字，但因为获取多了一次下一位需要回退一位
									if(flag1==1) 
									{
										i--;
										addNumNode(bstr.toString(),"double");
									}
									//否则，小数点后面为非数字
									else 
									{
										i--;
										//添加错误提示
										addErrorNode(row,i-bstr.length(),bstr.toString(),str);
									}
								}
							}
						}else
						
						addNumNode(bstr.toString(),"int");
					}
					//0后面不是‘.’或不是数字时，i回退一位
					else 
					{
						i--;
					}
				}
				//数字后面没有字符
				else 
				{
					addNumNode(bstr.toString(),"int");
				}
				//清除缓存数字串
				bstr.delete(0, bstr.length());
			}
			else addErrorNode(row,i-1,c+"",str);
				
		}while(i<str.length());
	}
	
	//获取下一个字符
	public char getChar(String str){
		char nextChar=str.charAt(i); 
//		System.out.println("当前获得的nextChar="+nextChar+"   ,i="+i);
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
			errorList.add("error"+errorList.size()+":第"+row+"行字符"+"初始位置存在非法字符\""+error+"\"");
		else 
			errorList.add("error"+errorList.size()+":第"+row+"行"+str.charAt(column-1)+"后面存在非法字符\""+error+"\"");
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Expression e1=new Expression();
		ArrayList<String> strl=new ArrayList<String>();
		//输入字符串
//		System.out.println("请输入,按Enter键换行,按Ctrl+Z结束。");
//		Scanner scanner=new Scanner(System.in);
//		String str;
//		while(scanner.hasNext()) {
//			str=scanner.nextLine();
//			strl.add(str);
//			str=null;
//		}
//		scanner.close();
		
//		//直接读txt文件
		try {
//			//读入txt文件
		//固定目录
//			String pathname="H:\\大学资料\\大三\\大三（1）\\编译原理\\aaa.txt";
		//自行键入目录
			System.out.println("请输入程序名称");
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
		System.out.println("作者：Sakalaka");
		for(int j=0;j<e1.list.size();j++){
			e1.list.get(j).print();
		}
		for(int j=0;j<e1.errorList.size();j++){
			System.out.println(e1.errorList.get(j));
		}
	}

}
