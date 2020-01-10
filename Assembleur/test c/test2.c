int main(void) {
  int a = 1;
  int j = 0;
  int b = 52;
  int result;
  for (int i = 0; i <= 15;i++){
     j += b << a;
  }

  if (j > b+a ) {
	j = j*2;
  }else{
     j = j - 5;
  }
  result = (a-b) / j;
}