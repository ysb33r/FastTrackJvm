def str = "aaabbcccaadddde"
def newStr = ""
str.each { c ->
     if (newStr == "") {
         newStr += "${c}"
     } else if (newStr.length() == 1) {
            newStr += "${c}"
     } else {
         String prevchar = newStr.charAt(newStr.length()-2)
          if (prevchar.size() != 0 && c == newStr.charAt(newStr.length()-1) && c != prevchar && prevchar != "_" ) {
             newStr += "${c}"
             newStr = newStr[0..-3] + '__'// += newStr.replace(newStr[-2..-1],"__")
//             println "New String " + newStr
         } else{
             newStr += "${c}"
         }
     }
}
     println newStr
assert newStr == 'aaa__ccc__dddde'