package spockstarter

class StringReplacer {
        String replace(String msg) {
            String newStr = ''
            char last
            String tmp = ''
            msg.each { c->
                if(last != c) {
                    newStr += tmp.size() == 2 ? '__' : tmp
                    tmp= ''
                }
                tmp+= last = c
            }
            newStr += tmp // tmp.size() == 2 ? '__' : tmp
        }
}
