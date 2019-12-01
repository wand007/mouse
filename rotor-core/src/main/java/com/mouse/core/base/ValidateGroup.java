package com.mouse.core.base;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-11-30
 */
@GroupSequence({ValidateGroup.GroupA.class, ValidateGroup.GroupB.class, ValidateGroup.GroupC.class, Default.class})
public class ValidateGroup {
    public ValidateGroup() {
    }

    public interface GroupC {
    }

    public interface GroupB {
    }

    public interface GroupA {
    }
}
