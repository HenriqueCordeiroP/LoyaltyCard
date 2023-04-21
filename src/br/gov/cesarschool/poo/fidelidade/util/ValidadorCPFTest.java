package br.gov.cesarschool.poo.fidelidade.util;
import org.junit.Assert;
import org.junit.Test;

public class ValidadorCPFTest {
	
	@Test
	public void testElevenChars() {
		Assert.assertTrue(ValidadorCPF.ehCpfValido("10034324410"));
		Assert.assertTrue(ValidadorCPF.ehCpfValido("62222597404"));
		
		Assert.assertFalse(ValidadorCPF.ehCpfValido("123"));
		
	}
	
	@Test
	public void testNotBlank() {
		Assert.assertTrue(ValidadorCPF.ehCpfValido("02269567412"));
		
		Assert.assertFalse(ValidadorCPF.ehCpfValido(""));
		
	}
	
	@Test
	public void testNotNull() {
		Assert.assertTrue(ValidadorCPF.ehCpfValido("10034324410"));
		
		Assert.assertFalse(ValidadorCPF.ehCpfValido(null));
		
	}
	
	@Test
	public void testNumericCPF() {
		Assert.assertTrue(ValidadorCPF.ehCpfValido("10034324410"));
		
		Assert.assertFalse(ValidadorCPF.ehCpfValido("10x343x404"));
		
	}
	
	@Test
	public void testValidVerificationDigits() {
		Assert.assertTrue(ValidadorCPF.ehCpfValido("10034324410"));
		Assert.assertTrue(ValidadorCPF.ehCpfValido("62222597404"));
	
	}
	
	@Test
	public void testInvalidVerificationDigits() {
		Assert.assertFalse(ValidadorCPF.ehCpfValido("10034324417"));
		Assert.assertFalse(ValidadorCPF.ehCpfValido("62222597499"));
		//dígitos de verificação de ambos os CPF estão incorretos para realização do teste
	}
}