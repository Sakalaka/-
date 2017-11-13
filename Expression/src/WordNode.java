
public class WordNode {
	String typeID,word,type;
	Object value;
	WordNode(String typeID,String word,String value,String type){
		this.typeID=typeID;
		this.word=word;
		this.type=type;
		if(type.equals("double"))
			this.value=Double.parseDouble(value);
		else if(type.equals("int"))
			this.value=Integer.parseInt(value);
		else this.value=value;
	}
	void print(){
		System.out.println("("+typeID+","+word+","+value+","+type+")");
		//System.out.println(value.getClass());
	}
}
