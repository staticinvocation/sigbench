package net.platinumdigitalgroup.sigbench;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author InvokeStatic
 */
public class Signature {

    private final byte[] signatureBytes;
    private final int sourceDocPosition;

    /**
     * Creates a signature from an arbitrary source
     * @param sig the signature as a byte array
     */
    public Signature(byte[] sig) {
        signatureBytes = sig;
        this.sourceDocPosition = 0;
    }

    /**
     * Creates a signature from a source document and byte array
     * @param sig the signature as a byte array
     * @param sourceDocPosition position in the source document
     */
    public Signature(byte[] sig, int sourceDocPosition) {
        this.signatureBytes = sig;
        this.sourceDocPosition = sourceDocPosition;
    }

    /**
     * Gets the byte index for the signature
     * @return the position index in the source document
     */
    public int getSourceDocPosition() {
        return sourceDocPosition;
    }

    /**
     * Finds the signature in another byte array
     * @param other the byte array to search
     * @return the position of the found signature
     * @throws ArrayIndexOutOfBoundsException if signature is not found
     */
    public int findSignature(byte[] other) throws ArrayIndexOutOfBoundsException {
        if(signatureBytes.length > other.length) throw new ArrayIndexOutOfBoundsException();
        int result = Collections.indexOfSubList(Arrays.asList(signatureBytes), Arrays.asList(other));
        if(result < 0) throw new ArrayIndexOutOfBoundsException();
        return result;
    }

    public int indexOf(byte[] outerArray, byte[] smallerArray) {
        for(int i = 0; i < outerArray.length - smallerArray.length+1; ++i) {
            boolean found = true;
            for(int j = 0; j < smallerArray.length; ++j) {
                if (outerArray[i+j] != smallerArray[j]) {
                    found = false;
                    break;
                }
            }
            if (found) return i;
        }
        return -1;
    }

    /**
     * Finds the signature in another byte array
     * @param other the byte array to search
     * @return a SignatureResult instance
     */
    public SignatureResult findSignatureResult(byte[] other) {
        int found = indexOf(other, signatureBytes);
        return new SignatureResult(signatureBytes, sourceDocPosition, found >= 0, found);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (byte b : signatureBytes) {
            sb.append(String.format("%02X ", b));
        }
        return sb.toString();
    }

}
