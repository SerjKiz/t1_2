package inno;

import java.util.*;

public class Account {
    private String ownerName;
    private Map<CodeCur,Integer> lCurr = new HashMap<>();
    private Deque<Command> saves = new ArrayDeque<>();
    private AccountState state;

    public Account(String ownerNameIn)
    {
        setName(ownerNameIn) ;
    }

    public String getName() {
        return ownerName;
    }

    public void setName(String ownerNameIn) {
        if (ownerNameIn == null || ownerNameIn.isEmpty() ) {
            throw new NullPointerException();
        }
        String tmp = Account.this.ownerName;
        saves.push(()->Account.this.ownerName=tmp);
        this.ownerName = ownerNameIn;
    }

    public HashMap<CodeCur, Integer> getValues() {
        return new HashMap<>(lCurr);
    }

    public void addCurr(CodeCur currency, Integer amount) {
        if (currency == null) {
            throw new IllegalArgumentException();
        }
        if (amount < 0) {
            throw new IllegalArgumentException("Сумма не может быть меньше нуля");
        }

        if (lCurr.containsKey(currency))
            saves.push(() -> Account.this.lCurr.put(currency, lCurr.get(currency)));
        else
            saves.push(() -> Account.this.lCurr.remove(currency));

        lCurr.put(currency, amount);
    }

    public void save(){
        this.state = new AccountState(this);
    }

    public void restore(){
        this.ownerName = this.state.getName();
        this.lCurr = this.state.getValues();
        this.saves = new ArrayDeque<>();
    }

    public SaveAccount saveAccount(){
        return new AccSave();
    }

    public void undo(){
        if (saves.size() == 0){
            throw new IllegalArgumentException("Нет предыдущих состояний");
        }
        saves.pop().make();
    }

    private class AccSave implements SaveAccount {
        private String ownerName = Account.this.ownerName;
        private final Map<CodeCur,Integer> curr = new HashMap<>(Account.this.lCurr);


        public void load(){
            Account.this.ownerName = ownerName;
            Account.this.lCurr.clear();
            Account.this.lCurr.putAll(lCurr);
        }

    }


    interface Command {
        void make();
    }



}

