package p1940;

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 재료 개수 입력
        int N = sc.nextInt();
        // 갑옷 제작에 필요한 재료 고유번호의 합 입력
        int M = sc.nextInt();
        // 재료 고유번호 입력
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        // 계수 정렬 원리 활용
        int k = 100000; // 재료 고유번호의 최대값
        int[] cnt = new int[k];
        int[] cnt2 = new int[k];
        for (int i = 0; i < N; i++) {
            cnt[arr[i]]++;
            cnt2[arr[i]]++;
        }
        cnt[0]--;
        for (int i = 1; i < k; i++) {
            cnt[i] += cnt[i - 1];
        }
        int sortedArr[] = new int[N];
        for (int i = N - 1; i >= 0; i--) {
            sortedArr[cnt[arr[i]]] = arr[i];
            cnt[arr[i]]--;
        }

        int successCount = 0;
        boolean skipSame = false;
        for (int i = 0; i < N - 1; i++) {
            int target = M - sortedArr[i];
            if (sortedArr[i] > target || (target == sortedArr[i] && skipSame)) {
                break; // 이미 확인한 조합이므로 건너뜀
            }

            if (target >= 0 && target < k) {
                int count = cnt2[target];
                if (sortedArr[i] == target && !skipSame) {
                    skipSame = true;
                    successCount += (int) Math.floor((double) count / 2);
                    continue;
                }
                successCount += count;
            }
        }

        System.out.println(successCount);
    }
}
