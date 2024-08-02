class SubstringMatch {

    static int countFreq(String pat, String txt) {
        int M = pat.length();
        int N = txt.length();
        int res = 0;
        // Loop to slide pat[] one by one
        for (int i = 0; i <= N - M; i++) {
            // Check for pattern match at current index
            int j;
            for (j = 0; j < M; j++) {
                if (txt.charAt(i + j) != pat.charAt(j)) {
                    break;
                }
            }
            // If pat[0...M-1] = txt[i, i+1, ...i+M-1]
            if (j == M) {
                res++;
            }
        }
        return res;
    } 

    public static void main(String[] args) {
        String txt = "dhimanman";
        String pat = "man";
        System.out.println(countFreq(pat, txt));
    }
}
