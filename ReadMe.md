# 1.目录


# 2.MurmurHash
1.  这是一个散列算法，是hash函数的一种吧；
2.  广义的hash函数，就是一个map吧
3.  散列函数的要求
    1.  Distribution – Passing Chi-Square distribution tests for all keysets and bucket sizes.
        分布要求
    2.  avalanche Behaviour – Maximum bias of 0.5%.
        
    3.  Collision Resistance – No collisions possible for 4-byte keys, no small (1- to 7-bit) differentials.
    
    4.  Simple and Fast – As few instructions as possible, as fast as possible while remaining statistically strong.
    
4.  