package org.zxx17.model2.netty.nio;

/**
 * MappedByteBuffer 可让文件直接在内存（堆外内存）修改,操作系统不需要拷贝一次.
 * 这的内存其实是计算机的直接内存，不属于JVM的运行时数据区，在堆中有一个指针DirectDataBuff指向堆外的直接内存--------《深入理解JVM》.
 * 这句话表达的意思基本正确，但存在一点不够精确的地方。在讨论Java内存模型时，特别是关于直接内存（Direct Memory）的概念，我们可以这样理解：
 *
 * 直接内存（Direct Memory）并不是Java虚拟机（JVM）运行时数据区的一部分，而是位于JVM外部，是操作系统管理的内存区域。
 * Java程序可以通过Java New I/O (NIO) 库中的DirectByteBuffer来访问这部分内存。当
 * 创建一个DirectByteBuffer时，JVM会分配一块直接内存，这块内存不是在Java堆上分配的，
 * 而是直接在物理内存中分配，因此它的访问速度通常比通过堆内存更快，尤其是在进行大量I/O操作的场景下。
 *
 * 关键点在于，“在堆中有一个指针DirectDataBuff指向堆外的直接内存”这一表述不够准确。
 * 实际上，当使用DirectByteBuffer时，JVM内部确实会有一个引用（可以理解为一个指针的抽象概念）指向这块直接内存，
 * 但这个引用本身是作为对象存储在Java堆中的。也就是说，DirectByteBuffer对象存在于Java堆里，它内部包含了一个直接指向堆外内存的引用或句柄，
 * 从而允许Java程序通过这个对象间接访问到直接内存区域。
 *
 * 总结来说，直接内存确实是计算机的直接内存，不属于JVM标准运行时数据区（如堆、栈、方法区等）的一部分，
 * 而DirectByteBuffer对象作为一个中介，其本身驻留在Java堆中，并通过内部的机制指向外部的直接内存区域。
 * 因此，更准确的说法应该是“通过堆中的DirectByteBuffer对象，Java程序能够访问到位于堆外的直接内存”。
 *
 * @author Xinxuan Zhuo
 * @version 1.0.0
 * @since 2024/6/26
 */
public class MappedByteBufferTest {
}
