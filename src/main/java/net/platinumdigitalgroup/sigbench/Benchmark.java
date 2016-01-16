package net.platinumdigitalgroup.sigbench;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * @author InvokeStatic
 */
public class Benchmark {

    private File file1, file2;
    private int sigSize;

    public Benchmark(File one, File two, int sigSize) {
        this.file1 = one;
        this.file2 = two;
        this.sigSize = sigSize;
    }


    public BenchmarkResult benchmark() throws IOException {
        /*
         * Benchmarking uses a Simplified Random Sampling technique.  Signature source position is picked randomly.
         * While we could probably use clustered or stratified sampling, SRS is probably good enough.
         */
        ArrayList<SignatureResult> signatures = new ArrayList<SignatureResult>();
        byte[] f1 = Files.readAllBytes(Paths.get(file1.getPath()));
        byte[] f2 = Files.readAllBytes(Paths.get(file2.getPath()));
        int searchesToRun = f1.length / (sigSize * 5);
        Random random = new Random();
        for(int i = 0; i < searchesToRun; i++) {
            int sourceDocPos = random.nextInt(f1.length - sigSize);
            byte[] sig = Arrays.copyOfRange(f1, sourceDocPos, sourceDocPos + sigSize);
            if(Arrays.binarySearch(sig, (byte)-52) != -1 || Arrays.binarySearch(sig, (byte)0) != -1) { continue; }
            signatures.add(new Signature(sig, sourceDocPos).findSignatureResult(f2));
        }
        return new BenchmarkResult(signatures);
    }

}
