package hard;

public class MedianFind {
/*
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * Find the median of the two sorted arrays. 
 * The overall run time complexity should be O(log (m+n)).
 * Example 1
 * nums1 = [1, 3], nums2 = [2]
 * The median is 2.0
 * Example 2
 * nums1 = [1, 2], nums2 = [3, 4]
 * The median is (2 + 3)/2 = 2.5
 * 找中位数，时间复杂度O(log(m+n))
 * 
 * */
	//TODO normal method:放在一个数组里，冒泡排序，取中位数
	//结果是超时，这个很简单，自己写吧
	//TODO medium method:下面这个思路是度受来的
	//首先，假设有n个数，我们只要取第n/2(设为k)个数，其实是不需要排序的
	//因为本身两个数组已经是排序过的了，只要挨个比较大小就好
	//这里设num1中的第p+1个或num2中的第q+1个为中位数，或二者的平均数是中位数
	//n为奇数时，要找到第(n/2+1)个数，n为偶数时，要找到第n/2和n/2+1个数
	//额外需要考虑的是，两个数组长度不相等的情况，代码如下
	//TODO hard method:二分法
	//首先，num1的中间假设第a个数，num2的中间假设为第b个数，我们要取的是第k个数
	//如果num1[a] < num2[b],那么我们认为第k个数不在num1的前a-1个数里
	//代码在最后的注释部分，是cpp的代码，还没看呢(.)
	
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int len = len1+len2;
        //begin
        if (len%2==0) {
            return (findm(nums1, nums2, len/2) + findm(nums1, nums2, len/2+1))/2.0;
        } else {
            return findm(nums1, nums2, len/2+1);
        }
    }
    public double findm(int[] nums1, int[] nums2, int size) {
        int p=0,q=0;
        for(int i=0;i < size-1;i++) {
            if (p>=nums1.length && q<nums2.length ) {
                q++;
            } else if (q>=nums2.length && p<nums1.length) {
                p++;
            } else if (nums1[p] > nums2[q]) {
                q++;
            } else {
                p++;
            }
        }
        if (p >= nums1.length) {
            return nums2[q];
        } else if (q >= nums2.length) {
            return nums1[p];
        } else {
            return Math.min(nums1[p], nums2[q]);
        }
    }
    

}
/*
double findKth(int a[], int m, int b[], int n, int k)  
{  
    //always assume that m is equal or smaller than n  
    if (m > n)  
        return findKth(b, n, a, m, k);  
    if (m == 0)  
        return b[k - 1];  
    if (k == 1)  
        return min(a[0], b[0]);  
    //divide k into two parts  
    int pa = min(k / 2, m), pb = k - pa;  
    if (a[pa - 1] < b[pb - 1])  
        return findKth(a + pa, m - pa, b, n, k - pa);  
    else if (a[pa - 1] > b[pb - 1])  
        return findKth(a, m, b + pb, n - pb, k - pb);  
    else  
        return a[pa - 1];  
}  
  
class Solution  
{  
public:  
    double findMedianSortedArrays(int A[], int m, int B[], int n)  
    {  
        int total = m + n;  
        if (total & 0x1)  
            return findKth(A, m, B, n, total / 2 + 1);  
        else  
            return (findKth(A, m, B, n, total / 2)  
                    + findKth(A, m, B, n, total / 2 + 1)) / 2;  
    }  
};  
*/