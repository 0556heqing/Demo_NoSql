package com.heqing.demo.noSql.mongoDB;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by heqing on 2017/9/14.
 */
public class MongoDBConstants {

    private static final Logger logger = LoggerFactory.getLogger(MongoDBConstants.class);

    /**
     * 此枚举主要用排序<br/>
     * ASC  ： 正序<br/>
     * DESC ： 倒序
     */
    public static enum order {
        ASC, DESC
    }

    /**
     * 此枚举主要用于判断数字的比较条件<br/>
     * LT  ： 小于<br/>
     * LTE ： 小于或等于<br/>
     * GT  ： 大于<br/>
     * GTE ： 大于或等于<br/>
     * EQ  ： 等于<br/>
     * NE  ： 不等于
     */
    public static enum comparisonDigital {
        LT, LTE, GT, GTE, EQ, NE
    }

    /**
     * 此枚举主要用于判断字符串的比较条件<br/>
     * EQ    ： 等于<br/>
     * NE    ： 不等于<br/>
     * START ： 以此字符开头<br/>
     * LIKE  ： 存在此字符<br/>
     * END   ： 以此字符结尾
     */
    public static enum comparison {
        EQ, NE, START, LIKE, END
    }
}
