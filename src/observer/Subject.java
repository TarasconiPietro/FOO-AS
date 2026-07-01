package observer;

import java.util.ArrayList;

public class Subject {
    ArrayList<Observer> observers = new ArrayList<>();

    public void adicionarObserver(Observer observer){
        observers.add(observer);
    }

    public void removerObserver(Observer observer){
        observers.remove(observer);
    }

    public void notificar(String mensagem){
        for(Observer observer : observers){
            observer.atualizar(mensagem);
        }
    }
}
