/*replace the vowels in the given string with the number

multiply the index of the vowel with 5 , then add the consecutive odd integers where they are <= result(which we get after multiply the index with 5). now minimize the result into a single digit until it does by adding the sum of its digits. if there is no vowel in the given string then return -1 . 

input string : program

output : pr7gr7m

explanation : 'o' is at index 2 so , multiply index with 5. we get 10.
               now add the consecutive odd integers up to 10 i.e 1+3+5+7+9=25
               since 25 is not a single digit add the digits i.e 2+5=7.
               now place 7 at vowel 'o' index position i.e at 2.
               similarly for next vowel in the given string do the same procedure.
               */


import java.util.*;

class vowel{
    static int sum = 0 , result = 0 , leng = 0 , counter = 0;
    public static int digSum(int n)
    {
           while (n > 0 || sum > 9)
           {
               if (n==0){
                   n = sum;
                   sum = 0;
               }
               sum += n%10;
               n = n/10;
           }
        return sum;
    }

    public static int consOdd(int hi)
    {
        for(int j = 1 ; j <= hi ; j++)
        {
            if((j%2)!=0)
                leng+= j;
        }
        return leng;
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String sr = new String();
        sr = sc.nextLine();
        //sr = sr.toLowerCase();
        StringBuilder sb = new StringBuilder(sr);
        StringBuilder sb1 = new StringBuilder("");
        sc.close();
        for(int i = 0 ; i<sb.length() ; i++)
        {
            if(sb.charAt(i)=='a' || sb.charAt(i)=='e' || sb.charAt(i)=='i' || sb.charAt(i)=='o' || sb.charAt(i)=='u' || sb.charAt(i)=='A' || sb.charAt(i)=='E' || sb.charAt(i)=='I' || sb.charAt(i)=='O' || sb.charAt(i)=='U')
            {
                result = i*5;
                consOdd(result);
                if(leng>=0 && leng<=9)
                    sb1.append(leng);
                else
                {
                    digSum(leng);
                    sb1.append(sum);
                }
                leng = 0;
                sum = 0;
            }
            else
                sb1.append(sb.charAt(i));  
        }
        for(int v = 0 ; v<sb1.length() ; v++)
        {
            if(sb.charAt(v)=='a' || sb.charAt(v)=='e' || sb.charAt(v)=='i' || sb.charAt(v)=='o' || sb.charAt(v)=='u' || sb.charAt(v)=='A' || sb.charAt(v)=='E' || sb.charAt(v)=='I' || sb.charAt(v)=='O' || sb.charAt(v)=='U')
                counter++;
        }
        if(counter > 0)
            System.out.println(sb1);
        else
            System.out.println("-1");
    }
}
