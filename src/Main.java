import inno.*;

public class Main {
    public static void main(String[] args) {
        SaveAccount saveAccount;
        System.out.printf("Hello and welcome!");
        Account account = new Account("Эмма");
        account.addCurr(CodeCur.USD, 5);
        account.setName("Ватсон");
        saveAccount = account.saveAccount();
        account.addCurr(CodeCur.RUB, 99);
        saveAccount.load();

    }
}
