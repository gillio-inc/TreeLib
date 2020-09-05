package com.gillio.treelib;


import java.util.ArrayList;

public abstract class Node {
    private String name;
    private int layer, globalId;
    private Node parent;
    private ArrayList<Node> childs = new ArrayList<>();

    Node(String name, int count){
        globalId=count;
        if (parent!=null) {
            layer = parent.getLayer()+1;
        }else{
            layer = 0;
        }
    }

    public int getGlobalId() {
        return globalId;
    }
    public Node getParent() {
        return parent;
    }
    public int getLayer() {
        return layer;
    }
    public Node getChildAt(int localId) {
        return childs.get(localId);
    }
    public void addChild(Node node){
        node.setParent(this);
        childs.add(node);
    }
    public void changeParent(Node newParent){
        newParent.addChild(this);
        parent.removeChild(this);
    }
    public void removeChild(Node rm){
        childs.remove(rm);
    }
    private void setParent(Node p){
        parent = p;
    }
    public String getName() {
        return name;
    }
    public Node getChildByName(String name) {
        Node curentNode=null;
        for (int i=0; i<childs.size(); i++){
            if(childs.get(i).getName().equals(name)){
                curentNode = childs.get(i);
                break;
            }
        }
        return curentNode;
    }
    public int getChildCount(){
        return childs.size();
    }
    public abstract void render();
}
