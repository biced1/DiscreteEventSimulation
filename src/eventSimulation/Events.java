package eventSimulation;

import java.util.List;
import java.util.ArrayList;

public class Events {
	private List<Event> events = new ArrayList<Event>();
	
	public void addEvent(Event e){
		int position = 0;
		for(int x = 1; x < events.size(); x++){
			if(e.getTicks() > events.get(x).getTicks()){
				position = x;
			}
		}
		events.add(position, e);
	}
	
	public void fireNextEvent(){
		if(!events.isEmpty()){
			Event e = events.get(0);
			e.fire();
			events.remove(e);
		}
	}
}
