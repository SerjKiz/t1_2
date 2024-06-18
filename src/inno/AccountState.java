package inno;

import java.util.*;

public class AccountState {
    private final String ownerName;
    private final Map<CodeCur,Integer> lCurr;

    public AccountState(Account account){
        this.ownerName = account.getName();
        this.lCurr = account.getValues();
    }

    public String getName() {
        return ownerName;
    }

    public Map<CodeCur, Integer> getValues() {
        return new HashMap<CodeCur, Integer>(lCurr);
    }

    @Override
    public String toString() {
        return "AccountState{" +
                "name='" + ownerName + '\'' +
                ", values=" + lCurr +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountState)) return false;
        AccountState that = (AccountState) o;
        return getName().equals(that.getName()) && getValues().equals(that.getValues());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getValues());
    }
}
