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
public class FileExistenceConditionTest {

    @Test
    public void testCheckConditionWhenFileExists() {
        // Specifica un percorso di cartella esistente e un nome di file esistente
        String folderPath = "C:\\Users\\39349\\Desktop\\Software\\Progetto\\IFTTT04";
        String fileName = "file_esistente.txt";

        // Crea un'istanza di FileExistenceCondition
        FileExistenceCondition condition = new FileExistenceCondition(folderPath, fileName);

        // Verifica che la condizione sia soddisfatta (file esistente)
        assertTrue(condition.checkCondition());
    }

    @Test
    public void testCheckConditionWhenFileDoesNotExist() {
        // Specifica un percorso di cartella esistente e un nome di file inesistente
        String folderPath = "C:\\Users\\39349\\Desktop\\Software\\Progetto\\IFTTT04";
        String fileName = "file_inesistente.txt";

        // Crea un'istanza di FileExistenceCondition
        FileExistenceCondition condition = new FileExistenceCondition(folderPath, fileName);

        // Verifica che la condizione non sia soddisfatta (file inesistente)
        assertFalse(condition.checkCondition());
    }

}