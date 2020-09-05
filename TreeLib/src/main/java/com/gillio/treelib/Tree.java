package com.gillio.treelib;

import java.util.ArrayList;
import java.util.HashMap;

public class Tree {
        private final Node mainNode;
        private Node curentNode;
        private HashMap<String, ArrayList<String>> pathes = new HashMap<>();
        private int idCounter=0;

        public Tree(Node node) {
            this.mainNode = node;
            ArrayList<String> cachePath = new ArrayList<>();
            cachePath.add("MAIN");
            pathes.put("MAIN", cachePath);
        }
        public void renderKnot(Node knot){
            int childCount=knot.getChildCount(); //получаем количество дочерних элементов узла
            HashMap<Integer, Integer> countersSave = new HashMap<>(); //сохранение позиции counter'а для того что-бы при выходе из слоя можно было продолжитьс нужного элемента
            countersSave.put(knot.getLayer(), 0); //устанавливаем нулевое значение для слоя начального узла
            curentNode = knot; //устанавливаем объект класса для удобства
            for (int i=0; i<childCount; i++){ // цикл повторяется пока counter меньше количества дочернихэлементов в узле
                if (i++ != childCount) { // если следующее значение counter'а не равно числу дочерних элементов узла то
                    if (curentNode.getChildAt(i).getChildCount() == 0) { // если дочерний  элемент узла в котором мы находимся не содержит детей
                        curentNode.getChildAt(i).render(); // рендер элемента по id counter
                    } else { // иначе
                        //вход в узел на следующий слой
                        curentNode.getChildAt(i).render(); // рендер элемента по id counter
                        countersSave.put(curentNode.getLayer(), i++); // сохраняем следующее значение counter чтобы небыло вечного зацикливания
                        curentNode = curentNode.getChildAt(i); // устанавливаем текущий узел
                        childCount = curentNode.getChildCount(); // получаем и сохраняем количество детей в новом узле
                        i = 0; // устанавливаем counter в значение 0
                    }
                }else { // иначе
                    curentNode.getChildAt(i).render(); // рендер элемента по id counter
                    //выход из узла
                    if (curentNode.getLayer()-1 != 0) // если слой текущего узла не равен 0 то
                        curentNode = curentNode.getParent(); // получаем родителя текущего узла и задаём его как текущий тем самым переходя на слой выше
                    i = countersSave.get(curentNode.getLayer()); // вспоминаем значение counter и задаём его
                    childCount = curentNode.getChildCount(); // и снова получаем количесво дочерних элементов для текущего узла
                }
            }
        }
        public Node findNode(String id){
            ArrayList<String> cache = pathes.get(id); // присвоения списка пути
            curentNode=mainNode.getChildByName(id);// установка текущего узла как main
            for (int i=1; i<cache.size(); i++){
                if(curentNode!=null)
                    curentNode = curentNode.getChildByName(cache.get(i)); // установка текушего узла как полученного ребёнкапо имени из позиции листа пути
                else break;
            }
            return curentNode; //выдача нужного узла
        }
        public void addNode(String id, String parent, Node node){
            curentNode = findNode(parent);
            curentNode.addChild(node);
            ArrayList<String> cachePath = new ArrayList<>();
            cachePath.addAll(pathes.get(parent));
            cachePath.add(id);
            pathes.put("MAIN", cachePath);
        }
    }