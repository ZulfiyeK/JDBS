public class Test01 {
    //beklenen data ile gercek datanin karsilastirilip uyumlu olup olmadigini kontrol etmektir. uyumlu ise gcer, degilse kalir.

    public static void main(String[] args) {

        String str = "Hello";
        if(str.contains("e")){
            System.out.println("PASS");
        }else {
            System.out.println("FAIL");
        }

        //bunun otomasyon testi  olabilmesi icin test framework'une ihtiyac vardir.




// Burada gecti ya da kaldi demedigi icin bu otomaston test olmuyor. Junit Test classinda @Test ile artik main methoda ihtiyacim kalmaz ve tum islemlerimi run edebilirim ve bana
        //gecti kaldi seklinde onay gosterir.











    }
}
