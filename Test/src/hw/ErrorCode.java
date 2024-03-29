package hw;

/**
 * 信号传播过程中会出现一些误码，不同的数字表示不同的误码ID，
 * 取值范围1～65535，用一个数组记录误码出现的情况。
 * 每个误码出现的次数代表误码频度，
 * 请找出记录中包含频度最高误码的最小子数组长度。
 * 输入描述：
 *   误码总数目：取值范围为0~255，取值为0表示没有误码的情况。
 *   误码出现频率数组：误码ID范围为1～65535，数组长度为1~1000。
 * 输出描述：
 *   包含频率最高的误码最小子数组长度。
 * 示例1
 * 输入：5
 *             1 2 2 4 1
 * 输出：2
 * 示例2
 * 输入：7
 *            1 2 2 4 2 1 1
 * 输出：4
 * 说明：频率最高的数字是1和2，最短的子数组是［2 2 4 2］
 */
public class ErrorCode {
}
