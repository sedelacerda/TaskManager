import java.io.Serializable;

public enum State {
	ACTIVE,
	CLOSED,
	FROZEN;
	
	/*public static State valueOf(String value){
        if(value.equalsIgnoreCase(ROCK.toString()))
            return Gesture.ROCK;
        else if(value.equalsIgnoreCase(PAPER.toString()))
            return Gesture.PAPER;
        else if(value.equalsIgnoreCase(SCISSORS.toString()))
            return Gesture.SCISSORS;
        else
            return null;
	}*/
}
