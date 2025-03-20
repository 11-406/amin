import java.util.Scanner;
public static void main(String[] args){
	
public static float stepen(int a int b){
	float stepen = 0;
	for(int i = 1;i <= b; i++){
		b *= b;
		return stepen;
	}
	float p = stepen(a,b/2);
	if (b%2==0){
		return p*p;
	} else {
		return p*p*a;
	    }
	}
}