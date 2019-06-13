package cn.xiewei.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class ForkJoinTest {
    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        // 生成一个计算任务，负责计算1+2+3+4
        CountTask countTask = new CountTask(1, 10);
        // 执行一个任务
        Future<Integer> result = forkJoinPool.submit(countTask);
        try {
            System.out.println(result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

class CountTask extends RecursiveTask<Integer> {
    private final static int THRESHOLD = 2;
    private int start;
    private int end;

    CountTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override // 重写接口抽象方法
    protected Integer compute() {
        int sum = 0;
        // 如果，首尾相减，如果任务大于阈值，就分裂成两个子任务计算
        boolean flag = (start - end) <= THRESHOLD;

        if (flag) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            int middle = (start + end) / 2;
            CountTask countTaskLeft = new CountTask(start, middle);// 递归调用，不断往下分
            CountTask countTaskRight = new CountTask(middle + 1, end);
            // 执行子任务
            countTaskLeft.fork();
            countTaskRight.fork();
            // 等待子任务执行完，并得到其结果
            int leftResult = countTaskLeft.join();
            int rightResult = countTaskRight.join();
            // 合并子任务
            sum = leftResult + rightResult;
        }
        return sum;
    }
}
