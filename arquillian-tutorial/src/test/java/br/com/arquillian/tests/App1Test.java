package br.com.arquillian.tests;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import arquillian.App1;

/**
 * A execução dos testes do Arquilian ocorre pela classe controladora
 * Arquillian. Ela é responsável pela chamada ao método estático anotado com
 * Deployment, despois invoca todos os métodos anotados com Test. Antes da
 * execução dos testes o Arquillian cria um arquivo de deploy, este arquivo deve
 * ser gerado pelo método anotado com Deployment e contém todas as classes que
 * serão testadas, assim como os arquivos de configuração e recursos
 * necessários.
 * 
 * @author mggoes
 *
 */
@RunWith(Arquillian.class)
public class App1Test {

	@Inject
	private App1 app1;

	private static String WAR_NAME;

	/**
	 * O Arquillian usa a biblioteca ShrinkWrap para criação de arquivos Java
	 * (JAR), WAR ou EAR que serão implantados no container para testes.
	 * 
	 * @return
	 */
	// @Deployment
	public static JavaArchive createJAR() {
		JavaArchive jar = ShrinkWrap.create(JavaArchive.class).addClass(App1.class).addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
		System.out.println(jar.toString(true));
		return jar;
	}

	@Deployment
	public static WebArchive createWAR() {
		WebArchive war = ShrinkWrap
				.create(WebArchive.class)
				.addAsWebInfResource(
						new File("C:\\Users\\mggoes\\Documents\\workspace - Financeiro\\arquillian-tutorial\\src\\main\\webapp\\WEB-INF\\faces-config.xml"))
				.addAsWebInfResource(new File("C:\\Users\\mggoes\\Documents\\workspace - Financeiro\\arquillian-tutorial\\src\\main\\webapp\\WEB-INF\\web.xml"))
				.addAsWebInfResource(
						new File("C:\\Users\\mggoes\\Documents\\workspace - Financeiro\\arquillian-tutorial\\src\\main\\webapp\\WEB-INF\\beans.xml"))
				.addPackage(Package.getPackage("arquillian"));
		System.out.println(war.toString(true));
		App1Test.WAR_NAME = war.getName();
		return war;
	}

	// @Test
	public void testCreateMessage() {
		Assert.assertNotNull(this.app1.createMessage());
	}

	@Test
	public void testShowMessage() throws IOException {
		Object content = new URL("http://localhost:8081/" + App1Test.WAR_NAME.replace(".war", "") + "/index.jsf").getContent();
		Assert.assertNotNull(content);
	}
}
