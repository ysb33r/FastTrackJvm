package spockstarter

/**
 * Created by test on 27/04/2016.
 */
class StringReplacer {

    String replace(String msg){
        String newMsg = ''

        def lastChar = ''
        def temp = ''
        for(int i = 0; i <= msg.size(); i++){
            def currentChar
            if(i < msg.size()){
                currentChar = msg[i]
            }
            if(currentChar != null && currentChar == lastChar) {
                temp += currentChar
            }
            else{
                newMsg += temp.size() == 2 ? '__' : temp
                temp = currentChar
            }

            lastChar = currentChar
        }
        return newMsg
    }

}

