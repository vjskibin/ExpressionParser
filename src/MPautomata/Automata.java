package MPautomata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Automata {
    private int startState; //start
    private List<Integer> finalStates; //final
    private Map<Integer, Map<Character, Integer>> mapStates = new HashMap<>(); //таблица переходов

    public Automata(int startState, List<Integer> finalStates){
        this.startState = startState;
        this.finalStates = finalStates;
    }

    public void addStep(int fromState, char ch, int toState)
    {
        Map<Character,Integer> tmp;
        if (!mapStates.containsKey(fromState)) {
            tmp = new HashMap<>();
            tmp.put(ch,toState);
            mapStates.put(fromState,tmp);

        }
        else
        {
            tmp = mapStates.get(fromState);
            tmp.put(ch,toState);
            mapStates.put(fromState, tmp);

        }
    }

    public void addStep(Integer fromState, List<Character> characters, Integer toState){
        for (Character ch : characters){
            addStep(fromState, ch, toState);
        }
    }

    public  Integer getNext(Integer fromState, Character ch){
        Map<Character, Integer> stateTransitions = mapStates.get(fromState);
        return stateTransitions.get(ch);
    }


    public boolean doesMatch(String str)
    {
        int state = startState;
        int pos = 0;
        while (pos < str.length())
        {
            char curr = str.charAt(pos);
            Integer nextState = getNext(state, curr);
            if(nextState == null) break;
            state = nextState;
            pos++;
        }
        if (finalStates.contains(state) && pos == str.length()){
            return true;
        } else {
            return false;
        }
    }

    public void setStartState(int startState) {
        this.startState = startState;
    }
}
