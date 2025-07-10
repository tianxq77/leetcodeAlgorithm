package me.xiaoqi.ortools;

import com.google.ortools.Loader;
import com.google.ortools.sat.*;

import java.util.Arrays;

public class SimpleCpModel {
    // 加载 OR-Tools 相关内容
    static {
        Loader.loadNativeLibraries();
    }
    public static void main(String[] args) {
        // 初始化 OR-Tools
        CpModel model = new CpModel();

        // 创建变量,可以是布尔类型，也可以是整数类型
         IntVar x = model.newBoolVar("x");
//        IntVar x = model.newIntVar(0, 10, "x");
        IntVar y = model.newIntVar(0, 10, "y");

        // 添加约束：x + y <= 10
        LinearExpr sum = LinearExpr.sum(Arrays.asList(x, y).toArray(new LinearArgument[0]));
        model.addLessThan(sum, 10);

        // 设置目标函数：最小化 x + y
        model.minimize(sum);

        // 创建求解器并求解模型
        CpSolver solver = new CpSolver();
        CpSolverStatus status = solver.solve(model);

        // 输出结果
        if (status == CpSolverStatus.OPTIMAL) {
            System.out.println("Solution found:");
            System.out.println("x = " + solver.value(x));
            System.out.println("y = " + solver.value(y));
        } else {
            System.out.println("No solution found.");
        }
    }
}
