/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rulerbug.first.Utils;

import java.util.ArrayList;

/**
 *
 * @author eldder
 */
public final class L extends ArrayList<Object>{
    
    public L() {}

    public static L c() {
        L r = new L();
        return r;
    }
    
    public L a(Object value) {
        super.add(value);
        return this;
    }
}
