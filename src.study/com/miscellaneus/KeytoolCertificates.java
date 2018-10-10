package com.miscellaneus;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.util.Base64;
import java.util.Enumeration;

public class KeytoolCertificates {

	public static void main(String[] args) {
		oldKeystore();
	}

	private static void newKeystore() {
		try (FileInputStream is = new FileInputStream(new File("/home/sabaja/keystore.p12"))) {
			KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
			Key key = keystore.getKey("tomcat", "changeit".toCharArray());
			byte[] encodedKey = Base64.getEncoder().encode(key.getEncoded());
			System.out.println("key ? " + new String(encodedKey));
		} catch (NoSuchAlgorithmException | KeyStoreException | IOException | UnrecoverableKeyException e) {
			e.printStackTrace();
		}
	}
	
	private static void oldKeystore(){
		try (FileInputStream is = new FileInputStream(new File("/home/sabaja/.keystore"))) {
			KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
			String password = "changeit";
			keystore.load(is, password.toCharArray());
			Enumeration<?> enumeration = keystore.aliases();
			while (enumeration.hasMoreElements()) {
				String alias = (String) enumeration.nextElement();
				System.out.println("alias name: " + alias);
				Certificate certificate = keystore.getCertificate(alias);
				System.out.println(certificate.toString());
			}

		} catch (java.security.cert.CertificateException | NoSuchAlgorithmException | KeyStoreException
				| IOException e) {
			e.printStackTrace();
		}
	}
}