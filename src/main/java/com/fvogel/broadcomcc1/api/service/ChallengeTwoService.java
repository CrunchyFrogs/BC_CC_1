package com.fvogel.broadcomcc1.api.service;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChallengeTwoService {
    public void findSums(int target, int currentSum, int base, List<List<Integer>> output, List<Integer> workingList) {

        if (target == currentSum) output.add(new ArrayList<>(workingList));

        for (int i = base; i < target; ++i) {
            int sumToNum = currentSum + i;
            if (sumToNum <= target) {
                workingList.add(i);
                findSums(target, sumToNum, i, output, workingList);
                workingList.remove(workingList.size() - 1);
            } else {
                return;
            }
        }
    }

    public String runCC2(int number) {
        int product;
        int bestProduct = 0;
        List<Integer> bestSolution = null;
        List<List<Integer>> solutions = new ArrayList<>();
        long startTime = System.nanoTime();

        if (number < 2) return "0";

        findSums(number, 0, 1, solutions, new ArrayList<>());
        System.out.printf("Computed in %sms\n", (System.nanoTime() - startTime) / 1000000.0);

        for (List<Integer> solution : solutions) {
            product = 1;
            for (Integer i : solution)
                product *= i;

            if (product > bestProduct) {
                bestProduct = product;
                bestSolution = new ArrayList<>(solution);
            }

            System.out.println(solution);
        }
        return "Best solution: " + bestSolution + " : " + bestProduct;
    }
}