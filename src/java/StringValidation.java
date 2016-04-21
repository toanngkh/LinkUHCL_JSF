/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nguyent2758
 */
public class StringValidation {
    public static boolean IsValid(String str){
        boolean hasDigit = false;
        boolean hasLetter = false;
        boolean hasSpecial = false;
        boolean hasSpace = false;
        for (int i = 0; i < str.length(); i++) {
            if(str.length() >= 3 && str.length() <= 10){
                if(str.charAt(i) >= 48 && str.charAt(i) <= 57){   // check from 0 to 9
                    hasDigit = true;
                    continue;
                }
                else if(str.charAt(i) >= 33 && str.charAt(i) <= 47 || 
                        str.charAt(i) >= 58 && str.charAt(i) <= 64 || 
                        str.charAt(i) >= 91 && str.charAt(i) <= 96){   // check special chars
                    hasSpecial = true;
                    continue;
                }
                else if (str.charAt(i) >= 65 && str.charAt(i) <= 90 || 
                         str.charAt(i) >= 97 && str.charAt(i) <= 122){    // check letters
                    hasLetter = true;
                    continue;
                }
                else if (str.charAt(i) == 32){     // check space
                    hasSpace = true;
                    continue;
                }
            }
            else return false;
        }
        
        return (hasDigit && hasLetter && hasSpecial && !hasSpace);
    }
}
