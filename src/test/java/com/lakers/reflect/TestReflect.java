package com.lakers.reflect;

import org.junit.Test;
import org.lakers.reflect.ObjectFrame;
import org.lakers.reflect.TargetObject;

/**
 * Created on 2023/4/13 10:34
 *
 * @author lakers
 */
public class TestReflect {

    @Test
    public void testSave() {
        ObjectFrame.saveObject(new TargetObject());
    }
}
