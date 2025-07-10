package me.xiaoqi;

import com.google.ortools.Loader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class Main {
    private static Logger logger = LoggerFactory.getLogger(Main.class);

    // 加载 OR-Tools 相关内容
    static {
        Loader.loadNativeLibraries();
    }

    public static void main(String[] args) {
        logger.info("Main Application started.");
        SpringApplication.run(Main.class, args);
    }
}













