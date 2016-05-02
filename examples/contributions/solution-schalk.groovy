// Replace all occurences of this string where there is only two 
// consecutive characters that are the same with underscores
String s = 'aaabbcccaadddde'
String newStr = ''

char last
String tmp = ''
s.each { c->
    if(last != c) {
        newStr += tmp.size() == 2 ? '__' : tmp
        tmp= ''
    } 
    tmp+= last = c
}
newStr+=tmp

assert newStr == 'aaa__ccc__dddde'