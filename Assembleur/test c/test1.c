int main(void) {
  int a = 2;
  int j = 0;
  int b = 52;
  for (int i = 0; i <= a;i++){
     j  += b * i;
  }
  if (j < 20) {
	j = j*2;
  }else{
     j = j -15;
  }
}