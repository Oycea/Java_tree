package ru.ac.uniyar.mf.summer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Node {
    private String id;
    private String name;
    private List<Node> childs;

    // КОНСТРУКТОРЫ

    public Node(){
        this.id = "";
        this.name = "";
        this.childs = new ArrayList<>();
    }

    public Node(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.childs = new ArrayList<>();
    }

    // ГЕТТЕРЫ

    public String getID(){
        return id;
    }

    public String getName(){
        return name;
    }

    public List<Node> getChilds(){
        return childs;
    }

    // СЕТТЕРЫ

    public void setName(String newName){
        name = newName;
    }

    // МЕТОДЫ

    // Добавление узла
    public void addNode(Node child){
        childs.add(child);
    }

    // Удаление дочернего узла по его ID
    public void deleteChild(String nodeID){
        for(int i = 0; i< childs.size(); i++){
            if(nodeID.equals(childs.get(i).getID())) {
                childs.remove(childs.get(i));
            }
        }
    }

    // Удаление всех дочерних узлов
    public void deleteAllChilds(){
        childs.clear();
    }

    // Изменение доченего узла
    public void changeChild(String childID, String newName){
        for(int i = 0; i< childs.size(); i++){
            if(childID.equals(childs.get(i).getID())) {
                childs.get(i).setName(newName);
            }
        }
    }

    // Печать узла
    public void printNode(){
        System.out.println("Идентификатор: " + id + " Имя узла: " + name);
    }

    // Поиск дочернего узла по имени
    public boolean findChild(String childName){
        boolean flag = false;
        for(int i = 0; i< childs.size(); i++){
            if(childName.equals(childs.get(i).getName())) {
                flag = true;
            }
        }
        return flag;
    }

    // Печать узла со сдвигами
    public String indentedPrintNode(Node prNode, int level) {
        String nodeName = prNode.getName();
        for (int i = 0; i < level; i++){
            nodeName = ' ' + nodeName;
        }
        nodeName = nodeName + '\n';
        for(int i = 0; i < prNode.getChilds().size(); i++){
            nodeName = nodeName + indentedPrintNode(prNode.getChilds().get(i), level + 1);
        }
        return nodeName;
    }

    // Печать в файл
    public void printFile() throws IOException{
        String cnt = indentedPrintNode(this, 0);
        String path = "file.txt";

        Files.write(Paths.get(path), cnt.getBytes());
    }
}
