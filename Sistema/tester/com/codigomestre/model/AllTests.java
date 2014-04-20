package com.codigomestre.model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ UsuarioDAOTest.class, ConexaoTest.class, ServidorSuporteTest.class })
public class AllTests {

}