package org.zxx17.model2.netty.nio;


import java.nio.IntBuffer;

/**
 * .
 *
 * @author Xinxuan Zhuo
 * @version 1.0.0
 * @since 2024/6/25
 */
public class BasicBuffer {

    public static void main(String[] args) {
        IntBuffer intBuffer = IntBuffer.allocate(5);
        System.out.println(intBuffer.capacity());
        for (int i = 0; i < intBuffer.capacity(); i++) {
            intBuffer.put(i * 2);
        }

        /*
         * flip() do this:
         *         limit = position;
         *         position = 0;
         *         mark = -1;
         *         return this;
         */
        intBuffer.flip();
        while (intBuffer.hasRemaining()){
            System.out.println(intBuffer.get());
        }
    }


}
