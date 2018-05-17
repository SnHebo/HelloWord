package com.sn.my.transform;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Builder<T> {
   public Builder() {

   }
   protected T field;

   public T build() {
       return  this.field;
   }

   public List<T> listTransForm(List< ? extends Builder> builders) {
        if (builders == null) {
            return null;
        } else {
            List<T> list = new ArrayList<>();
            Iterator i$ = builders.iterator();
            while(i$.hasNext()) {
                Builder<T> builder = (Builder)i$.next();
                list.add(builder.build());
            }
            return list;
        }
   }
}
