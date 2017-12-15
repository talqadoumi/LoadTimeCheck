package com.unv.loadtime;

import org.bouncycastle.bcpg.ArmoredOutputStream;
import org.bouncycastle.jce.provider.BouncyCastleProvider;


import org.bouncycastle.openpgp.*;
import org.bouncycastle.openpgp.operator.jcajce.JcePGPDataEncryptorBuilder;
import org.bouncycastle.openpgp.operator.jcajce.JcePublicKeyKeyEncryptionMethodGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Iterator;

public class test {

   
    private static String csFilePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "UploadCSFile" + File.separator;
    private static String keyDir = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "KeysFile";

          static {
        Security.addProvider(new BouncyCastleProvider());
    }

	public static final void encryptFile(File outPutFileName,File inputFileName,File pgpFile,boolean integrityCheck,boolean asciiArmor){
              try {
                  FileInputStream KeyInputStream = new FileInputStream(pgpFile);
                  PGPPublicKey publicKey = readPublicKey(KeyInputStream);
                  OutputStream out = null;
                  if (asciiArmor) {
                      out = new ArmoredOutputStream(new FileOutputStream(outPutFileName));
                  } else {
                      out = new BufferedOutputStream(new FileOutputStream(outPutFileName));
                  }
                  JcePGPDataEncryptorBuilder PGPDataEncryptionBuilder = new JcePGPDataEncryptorBuilder(PGPEncryptedData.CAST5)
                          .setWithIntegrityPacket(integrityCheck)
                          .setSecureRandom(new SecureRandom())
                          .setProvider("BC");
                  PGPEncryptedDataGenerator encryptedDataGenerator = new PGPEncryptedDataGenerator(PGPDataEncryptionBuilder);
                  encryptedDataGenerator.addMethod(new JcePublicKeyKeyEncryptionMethodGenerator(publicKey).setProvider("BC"));
                  OutputStream dataGeneratorOut = encryptedDataGenerator.open(out, new byte[1 << 16]);
                  PGPCompressedDataGenerator compressDataGeneratorOut = new PGPCompressedDataGenerator(PGPCompressedData.ZIP);
                  PGPUtil.writeFileToLiteralData(compressDataGeneratorOut.open(dataGeneratorOut), PGPLiteralData.BINARY, inputFileName, new byte[1 << 16]);
                  compressDataGeneratorOut.close();
                  dataGeneratorOut.close();
                  out.close();
              } catch (FileNotFoundException e) {
                  e.printStackTrace();
              } catch (PGPException e) {
                  e.printStackTrace();
              } catch (IOException e) {
                  e.printStackTrace();
              } catch (Exception e) {
                  e.printStackTrace();
              }


     }

        private static PGPPublicKey readPublicKey(InputStream in) throws Exception {
       
         	  PGPPublicKey key =null ;

        	try{

             in = PGPUtil.getDecoderStream(in);

             PGPPublicKeyRingCollection pkCol = new PGPPublicKeyRingCollection(in);
             PGPPublicKeyRing pkRing = null;

              System.out.println("key ring size=" + pkCol.size());
             Iterator it = pkCol.getKeyRings();
             while (it.hasNext()) {
                 pkRing = (PGPPublicKeyRing) it.next();
                 Iterator pkIt = pkRing.getPublicKeys();
                 while (pkIt.hasNext()) 
                 {
                      key = (PGPPublicKey) pkIt.next();
                     System.out.println("Encryption key = " + key.isEncryptionKey() + ", Master key = " +
                             key.isMasterKey());
                     if (key.isEncryptionKey())
                         return key;
                 }
             }


         } catch (IOException e) {
                     e.printStackTrace();
                 } catch (PGPException e) {
                     e.printStackTrace();
                 }
 return key;
   }

//    public static PGPPublicKey readPublicKey(InputStream in){
//         in= PGPUtil.getDecoderStream(in)
//    }

    public void encryptFile(String csFile) {


        File textFile = new File(csFilePath+csFile);
        File outputFile = new File(csFilePath+"test.txt.pgp");
        File publicKeyFile = new File(keyDir+File.separator+"WeightWatchersInternationalWWHSPublickey.asc");

        try {
            encryptFile(outputFile, textFile, publicKeyFile, true, true);
            System.out.println("File: "+textFile.getAbsolutePath());
            System.out.println("Encrypted to: "+outputFile.getAbsolutePath());
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

}
