package classesAndMethods;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;

import javax.swing.*;

public class OwnArrayList extends ArrayList{

    public void initList(OwnArrayList ownArrayList, String[] list){
        for (int i = 0; i < list.length; i++) {
            ownArrayList.add(list[i-1]);
        }
    }

    public void addToOAL(JComboBox comboBox, OwnArrayList ownArrayList) {
        ownArrayList.add(comboBox.getSelectedItem().toString());
    }

    public TreeMap<String,Integer> toTreeMap(OwnArrayList ownArrayList){
        TreeMap<String,Integer> treeMap = new TreeMap<>();
        Iterator iterator = ownArrayList.iterator();
        while(iterator.hasNext()){
            String key = iterator.next().toString();
            if(treeMap.get(key) == null){
                treeMap.put(key,1);
            }else{
                int value = treeMap.get(key) + 1;
                treeMap.put(key,value);
            }
        }
        return treeMap;
    }

    public String toStringTreeMap(TreeMap<String,Integer> treeMap){
        String string = "";
        return string;
    }

    public String ownToString(OwnArrayList ownArrayList){
        Iterator iterator = ownArrayList.iterator();
        String string = "";
        while (iterator.hasNext()){
            string += iterator.next();
            //avoiding the last comma
            if(iterator.hasNext()){
                string += ", ";
            }else{
                string += "";
            }
        }
        return string;
    }

    public static void main(String[] args) {

    }
}
