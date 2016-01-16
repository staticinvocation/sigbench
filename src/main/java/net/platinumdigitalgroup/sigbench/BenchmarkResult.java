package net.platinumdigitalgroup.sigbench;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * @author InvokeStatic
 */
public class BenchmarkResult {

    public final int score;
    public int matched;
    public final ArrayList<SignatureResult> signatures;

    public BenchmarkResult(ArrayList<SignatureResult> results) {
        this.signatures = results;
        this.score = calculateScore(results);
        for(SignatureResult r : results) {
            if (r.matched) {
                matched++;
            }
        }
    }

    public static int calculateScore(List<SignatureResult> resultList) {
        int count = resultList.size();
        if(count == 0) return 100;
        int matched = 0;
        for(SignatureResult r : resultList) {
            if (r.matched) {
                matched++;
            }
        }
        return 100 - (int)Math.floor((matched * 100 / count));
    }

}
