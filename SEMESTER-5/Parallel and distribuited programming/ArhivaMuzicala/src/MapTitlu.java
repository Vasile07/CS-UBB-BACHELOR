import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapTitlu {
    private final Map<Character, List<InregistrareMuzicala>> map;
    private final Object lock = new Object();

    public MapTitlu() {
        this.map = new HashMap<>();
    }


    public void addToMap(InregistrareMuzicala inregistrareMuzicala){
        synchronized (lock){
            Character firstLetter = inregistrareMuzicala.getTitlu().charAt(0);
            if(!map.containsKey(firstLetter))
                map.put(firstLetter,new ArrayList<>());
            map.get(firstLetter).add(inregistrareMuzicala);
        }
    }

    public Map<Character, List<InregistrareMuzicala>> getMap(){
        synchronized (lock){
            return new HashMap<>(map);
        }
    }

}
