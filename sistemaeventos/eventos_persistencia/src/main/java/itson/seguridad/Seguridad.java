package itson.seguridad;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import itson.excepciones.SeguridadException;

import java.util.Base64;

public class Seguridad {
    
    private static final String LLAVE_SECRETA = "EventosItsonBDA2"; // Llave de 16 caracteres (128 bits)
    private static final String ALGORITMO = "AES";

    /**
     * Encripta una cadena usando el algoritmo AES.
     * 
     * @param textoPlano El texto a encriptar
     * @return El texto encriptado en formato Base64
     * @throws Exception Si ocurre un error durante la encriptación
     */
    public static String encriptar(String textoPlano) throws SeguridadException {
        try{
            SecretKeySpec key = new SecretKeySpec(LLAVE_SECRETA.getBytes(), ALGORITMO);
            Cipher cipher = Cipher.getInstance(ALGORITMO);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            
            byte[] textoEncriptado = cipher.doFinal(textoPlano.getBytes());
            return Base64.getEncoder().encodeToString(textoEncriptado);
        } catch (Exception e) {
            throw new SeguridadException("Error al encriptar el texto: " + e.getMessage());
        }
    }

    /**
     * Desencripta una cadena encriptada usando el algoritmo AES.
     * 
     * @param textoEncriptado El texto encriptado en formato Base64
     * @return El texto original desencriptado
     * @throws Exception Si ocurre un error durante la desencriptación
     */
    public static String desencriptar(String textoEncriptado) throws SeguridadException {
        try{
            SecretKeySpec key = new SecretKeySpec(LLAVE_SECRETA.getBytes(), ALGORITMO);
            Cipher cipher = Cipher.getInstance(ALGORITMO);
            cipher.init(Cipher.DECRYPT_MODE, key);
            
            byte[] textoDesencriptado = cipher.doFinal(Base64.getDecoder().decode(textoEncriptado));
            return new String(textoDesencriptado);
        } catch (Exception e) {
            throw new SeguridadException("Error al desencriptar el texto: " + e.getMessage());
        }
    }
}