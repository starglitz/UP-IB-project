package com.example.Clinic.security.services;

import com.example.Clinic.security.support.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;

public class AsymmetricEncription {
    private static final String KEY_STORE_FILE = "./src/main/resources/rsaKeystore.jks";
    private PrivateKey privateKey;
    private PublicKey publicKey;
    private String data;


    public AsymmetricEncription(String data) {
        //Postavljamo providera, jer treba za RSA Enkripciji/Dekripciju
        Security.addProvider(new BouncyCastleProvider());
        this.data = data;
    }

    private Boolean readCertificate() {
        try {
            // kreiramo instancu KeyStore
            KeyStore ks = KeyStore.getInstance("JKS", "SUN");
            // ucitavamo podatke
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(KEY_STORE_FILE));
            ks.load(in, "12345".toCharArray());

            if (ks.isKeyEntry("tim2")) {
                Certificate cert = ks.getCertificate("tim2");
                this.publicKey = cert.getPublicKey();
                this.privateKey = (PrivateKey) ks.getKey("tim2", "tim2".toCharArray());
                return true;
            } else
                return false;

        } catch (KeyStoreException e) {
            e.printStackTrace();
            return false;
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
            return false;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return false;
        } catch (CertificateException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String encrypt() {
        if(readCertificate()) {
            try {


                //kriptovanje poruke javnim kljucem
                Cipher rsaCipherEnc = Cipher.getInstance("RSA/ECB/PKCS1Padding", "BC");
                //inicijalizacija za kriptovanje,
                //kod asimetricnog kriptuje se javnim kljucem, a dekriptuje privatnim
                rsaCipherEnc.init(Cipher.ENCRYPT_MODE, publicKey);

                //kriptovanje
                byte[] ciphertext = rsaCipherEnc.doFinal(data.getBytes());

                return Base64.encodeToString(ciphertext);


            } catch (InvalidKeyException e) {
                e.printStackTrace();
                return null;
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                return null;
            } catch (NoSuchProviderException e) {
                e.printStackTrace();
                return null;
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
                return null;
            } catch (IllegalStateException e) {
                e.printStackTrace();
                return null;
            } catch (IllegalBlockSizeException e) {
                e.printStackTrace();
                return null;
            } catch (BadPaddingException e) {
                e.printStackTrace();
                return null;
            }
        }else {
            return null;
        }

    }

    public String decrypt() {
        if(readCertificate()) {
            try {


                //dekriptovanje poruke privatnim klucem
                Cipher rsaCipherDec = Cipher.getInstance("RSA/ECB/PKCS1Padding", "BC");
                //inicijalizacija za dekriptovanje
                //dekriptovanje se vrsi privatnim kljucem
                rsaCipherDec.init(Cipher.DECRYPT_MODE, privateKey);

                //dekriptovanje
                byte[] receivedTxt = rsaCipherDec.doFinal(Base64.decode(data));

                return new String(receivedTxt);

            } catch (InvalidKeyException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (NoSuchProviderException e) {
                e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IllegalBlockSizeException e) {
                e.printStackTrace();
            } catch (BadPaddingException e) {
                e.printStackTrace();
            }
        }
        return null;

    }


}
