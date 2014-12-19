method ComputeFact(n : nat) returns (res : nat)
  requires n > 0;
  ensures res == fact(n);
 {
  res := 1;
  var i := 2;
  while (i <= n)
  invariant res == fact(i-1) && i <= n + 1;
  decreases n - i;
  {
    res := res * i;
    i := i + 1;
  }
 }
 
//fact(1) = 1
//fact(m) = m * fact(m - 1)
 function fact(m: int): int
  requires 0 <= m;
  ensures 1 <= fact(m);
{
  if m == 0 then 1 else fact(m-1) * m
}
