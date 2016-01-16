package net.platinumdigitalgroup.sigbench;

/**
 * @author InvokeStatic
 */
public class SignatureResult {

    public final byte[] signature;
    public final int sourcePosition;
    public final boolean matched;
    public final int matchedPosition;

    public SignatureResult(byte[] sig, int sourcePos, boolean matched, int matchedPosition) {
        this.signature = sig;
        this.sourcePosition = sourcePos;
        this.matched = matched;
        this.matchedPosition = matchedPosition;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (byte b : signature) {
            sb.append(String.format("%02X ", b));
        }
        return sb.toString();
    }

}
