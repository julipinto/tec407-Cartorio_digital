package util;

import JPAPersistence.DAO;
import facade.FacadeBack;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class Main {

    public static void main(String[] argv) throws Exception {
        String algorithm = "DSA"; // or RSA, DH, etc.
        DAO dao = new DAO();
        FacadeBack facadeb = FacadeBack.getInstance();
        Cript cript = new Cript();
        
        
        
        // Generate a 1024-bit Digital Signature Algorithm (DSA) key pair
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance(algorithm);
        keyGen.initialize(512);
        KeyPair keypair = keyGen.genKeyPair();
        PrivateKey privateKey = keypair.getPrivate();
        PublicKey publicKey = keypair.getPublic();

        byte[] privateKeyBytes = privateKey.getEncoded();
        byte[] publicKeyBytes = publicKey.getEncoded();

        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        PrivateKey privateKey2 = keyFactory.generatePrivate(privateKeySpec);

        EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
        PublicKey publicKey2 = keyFactory.generatePublic(publicKeySpec);

        // The orginal and new keys are the same
        boolean same = privateKey.equals(privateKey2);
        same = publicKey.equals(publicKey2);
    }

}