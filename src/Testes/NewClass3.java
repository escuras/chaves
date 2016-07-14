/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testes;

/**
 *
 * @author toze
 */
interface novo2 {
    int um = 3;
    int getUm();
    public static int gettres(){
        return 4;
    }
}

interface novo3 {
    int dois = 4;
    int getDois();
}

class NewClass3 implements novo2, novo3{

    @Override
    public int getUm() {
        System.out.println(novo3.dois);
        return novo3.dois;
    }


    @Override
    public int getDois() {
        System.out.println(novo2.um);
        return novo2.um;
    }
    
  
    
   
}

class qqd {
     public static void main(String[] args){
        int x= 0;
        int y= 0;
        for (int z = 0; z < 5; z++) 
        {
            if (( ++x > 2 ) && (++y > 2)) 
            {
                x++;
            }
        }
        System.out.println(x + " " + y);
        char oc = 33;
        
         System.out.println(Character.getNumericValue(oc));
    }
     
     
     public static int soma(int[] um,int dois) {
         um[0] = 3;
         return um[0] + dois;
     }
}
