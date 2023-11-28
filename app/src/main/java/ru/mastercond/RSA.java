package ru.mastercond;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Provider;
import java.security.Security;

import java.util.Arrays;
import java.util.Set;
import android.util.Log;
import android.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;



public class RSA {

KeyPairGenerator kpg;
KeyPair kp;
PublicKey publicKey;
PrivateKey privateKey;
byte[] encryptedBytes, decryptedBytes, decoded64;
Cipher cipher, cipher1;
String encrypted, decrypted;

public String Encrypt (String plain, PublicKey pubkey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException 
{

    cipher = Cipher.getInstance("RSA"); //ANDROID API 18+
    cipher.init(Cipher.ENCRYPT_MODE, pubkey);
    encryptedBytes = cipher.doFinal(plain.getBytes());
    int flags=Base64.NO_WRAP;
    encrypted = new String (Base64.encodeToString(encryptedBytes, flags));
    return encrypted;

}

public String Decrypt (String result, PrivateKey privkey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException 
{           

    cipher1=Cipher.getInstance("RSA");
    cipher1.init(Cipher.DECRYPT_MODE, privkey);
    int flags=Base64.NO_WRAP;
    decoded64 = Base64.decode(result, flags);
    decryptedBytes = cipher1.doFinal(decoded64);
    decrypted = new String(decryptedBytes);
    return decrypted;

}



public void getSecurityProviders() {

    String result = "";

    Provider[] providers = Security.getProviders();

    for (Provider provider : providers) {
  Log.d("ru.mastercond","Провайдер: "+provider.getName());
  Set<Provider.Service> services = provider.getServices();
  for (Provider.Service service : services) {
    Log.d("ru.mastercond","  Алгоритм: "+service.getAlgorithm());
  }
 } }
  
  public void GenerateCryptoKeys() throws Exception {
    //String password = "password";

    KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
    keyPairGenerator.initialize(2048);
    KeyPair keyPair = keyPairGenerator.genKeyPair();
    String publicKeyFilename = "publickeybm";
    byte[] publicKeyBytes = keyPair.getPublic().getEncoded();
    //MainActivity rootActivity = (MainActivity)getActivity(); 
    //rootActivity.saveKey(publicKeyBytes, publicKeyFilename);

    

    String privateKeyFilename = "privatekeybm";
    byte[] privateKeyBytes = keyPair.getPrivate().getEncoded();
    //byte[] encryptedPrivateKeyBytes = passwordEncrypt(password.toCharArray(), privateKeyBytes);
    //rootActivity.saveKey(privateKeyBytes, privateKeyFilename);
    
    
  }
  
  
  
}       


