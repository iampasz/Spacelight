package  com.appsforkids.pasz.spacelight.Builder;

import android.app.FragmentManager;
import android.content.Context;

public class Alert {

    public Alert(){

        }

    public static class AlertBuilder {

        private  String title;
        private String text;
        Context context;

        public AlertBuilder(Context context){
            this.context = context;
            }

        public AlertBuilder title(String title){
            return this;
        }

        public AlertBuilder text (String text){
            return this;
        }


        public Alert builder(){

            return new Alert ();
        }

        public void show(){



        }

    }

}
