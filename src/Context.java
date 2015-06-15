import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JList;


public class Context {
	
	public List<Object> contextOptions;
	
	public Context(){
		contextOptions = new ArrayList<Object>();
		for(int i=0; i<Main.searcher.Context.size(); i++){		
			contextOptions.add(Main.searcher.Context.get(i));
		}	
	}
	
	public String[] getContextList(){
		String[] contextString =new String [contextOptions.size()];
		for (int i = 0; i<contextOptions.size(); i++){
			contextString[i] = (String)contextOptions.get(i);
		}
		return contextString;
	}
	
	public void addNewContext(Object context){
		contextOptions.add(context);
		Main.searcher.addNewContext((String)context);
	}
	
	public void removeContext(Object context){
		if(contextOptions.remove(context))
			;
		else{
			//Mostar que no existe el contexto
		}
	}
	

}
