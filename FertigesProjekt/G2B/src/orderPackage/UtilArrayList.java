/**@author Mario \n @since (datum)
 * @description my own Array List. I need it to calculate and generate TreeMaps*/
package orderPackage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;

import javax.swing.*;

public class UtilArrayList extends ArrayList{
    /**@param list the list that initializes the arrayList*/
    public void initList(UtilArrayList utilArrayList, String[] list){
        for (int i = 0; i < list.length; i++) {
            utilArrayList.add(list[i-1]);
        }
    }

    /**
     * adding the selected object from a combobox to the arraylist
     * @param comboBox
     * @param utilArrayList
     */
    //adding the selected object from a combobox to the arraylist
    public void addToOAL(JComboBox comboBox, UtilArrayList utilArrayList) {
        utilArrayList.add(comboBox.getSelectedItem().toString());
    }

    /**
     * generating a new treemap from the utilarraylist
     * @param utilArrayList
     * @return treeMap TreeMap
     */
    //generating a new treemap from the utilarraylist
    public TreeMap<String,Integer> toTreeMap(UtilArrayList utilArrayList){
        TreeMap<String,Integer> treeMap = new TreeMap<>();
        Iterator iterator = utilArrayList.iterator();
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

    /**
     *  generating a string from the utilarraylist
     * @param utilArrayList
     * @return string String
     */
    //generating a string from the utilarraylist
    public String ownToString(UtilArrayList utilArrayList){
        Iterator iterator = utilArrayList.iterator();
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
