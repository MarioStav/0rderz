package classesAndMethods;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;

public class OwnArrayList extends ArrayList{

    public void initList(OwnArrayList ownArrayList, String[] list){
        for (int i = 0; i < list.length; i++) {
            ownArrayList.add(list[i]);
        }
    }

    public void addToOAL(JComboBox comboBox, OwnArrayList ownArrayList) {
        ownArrayList.add(comboBox.getSelectedItem().toString());
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
