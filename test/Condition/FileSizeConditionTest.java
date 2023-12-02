/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Condition;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 39349
 */
public class FileSizeConditionTest {

  public void testCheckConditionWhenFileSizeExceedsMinSize() {
    try {
        // Specifica un percorso di file esistente e una dimensione minima
        String filePath = "C:\\Users\\39349\\Desktop\\Software\\Progetto\\IFTTT04\\file_esistente.txt";
        long minSize = 5; // in KB

        // Crea un'istanza di FileSizeCondition
        FileSizeCondition condition = new FileSizeCondition(filePath, minSize);

        // Verifica che la condizione sia soddisfatta (la dimensione del file supera la dimensione minima)
        assertTrue(condition.checkCondition());
    } catch (Exception e) {
        e.printStackTrace();
        fail("Eccezione durante l'esecuzione del test");
    }
}

    @Test
    public void testCheckConditionWhenFileSizeDoesNotExceedMinSize() {
        // Specifica un percorso di file esistente e una dimensione minima irragionevolmente grande
        String filePath = "C:\\Users\\39349\\Desktop\\Software\\Progetto\\IFTTT04\\file_esistente.txt";
        long minSize = 100000; // in KB (un valore molto grande)

        // Crea un'istanza di FileSizeCondition
        FileSizeCondition condition = new FileSizeCondition(filePath, minSize);

        // Verifica che la condizione non sia soddisfatta (la dimensione del file non supera la dimensione minima)
        assertFalse(condition.checkCondition());
    }

    @Test
    public void testCheckConditionWhenFileDoesNotExist() {
        // Specifica un percorso di file inesistente e una dimensione minima
        String filePath = "C:\\Users\\39349\\Desktop\\Software\\Progetto\\IFTTT04\\file_inesistente.txt";
        long minSize = 1; // in KB

        // Crea un'istanza di FileSizeCondition
        FileSizeCondition condition = new FileSizeCondition(filePath, minSize);

        // Verifica che la condizione non sia soddisfatta (il file non esiste)
        assertFalse(condition.checkCondition());
    }

    
}