package code;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Cheyukbok {
    public Cheyukbok() {
    }

    // 도난당한 학생 배열 lost
    // 여벌 옷을 가져온 학생이 reverve
    // 여벌 옷을 빌려줄 수 있는 사람 x-1 or x+1
    // 2 <= n <= 30
    // 1 <= lost.size() <= n
    // 여별이 있지만 도난 당한 경우, 빌려줄 수 없다.

    /**
     * 내 풀이
     * @param n
     * @param lost
     * @param reserve
     * @return
     */
    public int solution(int n, int[] lost, int[] reserve) {

        // 리스트 만들기
        List<Integer> lostList = IntStream.of(lost)
                .boxed().collect(Collectors.toList());
        List<Integer> reserveList = IntStream.of(reserve)
                .boxed().collect(Collectors.toList());

        // 각 리스트의 중복 제거
        lostList.removeAll(IntStream.of(reserve)
                .boxed().collect(Collectors.toList()));
        reserveList.removeAll(IntStream.of(lost)
                .boxed().collect(Collectors.toList()));

        // 빌려주는게 가능한 학생의 최종 목록
        HashSet<Integer> ableReserve = new HashSet<>();
        // 빌려주는게 가능한 학생이 있는지 체크
        for(int i:lostList) {
            // 앞 번호 학생
            int preNum = i - 1;
            boolean isPre = reserveList.contains(preNum);
            if (isPre) {
                ableReserve.add(preNum);
            }
            // 뒷번호 학생
            int nextNum = i + 1;
            boolean isNext = reserveList.contains(nextNum);
            if (isNext) {
                ableReserve.add(nextNum);
            }

        }

        // 아예 체육복이 없는 학생 - 최종 빌려줄 수 있는 학생
        int nothing = (lostList.size() - ableReserve.size());
        // 빌려주는 학생이 더 많은 경우에 n
        return nothing > 0 ? n - nothing : n;
    }

    /**
     * 다른 사람 풀이
     * @param n
     * @param lost
     * @param reserve
     * @return
     */
    public int solution1(int n, int[] lost, int[] reserve) {
        int[] people = new int[n];
        int answer = n;

        for (int l : lost)
            people[l-1]--;
        for (int r : reserve)
            people[r-1]++;

        for (int i = 0; i < people.length; i++) {
            if(people[i] == -1) {
                if(i-1>=0 && people[i-1] == 1) {
                    people[i]++;
                    people[i-1]--;
                }else if(i+1< people.length && people[i+1] == 1) {
                    people[i]++;
                    people[i+1]--;
                }else
                    answer--;
            }
        }
        return answer;
    }


}
