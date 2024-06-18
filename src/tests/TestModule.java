package tests;

import inno.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.*;

public class TestModule {

        @Test
        public void testAccountConstructor(){
            Assertions.assertThrows(NullPointerException.class, ()->new Account(""));
            Account acc = new Account("Эмма");
            Assertions.assertEquals("Эмма", acc.getName());
        }
        @Test
        public void testAccountSetters(){
            Account acc = new Account("Эмма");
            acc.setName("Борисовна");
            Assertions.assertEquals("Борисовна", acc.getName());
            acc.addCurr(CodeCur.USD, 5);
            Assertions.assertEquals(acc.getValues().get(CodeCur.USD), 5);
            Assertions.assertThrows(IllegalArgumentException.class
                    , ()-> acc.addCurr(CodeCur.USD, -10));
            Assertions.assertThrows(NullPointerException.class
                    , ()-> acc.addCurr(CodeCur.USD, null));
        }
        @Test
        public void testAccountUndo(){
            Account account = new Account("Эмма");
            account.setName("Вотсон");
            account.addCurr(CodeCur.RUB, 100);
            account.addCurr(CodeCur.RUB, 200);
            account.undo();
            account.undo();
            account.undo();
            account.undo();
            Assertions.assertThrows(IllegalArgumentException.class
                    , account::undo);
        }
        @Test
        public void testAccountRestore(){
            Account acc = new Account("Эмма");
            acc.addCurr(CodeCur.USD, 100);
            acc.addCurr(CodeCur.RUB, 1);
            HashMap<CodeCur, Integer> values = acc.getValues();
            acc.save();
            acc.setName("Zina");
            acc.addCurr(CodeCur.RUB, 200);
            acc.restore();
            Assertions.assertEquals(acc.getName(), "Zina");
            Assertions.assertEquals(acc.getValues().get(CodeCur.USD), 5);
            Assertions.assertEquals(acc.getValues(), values);
        }

    }


