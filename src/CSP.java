import java.util.Arrays;

class CSP {
    static int[] use = new int[10];

    static class Node {
        char letter;
        int value;
    }

    static int isValid(Node[] nodeList, int count, String s1, String s2, String s3) {
        int val1 = 0, val2 = 0, val3 = 0, m = 1, j, i;

        for (i = s1.length() - 1; i >= 0; i--) {
            char ch = s1.charAt(i);
            for (j = 0; j < count; j++)
                if (nodeList[j].letter == ch)
                    break;
            val1 += m * nodeList[j].value;
            m *= 10;
        }

        m = 1;
        for (i = s2.length() - 1; i >= 0; i--) {
            char ch = s2.charAt(i);
            for (j = 0; j < count; j++)
                if (nodeList[j].letter == ch)
                    break;
            val2 += m * nodeList[j].value;
            m *= 10;
        }

        m = 1;
        for (i = s3.length() - 1; i >= 0; i--) {
            char ch = s3.charAt(i);
            for (j = 0; j < count; j++)
                if (nodeList[j].letter == ch)
                    break;
            val3 += m * nodeList[j].value;
            m *= 10;
        }

        if (val3 == (val1 + val2))
            return 1;
        return 0;
    }

    static boolean permutation(int count, Node[] nodeList, int n, String s1, String s2, String s3) {
        if (n == count - 1) {
            for (int i = 0; i < 10; i++) {
                if (use[i] == 0) {
                    nodeList[n].value = i;
                    if (isValid(nodeList, count, s1, s2, s3) == 1) {
                        System.out.print("Solution found:");
                        for (int j = 0; j < count; j++)
                            System.out.print(" " + nodeList[j].letter + " = " + nodeList[j].value);
                        return true;
                    }
                }
            }
            return false;
        }

        for (int i = 0; i < 10; i++) {
            if (use[i] == 0) {
                nodeList[n].value = i;
                use[i] = 1;
                if (permutation(count, nodeList, n + 1, s1, s2, s3))
                    return true;
                use[i] = 0;
            }
        }
        return false;
    }

    static boolean solvePuzzle(String s1, String s2, String s3) {
        int uniqueChar = 0;
        int len1 = s1.length();
        int len2 = s2.length();
        int len3 = s3.length();

        int[] freq = new int[26];

        for (int i = 0; i < len1; i++)
            ++freq[s1.charAt(i) - 'A'];
        for (int i = 0; i < len2; i++)
            ++freq[s2.charAt(i) - 'A'];
        for (int i = 0; i < len3; i++)
            ++freq[s3.charAt(i) - 'A'];

        for (int i = 0; i < 26; i++)
            if (freq[i] > 0)
                uniqueChar++;

        if (uniqueChar > 10) {
            System.out.println("Invalid strings");
            return false;
        }

        Node[] nodeList = new Node[uniqueChar];
        for (int i = 0, j = 0; i < 26; i++) {
            if (freq[i] > 0) {
                nodeList[j] = new Node();
                nodeList[j].letter = (char) (i + 'A');
                j++;
            }
        }
        Arrays.fill(use, 0);
        return permutation(uniqueChar, nodeList, 0, s1, s2, s3);
    }

    public static void main(String[] args) {
        String s1 = "CROSS";
        String s2 = "ROADS";
        String s3 = "DANGER";

        if (!solvePuzzle(s1, s2, s3))
            System.out.println("No solution");
    }
}
