method Q3(n0 : int, m0 : int) returns (res : int)
ensures res == n0*m0;
{
  var n, m : int;
  res := 0;
  if (n0 >= 0) 
       {n,m := n0, m0;} 
  else 
       {n,m := -n0, -m0;}
  while (0 < n) 
  invariant n0 >= 0 ==> (res == m * (n0-n) && m == m0) &&
            n0 < 0 ==> (res == m*(-n0-n) && m == -m0) &&
            n >= 0;
  decreases n;
  { 
    res := res + m; 
    n := n - 1; 
  }
}
