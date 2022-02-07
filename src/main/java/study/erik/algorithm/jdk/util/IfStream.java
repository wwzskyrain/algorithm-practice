/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.jdk.util;

import lombok.Data;

import java.util.Random;

/**
 * @author yueyi
 * @version : IfStream.java, v 0.1 2022年01月22日 4:40 下午 yueyi Exp $
 */

/**
 * 手写builder，感觉自己的记忆里还不错
 */
@Data
public class IfStream implements IfStreamInterface {

    private Boolean  boolExpress;
    private Runnable trueRunnable;
    private Runnable falseRunnable;

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Boolean  boolExpress;
        private Runnable trueRunnable;
        private Runnable falseRunnable;

        public Builder boolExpress(Boolean boolExpress) {
            this.boolExpress = boolExpress;
            return this;
        }

        public Builder trueRunnable(Runnable trueRunnable) {
            this.trueRunnable = trueRunnable;
            return this;
        }

        public Builder falseRunnable(Runnable falseRunnable) {
            this.falseRunnable = falseRunnable;
            return this;
        }

        public IfStream build() {
            IfStream ifStream = new IfStream();
            ifStream.setBoolExpress(this.boolExpress);
            ifStream.setTrueRunnable(this.trueRunnable);
            ifStream.setFalseRunnable(this.falseRunnable);
            return ifStream;
        }
    }

    public static void main(String[] args) {

        Random random = new Random(System.currentTimeMillis());
        IfStream.builder()
                .boolExpress(random.nextBoolean())
                .trueRunnable(() -> System.out.println("true"))
                .falseRunnable(() -> System.out.println("false"))
                .build()
                .run();

    }
    
}